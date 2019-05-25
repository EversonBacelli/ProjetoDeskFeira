package Dao;

import java.util.List;

import Model.Produto;

public interface ProdutoDAO {
	void adicionar(Produto p) throws DAOException;

	List<Produto> pesquisarNome(String nome) throws DAOException;

	List<Produto> pesquisarId(int I) throws DAOException;
	
	void excluir(int id) throws DAOException;
	
	void alterar(int id) throws DAOException;
}
