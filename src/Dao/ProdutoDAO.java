package Dao;

import java.util.List;

import Model.Produto;

public interface ProdutoDAO {
	void adicionar(Produto p) throws DAOException;

	Produto pesquisarNome(String nome) throws DAOException;

	Produto pesquisarId(int id) throws DAOException;

	List<Produto> listar() throws DAOException;

	void excluir(Produto produto) throws DAOException;

	void alterar(Produto p) throws DAOException;
	
	int proximoId() throws DAOException;
}