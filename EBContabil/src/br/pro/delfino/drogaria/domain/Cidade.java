package br.pro.delfino.drogaria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Cidade extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nome;

	@ManyToOne  // significa que muitas cidades pertencem a um estado
	@JoinColumn(nullable = false) // para personalizar a chave estrangeira. Na chave estrageira nao pode usar o "@Column", tem que usar o "@JoinColumn"
	private Estado estado; // essa é a chave estrangeira

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
