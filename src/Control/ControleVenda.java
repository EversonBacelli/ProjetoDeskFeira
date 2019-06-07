package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import Model.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleVenda {
	int id_venda = 0;
	
	private ObservableList<Venda> listaVenda = FXCollections.observableArrayList();	

	
	public void realizarVenda(Venda v) {
		if(validarVenda()) {
			this.listaVenda.add(v);
		}
	}
	
//	public void realizarVenda(Venda v) {
//		if(validarVenda()) {
//			this.listaVenda.add(v);
//		}
//	}
	
	public void removerVenda(Venda v) {
		this.listaVenda.remove(v);
	}
	
	public boolean validarVenda() {
		return false;
	}
	
	public int proximoId() {
		return ++this.id_venda;
	}
	
	public boolean validarVenda(Venda v) {
		ControleDeLoteProduto cLoteProd = new ControleDeLoteProduto();
		List<LoteProduto>listaLote = cLoteProd.getListItem();
		
		Boolean compraValidada = true;
		Boolean passou = false;
		
		for(ProdutoVendido pVendido : v.getListaProdutoVendido()) {
			passou = true;
			if(compraValidada) {
			int quantidade = pVendido.getQuantidade();
				for(LoteProduto lProduto : listaLote) {
					if(pVendido.getProduto().equals(lProduto.getProduto()) && quantidade != 0) {
						if(quantidade > 0) {
							if(pVendido.getQuantidade() < lProduto.getQuantidade()) {
								lProduto.setQuantidade(lProduto.getQuantidade() - quantidade);
							}else if(lProduto.getQuantidade() > 0){
								quantidade = quantidade - lProduto.getQuantidade();
								lProduto.setQuantidade(0);
							}
						}
					}
				}
				if(quantidade > 0) {
					compraValidada = false;
				}
			}
		}
		
		if(compraValidada && passou) {
			realizarVenda(v);
			abaterLote(listaLote);
		}
		return validarVenda();
	}
	
	public void abaterLote(List<LoteProduto> listaLote) {
		ControleDeLoteProduto cLoteProd = new ControleDeLoteProduto();
		for(LoteProduto loteProd : listaLote) {
			if(loteProd.getQuantidade() == 0) {
				cLoteProd.removerLoteProduto(loteProd);
			}else {
				cLoteProd.alterarLoteProduto(loteProd);
			}
		}
	}
	
	
//	public void realizarVenda(Venda venda) {
//		// criando o metodo para usar a altera��o da quantidade dentro do
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
//			// Se eu n�o saber os itens que foram alterado, como vou fazer para
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