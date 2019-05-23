package Model;

import java.util.ArrayList;
import java.util.List;

public class Perda {
         List<LoteProduto> listPerda = new ArrayList();
         Perda perda;
         int qtdPerda;
         String data;
         
	public Perda(LoteProduto item, int qtdPerda, String data) 
	    {
			this.qtdPerda = qtdPerda;
			this.data = data;
			listPerda.add(item);
		} 
}

