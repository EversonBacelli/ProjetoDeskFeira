package View;


import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.LALOAD;

import Control.ControleDeLoteProduto;
import Control.ControleProduto;
import Control.ControleProdutoVendido;
import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import Model.Venda;
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
	private ControleProduto CProduto = new ControleProduto();
	ObservableList<Produto> ProdutoVenda;
	private GridPane topoPainel2 = new GridPane();
	private GridPane topoPainel3 = new GridPane();
	private GridPane topoPainel4 = new GridPane();
	private GridPane paneButtons = new GridPane();

	
	private VBox topoPainel = new VBox();
	private VBox painelCentral = new VBox();
	private TilePane painelBottom = new TilePane();
	private TableView<ProdutoVendido> table = new TableView<>();
	private BorderPane painelPrincipal = new BorderPane();
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	private ControleProdutoVendido cpv = new ControleProdutoVendido();
	private ComboBox<Produto> comboProd = new ComboBox<Produto>();
	
	private Label lblIdVenda = new Label("Id venda");
	private Label lblQtdVendida = new Label("Qtd");
	private Label lblValorTotal = new Label("Valor Total");
	private Label lblDataVenda = new Label("Data da Venda");
	private Label lblPesquisarProduto = new Label("Pesquisar Produto");
	private Label lblProduto = new Label("Produto");
	private Label lblProdutoSelecionado = new Label("ADICIONAR/REMOVER PRODUTO SELECIONADO ");

	private Button btnAdicionarQtd = new Button("+");
	private Button btnRemoverQtd = new Button("-");
	private Button btnAdicionarNaLista = new Button("Adicionar Produto");
	private Button btnRemoverNaLista = new Button("Remover Produto");
	private Button btnRealizarVenda = new Button("Realizar Venda");
	
	private TextField tfIdVenda = new TextField();
	private TextField tfQtdVendida = new TextField();
	private TextField tfValorTotal = new TextField();
	private TextField tfDataVenda = new TextField();
	private TextField tfPesquisarProd = new TextField();

	@Override
	public void start(Stage stage) throws Exception {
		CProduto.getListaProd();
		ProdutoVenda = comboProd.getItems();
		adicionandoProdutosTeste(); 
		for(Produto x: CProduto.getListaProd()) 	{ProdutoVenda.add(x);}
		
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
		this.comboProd.setPrefWidth(100);
		this.tfQtdVendida.setPrefWidth(80);
	}
	
	
	public void adicionandoElementosPaineis() {
		this.painelPrincipal.setCenter(this.painelCentral);
		this.painelPrincipal.setTop(this.topoPainel);
		this.painelPrincipal.setBottom(this.painelBottom);

		
		
		this.painelCentral.getChildren().add(table);
		this.painelCentral.getChildren().add(this.painelBottom);

		
		this.topoPainel.getChildren().add(topoPainel2);
		this.topoPainel.getChildren().add(topoPainel3);
		this.topoPainel.getChildren().add(topoPainel4);
		
		this.topoPainel2.add(this.lblIdVenda,0,0);
		this.topoPainel2.add(this.tfIdVenda,1,0);
		this.topoPainel2.add(this.lblDataVenda,2,0);
		this.topoPainel2.add(this.tfDataVenda,3,0);
		
		this.topoPainel3.add(this.lblProduto, 0, 0);
		this.topoPainel3.add(this.comboProd, 1, 0);
		this.topoPainel3.add(this.lblPesquisarProduto, 2, 0);
		this.topoPainel3.add(this.tfPesquisarProd, 3, 0);
		this.topoPainel3.add(this.lblQtdVendida, 4, 0);
		this.topoPainel3.add(this.tfQtdVendida, 5, 0);
		this.topoPainel3.add(this.paneButtons, 6, 0);
		
		this.topoPainel4.add(this.btnAdicionarNaLista, 1, 0);
		this.topoPainel4.add(this.btnRemoverNaLista, 2, 0);

		this.painelBottom.getChildren().add(this.lblValorTotal);
		this.painelBottom.getChildren().add(this.tfValorTotal);
		//this.painelBottom.getChildren().add(this.paneButtons);
		this.painelBottom.getChildren().add(this.btnRealizarVenda);
//		
		this.paneButtons.add(this.btnAdicionarQtd, 0, 0);
		this.paneButtons.add(this.btnRemoverQtd, 1, 0);
//	
	}
	
	public void adicionandoMargins() {
		Insets marginTop = new Insets(30, 0, 0, 50);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginTable = new Insets(20, 100, 50, 100);
		Insets marginTop2 = new Insets(0, 0, 0, 55);
		Insets marginTop3 = new Insets(0, 20, 0, 55);
		Insets marginRight = new Insets(0, 20, 0, 0);

		
		this.painelPrincipal.setMargin(this.topoPainel, marginTop);
		this.painelPrincipal.setMargin(this.painelCentral, marginTable);
		
		this.topoPainel.setMargin(this.topoPainel2, marginTop2);
		this.topoPainel.setMargin(this.topoPainel3, marginTop3);
		this.topoPainel.setMargin(this.topoPainel4, marginTop3);
		
		this.topoPainel2.setMargin(this.lblIdVenda, marginRight);
		this.topoPainel2.setMargin(this.tfIdVenda, new Insets(0, 65, 0, 0));
		this.topoPainel2.setMargin(this.lblDataVenda, new Insets(0, 50, 0, 0));
		
		this.topoPainel3.setMargin(this.lblProduto, marginRight);
		this.topoPainel3.setMargin(this.comboProd, marginRight);
		this.topoPainel3.setMargin(this.lblPesquisarProduto, marginRight);
		this.topoPainel3.setMargin(this.tfPesquisarProd, marginRight);
		
	
		
		
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
		this.lblProdutoSelecionado.setFont(new Font(20));

		
		this.tfIdVenda.setMaxSize(50, 50);
		//this.btnAdicionar.setPrefWidth(20);
		this.btnRemoverQtd.setMaxWidth(30);
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		
	}


	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void definirColunas() {
		
		TableColumn<ProdutoVendido, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(240);

		TableColumn<ProdutoVendido, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(240);
		
		TableColumn<ProdutoVendido, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(250);
		
		table.getColumns().addAll(colunaNome, colunaQuantidade, colunaPreco);

		table.setItems(cpv.getListaProd());
		
	}
	
	public Venda telaParaVenda() {
		Venda v = new Venda();
		v.setId(Integer.parseInt(this.tfIdVenda.getText()));
		v.setDataVenda(this.tfDataVenda.getText());
		v.setListaLoteProduto(cpv.getListaProd());
		v.setQtdVenda(Integer.parseInt(this.tfQtdVendida.getText()));
		v.setValorTotal(Double.parseDouble(this.tfValorTotal.getText()));
		return v;
	}
	

	public void adicionandoProdutosTeste() {
		Produto p1= new Produto();
		p1.setId(1);
		p1.setNome("Helio Pinto");
		p1.setDescricao("Helio Pinto Pequeno");
		p1.setQtdMax(12);
		p1.setQtdMin(2);
		p1.setQtdTempoVida(5);
		p1.setPreco(100);
		CProduto.inserirProduto(p1);
		Produto p2= new Produto();
		p2.setId(2);
		p2.setNome("Helio Pinto2");
		p2.setDescricao("Cabra safado, usuário habitual do badoo e do tinder");
		p2.setQtdMax(12);
		p2.setQtdMin(2);
		p2.setQtdTempoVida(5);
		p2.setPreco(50);
		CProduto.inserirProduto(p2);
		Produto p3= new Produto();
		p3.setId(1);
		p3.setNome("Helio Pinto");
		p3.setDescricao("Cabra safado, usuário habitual do badoo e do tinder");
		p3.setQtdMax(12);
		p3.setQtdMin(2);
		p3.setQtdTempoVida(5);
		p3.setPreco(400);
		CProduto.inserirProduto(p3);
		Produto p4= new Produto();
		p4.setId(2);
		p4.setNome("Helio Pinto2");
		p4.setDescricao("Cabra safado, usuário habitual do badoo e do tinder");
		p4.setQtdMax(12);
		p4.setQtdMin(2);
		p4.setQtdTempoVida(5);
		p4.setPreco(500);
		CProduto.inserirProduto(p4);
	}

}
