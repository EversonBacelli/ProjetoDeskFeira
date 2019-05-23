package Model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	int id;
	// lista de lote de produto
	List<LoteProduto> item = new ArrayList();
	// data da venda
	String dataVenda;
	// Valor total da venda
	double valorTotal;
	// variavel que guarda a quantidade para diminuir dentro do lote
	int qtdVenda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<LoteProduto> getItem() {
		return item;
	}
	public void setItem(List<LoteProduto> item) {
		this.item = item;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getQtdVenda() {
		return qtdVenda;
	}
	public void setQtdVenda(int qtdVenda) {
		this.qtdVenda = qtdVenda;
	}
	
	
}
