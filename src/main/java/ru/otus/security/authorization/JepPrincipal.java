package ru.otus.security.authorization;

import java.io.Serializable;
import java.security.Principal;
import java.util.Objects;

/**
 * <p> This class implements the <code>JepPrincipal</code> interface
 * and represents a Sample user.
 *
 * <p> Principals may be associated with a particular <code>Subject</code>
 * to augment that <code>Subject</code> with an additional
 * identity.  Refer to the <code>Subject</code> class for more information
 * on how to achieve this.  Authorization decisions can then be based upon
 * the Principals associated with a <code>Subject</code>.
 *
 * @see java.security.Principal
 * @see javax.security.auth.Subject
 */
public class JepPrincipal implements Principal, Serializable {

    private static final long serialVersionUID = 1L;

    private String _name;
    private Integer _operatorId;

    public JepPrincipal(String role) {
        if (role == null) {
            throw new NullPointerException("name cannot be null");
        }
        this._name = role;
    }

    public JepPrincipal(String role, Integer operatorId) {
        if (role == null) {
            throw new NullPointerException("name cannot be null");
        }
        this._name = role;
        this._operatorId = operatorId;
    }

    /**
     * Return a string representation of this <code>JepPrincipal</code>.
     *
     * <p>
     *
     * @return a string representation of this <code>JepPrincipal</code>.
     */
    public String getName() {
        return _name;
    }

    /**
     * Возвращает иденитификатор опеартора размещенный в данный <code>JepPrincipal</code>.
     *
     * <p>
     *
     * @return иденитификатор опеартора размещенный в данный <code>JepPrincipal</code>.
     */
    public Integer getOperatorId() {
        return _operatorId;
    }

    /**
     * Return a string representation of this <code>JepPrincipal</code>.
     *
     * <p>
     *
     * @return a string representation of this <code>JepPrincipal</code>.
     */
    public String toString() {
        return "[JepPrincipal] : " + _name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JepPrincipal that = (JepPrincipal) o;
        return Objects.equals(_name, that._name) &&
                Objects.equals(_operatorId, that._operatorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _operatorId);
    }
}