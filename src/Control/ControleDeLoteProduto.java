package Control;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import Model.LoteProduto;
import Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class ControleDeLoteProduto {

	private ObservableList<LoteProduto> listItem = FXCollections.observableArrayList();

	private ObservableList<LoteProduto> produtoAdicionados = FXCollections.observableArrayList();
	
	public void inserirLoteProduto(LoteProduto p) 
	{ 
		listItem.add(p);
		JOptionPane.showMessageDialog(null, "Produto inserido com sucesso");
	}
	
	public void removerLoteProduto(LoteProduto p) { listItem.remove(p);}
	
	public void alterarLoteProduto(LoteProduto p) {
		
	}
	
	public List<LoteProduto> pesquisarProduto(LoteProduto p) 
	{
		List<LoteProduto> lista = new ArrayList();
		for(LoteProduto item: listItem) 
		{
			if(item.getProduto().getId() == p.getProduto().getId()) { lista.add(item);}
		}
		return lista;
	}
	

	public ObservableList<LoteProduto> getProdutoAdicionados() {
		return produtoAdicionados;
	}

	public void setProdutoAdicionados(ObservableList<LoteProduto> produtoAdicionados) {
		this.produtoAdicionados = produtoAdicionados;
	}
	
	public ObservableList<LoteProduto> getListItem() {
		return listItem;
	}

	public void setListItem(ObservableList<LoteProduto> listItem) {
		this.listItem = listItem;
	}
	public void alterarEstoque(LoteProduto prodLote, int usuario) 
	{
		// reduzir do estoque
		int reduzir = prodLote.getQuantidade();
		int qtdTotal = 0;
		
		
		// calcular quantidade de produto em estoque
		for(LoteProduto p: this.listItem) {	qtdTotal += p.getQuantidade();}
		
		// verificar se o estoque é suficiente
		if(qtdTotal< prodLote.getQuantidade()) 
		{
			JOptionPane.showMessageDialog(null, "Há apenas" + qtdTotal +" do "+ prodLote.getProduto().getDescricao() +" em estoque");
			return;
		}
		
		
		if(listItem.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não temos " + prodLote.getProduto().getNome() + " em estoque");
		} else 
		{
			// iterar lista até reduzir pedido a 0
			while(reduzir>0) 
			{
				for(LoteProduto p: listItem) 
				{
				     if(reduzir >= p.getQuantidade()) 
				     {
				    	 reduzir -= p.getQuantidade();
				    	 p.setQuantidade(0);
				    	 removerLoteProduto(p);
				     } else 
				     {
				    	 p.setQuantidade(p.getQuantidade() - reduzir);
				    	 reduzir = 0 ;
				     }
				}
			}
		}
		
	}
}
