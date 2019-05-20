package Atores;

public abstract class Funcionario 
{
	String nome;
	int registroFuncional;
	int funcao;
	boolean ativo;
	
	public abstract void setAtivo();
	
	public String getNome() { return nome;}
	
	public int getRegistroFuncional() { return registroFuncional;}
	
	public int getFuncao() { return funcao;}
	
	public boolean getAtivo() { return ativo;}
	
	public void setNome(String name) {this.nome = name;}
	
	public void setRegistroFuncional(int rf) {this.registroFuncional = rf;}
	
	public void setFuncao(int f) {this.funcao = f;}
	
	
}
