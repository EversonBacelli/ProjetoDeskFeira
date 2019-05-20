package Atores;

public class Administrador extends Funcionario implements IVendedor, IEstoquista{

	public Administrador(String nome, int registro) 
	{
		this.nome = nome;
		this.registroFuncional = registro;
		this.funcao = 0;
		this.ativo = true;
	}
	
	@Override
	public void setAtivo() {
		if (this.ativo = true) 
		{this.ativo = false;} else {this.ativo = true;}	
	}

	public void criarEstoque() {
		// TODO Auto-generated method stub
		
	}

	public void alterarEstoque() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realizarVenda() {
		// TODO Auto-generated method stub
		
	}

}
