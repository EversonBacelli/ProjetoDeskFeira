package Model;

public enum TipoUsuario {
	// https://www.devmedia.com.br/tipos-enum-no-java/25729

	ADMINISTRADOR(1), ESTOQUISTA(2), VENDEDOR(3);

	private final int valor;

	TipoUsuario(int valorOpcao) {
		valor = valorOpcao;
	}

	public int getValor() {
		return valor;
	}
}
