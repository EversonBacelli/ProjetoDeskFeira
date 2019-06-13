package Dao;

import java.util.List;

import Model.Funcionario;

public interface FuncionarioDAO {
	
	void inserir(Funcionario f) throws DAOException;

	List<Funcionario> listar() throws DAOException;

	void excluir(Funcionario f) throws DAOException;

	void alterar(Funcionario f) throws DAOException;

	int verificarLogin(String login, String senha) throws DAOException;
}
