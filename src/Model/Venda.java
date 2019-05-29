package Model;

import java.util.ArrayList;
import java.util.List;

public class Venda {

	private int id;
	private String dataVenda;
	private List<ProdutoVendido> listaLoteProduto;
	private double valorTotal;
	private int qtdVenda;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProdutoVendido> getListaLoteProduto() {
		return listaLoteProduto;
	}

	public void setListaLoteProduto(List<ProdutoVendido> listaProdutoVendido) {
		this.listaLoteProduto = listaProdutoVendido;
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
