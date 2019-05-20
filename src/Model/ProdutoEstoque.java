package Model;

public class ProdutoEstoque 
{
	private Produto item;
	private int qtdDisponivel;
	private String dataEntrada;
	
	public ProdutoEstoque(String nome, String descricao, int estoqueMax, int estoqueMin, double preco, String tempoVida, 
			              int entrada, String data) 
	{
		item = new Produto(nome, descricao, estoqueMax, estoqueMin, preco, tempoVida);
		qtdDisponivel = entrada;
		dataEntrada = data;
	}

	public Produto getItem() {
		return item;
	}

	public void setItem(Produto item) {
		this.item = item;
	}

	public int getQtdDisponivel() {
		return qtdDisponivel;
	}

	public void setQtdDisponivel(int qtdDisponivel) {
		this.qtdDisponivel = qtdDisponivel;
	}

	public String getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
		
			
}
