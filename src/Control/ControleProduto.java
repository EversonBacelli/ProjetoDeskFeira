package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Dao.DAOException;
import Dao.ProdutoDAO;
import Dao.ProdutoDAOImpl;
import Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleProduto {


	private ObservableList<Produto> listaProd = FXCollections.observableArrayList();

	public void inserirProduto(Produto p) 
	{
		ProdutoDAO pDao = new ProdutoDAOImpl();
		try {
			pDao.adicionar(p);
			listaProd.add(p);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Produto pesquisarProdutoNome(Produto produtoPesquisado) 
	{
		ProdutoDAO pDao = new ProdutoDAOImpl();
		try {
			produtoPesquisado = (pDao.pesquisarNome(produtoPesquisado.getNome()));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return produtoPesquisado;
	}
	
	
	public void alterarProduto(Produto produtoAlterado) 
	{
		ProdutoDAO pDao = new ProdutoDAOImpl();
		try {
			pDao.alterar(produtoAlterado);
			listaProd.clear();
			listaProd.setAll(pDao.listar());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removerProduto(Produto prod) {
		ProdutoDAO pDao = new ProdutoDAOImpl();
		try {
			pDao.excluir(prod);
			listaProd.clear();
			listaProd.addAll(pDao.listar());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public boolean produtoExiste(Produto prod) {
		ProdutoDAO pDao = new ProdutoDAOImpl();
		boolean existe = false;
		for(Produto p : listaProd) {
			if(p.getNome().equals(prod.getNome())) {
				existe = true;
			}
		}
		return existe;
	}

	public Produto pesquisarProdutoId(Produto produtoPesquisado) {
		ProdutoDAO pDao = new ProdutoDAOImpl();
		try {
			produtoPesquisado = pDao.pesquisarId(produtoPesquisado.getId());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return produtoPesquisado;
	}
	
	
	public ObservableList<Produto> getListaProd() 
	{
		return listaProd;
	}
	
	
	public ObservableList<Produto> getListaProdDAO() {
		ProdutoDAO pDao = new ProdutoDAOImpl();
		try {
			this.listaProd.clear();
			this.listaProd.addAll(pDao.listar());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProd;
	}
	
	
	public void setListaProd(ObservableList<Produto> listaProd) {
		this.listaProd = listaProd;
	}
	
	
}
