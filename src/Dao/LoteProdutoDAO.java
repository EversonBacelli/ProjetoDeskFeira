package Dao;

import java.util.List;

import Model.LoteProduto;

public interface LoteProdutoDAO {
	
	void adicionar(LoteProduto lp) throws DAOException;

	List<LoteProduto> listar(LoteProduto lp) throws DAOException;

	void excluir(LoteProduto lp) throws DAOException;

	void alterar(LoteProduto lp) throws DAOException;
	
}
