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
				listaVenda.add(v);
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
	
	public ObservableList<Venda> getListaVenda() {
		return listaVenda;
	}
	
	public ObservableList<Venda> getListaVendaDAO() {
		VendaDAO vDao = new VendaDAOImpl();
		try {
			this.listaVenda.addAll(vDao.listar());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.listaVenda;
	}


	public void setListaVenda(ObservableList<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
}