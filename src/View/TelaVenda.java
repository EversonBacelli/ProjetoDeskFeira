package View;


import java.util.ArrayList;
import java.util.List;

import Control.ControleDeLoteProduto;
import Model.LoteProduto;
import Model.Produto;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaVenda extends Application implements EventHandler<ActionEvent>{
	private GridPane topoPainel2 = new GridPane();
	private GridPane topoPainel3 = new GridPane();
	private GridPane paneButtons = new GridPane();
	private VBox topoPainel = new VBox();
	private VBox painelCentral = new VBox();
	private TilePane painelBottom = new TilePane();
	private TableView<LoteProduto> table = new TableView<>();
	private BorderPane painelPrincipal = new BorderPane();
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	private ControleDeLoteProduto clp = new ControleDeLoteProduto();
	private ComboBox<LoteProduto> comboProd = new ComboBox<LoteProduto>();
	
	private Label lblIdVenda = new Label("Id venda");
	private Label lblQtdVendida = new Label("Quantidade Vendida");
	private Label lblValorTotal = new Label("Valor Total");
	private Label lblDataVenda = new Label("Data da Venda");
	private Label lblPesquisarProduto = new Label("Pesquisar Produto");
	private Label lblProduto = new Label("Produto");

	
	private Button btnAdicionar = new Button("+");
	private Button btnRemover = new Button("-");
	private Button btnRealizarVenda = new Button("Realizar Venda");
	
	private TextField tfIdVenda = new TextField();
	private TextField tfQtdVendida = new TextField();
	private TextField tfValorTotal = new TextField();
	private TextField tfDataVenda = new TextField();
	private TextField tfPesquisarProd = new TextField();
	@Override
	public void start(Stage stage) throws Exception {
		adicionandoElementosPaineis();
		mudandoEstilo();
		definirColunas();
		adicionandoMargins();
		adicionandoEstiloVenda();
		stage.setScene(scn);
		stage.setTitle("Venda");
		stage.show();
	}
	
	public void adicionandoEstiloVenda() {
		//this.tfValorTotal.setMaxSize(70, 70);
	}
	
	
	public void adicionandoElementosPaineis() {
		this.painelPrincipal.setCenter(this.painelCentral);
		this.painelPrincipal.setTop(this.topoPainel);
		this.painelPrincipal.setBottom(this.painelBottom);
		
		
		this.painelCentral.getChildren().add(table);
		this.painelCentral.getChildren().add(this.painelBottom);

		
		this.topoPainel.getChildren().add(topoPainel2);
		this.topoPainel.getChildren().add(topoPainel3);
		
		this.topoPainel2.add(this.lblIdVenda,0,0);
		this.topoPainel2.add(this.tfIdVenda,1,0);
		this.topoPainel2.add(this.lblDataVenda,2,0);
		this.topoPainel2.add(this.tfDataVenda,3,0);
		
		this.topoPainel3.add(this.lblProduto, 0, 0);
		this.topoPainel3.add(this.comboProd, 1, 0);
		this.topoPainel3.add(this.lblPesquisarProduto, 2, 0);
		this.topoPainel3.add(this.tfPesquisarProd, 3, 0);
		this.topoPainel3.add(this.paneButtons, 4, 0);

		this.painelBottom.getChildren().add(this.lblValorTotal);
		this.painelBottom.getChildren().add(this.tfValorTotal);
		//this.painelBottom.getChildren().add(this.paneButtons);
		this.painelBottom.getChildren().add(this.btnRealizarVenda);
//		
		this.paneButtons.add(this.btnAdicionar, 0, 0);
		this.paneButtons.add(this.btnRemover, 1, 0);
	
	}
	
	public void adicionandoMargins() {
		Insets marginTop = new Insets(30, 0, 0, 50);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginTable = new Insets(20, 200, 50, 100);
		Insets marginTop2 = new Insets(0, 0, 0, 55);
		Insets marginTop3 = new Insets(0, 20, 0, 55);
		Insets marginRight = new Insets(0, 20, 0, 0);
		
		this.painelPrincipal.setMargin(this.topoPainel, marginTop);
		this.painelPrincipal.setMargin(this.painelCentral, marginTable);
		
		this.topoPainel.setMargin(this.topoPainel2, marginTop2);
		this.topoPainel.setMargin(this.topoPainel3, marginTop3);
		
		this.topoPainel2.setMargin(this.lblIdVenda, marginRight);
		this.topoPainel2.setMargin(this.tfIdVenda, marginRight);
		this.topoPainel2.setMargin(this.lblDataVenda, new Insets(0, 50, 0, 0));
		
		this.topoPainel3.setMargin(this.lblProduto, marginRight);
		this.topoPainel3.setMargin(this.comboProd, marginRight);
		this.topoPainel3.setMargin(this.lblPesquisarProduto, marginRight);
		this.topoPainel3.setMargin(this.tfPesquisarProd, new Insets(0, 80, 0, 0));
		
		this.painelCentral.setMargin(this.painelBottom, new Insets(10, 0, 0, 0));
		
		this.painelBottom.setMargin(this.tfValorTotal, new Insets(0, 20, 0, 0));

	}
	
	public void mudandoEstilo() {
		this.lblProduto.setFont(new Font(20));
		this.lblIdVenda.setFont(new Font(20));
		this.lblQtdVendida.setFont(new Font(20));
		this.lblValorTotal.setFont(new Font(20));
		this.lblDataVenda.setFont(new Font(20));
		this.lblPesquisarProduto.setFont(new Font(20));
		
		this.tfIdVenda.setMaxSize(50, 50);
		//this.btnAdicionar.setPrefWidth(20);
		this.btnRemover.setMaxWidth(30);
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		
	}


	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void definirColunas() {
		
		TableColumn<LoteProduto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(240);

		TableColumn<LoteProduto, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(240);
		
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(250);
		
		table.getColumns().addAll(colunaNome, colunaQuantidade, colunaPreco);

		table.setItems(clp.getProdutoAdicionados());
		
	}
	
	
}
