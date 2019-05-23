package View;

import Control.ControleProduto;
import Model.LoteProduto;
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

public class TelaEntradaLoteProduto extends Application {
	ControleProduto control = new ControleProduto();
	Produto p;
	LoteProduto loteProduto;
	
	// Objetos que Fazem parte do Produto
	Label lblID;
	TextField txtID;
	//
	Label lblNome;
	TextField txtNome;
	//
	Label lblDescricao;
	TextArea txtDescricao;
	// ----------------------------------
	// Objetos que fazem parte do Lote Produto
	Label lblIdLote;
	TextField txtIdLote;
	//
	Label lblQtdMax;
	TextField txtQtdMax;
	//
	Label lblQtdMin;
	TextField txtQtdMin;
	//
	Label lblPreco;
	TextField txtPreco;
	//
	Label lblTempoVida;
	TextField txtTempoVida;
	//
	Label lblQtdDisponivel;
	TextField txtQtdDisponivel;
	//
	Label lbldataEntrada;
	TextField txtdataEntrada;
	//
	Label lblvalidade;
	TextField txtvalidade; 
	//
	Button cadastrar;


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
		lblvalidade = new Label("Validade");
		txtvalidade = new TextField();
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
		pane.getChildren().add(lblvalidade);
		pane.getChildren().add(txtvalidade);
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
			loteProduto = telaLoteProduto(p);
			
		}

		private LoteProduto telaLoteProduto(Produto p) {
			loteProduto.setProduto(p);
			loteProduto.setId(Integer.parseInt(txtID.getText()));
			loteProduto.setQtdMax(Integer.parseInt(txtQtdMax.getText()));
			loteProduto.setQtdMax(Integer.parseInt(txtQtdMin.getText()));
			loteProduto.setDataEntrada(txtdataEntrada.getText());
			return loteProduto;
		}

		public Produto telaParaProduto() {
			ControleProduto cp;
			cp.pesquisarProduto(p)
			p = new Produto(); 
			p.setId(Integer.parseInt(txtID.getText()));
			p.setNome(txtNome.getText());
			p.setNome(txtDescricao.getText());
			return p;
		}
	}
}
