package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Tela_Funcio_Cad extends Application {

	@Override
	public void start(Stage telaCadastro) throws Exception 
	{
		// Esrutura
		FlowPane pane = new FlowPane();
		
		
		// Objetos da tela
		Label lblfuncionario     =  new Label ("FUNÇÃO");
		ComboBox boxFuncionario  =  new ComboBox();
		//
		
		Label lblnome = new Label();
		TextField txtnome = new TextField();
		//
		Label lblID = new Label("ID");
		TextField txtID = new TextField();
		
		//
		Button btnInserir   = new Button("INSERIR FUNCIONARIO");
		Button btnPesquisar = new Button("PESQUISAR FUNCIONARIO");
		Button btnExcluir   = new Button("EXCLUIR FUNCIONARIO");
		Button btnEditar   = new Button("EDITAR FUNCIONARIO");
		//
		// Inserir objetos na tela
		pane.getChildren().add(lblfuncionario);
		pane.getChildren().add(boxFuncionario);
		pane.getChildren().add(lblnome);
		pane.getChildren().add(txtnome);
		pane.getChildren().add(lblID);
		pane.getChildren().add(txtID);
		pane.getChildren().add(btnInserir);
		pane.getChildren().add(btnPesquisar);
		pane.getChildren().add(btnExcluir);
		pane.getChildren().add(btnEditar);
		
		
		Scene scn = new Scene(pane, 400, 400);
		
		telaCadastro.setTitle("TELA DE SAIDA DE ESTOQUE");
		telaCadastro.setScene(scn);
		telaCadastro.show();
	}
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

}
