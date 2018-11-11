package ru.otus.jsf;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class MainBean implements Serializable {

    private String inputText;

    public void showMessage() {
        FacesMessage message = new FacesMessage("Заголовок", "Частичное обновление страницы");
        message.setSeverity(FacesMessage.SEVERITY_INFO); //как выглядит окошко с сообщением

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, message);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Всплывашка", "GrowlMessage"));
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Значение", inputText));
    }
}
