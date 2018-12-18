package ru.otus.security.authorization.tomcat;

import ru.otus.security.authorization.JepPrincipal;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class LoginModule implements javax.security.auth.spi.LoginModule {

    // initial state
    protected Subject _subject;
    protected CallbackHandler _callbackHandler;
    protected Map _sharedState;
    protected Map _options;

    // configuration options
    protected boolean _debug;

    // the authentication status
    protected boolean _succeeded;
    protected boolean _commitSucceeded;

    protected Principal[] _authPrincipals;

    protected Principal[] login;

    /**
     * Имя пользователя (login username)
     */
    private String username = null;

    /**
     * Коллекция ролей
     */
    private List<String> roles = null;

    /**
     * Initialize this <code>LoginModule</code>.
     * <p/>
     * <p/>
     *
     * @param subject         the <code>Subject</code> to be authenticated. <p>
     * @param callbackHandler a <code>CallbackHandler</code> for communicating
     * with the end user (prompting for usernames and passwords, for example).
     * <p>
     *
     * @param sharedState shared <code>LoginModule</code> state. <p>
     *
     * @param options options specified in the login <code>Configuration</code>
     * for this particular <code>LoginModule</code>.
     */
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) {
        this._subject = subject;
        this._callbackHandler = callbackHandler;
        this._sharedState = sharedState;
        this._options = options;

        // initialize any configured options
        _debug = "true".equalsIgnoreCase((String) _options.get("debug"));
    }

    /**
     * Возвращает признак работы объекта в режиме отладки.
     */
    final public boolean debug() {
        return _debug;
    }

    /**
     * Возвращает массив ролей.
     */
    protected Principal[] getAuthPrincipals() {
        return _authPrincipals;
    }

    /**
     * Authenticate the user by prompting for a username and password.
     * <p/>
     * <p/>
     *
     * @return true if the authentication succeeded, or false if this
     *         <code>LoginModule</code> should be ignored.
     * @throws FailedLoginException if the authentication fails. <p>
     * @throws LoginException if this <code>LoginModule</code>
     *                              is unable to perform the authentication.
     */
    public boolean login() throws LoginException {
        this.username = null;

        if (_callbackHandler == null)
            throw new LoginException("Error: no CallbackHandler available to garner authentication information from the user");

        Callback[] callbacks = new Callback[] {
                new NameCallback("Username"),
                new PasswordCallback("Password:", false)
        };

        try {
            _callbackHandler.handle(callbacks);
        } catch (Exception e) {
            e.printStackTrace();
            _succeeded = false;
            throw new LoginException(e.getMessage());
        }

        Integer operatorId = null;
        this.username = ((NameCallback) callbacks[0]).getName();
        String password = getPassword(callbacks);
        try {
            operatorId = doAuthentication(this.username, password);
        } catch (Throwable th) {
            throw new LoginException("Authorization failed: " + th.getLocalizedMessage());
        }

        try {
            fillPrincipals(operatorId);
        } catch (SQLException e) {
            throw new LoginException("Authorization failed: " + e.getMessage());
        }

        this._succeeded = true;

        return true;
    }

    /**
     * <p> This method is called if the LoginContext's
     * overall authentication succeeded
     * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
     * succeeded).
     * <p/>
     * <p> If this LoginModule's own authentication attempt
     * succeeded (checked by retrieving the private state saved by the
     * <code>login</code> method), then this method associates a
     * <code>Principal</code>
     * with the <code>Subject</code> located in the
     * <code>LoginModule</code>.  If this LoginModule's own
     * authentication attempted failed, then this method removes
     * any state that was originally saved.
     * <p/>
     * <p/>
     *
     * @return true if this LoginModule's own login and commit
     *         attempts succeeded, or false otherwise.
     * @throws LoginException if the commit fails.
     */
    public boolean commit() throws LoginException {
        try {
            if (_succeeded == false) {
                return false;
            }

            if (_subject.isReadOnly()) {
                throw new LoginException("Subject is ReadOnly");
            }

            // add authenticated principals to the Subject
            if (getAuthPrincipals() != null) {
                for (int i = 0; i < getAuthPrincipals().length; i++) {
                    if (!_subject.getPrincipals().contains(getAuthPrincipals()[i])) {
                        Principal principal = getAuthPrincipals()[i];
                        if(principal != null) {
                            _subject.getPrincipals().add(principal);
                        }
                    }
                }
            }

            // in any case, clean out state
            cleanup();

            _commitSucceeded = true;

            return true;

        } catch (Throwable t) {
            throw new LoginException(t.toString());
        }
    }

    /**
     * <p> This method is called if the LoginContext's
     * overall authentication failed.
     * (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL LoginModules
     * did not succeed).
     * <p/>
     * <p> If this LoginModule's own authentication attempt
     * succeeded (checked by retrieving the private state saved by the
     * <code>login</code> and <code>commit</code> methods),
     * then this method cleans up any state that was originally saved.
     * <p/>
     * <p/>
     *
     * @return false if this LoginModule's own login and/or commit attempts
     * failed, and true otherwise.
     *
     * @throws LoginException if the abort fails.
     */
    public boolean abort() throws LoginException {
        if (_succeeded == false) {
            cleanup();
            return false;
        } else if (_succeeded == true && _commitSucceeded == false) {
            // login succeeded but overall authentication failed
            _succeeded = false;
            cleanup();
        } else {
            // overall authentication succeeded and commit succeeded,
            // but someone else's commit failed
            logout();
        }
        return true;
    }

    /**
     * Освободим ресурсы.
     */
    protected void cleanup() {

    }

    /**
     * Удалим все роли пользователя.
     */
    protected void cleanupAll() {
        cleanup();

        if (getAuthPrincipals() != null) {
            for (int i = 0; i < getAuthPrincipals().length; i++) {
                JepPrincipal jepPrincipal = (JepPrincipal) getAuthPrincipals()[i];
                _subject.getPrincipals().remove(jepPrincipal);
            }
        }
    }

    /**
     * Logout the user.
     * <p/>
     * <p> This method removes the <code>Principal</code>
     * that was added by the <code>commit</code> method.
     * <p/>
     * <p/>
     *
     * @return true in all cases since this <code>LoginModule</code>
     *         should not be ignored.
     * @throws LoginException if the logout fails.
     */
    public boolean logout() throws LoginException {
        _succeeded = false;
        _commitSucceeded = false;

        cleanupAll();
        return true;
    }

    /**
     * Проверка логина и пароля пользователя.
     *
     * @param username    логин пользователя
     * @param password    пароль пользователя
     * @throws SQLException при неудачной аутентификации
     */
    private int doAuthentication(String username, String password) throws FailedLoginException {
        //Проверим логин/пароль пользователя.
        try {
            //TODO: Login
        } catch (Exception e) {
            throw new FailedLoginException("Authentication failed: Password does not match");
        }

        return -1;
    }

    private List<String> getRoles() throws SQLException {
        if(this.roles == null) {
            //TODO: fill up roles
        }
        return this.roles;
    }

    /**
     * Формирует роли пользователя для дальнейшего использования.
     * @param operatorId пользователь
     * @throws SQLException
     */
    private void fillPrincipals(Integer operatorId) throws SQLException {
        //Выделим массив для "ролей": идентификатор оператора и собственно роли/права.
        List<String> roles = getRoles();
        _authPrincipals = new JepPrincipal[roles.size() + 2]; // Добавляем элементы для operatorId, для imSessionId и для APPLET_USER_ROLE
        int i = 0;
        // Добавление в свойства принципала operatorId
        if(operatorId != null) {
            JepPrincipal jepPrincipal = new JepPrincipal(this.username, operatorId);
            System.out.println(this.getClass() + ".fillPrincipals(" + operatorId + "): jepPrincipal = " + jepPrincipal);
            _authPrincipals[i++] = jepPrincipal;
        }

        // Добавление ролей
        for(String role: roles) {
            _authPrincipals[i++] = new JepPrincipal(role);
        }
    }

    private String getPassword(Callback[] callbacks) {
        String password = null;
        char[] charPassword = ((PasswordCallback) callbacks[1]).getPassword();
        if (charPassword != null) {
            password = new String(charPassword);
        }
        return password;
    }
}