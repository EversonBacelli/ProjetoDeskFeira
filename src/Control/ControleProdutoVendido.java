package Control;

import Model.Produto;
import Model.ProdutoVendido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleProdutoVendido {
	
	private ObservableList<ProdutoVendido> listaProd = FXCollections.observableArrayList();
	
	private int id = 0;

	public void adicionar(ProdutoVendido p) {
		this.listaProd.add(p);
	}
	
	public void remover(ProdutoVendido p) {
		this.listaProd.remove(p);
	}
	
	public int getProximoId() {
		return ++this.id;
	}
	
	public ObservableList<ProdutoVendido> getListaProd() {
		return listaProd;
	}

	public void setListaProd(ObservableList<ProdutoVendido> listaProd) {
		this.listaProd = listaProd;
	}
	
	public double calcularValorTotal() {
		double valorTotal = 0;
		for(ProdutoVendido p : this.listaProd) {
			valorTotal += p.getValorTortal();
		}
		return valorTotal;
	}
	
	
}
