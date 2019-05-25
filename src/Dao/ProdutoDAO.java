package Dao;

import java.util.List;

import Model.Produto;

public interface ProdutoDAO {
	void adicionar(Produto p) throws DAOException;

	Produto pesquisarNome(String nome) throws DAOException;

	Produto pesquisarId(int id) throws DAOException;

	List<Produto> listar(Produto p) throws DAOException;

	void excluir(int id) throws DAOException;

	void alterar(Produto p) throws DAOException;
}
