package Model;

public class Produto {
	    private int id;
		private String nome;
		private String descricao;
		private int estoqueMax;
		private int estoqueMin;
		private double preco;
		private String tempoVida;
		private int qtd;
		private String dataEntrada;
		
		public Produto(int id, String nome, String descricao, int estoqueMax, int estoqueMin, double preco, String tempoVida, int qtd,
				String dataEntrada)
		{
			this.id = id;
			this.nome = nome;
			this.descricao = descricao;
			this.estoqueMax = estoqueMax;
			this.estoqueMin = estoqueMin;
			this.preco = preco;
			this.tempoVida = tempoVida;
			this.qtd = qtd;
			this.dataEntrada = dataEntrada;
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
		public int getQtd() {
			return qtd;
		}

		public void setQtd(int qtd) {
			this.qtd = qtd;
		}

		public String getDataEntrada() {
			return dataEntrada;
		}

		public void setDataEntrada(String dataEntrada) {
			this.dataEntrada = dataEntrada;
		}

		public void setTempoVida(String tempoVida) {
			this.tempoVida = tempoVida;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		
}
