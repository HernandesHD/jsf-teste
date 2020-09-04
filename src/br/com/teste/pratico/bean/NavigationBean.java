package br.com.teste.pratico.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String formInicio() {
		System.out.println("Chamando INDEX.");
		return null;
	}
	
	public String formQuadroDeTarefas() {
		System.out.println("Chamando formulario CADASTRAR Quadro de Tarefas.");
		return "cadastrar?faces-redirect=true";
	}
	
	public String formListar() {
		System.out.println("Chamando formulario LISTAR Quadro de Tarefas.");
		return "listar?faces-redirect=true";
	}

}
