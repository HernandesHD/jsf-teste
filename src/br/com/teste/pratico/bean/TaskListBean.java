package br.com.teste.pratico.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.teste.pratico.modelo.QuadroDeTarefa;
import br.com.teste.pratico.modelo.Tarefa;

@ManagedBean
@SessionScoped
public class TaskListBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private QuadroDeTarefa quadroDeTarefa = new QuadroDeTarefa();
	private List<Tarefa> tarefasInput;
	
	@PostConstruct
	public void init() {
		tarefasInput = new ArrayList<Tarefa>();
	}
	
	public void adicionaInput() {
		tarefasInput.add(new Tarefa());
		System.out.println(tarefasInput.size());
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
	
	public void salvarNoBanco() {
		System.out.println("ID: " + this.quadroDeTarefa.getId());
		System.out.println("Titulo: " + this.quadroDeTarefa.getTitulo());
		
		//Formata a data
		DateFormat formataData = DateFormat.getDateInstance();
		
		System.out.println("Data de criação: " + formataData.format(this.quadroDeTarefa.getDataDeCriacao()));
		System.out.println("Status: " + this.quadroDeTarefa.isStatus());
		System.out.println("Descrição: " + this.quadroDeTarefa.getDescricao());
		
		quadroDeTarefa.setTarefas(tarefasInput);
		
		for (Tarefa tarefa : this.quadroDeTarefa.getTarefas()) {
			System.out.println("Tarefas: " + tarefa.getDescricao());
		}
	}

}
