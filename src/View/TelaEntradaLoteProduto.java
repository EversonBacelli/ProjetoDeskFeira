package View;

import Control.ControleProduto;
import Model.LoteProduto;
import Model.Produto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaEntradaLoteProduto extends Application {
	ControleProduto control = new ControleProduto();
	Produto p;
	LoteProduto loteProduto;
	
	// Objetos que Fazem parte do Produto
	static Label lblID;
	static TextField txtID;
	//
	static Label lblNome;
	static ComboBox txtNome;
	//
	static Label lblDescricao;
	static TextArea txtDescricao;
	// ----------------------------------
	// Objetos que fazem parte do Lote Produto
	static Label lblQtdMax;
	static TextField txtQtdMax;
	//
	static Label lblQtdMin;
	static TextField txtQtdMin;
	//
	static Label lblPreco;
	static TextField txtPreco;
	//
	static Label lblTempoVida;
	static TextField txtTempoVida;
	//
	static Label lblQtdDisponivel;
	static TextField txtQtdDisponivel;
	//
	static Label lbldataEntrada;
	static TextField txtdataEntrada;
	//
	static Label lblvalidade;
	static TextField txtvalidade; 
	//
	static Button cadastrar;
	//---------------------------
	// Estrutura
	
	
	GridPane pane;
	TilePane tpane;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage tela) throws Exception {

		iniciarObjetos();
		// Estrutura da Tela
		pane = new GridPane();
		tpane = new TilePane();
		//
		inserirObjetosTela(pane, tpane);
		editarTamanhoTXT();
		
		marginPaine();
		adicionandoEstiloElementos();
		
		Scene scn = new Scene(pane, 700, 600);
		EventHandler<ActionEvent> manipulador = new ManipuladorMouse();
		tpane.addEventFilter(ActionEvent.ACTION, manipulador);

		tela.setTitle("TELA DE ENTRADA DE ESTOQUE");
		tela.setScene(scn);
		tela.show();
	}
	
	public class ManipuladorMouse implements EventHandler<ActionEvent> {
		@Override
		
		public void handle(ActionEvent e) 
		{
			
		}

   }

	// --------------- METODOS  --------------------//

	private void marginPaine() {
		Insets marginTop = new Insets(20, 20, 20, 150);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginBot = new Insets(0, 0, 250,300);
		
		pane.setMargin(lblNome, margin);
		pane.setMargin(txtNome, margin);
		pane.setMargin(lblID, margin);
		pane.setMargin(txtID, margin);
		pane.setMargin(lblDescricao, margin);
		pane.setMargin(txtDescricao, margin);
		pane.setMargin(lblQtdMax, margin);
		pane.setMargin(txtQtdMax, margin);
		pane.setMargin(lblQtdMin, margin);
		pane.setMargin(txtQtdMin, margin);
		pane.setMargin(lblPreco, margin);
		pane.setMargin(txtPreco, margin);
		pane.setMargin(lblTempoVida, margin);
		pane.setMargin(txtTempoVida, margin);
		pane.setMargin(lblQtdDisponivel, margin);
		pane.setMargin(txtQtdDisponivel, margin);
		pane.setMargin(lbldataEntrada, margin);
		pane.setMargin(txtdataEntrada, margin);
		pane.setMargin(lblvalidade, margin);
		pane.setMargin(txtvalidade, margin);
		pane.setMargin(cadastrar, margin);
		
	}

	
	public void adicionandoEstiloElementos() {

		lblID.setFont(new Font(12));
		lblNome.setFont(new Font(12));
		lblDescricao.setFont(new Font(12));
		
		lblID.setStyle("-fx-font-weight: bold");
		lblNome.setStyle("-fx-font-weight: bold");
		lblDescricao.setStyle("-fx-font-weight: bold");
		
		txtID.setFont(new Font(12));
		txtDescricao.setFont(new Font(12));
		
		cadastrar.setStyle("-fx-font-weight: bold");
		cadastrar.setFont(new Font(20));
	}
	
	private void inserirObjetosTela(GridPane pane, TilePane tpane) 
	{
		pane.add(lblNome           , 0, 0);
		pane.add(txtNome           , 1, 0);
		pane.add(lblID             , 2, 0);
		pane.add(txtID             , 3, 0);
		pane.add(lblDescricao      , 0, 2);
		pane.add(txtDescricao      , 1, 2);
		pane.add(lblQtdMax         , 0, 3);
		pane.add(txtQtdMax         , 1, 3);
		pane.add(lblQtdMin         , 2, 3);
		pane.add(txtQtdMin         , 3, 3);
		pane.add(lblTempoVida      , 0, 5);
		pane.add(txtTempoVida      , 1, 5);
		pane.add(lblPreco          , 2, 5);
		pane.add(txtPreco          , 3, 5);
		pane.add(lblQtdDisponivel  , 0, 7);
		pane.add(txtQtdDisponivel  , 1, 7);
		pane.add(lbldataEntrada    , 2, 7);
		pane.add(txtdataEntrada    , 3, 7);
		pane.add(lblvalidade       , 0, 8);
		pane.add(txtvalidade       , 1, 8);
		pane.add(cadastrar         , 2, 9);
	}
	
	
	void editarTamanhoTXT()
	{
		txtID.setMaxSize(50,100);
		txtNome.setMaxSize(400,30);
		txtDescricao.setMaxSize(200, 100);
		txtQtdMax.setMaxSize(50,40);
		txtQtdMin.setMaxSize(50,40);
		txtPreco.setMaxSize(50,40);
		txtTempoVida.setMaxSize(50,40);
		txtQtdDisponivel.setMaxSize(50,40);
		txtdataEntrada.setMaxSize(50,40);
		txtvalidade.setMaxSize(50,40);
	}
	
	private void iniciarObjetos() {
		// Objetos da Tela
		lblNome = new Label("Nome do Produto");
		txtNome = new ComboBox();
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
	}
}
