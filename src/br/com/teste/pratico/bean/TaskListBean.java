package br.com.teste.pratico.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import br.com.teste.pratico.dao.DAO;
import br.com.teste.pratico.dao.JPAUtil;
import br.com.teste.pratico.enums.TipoStatus;
import br.com.teste.pratico.modelo.QuadroDeTarefa;
import br.com.teste.pratico.modelo.Tarefa;

@ManagedBean
@SessionScoped
public class TaskListBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private QuadroDeTarefa quadroDeTarefa = new QuadroDeTarefa();
	private List<Tarefa> tarefasInput;
	
	private boolean valueCheckBox;
	
	@PostConstruct
	public void init() {
		tarefasInput = new ArrayList<Tarefa>();
	}
	
	public boolean isValueCheckBox() {
		return valueCheckBox;
	}

	public void setValueCheckBox(boolean valueCheckBox) {
		this.valueCheckBox = valueCheckBox;
	}

	public void adicionaInput() {
		tarefasInput.add(new Tarefa());
	}
	
	public void remove(Tarefa inputTarefa) {
		tarefasInput.remove(inputTarefa);
	}
	
	public List<Tarefa> getTarefasInput() {
		return tarefasInput;
	}

	public QuadroDeTarefa getQuadroDeTarefa() {
		return quadroDeTarefa;
	}
	
	public List<QuadroDeTarefa> getQuadros() {
		return new DAO<QuadroDeTarefa>(QuadroDeTarefa.class).listaTodos();
	}
	
	public String salvarNoBanco() {	
		
		quadroDeTarefa.setTarefas(tarefasInput);
		
		if(this.tarefasInput.isEmpty() || this.tarefasInput == null) {
			FacesContext.getCurrentInstance().addMessage("tarefa", new FacesMessage("Deve adicionar no minimo uma tarefa."));
			return null;
		}

		if(this.quadroDeTarefa.getId() == null) {
			new DAO<QuadroDeTarefa>(QuadroDeTarefa.class).adiciona(this.quadroDeTarefa);
		} else {
			new DAO<QuadroDeTarefa>(QuadroDeTarefa.class).atualiza(this.quadroDeTarefa);

		}
		
		this.quadroDeTarefa = new QuadroDeTarefa();
		this.tarefasInput = new ArrayList<Tarefa>();
		
		return "listar?faces-redirect=true";
		
	}
	
	public void remover(QuadroDeTarefa quadroDeTarefa) {
		new DAO<QuadroDeTarefa>(QuadroDeTarefa.class).remove(quadroDeTarefa);
	}
	
	public String carregar(QuadroDeTarefa quadroDeTarefa) {
		this.quadroDeTarefa = quadroDeTarefa;
		for(Tarefa tarefa : this.quadroDeTarefa.getTarefas()) {
			adicionaInput();
		}
		tarefasInput = this.getQuadroDeTarefa().getTarefas();
		
		return "cadastrar?faces-redirect=true";
	}
	
	public void limparForm() {
		this.quadroDeTarefa = new QuadroDeTarefa();
		this.tarefasInput = new ArrayList<Tarefa>();
	}
	
	public void concluiQuadro(QuadroDeTarefa quadroDeTarefa) {
		if(valueCheckBox) {
			quadroDeTarefa.setStatus(TipoStatus.CONCLUIDO);
			new DAO<QuadroDeTarefa>(QuadroDeTarefa.class).atualiza(quadroDeTarefa);
		} else {
			quadroDeTarefa.setStatus(TipoStatus.ANDAMENTO);
			new DAO<QuadroDeTarefa>(QuadroDeTarefa.class).atualiza(quadroDeTarefa);
		}
    }

}
