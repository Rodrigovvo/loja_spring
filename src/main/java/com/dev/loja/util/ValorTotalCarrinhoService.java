package com.dev.loja.util;

import java.util.List;

import com.dev.loja.model.ItensCompra;

public class ValorTotalCarrinhoService {

	public Double calcularTotalSemFrete(List<ItensCompra> itensCompra) {
		Double valorTotalSemFrete = 0D;
		for (ItensCompra item : itensCompra) {
			valorTotalSemFrete = valorTotalSemFrete + item.getValorTotal();
		}
		return valorTotalSemFrete;
	}
	
	// Implementar o preço do carrinho e o cálculo do frete
	// TODO: public Double calcularFrete(List<ItensCompra> itensCompra) {}
	
	// TODO: public Double calcularTotalComFrete(List<ItensCompra> itensCompra) {}
	
}
