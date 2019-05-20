package Atores;

public class Estoquista extends Funcionario implements IEstoquista
{
	public Estoquista(String nome, int registro) 
	{
		this.nome = nome;
		this.registroFuncional = registro;
		this.funcao = 1;
		this.ativo = true;
	}
	
	public void setAtivo() 
	{
		if (this.ativo = true) 
		{this.ativo = false;} else {this.ativo = true;}	
	}

	public void criarEstoque() {
		// TODO Auto-generated method stub
		
	}

	public void alterarEstoque() {
		// TODO Auto-generated method stub
		
	}

	
}
