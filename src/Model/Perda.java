package Model;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Perda{
         List<LoteProduto> listPerda = new ArrayList();

         int qtdPerda;
         String data;
         
	public Perda(LoteProduto item, int qtdPerda, String data) 
	    {
			this.qtdPerda = qtdPerda;
			this.data = data;
			listPerda.add(item);
		}

}

