package Model;

public class EstoqueResumo {
	private Produto p = new Produto();
	private int qtd;
	
	public Produto getP() {
		return p;
	}
	public void setP(Produto p) {
		this.p = p;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
}
