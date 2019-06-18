package Model;

public class LoteProduto
{
	private Produto produto;
	private int id;
	private int quantidade;
	private String dataValidade;
	private String dataEntrada;
	
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
	
	
	public String getDataValidade() {
		return dataValidade;
	}
	
	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}
	
	public double getValorTortal(){
		return this.produto.getPreco() * this.quantidade;
	}
	
	public String toString() {
		return this.produto.getNome();
	}
	}
