package View;

import Model.Produto;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaVenda extends Application implements EventHandler<ActionEvent>{

	
	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelLbl = new GridPane();
	private GridPane painelTf = new GridPane();
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	
	private ComboBox<Produto> comboProd = new ComboBox<Produto>();
	
	private Label lblIdVenda = new Label("Id venda");
	private Label lblQtdVendida = new Label("Quantidade Vendida");
	private Label lblValorTotal = new Label("Valor Total");
	private Label lblDataVenda = new Label("Data da Venda");
	private Label lblPesquisarProduto = new Label("Pesquisar Produto");
	
	private TextField tfIdVenda = new TextField();
	private TextField tfQtdVendida = new TextField();
	private TextField tfValorTotal = new TextField();
	private TextField tfDataVenda = new TextField();
	private TextField tfProduto = new TextField();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		adicionandoElementosPaineis();
		mudandoEstilo();
		stage.setScene(scn);
		stage.setTitle("Venda");
		stage.show();
	}
	
	public void adicionandoElementosPaineis() {
		this.painelPrincipal.setLeft(this.painelLbl);
		this.painelPrincipal.setCenter(this.painelTf);
		
		this.painelLbl.add(this.lblIdVenda, 0, 1);
		this.painelLbl.add(this.lblQtdVendida, 0, 2);
		this.painelLbl.add(this.lblValorTotal, 0, 3);
		this.painelLbl.add(this.lblDataVenda, 0, 4);
		this.painelLbl.add(this.lblPesquisarProduto, 0, 5);
		
		this.painelTf.add(this.tfIdVenda, 0, 1);
		this.painelTf.add(this.tfQtdVendida, 0, 2);
		this.painelTf.add(this.tfValorTotal, 0, 3);
		this.painelTf.add(this.tfDataVenda, 0, 4);
		this.painelTf.add(this.tfProduto, 0, 5);
		
	}
	
	public void adicionandoMargins() {
		Insets marginTop = new Insets(20, 20, 20, 550);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginBot = new Insets(0, 0, 150,200);
		Insets marginRight = new Insets(0, 50, 0, 100);
		Insets marginTiraDesc = new Insets(160, 0, 0, 0);
		
		this.painelPrincipal.setMargin(this.painelLbl, margin);
		this.painelPrincipal.setMargin(this.painelTf, margin);
	}
	
	public void mudandoEstilo() {
		this.lblIdVenda.setFont(new Font(20));
		this.lblQtdVendida.setFont(new Font(20));
		this.lblValorTotal.setFont(new Font(20));
		this.lblDataVenda.setFont(new Font(20));
		this.lblPesquisarProduto.setFont(new Font(20));
	}
	
	
	@Override
	public void handle(ActionEvent event) {
		
	}


	
	public static void main(String[] args) {
		Application.launch(args);
	}
	

	
	
}
