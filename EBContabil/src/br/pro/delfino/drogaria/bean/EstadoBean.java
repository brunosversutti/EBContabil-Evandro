package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.EstadoDAO;
import br.pro.delfino.drogaria.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

	// entao, o que seria esse Modelo: vamos criar atributos aqui dentro
	// onde esse atributo vai guardar os dados da tela e precisamos criar
	// get e set para eles para que eles possam ser acessiveis na Visao
	// Entao, a Visao, quando quiser conversar com o Modelo, ela vai usar
	// o get e set.

	// Entao, eu quero gravar um Estado. Vamos criar um objeto "estado" que é do
	// tipo "Estado" mesmo. Vamos fazer get e set. Para poder amarrar a tela
	// com o Modelo, usamos o EL tbem. Só que o EL, no caso, não usa as coisas
	// privadas, e por isso precisamos do get e set.
	private Estado estado;
	
	private List<Estado> estados;

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@PostConstruct
	public void listar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estados = estadoDAO.listar();  
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Estados");
			erro.printStackTrace(); // serve para imprimir a pilha de execução - aquele em azul
		}
	}
	
	public void novo() {
		estado = new Estado();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void salvar() {
		try {
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.merge(estado);

			novo();
			
			estados = estadoDAO.listar(); // recarregar a pesquisa dos estados 

			Messages.addGlobalInfo("Estado salvo com sucesso!!");

			// throw new RuntimeException("Erro forçado");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Estado");
			erro.printStackTrace(); // serve para imprimir a pilha de execução - aquele em azul
		}

		// String msg = "Programacao web com java";
		// FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg);
		// FacesContext conexto = FacesContext.getCurrentInstance();
		// conexto.addMessage(null, mensagem);

	}
	public void excluir(ActionEvent evento) {
		try {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.excluir(estado);
		
		estados = estadoDAO.listar(); // recarregar a pesquisa dos estados 
		
		Messages.addGlobalInfo("Estado excluido com sucesso!!");
		//Messages.addGlobalInfo("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
		}catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao exluir o estado !!");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento) {
		estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
		Messages.addGlobalInfo("Nome: " + estado.getNome() + " Sigla: " + estado.getSigla());
	}
}

