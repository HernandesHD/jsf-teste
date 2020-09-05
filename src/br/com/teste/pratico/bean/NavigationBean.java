package br.com.teste.pratico.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String formInicio() {
		return null;
	}
	
	public String formQuadroDeTarefas() {
		return "cadastrar?faces-redirect=true";
	}
	
	public String formListar() {
		return "listar?faces-redirect=true";
	}

}
