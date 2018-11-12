package ru.otus.jsf;

import lombok.Data;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Data
@ManagedBean
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
