package Control;

import java.util.Observer;

import Model.EstoqueResumo;
import Model.LoteProduto;
import Model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ControleEstoqueResumo {
	private ObservableList<EstoqueResumo> listaResumo = FXCollections.observableArrayList();
	
	public ObservableList<EstoqueResumo> getListaResumo() {return listaResumo;}

	public void setListaResumo(ObservableList<EstoqueResumo> listaResumo) { this.listaResumo = listaResumo;}

	public void limpar() { 	listaResumo.clear();}
	
	
	
	
	public void calcularResumo(ControleProduto cProduto, ControleDeLoteProduto cLote)
	{
		for(Produto x: cProduto.getListaProd()) 
		{		
			EstoqueResumo rProduto = new EstoqueResumo();
			rProduto.setP(x);
			listaResumo.add(rProduto);
			
			for(LoteProduto l: cLote.getListItem()) 
			{
				if(x == l.getProduto()) 
				{
					rProduto.setQtd(rProduto.getQtd() + l.getQuantidade());
				}
			}
		}
	}
}

