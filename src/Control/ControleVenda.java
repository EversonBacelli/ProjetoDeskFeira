package Control;

import java.util.ArrayList;
import java.util.List;

import Model.LoteProduto;
import Model.Venda;

public class ControleVenda {
	
	
	
	
	public void realizarVenda() {
		
	}
	
	public boolean validarVenda() {
		return false;
	}
	
//	public void realizarVenda(Venda venda) {
//		// criando o metodo para usar a alteração da quantidade dentro do
//		// estoque
//		ControleDeLoteProduto controleProduto = new ControleDeLoteProduto();
//		
//		//List<LoteProduto> listaLoteProdutos = venda.getListaLoteProduto();
//		
//		double valorTotal = 0;
//		int qtdTotalProdutos = 0;
//
//		// diminuindo o valor dos LoteProdutos comprados
//		for (LoteProduto loteProduto : listaLoteProdutos) {
//
//			// 0 = administrador
//			// 1 = vendedor
//			// 2 = estoquista
//			controleProduto.alterarEstoque(loteProduto, 1);
//			// como vou saber se o produto foi alterado ou nao?
//			// Se eu não saber os itens que foram alterado, como vou fazer para
//			// garantir que, o produto que eu estou cobrando em R$ esta sendo
//			// diminuido no lote?
//
//			// se estoque foi alterado, entao vamos somar o valor da compra
//			valorTotal += loteProduto.getValorTortal();
//
//			// se estoque foi alterado, gravar a quantidade total de um produto
//			qtdTotalProdutos += loteProduto.getQuantidade();
//		}
//		venda.setValorTotal(valorTotal);
//		venda.setQtdVenda(qtdTotalProdutos);
//		
//		//salvando a venda efetuada
//		lista_venda.add(venda);
//	}
	
}