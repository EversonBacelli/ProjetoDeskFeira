package Control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import Model.LoteProduto;
import Model.Produto;

public class ControleProduto {

	Set<LoteProduto> listItem = new HashSet();
	
	
	public void inserirProduto(LoteProduto p) { listItem.add(p);}
	
	public void removerProduto(Produto p) { listItem.remove(p);}
	
	public List<LoteProduto> pesquisarProduto(LoteProduto p) 
	{
		List<LoteProduto> lista = new ArrayList();
		for(LoteProduto item: listItem) 
		{
			if(item.getProduto().getId() == p.getProduto().getId()) { lista.add(item);}
		}
		return lista;
	}
	
	public void alterarEstoque(LoteProduto produto, int usuario) 
	{
		// reduzir do estoque
		int reduzir = produto.getQuantidade();
		int qtdTotal = 0;
		
		pesquisarProduto(produto.getId());
		
		// calcular quantidade de produto em estoque
		for(Produto p: lista) {	qtdTotal += p.getQtd(); }
		
		// verificar se o estoque é suficiente
		if(qtdTotal< produto.getQtd()) 
		{
			JOptionPane.showMessageDialog(null, "Há apenas" + qtdTotal +" do "+ produto.getNome() +" em estoque");
			return;
		}
		
		
		if(lista.isEmpty()) {JOptionPane.showMessageDialog(null, "Não" + produto.getNome() + " em estoque");} 
		else 
		{
			// iterar lista até reduzir pedido a 0
			while(reduzir>0) 
			{
				for(Produto p: lista) 
				{
				     if(reduzir >= p.getQtd()) 
				     {
				    	 reduzir -= p.getQtd();
				    	 p.setQtd(0);
				     } else 
				     {
				    	 p.setQtd(p.getQtd() - reduzir);
				    	 reduzir = 0 ;
				     }
				}
			}
		}
		
	}
}
