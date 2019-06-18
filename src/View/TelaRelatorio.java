package View;

import Control.ControleDeLoteProduto;
import Control.ControleProdutoVendido;
import Control.ControleVenda;
import Model.LoteProduto;
import Model.ProdutoVendido;
import Model.Venda;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaRelatorio extends Application implements EventHandler<ActionEvent> {

	private BorderPane painelPrincipal = new BorderPane();
	private int tipoUser;

	
	private FlowPane paneVenda = new FlowPane();
	private FlowPane paneRelEstoque = new FlowPane();
	
	private VBox paneVenda1     = new VBox();
	private VBox paneRelEstoque1 = new VBox();

	private Button btnVoltar = new Button("  Voltar  ");
	
	private MenuBar barra_Menu;
	private Menu rel_venda;
	private Menu rel_estoque;
	private MenuItem item_listarVenda;
	private MenuItem item_listarEstoque;
	
	static Stage stageAux;
	
	private ControleVenda cVenda = new ControleVenda();
	private ControleDeLoteProduto cLoteProd = new ControleDeLoteProduto();

	private TableView<LoteProduto>     tblEstoque = new TableView<>();
	private TableView<Venda>  tblVenda = new TableView<>();

	
	private Scene scn = new Scene(this.painelPrincipal, 1000, 563);
	
	public TelaRelatorio(int valor) {
		tipoUser = valor;
	}
	
//	public static void main(String[] args) 
//	{
//		Application.launch(args);
//	}

	@Override
	public void start(Stage stage) throws Exception	{
		stageAux = stage;
		
		iniciarMenu();
		marginPaine();
		inserirEventos();
		adicionandoEstilos();
		
		iniciarRelatorioEstoque();
		iniciarRelatorioVenda();
		
		definirColunasEstoqueQuantidade();
		definirColunasVenda();
		
		stage.setResizable(false);
		stage.setTitle("Relatorios");
		stage.setScene(scn);
		stage.show();
	}

	
	@Override
	public void handle(ActionEvent e) 
	{
		if(e.getTarget() == this.item_listarVenda) {
			mostrarParaRelatorioVenda();
		}else if(e.getTarget() == this.item_listarEstoque) {
			mostrarRelatorioEstoque();
		}else if(e.getTarget() == this.btnVoltar) {
			voltarParaTelaPrincipal();
		}
	}
	
	public void voltarParaTelaPrincipal() 
	{
		TelaPrincipal telaPrincipal = new TelaPrincipal(tipoUser);
		try {
			telaPrincipal.start(stageAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionandoEstilos() 
	{
		this.painelPrincipal.setStyle("-fx-background-color: #A60525;");
	}
	
	private void marginPaine() 
	{
		this.painelPrincipal.setMargin(this.barra_Menu, new Insets(0, 30, 0, 0));
		this.painelPrincipal.setMargin(this.btnVoltar, new Insets(-31, 0, 0, 0));
	}
	
	public void iniciarRelatorioEstoque() {
		this.paneRelEstoque.getChildren().add(this.paneRelEstoque1);
		this.paneRelEstoque1.getChildren().add(tblEstoque);
	}
	
	public void iniciarRelatorioVenda() {
		this.paneVenda.getChildren().add(this.paneVenda1);
		this.paneVenda1.getChildren().add(this.tblVenda);
	}

	public void mostrarRelatorioEstoque() {
		this.painelPrincipal.setCenter(this.paneRelEstoque);
		this.painelPrincipal.setMargin(this.paneRelEstoque, new Insets(50, 0, 0, 30));
	}
	
	public void mostrarParaRelatorioVenda() {
		this.painelPrincipal.setCenter(this.paneVenda);
		this.painelPrincipal.setMargin(this.paneVenda, new Insets(50, 0, 0, 30));
	}
	
	
	

	
	public void iniciarMenu() {
		
		
		barra_Menu = new MenuBar();
		
		rel_venda = new Menu(" Relatorio de Venda");
		rel_estoque = new Menu(" Relatorio de Quantidade");
		
		item_listarVenda = new MenuItem("Listar Vendas");
		item_listarEstoque = new MenuItem("Listar Estoques");

		// -----------------------------------------------------------
		rel_venda.getItems().add(item_listarVenda);
		rel_estoque.getItems().add(item_listarEstoque);


		// -----------------------------------------------------------
		barra_Menu.getMenus().add(rel_venda);
		barra_Menu.getMenus().add(rel_estoque);



		
		this.painelPrincipal.setTop(this.barra_Menu);
		this.painelPrincipal.setRight(this.btnVoltar);
		// -----------------------------------------------------------
		// -----------------------------------------------------------
	}
	
	
	private void inserirEventos() 
	{
		item_listarVenda.addEventHandler(ActionEvent.ANY, this);
		item_listarEstoque.addEventHandler(ActionEvent.ANY, this);
		btnVoltar.addEventHandler(ActionEvent.ANY, this);
	}
	
	
	public void definirColunasVenda() 
	{
		TableColumn<Venda, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()));
		colunaID.setPrefWidth(300);
		
		TableColumn<Venda, String> colunaData = new TableColumn<>("ID");
		colunaData.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataVenda()));
		colunaData.setPrefWidth(300);
		
		TableColumn<Venda, Number> colunaValorTotal = new TableColumn<>("Valor Total");
		colunaValorTotal.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTotal()));
		colunaValorTotal.setPrefWidth(300);
		
		tblVenda.getColumns().addAll(colunaID, colunaData, colunaValorTotal);
		tblVenda.setItems(cVenda.getListaVendaDAO());
	}

	
	public void definirColunasEstoqueQuantidade() 
	{
		TableColumn<LoteProduto, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()));
		colunaID.setPrefWidth(300);
		
		TableColumn<LoteProduto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(300);
		
		TableColumn<LoteProduto, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(300);
		
		/*
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(80);
		*/
		
		tblEstoque.getColumns().addAll(colunaID, colunaNome, colunaQuantidade);

		tblEstoque.setItems(cLoteProd.getListItemDAO());
	}
	

}
