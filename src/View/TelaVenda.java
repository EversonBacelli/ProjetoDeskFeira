package View;


import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.LALOAD;

import Control.ControleDeLoteProduto;
import Control.ControleProduto;
import Control.ControleProdutoVendido;
import Control.ControleVenda;
import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import Model.Venda;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	private ControleProduto cProduto = new ControleProduto();
	private ControleVenda cVenda = new ControleVenda();
	private ObservableList<Produto> ProdutoVenda;
	
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
	private Button btnAdicionarProduto = new Button("Adicionar Produto");
	private Button btnRemoverProduto = new Button("Remover Produto");
	private Button btnRealizarVenda = new Button("Realizar Venda");
	private Button btnPesquisarProduto = new Button("?");
	
	private TextField tfIdVenda = new TextField();
	private TextField tfQtdVendida = new TextField();
	private TextField tfValorTotal = new TextField();
	private TextField tfDataVenda = new TextField();
	private TextField tfPesquisarProd = new TextField();
	
	private ProdutoVendido prodVend;

	@Override
	public void start(Stage stage) throws Exception {
		cProduto.getListaProd();
		ProdutoVenda = comboProd.getItems();
		
		adicionandoProdutosTeste(); 
		
		for(Produto x: cProduto.getListaProd()) 	{
			ProdutoVenda.add(x);
		}
		
		
		adicionarEventos();
		adicionandoElementosPaineis();
		mudandoEstilo();
		definirColunas();
		adicionandoMargins();
		adicionandoEstiloVenda();
		responsividadeLista();
		
		stage.setScene(scn);
		stage.setTitle("Venda");
		stage.show();
	}
	
	public void adicionandoEstiloVenda() {
		//this.tfValorTotal.setMaxSize(70, 70);
		this.comboProd.setPrefWidth(130);
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
		this.topoPainel3.add(this.lblQtdVendida, 2, 0);
		this.topoPainel3.add(this.tfQtdVendida, 3, 0);
		this.topoPainel3.add(this.paneButtons, 4, 0);
		this.topoPainel3.add(this.lblPesquisarProduto, 5, 0);
		this.topoPainel3.add(this.tfPesquisarProd, 6, 0);
		this.topoPainel3.add(this.btnPesquisarProduto, 7, 0);


		
		this.topoPainel4.add(this.btnAdicionarProduto, 1, 0);
		this.topoPainel4.add(this.btnRemoverProduto, 2, 0);

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
		Insets marginTable = new Insets(20, 80, 50, 100);
		Insets marginTop2 = new Insets(0, 0, 0, 55);
		Insets marginTop3 = new Insets(0, 20, 0, 55);
		Insets marginRight = new Insets(0, 15, 0, 0);

		
		this.painelPrincipal.setMargin(this.topoPainel, marginTop);
		this.painelPrincipal.setMargin(this.painelCentral, marginTable);
		
		this.topoPainel.setMargin(this.topoPainel2, marginTop2);
		this.topoPainel.setMargin(this.topoPainel3, marginTop3);
		this.topoPainel.setMargin(this.topoPainel4, marginTop3);
		
		this.topoPainel2.setMargin(this.lblIdVenda, marginRight);
		this.topoPainel2.setMargin(this.tfIdVenda, new Insets(0, 90, 0, 0));
		this.topoPainel2.setMargin(this.lblDataVenda, new Insets(0, 10, 0, 0));
		
		this.topoPainel3.setMargin(this.lblProduto, marginRight);
		this.topoPainel3.setMargin(this.comboProd, marginRight);
		this.topoPainel3.setMargin(this.paneButtons, marginRight);
		this.topoPainel3.setMargin(this.lblPesquisarProduto, marginRight);

		
	
		
		
		this.painelCentral.setMargin(this.painelBottom, new Insets(10, 0, 0, 0));
		
		this.painelBottom.setMargin(this.tfValorTotal, new Insets(0, 20, 0, 0));

	}
	
	public void responsividadeLista() {
		
		this.table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProdutoVendido>() {
			@Override
			public void changed(ObservableValue<? extends ProdutoVendido> arg0, ProdutoVendido arg1, ProdutoVendido arg2) {
				prodVend = arg2;
			}
		});
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

	
	public void adicionarEventos() {
		this.btnAdicionarProduto.addEventHandler(ActionEvent.ANY, this);
		this.btnRemoverProduto.addEventHandler(ActionEvent.ANY, this);
		this.btnAdicionarQtd.addEventHandler(ActionEvent.ANY, this);
		this.btnRemoverQtd.addEventHandler(ActionEvent.ANY, this);
		this.btnRealizarVenda.addEventHandler(ActionEvent.ANY, this);
	}
	
	public void incrementarQuantidade() {
		try {
			if(Integer.parseInt(this.tfQtdVendida.getText()) >= 0) {
				int valor = Integer.parseInt(this.tfQtdVendida.getText()) + 1;
				this.tfQtdVendida.setText(""+valor);
			}else {
				JOptionPane.showMessageDialog(null, "Você não pode vender quantidades negativas");
				this.tfQtdVendida.setText("0");
			}
		} catch (NumberFormatException e) {
			if(this.tfQtdVendida.getText().equals("")){
				this.tfQtdVendida.setText(""+1);
			}else {
				JOptionPane.showMessageDialog(null, "Você deve digitar um valor numerico");
				e.printStackTrace();
			}
		}
	}
	
	
	public void decrementarQuantidade() {
		
		try {
			if (Integer.parseInt(this.tfQtdVendida.getText()) > 0){
				int valor = Integer.parseInt(this.tfQtdVendida.getText()) - 1;
				this.tfQtdVendida.setText(""+valor);
			}else {
				JOptionPane.showMessageDialog(null, "Você não pode vender quantidades negativas");
				this.tfQtdVendida.setText("0");
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Você deve digitar um valor numerico");
			e.printStackTrace();
		} 
	
	}

	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnAdicionarProduto) {
			adicionarProduto();
			if(!this.tfQtdVendida.getText().equals("") && this.comboProd.getValue() != null) {
				this.tfValorTotal.setText(Double.toString(cpv.calcularValorTotal()));
			}
			System.out.println("passei aqui");
		}
		if(e.getTarget() == btnRemoverProduto) {
			removerProduto();
			this.tfValorTotal.setText(Double.toString(cpv.calcularValorTotal()));
		}
		if(e.getTarget() == btnRealizarVenda) {
			Venda v = telaParaVenda();
			this.cVenda.realizarVenda(v);
			
		}
		if(e.getTarget() == btnAdicionarQtd) {
			if(comboProd.getValue() != null) {
				incrementarQuantidade();
			}
		}
		if(e.getTarget() == btnRemoverQtd) {
			if(comboProd.getValue() != null) {
				decrementarQuantidade();
			}
		}
		if(e.getTarget() == btnPesquisarProduto) {
			pesquisarProduto();
		}
	}
	
	public void removerProduto() {
		if(this.prodVend != null) {
			cpv.remover(prodVend);
		}
	}
	
	public void adicionarProduto() {
		if(!this.tfQtdVendida.getText().equals("") && this.comboProd.getValue() != null) {
			ProdutoVendido pv = new ProdutoVendido();
			pv.setIdProduto(cpv.getProximoId());
			pv.setProduto(comboProd.getValue());
			pv.setQuantidade(Integer.parseInt(this.tfQtdVendida.getText()));
			cpv.adicionar(pv);
		}else {
			JOptionPane.showMessageDialog(null, "Você não pode adicionar um produto sem inserir sua quantidade");
		}
	}
	
	public Venda telaParaVenda() {
		Venda v = new Venda();
		if(cpv.getListaProd() != null) {
			v.setId(Integer.parseInt(this.tfIdVenda.getText()));
			v.setDataVenda(this.tfDataVenda.getText());
			v.setListaLoteProduto(cpv.getListaProd());
			v.setQtdVenda(Integer.parseInt(this.tfQtdVendida.getText()));
			v.setValorTotal(Double.parseDouble(this.tfValorTotal.getText()));
		}
		return v;
	}
	
	public void pesquisarProduto() {
		if(!this.tfPesquisarProd.getText().equals("")) {
			Produto p  = new Produto();
			p.setNome(this.tfPesquisarProd.getText());
			if(cProduto.produtoExiste(p)) {
				p = cProduto.pesquisarProdutoNome(p);
				
			}
		}
	}

	//public void remove
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void definirColunas() {
		
		TableColumn<ProdutoVendido, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(260);

		TableColumn<ProdutoVendido, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(260);
		
		TableColumn<ProdutoVendido, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(260);
		
		table.getColumns().addAll(colunaNome, colunaQuantidade, colunaPreco);

		table.setItems(cpv.getListaProd());
		
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
		cProduto.inserirProduto(p1);
	}

}
