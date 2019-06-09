package Model;

public class ProdutoVendido {
//	private int idProdutoVendido;
	private int quantidade;
	private Produto produto;
	private int idVenda;
	
	
//	public int getIdProdutoVendido() {
//		return idProdutoVendido;
//	}
//	public void setIdProdutoVendido(int idProdutoVendido) {
//		this.idProdutoVendido = idProdutoVendido;
//	}
//	public int getIdProduto() {
//		return idProdutoVendido;
//	}
//	public void setIdProduto(int idProduto) {
//		this.idProdutoVendido = idProduto;
//	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public double getValorTortal(){
		return this.produto.getPreco() * this.quantidade;
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	
}
