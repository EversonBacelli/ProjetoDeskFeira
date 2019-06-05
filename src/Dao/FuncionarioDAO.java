package Dao;

import java.util.List;

import Model.Funcionario;

public interface FuncionarioDAO {
	
	void adicionar(Funcionario f) throws DAOException;

	List<Funcionario> listar(Funcionario f) throws DAOException;

	void excluir(Funcionario f) throws DAOException;

	void alterar(Funcionario f) throws DAOException;
}
