package View;

import Control.ControleDeLoteProduto;
import Control.ControleProdutoVendido;
import Model.LoteProduto;
import Model.ProdutoVendido;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaRelatorio extends Application implements EventHandler<ActionEvent> {

	static Stage stageAux;
	BorderPane borderPane1;
	GridPane gridPane1;
	VBox box1;
	ControleProdutoVendido cpVendido = new ControleProdutoVendido();
	ControleDeLoteProduto cLote = new ControleDeLoteProduto();
	private Line linha1 = new Line();
	private ImageView imgEstoque = new ImageView(new Image("file:Images/Estoque.jpg"));
	private ImageView imgVenda = new ImageView(new Image("file:Images/Venda.png"));
	private ImageView imgValidade = new ImageView(new Image("file:Images/Validade.png"));
	Button btnVoltar = new Button("  Voltar  ");
	
	MenuBar barra_Menu;
	Menu rel_venda;
	Menu rel_estoque;
	MenuItem item_listarVenda;
	MenuItem item_listarEstoque;
	MenuItem item_listarVencidos;
	
	VBox paneVenda      = new VBox();
	VBox paneQuantidade = new VBox();
	VBox paneVencido    = new VBox();
	
	private TableView<LoteProduto>     tblQuantidade = new TableView<>();
	private TableView<ProdutoVendido>  tblVenda = new TableView<>();
	private TableView<LoteProduto>     tblVencido = new TableView<>();
	
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		stageAux = stage;
		iniciarObjetos();
		inserirEventos();
		borderPane1.setStyle("-fx-background-color: #CC00FF;");
			
		Scene scn = new Scene(borderPane1, 1000, 563);
		stage.setTitle(" Relatório de Venda ");
		stage.setScene(scn);
		stage.show();
	}

	
	@Override
	public void handle(ActionEvent e) 
	{
		borderPane1.setCenter(null);
		
		if(e.getTarget() == item_listarVenda) 
		{	
			borderPane1.setLeft(imgVenda);
			borderPane1.setCenter(paneVenda);
		} else 
		{
			if(e.getTarget()== item_listarEstoque) 
			{
				borderPane1.setLeft(imgEstoque);
				borderPane1.setCenter(paneQuantidade);
			} else 
			{
				if(e.getTarget() == item_listarVencidos) 
				{
					borderPane1.setLeft(imgValidade);
					borderPane1.setCenter(paneVencido);
				}
			}
		}
		if(e.getTarget() == btnVoltar) 
		{
			TelaPrincipal telaPrincipal = new TelaPrincipal();
			try {
				telaPrincipal.start(stageAux);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	public void iniciarObjetos() 
	{
		iniciarMenu();
		estiloLinha();
		//
		definirColunasVenda();
		relatorioVenda();
		//
		definirColunasEstoqueQuantidade();
		relatorioEstoqueQuantidade();
		//
		definirColunasEstoqueVencido();
		relatorioVencido();
		//
		marginPaine();	
	}

	private void marginPaine() 
	{
		Insets marginImage = new Insets(150, 0, 0, 50);
		Insets margin = new Insets(50, 50, 50, 80);
	
		borderPane1.setMargin(paneVenda, margin);
		borderPane1.setMargin(paneQuantidade, margin);
		borderPane1.setMargin(paneVencido, margin);
		
		borderPane1.setMargin(imgEstoque, marginImage);
		borderPane1.setMargin(imgVenda, marginImage);
		borderPane1.setMargin(imgValidade, marginImage);
	}
	
	public void estiloLinha() 
	{
	this.linha1.setStroke(Color.LIGHTSKYBLUE);
    this.linha1.setStrokeWidth(10.0f);
    this.linha1.setStartX(0);
	this.linha1.setEndX(900);
	}
	
	private void relatorioVenda() 
	{
		paneVenda.getChildren().add(tblVenda);
	}
	
	private void relatorioEstoqueQuantidade() {
		paneQuantidade.getChildren().add(tblQuantidade);
	}
	
	
	private void relatorioVencido() {
		paneVenda.getChildren().add(linha1);
		paneVencido.getChildren().add(tblVencido);
	}
	
	
	public void iniciarMenu() 
	{
		// Inicializar
		borderPane1 = new BorderPane();
		box1 = new VBox();
		barra_Menu = new MenuBar();
		
		rel_venda = new Menu(" Relatorio de Venda");
		rel_estoque = new Menu(" Relatorio de Quantidade");
		
		item_listarVenda = new MenuItem("Listar Vendas");
		item_listarEstoque = new MenuItem("Listar Estoques");
		item_listarVencidos = new MenuItem("Lista Produtos Vencidos");

		// -----------------------------------------------------------
		rel_venda.getItems().add(item_listarVenda);
		rel_estoque.getItems().add(item_listarEstoque);
		rel_estoque.getItems().add(item_listarVencidos);

		// -----------------------------------------------------------
		barra_Menu.getMenus().add(rel_venda);
		barra_Menu.getMenus().add(rel_estoque);

		// -----------------------------------------------------------
		box1.getChildren().add(barra_Menu);

		// -----------------------------------------------------------
		borderPane1.setCenter(box1);
		borderPane1.setRight(btnVoltar);
	}
	
	
	private void inserirEventos() 
	{
		item_listarVenda.addEventHandler(ActionEvent.ANY, this);
		item_listarEstoque.addEventHandler(ActionEvent.ANY, this);
		item_listarVencidos.addEventHandler(ActionEvent.ANY, this);
		btnVoltar.addEventHandler(ActionEvent.ANY, this);
	}
	
	
	public void definirColunasVenda() 
	{
		TableColumn<ProdutoVendido, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getIdProduto()));
		colunaID.setPrefWidth(50);

		TableColumn<ProdutoVendido, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(200);

		TableColumn<ProdutoVendido, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(50);
		
		
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(80);
		
		TableColumn<LoteProduto, Number> colunaTotal = new TableColumn<>("Valor Total");
		colunaTotal.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper
		(itemData.getValue().getQuantidade()*itemData.getValue().getValorTortal()));
		colunaTotal.setPrefWidth(80);
					
		tblVenda.getColumns().addAll(colunaID, colunaNome, colunaQuantidade);
		tblVenda.setItems(cpVendido.getListaProd());
	}

	
	public void definirColunasEstoqueQuantidade() 
	{
		TableColumn<LoteProduto, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()));
		colunaID.setPrefWidth(50);
		
		TableColumn<LoteProduto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(200);
		
		TableColumn<LoteProduto, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(90);
		
		/*
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(80);
		*/
		
		tblQuantidade.getColumns().addAll(colunaID, colunaNome, colunaQuantidade);

		tblQuantidade.setItems(cLote.getListItem());
	}
	
	
	public void definirColunasEstoqueVencido() 
	{
		/*
		TableColumn<LoteProduto, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()));
		colunaID.setPrefWidth(50);
	   */
		
		TableColumn<LoteProduto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(200);
	
		TableColumn<LoteProduto, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(90);
	
		tblVencido.getColumns().addAll(colunaNome, colunaQuantidade);

		tblQuantidade.setItems(cLote.getListItem());
	}
}
