package Control;

import Model.Produto;
import Model.ProdutoVendido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleProdutoVendido {
	
	private ObservableList<ProdutoVendido> listaProd = FXCollections.observableArrayList();


	public void adicionar(ProdutoVendido p) {
		this.listaProd.add(p);
	}
	
	public void remover(Produto p) {
		this.listaProd.remove(p);
	}
	
	public ObservableList<ProdutoVendido> getListaProd() {
		return listaProd;
	}

	public void setListaProd(ObservableList<ProdutoVendido> listaProd) {
		this.listaProd = listaProd;
	}
}
