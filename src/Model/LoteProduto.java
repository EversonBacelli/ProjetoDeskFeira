package Model;

public class LoteProduto 
{
	Produto produto;
	int id;
	int quantidade;
	int qtdMax;
	int qtdMin;
	String dataValidade;
	String dataEntrada;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getDataEntrada() {
		return dataEntrada;
	}
	
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQtdMax() {
		return qtdMax;
	}
	
	public void setQtdMax(int qtdMax) {
		this.qtdMax = qtdMax;
	}
	
	public int getQtdMin() {
		return qtdMin;
	}
	
	public void setQtdMin(int qtdMin) {
		this.qtdMin = qtdMin;
	}
	
	public String getDataValidade() {
		return dataValidade;
	}
	
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
}
