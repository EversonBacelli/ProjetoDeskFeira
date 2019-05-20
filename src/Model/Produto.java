package Model;

public class Produto {
		private String nome;
		private String descricao;
		private int estoqueMax;
		private int estoqueMin;
		private double preco;
		private String tempoVida;
		
		
		public Produto(String nome, String descricao, int estoqueMax, int estoqueMin, double preco, String tempoVida)
		{
			this.nome = nome;
			this.descricao = descricao;
			this.estoqueMax = estoqueMax;
			this.estoqueMin = estoqueMin;
			this.preco = preco;
			this.tempoVida = tempoVida;
		}
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public int getEstoqueMax() {
			return estoqueMax;
		}
		public void setEstoqueMax(int estoqueMax) {
			this.estoqueMax = estoqueMax;
		}
		public int getEstoqueMin() {
			return estoqueMin;
		}
		public void setEstoqueMin(int estoqueMin) {
			this.estoqueMin = estoqueMin;
		}
		public double getPreco() {
			return preco;
		}
		public void setPreco(double preco) {
			this.preco = preco;
		}
		public String getTempoVida() {
			return tempoVida;
		}
		public void setTempoVida(String tempoVida) {
			this.tempoVida = tempoVida;
		}
}
