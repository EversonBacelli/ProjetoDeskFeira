package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TelaSaidaEstoque {

		public void start(Stage telaVenda) throws Exception 
		{
			// Objetos da Tela
			Label lblProduto = new Label("Produto");
			ComboBox boxProduto = new ComboBox();
			//
			Label lblQuantidade = new Label("Quantidade");
			TextField txtQuantidade = new TextField();
			//
			Label lblData = new Label("Data");
			TextField txtData = new TextField();
			//
			Button InserirNoPedido   = new Button("Inserir no Pedido");
			Button FinalizarCompra   = new Button("FinalizarPedido");
			Button RetirarItem      = new Button("Retirar do Pedido");
			//------------------------------------------------------
			
			// Estrutura da Tela 
			FlowPane pane = new FlowPane();
			TilePane tpane1 = new TilePane();
			TilePane tpane2 = new TilePane();
			//
			// Inserindo os elementos na tela:
			tpane1.getChildren().add(lblProduto);
			tpane1.getChildren().add(boxProduto);
			tpane1.getChildren().add(lblQuantidade);
			tpane1.getChildren().add(txtQuantidade);
			tpane1.getChildren().add(lblData);
			tpane1.getChildren().add(txtData);
			tpane1.getChildren().add(InserirNoPedido);
			tpane1.getChildren().add(FinalizarCompra);
			tpane1.getChildren().add(RetirarItem);
			pane.getChildren().add(tpane1);
			pane.getChildren().add(tpane2);
			//------------------------------------------------------
			
			Scene scn = new Scene(pane, 400, 400);
			
			telaVenda.setTitle("TELA DE SAIDA DE ESTOQUE");
			telaVenda.setScene(scn);
			telaVenda.show();
			
		}
		
		public static void main(String[] args) 
		{
		    Application.launch(args);	
		}
}

