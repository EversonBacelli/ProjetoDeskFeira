package Control;

import java.util.ArrayList;
import java.util.List;

import Model.Produto;

public class ControleProduto {
	
	private List<Produto> listaProd = new ArrayList<Produto>();
	
	public void inserirProduto(Produto p) {
		this.listaProd.add(p);
	}
	public Produto pesquisarProduto(Produto produtoPesquisado) {
		Produto retornado = new Produto();
		for(Produto p : listaProd) {
			if(p.getNome() == produtoPesquisado.getNome()) {
				retornado = p;
			}
		}
		return retornado;
	}
	
	public void alterarProduto(Produto produtoAlterado) {
		for(Produto p : listaProd) {
			if(p.getId() == produtoAlterado.getId()) {
				p = produtoAlterado;
			}
		}
	}
	
	public void removerProduto(Produto p) {
		this.listaProd.remove(p);
	}
	
	public List<Produto> pesquisarProdutos() {
		return this.listaProd;
	}
}
