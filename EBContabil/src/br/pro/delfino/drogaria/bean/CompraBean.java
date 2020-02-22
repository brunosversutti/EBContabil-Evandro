package br.pro.delfino.drogaria.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.pro.delfino.drogaria.dao.FornecedorDAO;
import br.pro.delfino.drogaria.domain.Fornecedor;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class CompraBean implements Serializable {

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;

	double valor1;
	double valor2;
	double total;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public double getValor1() {
		return valor1;
	}

	public void setValor1(double valor1) {
		this.valor1 = valor1;
	}

	public double getValor2() {
		return valor2;
	}

	public void setValor2(double valor2) {
		this.valor2 = valor2;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void somar() {
		this.total = this.valor1 + this.valor2;
	}

	public void subtrair() {
		this.total = this.valor1 - this.valor2;
	}

	public void dividir() {
		this.total = this.valor1 / this.valor2;
	}

	public void multiplicar() {
		this.total = this.valor1 * this.valor2;
	}
	
	@PostConstruct
	public void listar() {
		try {
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			fornecedores =fornecedorDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}
	
	public void abrirDialogo() {
		
	}
}