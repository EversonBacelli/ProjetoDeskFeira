package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleProduto {


	private ObservableList<Produto> listaProd = FXCollections.observableArrayList();

	public void inserirProduto(Produto p) {
		this.listaProd.add(p);
		JOptionPane.showMessageDialog(null,  "Produto adicionado com sucesso");
	}
	public Produto pesquisarProdutoNome(Produto produtoPesquisado) {
		Produto retornado = new Produto();
		for(Produto p : listaProd) {
			if(p.getNome().equals(produtoPesquisado.getNome())) {
				JOptionPane.showMessageDialog(null,  "Produto Encontrado");
				return p;
			}
		}
		if(retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null,"PRODUTO NÃO ENCONTRADO");
		}
		return retornado;
	}
	
	public void alterarProduto(Produto produtoAlterado) {
		for(Produto p : listaProd) {
			if(p.getNome().equals(produtoAlterado.getNome())) {
				listaProd.remove(p);
				listaProd.add(produtoAlterado);
				JOptionPane.showMessageDialog(null,  "Produto alterado com sucesso");
			}
		}
	}
	
	public void removerProduto(Produto prod) {
		for(Produto p : listaProd) {
			if(p.getNome().equals(prod.getNome())) {
				listaProd.remove(p);
				JOptionPane.showMessageDialog(null,  "Produto removido com sucesso");
				break;
			}
		}
	}
	

	
	public boolean produtoExiste(Produto prod) {
		boolean existe = false;
		for(Produto p : listaProd) {
			if(p.getNome().equals(prod.getNome())) {
				existe = true;
			}
		}
		return existe;
	}

	public Produto pesquisarProdutoNomeId(Produto produtoPesquisado) {
		Produto retornado = new Produto();
		for(Produto p : listaProd) {
			if(p.getId() == produtoPesquisado.getId()) {
				JOptionPane.showMessageDialog(null,  "Produto Encontrado");
				return p;
			}
		}
		if(retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null,"PRODUTO NÃO ENCONTRADO");
		}
		return retornado;
	}
	
	public List<Produto> pesquisarProdutos() {
		return this.listaProd;
	}
	
	public ObservableList<Produto> getListaProd() {
		return listaProd;
	}
	public void setListaProd(ObservableList<Produto> listaProd) {
		this.listaProd = listaProd;
	}
	
	
}
