package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TelaEntradaEstoque extends Application{

	@Override
	public void start(Stage tela) throws Exception 
	{
		// Objetos da Tela
		Label lblNome = new Label("Nome do Produto");
		TextField txtNome = new TextField();
		//
		Label lblID = new Label("Numero de Identificação");
		TextField txtID = new TextField();
		//
		Label lblDescricao = new Label("Descricao Produto");
		TextArea txtDescricao = new TextArea();
		//
		Label lblQtdMax = new Label("Quantidade Máxima");
		TextField txtQtdMax = new TextField();
		//
		Label lblQtdMin = new Label("Quantidade Minima");
		TextField txtQtdMin = new TextField();
		//
		Button cadastrar = new Button("Cadastrar");
		//------------------------------------------------------
		
		// Estrutura da Tela 
		FlowPane pane = new FlowPane();
		TilePane tpane = new TilePane();
		//
		// Inserindo os elementos na tela:
		pane.getChildren().add(lblNome);
		pane.getChildren().add(txtNome);
		pane.getChildren().add(lblID);
		pane.getChildren().add(txtID);
		pane.getChildren().add(lblDescricao);
		pane.getChildren().add(txtDescricao);
		pane.getChildren().add(lblQtdMax);
		pane.getChildren().add(txtQtdMax);
		pane.getChildren().add(lblQtdMin);
		pane.getChildren().add(txtQtdMin);
		pane.getChildren().add(cadastrar);
		pane.getChildren().add(tpane);
		
		cadastrar.setLayoutX(0);
		cadastrar.setLayoutY(350);
		//------------------------------------------------------
		
		Scene scn = new Scene(pane, 400, 400);
		
		tela.setTitle("TELA DE ENTRADA DE ESTOQUE");
		tela.setScene(scn);
		tela.show();
		
	}
	
	public static void main(String[] args) 
	{
	    Application.launch(args);	
	}
}
