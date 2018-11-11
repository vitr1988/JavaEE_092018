package ru.otus.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.otus.gwt.client.service.ApplicationService;
import ru.otus.gwt.client.service.ContactService;
import ru.otus.gwt.shared.User;
import ru.otus.gwt.shared.exception.WrongCredentialException;
import ru.otus.gwt.shared.model.Contact;
import ru.otus.gwt.shared.model.ContactDetails;
import ru.otus.gwt.shared.validation.ValidationRule;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class ApplicationServiceImpl extends RemoteServiceServlet implements ApplicationService, ContactService {

    private static final String[] contactsFirstNameData = new String[] {
            "Alexander", "Boris", "Denis", "Evgeny", "Fedor", "Grigory",
            "Zhanna", "Zinaida", "Ivan", "Klim", "Leontiy", "Marina",
            "Natalya", "Oleg", "Petr", "Stas", "Taras", "Ulyana",
            "Victoria", "Hariton", "Yuriy", "Yan"};

    private final String[] contactsLastNameData = new String[] {
            "Ivanov", "Petrov", "Sidorov", "Gavrilov", "Bestyjiy", "Dumko",
            "Eremenko", "Frolova", "Zhdanov", "Zaikin", "Usmanov", "Starovoitova",
            "Drobotenko", "Ulyanov", "Novikov", "Trotsky", "Toporov", "Gromkayy",
            "Golubevf", "Andronov", "Tumanov", "Quadratov"};

    private final String[] contactsEmailData = new String[] {
            "mark@example.com", "hollie@example.com", "boticario@example.com",
            "emerson@example.com", "healy@example.com", "brigitte@example.com",
            "elba@example.com", "claudio@example.com", "dena@example.com",
            "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
            "qetlyxxogg@example.com", "antenas_sul@example.com",
            "cblake@example.com", "gailh@example.com", "orville@example.com",
            "post_master@example.com", "rchilders@example.com", "buster@example.com",
            "user31065@example.com", "ftsgeolbx@example.com"};

    private final HashMap<String, Contact> contacts = new HashMap<>();

    public ApplicationServiceImpl() {
        initContacts();
    }

    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }

    @Override
    public void authorize(User user) throws WrongCredentialException {
        if (ValidationRule.isValid(user)){
            try {
                getThreadLocalRequest().login(user.getLogin(), user.getPassword());
            } catch (ServletException e) {
                throw new WrongCredentialException("Некорректные логин/пароль");
            }
        }
    }

    private void initContacts() {
        for (int i = 0; i < contactsFirstNameData.length
                && i < contactsLastNameData.length
                && i < contactsEmailData.length; i++) {
            Contact contact = new Contact(String.valueOf(i), contactsFirstNameData[i], contactsLastNameData[i], contactsEmailData[i]);
            contacts.put(contact.getId(), contact);
        }
    }

    @Override
    public Contact addContact(Contact contact) {
        contact.setId(String.valueOf(contacts.size()));
        contacts.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public Contact updateContact(Contact contact) {
        contacts.remove(contact.getId());
        contacts.put(contact.getId(), contact);
        return contact;
    }

    @Override
    public Boolean deleteContact(String id) {
        contacts.remove(id);
        return true;
    }

    @Override
    public ArrayList<ContactDetails> deleteContacts(ArrayList<String> ids) {
        for (int i = 0; i < ids.size(); i++) {
            deleteContact(ids.get(i));
        }
        return getContactDetails();
    }

    @Override
    public ArrayList<ContactDetails> getContactDetails() {
        ArrayList<ContactDetails> contactDetails = new ArrayList<>();
        Iterator<String> it = contacts.keySet().iterator();
        while(it.hasNext()) {
            Contact contact = contacts.get(it.next());
            contactDetails.add(contact.getLightWeightContact());
        }
        return contactDetails;
    }

    @Override
    public Contact getContact(String id) {
        return contacts.get(id);
    }
}