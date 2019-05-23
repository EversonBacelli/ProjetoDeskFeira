package View;

import Control.ControleDeLoteProduto;
import Model.Produto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TelaEntradaEstoque extends Application {
	ControleDeLoteProduto control = new ControleDeLoteProduto();
	Label lblNome;
	TextField txtNome;
	Label lblID;
	TextField txtID;
	Label lblDescricao;
	TextArea txtDescricao;
	Label lblQtdMax;
	TextField txtQtdMax;
	Label lblQtdMin;
	TextField txtQtdMin;
	Label lblPreco;
	TextField txtPreco;
	Label lblTempoVida;
	TextField txtTempoVida;
	Label lblQtdDisponivel;
	TextField txtQtdDisponivel;
	Label lbldataEntrada;
	TextField txtdataEntrada;
	Button cadastrar;

	Produto p;

	@Override
	public void start(Stage tela) throws Exception {
		// Objetos da Tela
		lblNome = new Label("Nome do Produto");
		txtNome = new TextField();
		//
		lblID = new Label("Numero de Identificação");
		txtID = new TextField();
		//
		lblDescricao = new Label("Descricao Produto");
		txtDescricao = new TextArea();
		//
		lblQtdMax = new Label("Quantidade Máxima");
		txtQtdMax = new TextField();
		//
		lblQtdMin = new Label("Quantidade Minima");
		txtQtdMin = new TextField();
		//
		lblPreco = new Label("Preco");
		txtPreco = new TextField();
		//
		lblTempoVida = new Label("Tempo de vida");
		txtTempoVida = new TextField();
		//
		lblQtdDisponivel = new Label("Quantidade disponivel");
		txtQtdDisponivel = new TextField();
		//
		lbldataEntrada = new Label("Data de Entrada");
		txtdataEntrada = new TextField();
		//
		cadastrar = new Button("Cadastrar");
		// ------------------------------------------------------

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
		pane.getChildren().add(lblTempoVida);
		pane.getChildren().add(txtTempoVida);
		pane.getChildren().add(lblPreco);
		pane.getChildren().add(txtPreco);
		pane.getChildren().add(lblQtdDisponivel);
		pane.getChildren().add(txtQtdDisponivel);
		pane.getChildren().add(lbldataEntrada);
		pane.getChildren().add(txtdataEntrada);
		pane.getChildren().add(tpane);
		tpane.getChildren().add(cadastrar);

		// ------------------------------------------------------

		Scene scn = new Scene(pane, 600, 400);
		EventHandler<ActionEvent> manipulador = new ManipuladorMouse();
		tpane.addEventFilter(ActionEvent.ACTION, manipulador);

		tela.setTitle("TELA DE ENTRADA DE ESTOQUE");
		tela.setScene(scn);
		tela.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public class ManipuladorMouse implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			p = telaParaProduto();
		}

		public Produto telaParaProduto() {
			p = new Produto(Integer.parseInt(txtID.getId()), txtNome.getText(), txtDescricao.getText(),
					Integer.parseInt(txtQtdMax.getText()), Integer.parseInt(txtQtdMin.getText()),
					Float.parseFloat(txtPreco.getText()), txtTempoVida.getText(),
					Integer.parseInt(txtQtdDisponivel.getText()), txtdataEntrada.getText());
			        control.inserirProduto(p);
			return p;
		}
	}
}
