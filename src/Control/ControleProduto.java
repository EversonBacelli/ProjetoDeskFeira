package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Produto;

public class ControleProduto {
	
	private List<Produto> listaProd = new ArrayList<Produto>();
	
	public void inserirProduto(Produto p) {
		this.listaProd.add(p);
		JOptionPane.showMessageDialog(null,  "Produto adicionado com sucesso");
	}
	public Produto pesquisarProduto(Produto produtoPesquisado) {
		Produto retornado = new Produto();
		for(Produto p : listaProd) {
			if(p.getNome().equals(produtoPesquisado.getNome())) {
				JOptionPane.showMessageDialog(null,  "Produto Encontrado");
				return p;
			}
		}
		if(retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null,"PRODUTO N�O ENCONTRADO");
		}
		return retornado;
	}
	
	public void alterarProduto(Produto produtoAlterado) {
		for(Produto p : listaProd) {
			if(p.getNome().equals(produtoAlterado.getNome())) {
				p = produtoAlterado;
				JOptionPane.showMessageDialog(null,  "Produto alterado com sucesso");
			}
		}
	}
	
	public void removerProduto(Produto prod) {
		for(Produto p : listaProd) {
			if(p.getNome().equals(prod.getNome())) {
				listaProd.remove(p);
				JOptionPane.showMessageDialog(null,  "Produto removido com sucesso");
			}
		}
	}
	
	public List<Produto> pesquisarProdutos() {
		return this.listaProd;
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
}
