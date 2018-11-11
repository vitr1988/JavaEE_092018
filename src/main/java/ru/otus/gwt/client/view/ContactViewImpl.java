package ru.otus.gwt.client.view;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.*;
import ru.otus.gwt.client.gin.ApplicationInjector;
import ru.otus.gwt.client.presenter.ContactPresenter;

import java.util.ArrayList;
import java.util.List;

public class ContactViewImpl extends Composite implements ContactPresenter.Display {

  @UiTemplate("ContactsView.ui.xml")
  public interface ContactView extends UiBinder<DecoratorPanel, ContactViewImpl> {
  }


  @UiField
  Button addButton;

  @UiField
  Button deleteButton;

  @UiField
  FlexTable contactsTable;

  public ContactViewImpl() {
    initWidget(ApplicationInjector.INSTANCE.getContactView().createAndBindUi(this));
  }

  public HasClickHandlers getAddButton() {
    return addButton;
  }

  public HasClickHandlers getDeleteButton() {
    return deleteButton;
  }

  public HasClickHandlers getList() {
    return contactsTable;
  }

  public void setData(List<String> data) {
    contactsTable.removeAllRows();

    for (int i = 0; i < data.size(); ++i) {
      contactsTable.setWidget(i, 0, new CheckBox());
      contactsTable.setText(i, 1, data.get(i));
    }
  }

  public int getClickedRow(ClickEvent event) {
    int selectedRow = -1;
    HTMLTable.Cell cell = contactsTable.getCellForEvent(event);

    if (cell != null) {
      // Suppress clicks if the user is actually selecting the
      //  check box
      //
      if (cell.getCellIndex() > 0) {
        selectedRow = cell.getRowIndex();
      }
    }

    return selectedRow;
  }

  public List<Integer> getSelectedRows() {
    List<Integer> selectedRows = new ArrayList<Integer>();

    for (int i = 0; i < contactsTable.getRowCount(); ++i) {
      CheckBox checkBox = (CheckBox)contactsTable.getWidget(i, 0);
      if (checkBox.getValue()) {
        selectedRows.add(i);
      }
    }

    return selectedRows;
  }

  public Widget asWidget() {
    return this;
  }
}