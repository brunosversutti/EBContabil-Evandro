package br.pro.delfino.drogaria.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String descricao;

	@Column(nullable = false) // eu nao preciso personalizar o tamanho dele - o mysql faz isso
	private Short quantidade;
	// os tipos primitivos em java: short, int e long (tudo minusculo) possuum valor
	// padrão = zero
	// Agora as classes: Short, Integer e Long (tudo maiusculo) são objetos cujo
	// valor padrão é nulo
	// ou seja, isso na tela depois vai facilitar para a gente pq se eu tiver um
	// zero,
	// eu nao sei se o usuaria digitou zero ou é zero padrao.
	// Entao, nesse caso, o Short, se tiver zero, eu sei que foi o usuário que
	// digitou,
	// porque por padrão é nulo, mas tem zero, entao alguem digitou. Se tiver nulo,
	// eu sei que ele nao digitou

	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Fabricante fabricante;

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	
}
