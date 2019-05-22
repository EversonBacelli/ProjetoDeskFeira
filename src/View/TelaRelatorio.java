package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TelaRelatorio extends Application {

	@Override
	public void start(Stage telaRelatorio) throws Exception 
	{
		// Estrtura
		FlowPane pane = new FlowPane();
				
		Scene scn = new Scene(pane, 400, 400);
		
		telaRelatorio.setTitle("TELA DE SAIDA DE ESTOQUE");
		telaRelatorio.setScene(scn);
		telaRelatorio.show();
		
	}

	public static void main(String[] args) 
	{
		Application.launch(args);
	}
}
