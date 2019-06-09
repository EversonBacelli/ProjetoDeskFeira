package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Dao.DAOException;
import Dao.VendaDAO;
import Dao.VendaDAOImpl;
import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import Model.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleVenda {
	int id_venda = 0;
	
	private ObservableList<Venda> listaVenda = FXCollections.observableArrayList();
	ControleDeLoteProduto cLoteProd = new ControleDeLoteProduto();
	private List<LoteProduto>listaLote;

	
//	public void realizarVenda(Venda v) {
//		if(validarVenda()) {
//			this.listaVenda.add(v);
//		}
//	}
	
	public void removerVenda(Venda v) {
		this.listaVenda.remove(v);
	}

	public int proximoId() {
		return ++this.id_venda;
	}
	
	public void realizarVenda(Venda v) {
		Boolean compraValidada = true;
		Boolean passou = false;
		listaLote = cLoteProd.getListItem();
		
		compraValidada = validarVenda(v);
		
		if(compraValidada) {
			VendaDAO vDao = new VendaDAOImpl();
			try {
				vDao.adicionar(v);
				abaterLote(this.listaLote);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public boolean validarVenda(Venda v) {
		Boolean compraValidada = true;
		Boolean passou = false;
		
		for(ProdutoVendido pVendido : v.getListaProdutoVendido()) {
			passou = true;
			if(compraValidada) {
			int quantidade = pVendido.getQuantidade();
				for(LoteProduto lProduto : this.listaLote) {
					if(pVendido.getProduto().getNome().equals(lProduto.getProduto().getNome()) && quantidade != 0) {
						if(quantidade > 0) {
							if(pVendido.getQuantidade() < lProduto.getQuantidade()) {
								lProduto.setQuantidade(lProduto.getQuantidade() - quantidade);
								quantidade = 0;
							}else if(lProduto.getQuantidade() > 0){
								quantidade = quantidade - lProduto.getQuantidade();
								lProduto.setQuantidade(0);
							}
						}
					}
				}
				if(quantidade > 0) {
					compraValidada = false;
					JOptionPane.showMessageDialog(null, "Venda não realizada, não ha " + pVendido.getProduto().getNome() + "s o suficiente para venda!");
				}
			}
		}

		if(passou && compraValidada) {
			JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
			return true;
		}else {
			return false;
		}
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