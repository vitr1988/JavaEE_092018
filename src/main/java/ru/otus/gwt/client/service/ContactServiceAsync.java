package ru.otus.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.otus.gwt.shared.model.Contact;
import ru.otus.gwt.shared.model.ContactDetails;

import java.util.ArrayList;

public interface ContactServiceAsync {
  void addContact(Contact contact, AsyncCallback<Contact> callback);
  void deleteContact(String id, AsyncCallback<Boolean> callback);
  void deleteContacts(ArrayList<String> ids, AsyncCallback<ArrayList<ContactDetails>> callback);
  void getContactDetails(AsyncCallback<ArrayList<ContactDetails>> callback);
  void getContact(String id, AsyncCallback<Contact> callback);
  void updateContact(Contact contact, AsyncCallback<Contact> callback);
}

