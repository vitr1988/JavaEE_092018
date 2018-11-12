package ru.otus.jsf.util;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ApplicationScoped
@Getter
public class AppUrlStore implements Serializable {
	private static final long serialVersionUID = 1L;

	private String baseUrl;
	private String simpleCrudUrl;

	@PostConstruct
    public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String baseUrl = externalContext.getInitParameter("BaseUrl");

		this.baseUrl = baseUrl;
		this.simpleCrudUrl = baseUrl + "simplecrud.xhtml";
    }
}
