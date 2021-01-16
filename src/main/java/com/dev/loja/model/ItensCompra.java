package com.dev.loja.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_compra")
public class ItensCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Compra compra;

	private int quantidade = 0;

	@Column(name = "valor_produto")
	private Double valorProduto;

	@Column(name = "valor_total")
	private Double valorTotal;
	
	

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Double getValorProduto() {
		return valorProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compra == null) ? 0 : compra.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + quantidade;
		result = prime * result + ((valorProduto == null) ? 0 : valorProduto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensCompra other = (ItensCompra) obj;
		if (compra == null) {
			if (other.compra != null)
				return false;
		} else if (!compra.equals(other.compra))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.getNome().equals(other.produto.getNome()))
			return false;
		return true;
	}

}
