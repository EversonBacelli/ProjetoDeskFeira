package Model;

public class Produto {
	    private int id;
		private String nome;
		private String descricao;
		private int qtdMax;
		private int qtdMin;
		private int qtdTempoVida;
		
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
		public int getQtdTempoVida() {
			return qtdTempoVida;
		}
		public void setQtdTempoVida(int qtdTempoVida) {
			this.qtdTempoVida = qtdTempoVida;
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

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		
		
}
