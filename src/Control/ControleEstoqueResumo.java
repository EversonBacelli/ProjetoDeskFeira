package Control;

import java.util.List;
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
		ObservableList<LoteProduto> loteProduto = cLote.getListItem();
		ObservableList<Produto> listaProduto = cProduto.getListaProdDAO();
		
		for(Produto x: listaProduto) 
		{
			EstoqueResumo rProduto = new EstoqueResumo();
			rProduto.setP(x);
			listaResumo.add(rProduto);
			
			for(LoteProduto l: loteProduto) 
			{
				if(x.getNome().equals(l.getProduto().getNome())) 
				{
					rProduto.setQtd(rProduto.getQtd() + l.getQuantidade());
				}
			}
		}
	}
}

