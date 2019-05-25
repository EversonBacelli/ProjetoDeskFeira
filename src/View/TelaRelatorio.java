package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TelaRelatorio extends Application {

	@Override
	public void start(Stage telaRelatorio) throws Exception 
	{
		// Estrtura
		FlowPane pane = new FlowPane();
		ImageView img = new ImageView(new Image("file:Images/DESKFEIRA.png"));		
		Scene scn = new Scene(pane, 1600, 900);
		
		pane.getChildren().add(img);
		pane.setMargin(img, new Insets(0, 0, 0, 430));
		
		telaRelatorio.setTitle("TELA DE SAIDA DE ESTOQUE");
		telaRelatorio.setScene(scn);
		telaRelatorio.show();
		
	}

	public static void main(String[] args) 
	{
		Application.launch(args);
	}
}
