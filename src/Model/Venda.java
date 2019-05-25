package Model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	int id;
	List<LoteProduto> listaLoteProduto;
	String dataVenda;
	double valorTotal;
	int qtdVenda;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<LoteProduto> getListaLoteProduto() {
		return listaLoteProduto;
	}

	public void setListaLoteProduto(List<LoteProduto> listaLoteProduto) {
		this.listaLoteProduto = listaLoteProduto;
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
