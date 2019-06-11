package Dao;

import java.util.List;

import Model.LoteProduto;
import Model.Venda;

public interface VendaDAO {
	
	void adicionar(Venda v) throws DAOException;

	List<Venda> listar() throws DAOException;
	
}
