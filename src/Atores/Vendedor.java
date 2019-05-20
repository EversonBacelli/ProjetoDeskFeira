package Atores;

public class Vendedor extends Funcionario implements IVendedor {

	
	public Vendedor(String nome, int registro) 
	{
		this.nome = nome;
		this.registroFuncional = registro;
		this.funcao = 2;
		this.ativo = true;
	}
	
	public void setAtivo() 
	{
		if (this.ativo = true) 
		{this.ativo = false;} else {this.ativo = true;}	
	}

	@Override
	public void realizarVenda() {
		// TODO Auto-generated method stub
		
	}
}
