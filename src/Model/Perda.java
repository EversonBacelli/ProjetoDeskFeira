package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Perda{
         private List<LoteProduto> listPerda = new ArrayList();
         private int qtdPerda;
         private String data;
         
	public Perda(LoteProduto item, int qtdPerda, String data) 
	    {
			this.qtdPerda = qtdPerda;
			this.data = data;
			listPerda.add(item);
		}

}

