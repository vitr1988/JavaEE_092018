package ru.otus.gwt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import ru.otus.gwt.client.event.AddContactEvent;
import ru.otus.gwt.client.event.EditContactEvent;
import ru.otus.gwt.client.service.ContactServiceAsync;
import ru.otus.gwt.shared.model.ContactDetails;

import java.util.ArrayList;
import java.util.List;

public class ContactPresenter implements Presenter {

  private List<ContactDetails> contactDetails;

  public interface Display {
    HasClickHandlers getAddButton();
    HasClickHandlers getDeleteButton();
    HasClickHandlers getList();
    void setData(List<String> data);
    int getClickedRow(ClickEvent event);
    List<Integer> getSelectedRows();
    Widget asWidget();
  }

  private final ContactServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;

  public ContactPresenter(ContactServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }

  public void bind() {

    display.getAddButton().addClickHandler(event -> eventBus.fireEvent(new AddContactEvent()));

    display.getDeleteButton().addClickHandler(event -> deleteSelectedContacts());

    display.getList().addClickHandler(event -> {
      int selectedRow = display.getClickedRow(event);

      if (selectedRow >= 0) {
        String id = contactDetails.get(selectedRow).getId();
        eventBus.fireEvent(new EditContactEvent(id));
      }
    });
  }

  public void go(final HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
    fetchContactDetails();
  }

  public void sortContactDetails() {

    // Yes, we could use a more optimized method of sorting, but the
    //  point is to create a test case that helps illustrate the higher
    //  level concepts used when creating MVP-based applications.
    for (int i = 0; i < contactDetails.size(); i++) {
      for (int j = 0; j < contactDetails.size() - 1; j++) {
        if (contactDetails.get(j).getDisplayName().compareToIgnoreCase(contactDetails.get(j + 1).getDisplayName()) >= 0) {
          ContactDetails tmp = contactDetails.get(j);
          contactDetails.set(j, contactDetails.get(j + 1));
          contactDetails.set(j + 1, tmp);
        }
      }
    }
  }

  private void fetchContactDetails() {
    rpcService.getContactDetails(new AsyncCallback<ArrayList<ContactDetails>>() {
      @Override
      public void onSuccess(ArrayList<ContactDetails> result) {
        contactDetails = result;
        sortContactDetails();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
          data.add(contactDetails.get(i).getDisplayName());
        }
        display.setData(data);
      }
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("Error fetching contact details");
      }
    });
  }

  private void deleteSelectedContacts() {
    List<Integer> selectedRows = display.getSelectedRows();
    ArrayList<String> ids = new ArrayList<>();

    for (int i = 0; i < selectedRows.size(); i++) {
      ids.add(contactDetails.get(selectedRows.get(i)).getId());
    }

    rpcService.deleteContacts(ids, new AsyncCallback<ArrayList<ContactDetails>>() {
      @Override
      public void onSuccess(ArrayList<ContactDetails> result) {
        contactDetails = result;
        sortContactDetails();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
          data.add(contactDetails.get(i).getDisplayName());
        }
        display.setData(data);
      }
      @Override
      public void onFailure(Throwable caught) {
        Window.alert("Error deleting selected contacts");
      }
    });
  }
}
