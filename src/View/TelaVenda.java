package View;


import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaVenda extends Application implements EventHandler<ActionEvent>{
	private static Stage stageAux = new Stage();
	private int tipoUser;
	
	private VBox topoPainel = new VBox();
	private VBox painelCentral = new VBox();
	private TilePane painelBottom = new TilePane();
	private TableView<ProdutoVendido> table = new TableView<>();
	private BorderPane painelPrincipal = new BorderPane();
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	private ControleProdutoVendido cpv = new ControleProdutoVendido();
	private ComboBox<Produto> comboProd = new ComboBox<Produto>();
	private ImageView img = new ImageView(new Image("file:Images/venda.png"));
	
	private GridPane topoPainel2 = new GridPane();
	private GridPane topoPainel3 = new GridPane();
	private GridPane topoPainel4 = new GridPane();
	private GridPane paneButtons = new GridPane();
	

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
	private Button btnVoltar = new Button("Voltar");
	
	private TextField tfQtdVendida = new TextField();
	private TextField tfValorTotal = new TextField();
	private TextField tfDataVenda = new TextField();
	private TextField tfPesquisarProd = new TextField();
	
	private ControleProduto cProduto = new ControleProduto();
	private ControleVenda cVenda = new ControleVenda();
	private ObservableList<Produto> ProdutoVenda;
	private ProdutoVendido prodVend;

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;
		
		comboProd.getItems().setAll(cProduto.getListaProdDAO());
		
		adicionarEventos();
		adicionandoElementosPaineis();
		mudandoEstilo();
		definirColunas();
		adicionandoMargins();
		adicionandoEstiloVenda();
		responsividadeLista();
		tfDataVenda.setText(getDateTime());
		
		stage.setResizable(false);
		stage.setScene(scn);
		stage.setTitle("Venda");
		stage.show();
	}

	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnAdicionarProduto) {
			adicionarProduto();
			if(!this.tfQtdVendida.getText().equals("") && this.comboProd.getValue() != null) {
				this.tfValorTotal.setText(Double.toString(cpv.calcularValorTotal()));
			}
		}
		else if(e.getTarget() == btnRemoverProduto) {
			removerProduto();
			this.tfValorTotal.setText(Double.toString(cpv.calcularValorTotal()));
		}
		else if(e.getTarget() == btnAdicionarQtd) {
			if(comboProd.getValue() != null) {
				incrementarQuantidade();
			}
		}
		else if(e.getTarget() == btnRemoverQtd) {
			if(comboProd.getValue() != null) {
				decrementarQuantidade();
			}
		}
		else if(e.getTarget() == btnPesquisarProduto) {
			pesquisarProduto();
		}
		else if(e.getTarget() == btnRealizarVenda) {
			Venda v = telaParaVenda();
			this.cVenda.realizarVenda(v);
			limparCampos();
		}else if(e.getTarget() == btnVoltar) {
			voltarParaTelaPrincipal();
		}
	}
	
	public void adicionandoEstiloVenda() {
		//this.tfValorTotal.setMaxSize(70, 70);
		this.comboProd.setPrefWidth(130);
		this.tfQtdVendida.setPrefWidth(80);
		this.tfDataVenda.setEditable(false);
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
		
		this.topoPainel2.add(this.img,0,0);
		this.topoPainel2.add(this.btnVoltar, 1, 0);
		
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
		this.topoPainel4.add(this.lblDataVenda,3,0);
		this.topoPainel4.add(this.tfDataVenda, 4, 0);
		
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
		Insets marginRight = new Insets(0, 10, 10, 0);
		
		
		
		this.painelPrincipal.setMargin(this.topoPainel, marginTop);
		this.painelPrincipal.setMargin(this.painelCentral, marginTable);
		
		this.topoPainel.setMargin(this.topoPainel2, marginTop2);
		this.topoPainel.setMargin(this.topoPainel3, marginTop3);
		this.topoPainel.setMargin(this.topoPainel4, marginTop3);
		
		this.topoPainel3.setMargin(this.lblQtdVendida, marginRight);
		this.topoPainel3.setMargin(this.tfQtdVendida, marginRight);
		this.topoPainel3.setMargin(this.lblProduto, marginRight);
		this.topoPainel3.setMargin(this.comboProd, marginRight);
		this.topoPainel3.setMargin(this.paneButtons, marginRight);
		this.topoPainel3.setMargin(this.lblPesquisarProduto, marginRight);
		
		this.topoPainel2.setMargin(this.btnVoltar, new Insets(0, 0, 0, 532));
		
		this.topoPainel4.setMargin(this.btnRemoverProduto, new Insets(0, 30, 0, 0));
		this.topoPainel4.setMargin(this.lblDataVenda, new Insets(0, 10, 0, 0));
		this.topoPainel4.setMargin(this.tfPesquisarProd, new Insets(0, 5, 10, 0));
		this.topoPainel4.setMargin(this.btnPesquisarProduto, new Insets(0, 0, 10, 0));

		this.painelCentral.setMargin(this.painelBottom, new Insets(10, 0, 0, 0));
		this.painelBottom.setMargin(this.tfValorTotal, new Insets(0, 20, 0, 0));

	}

	
	public void mudandoEstilo() {
		this.painelPrincipal.setStyle("-fx-background-color: #b1ec93;");
		this.lblProduto.setFont(new Font(20));
		this.lblQtdVendida.setFont(new Font(20));
		this.lblValorTotal.setFont(new Font(20));
		this.lblDataVenda.setFont(new Font(20));
		this.lblPesquisarProduto.setFont(new Font(20));
		this.lblProdutoSelecionado.setFont(new Font(20));

		this.tfDataVenda.setStyle("-fx-background-color: #e0e0d1;");
		//this.btnAdicionar.setPrefWidth(20);
		this.btnRemoverQtd.setMaxWidth(30);

	}

	public void voltarParaTelaPrincipal() {
		TelaPrincipal telaPrincipal = new TelaPrincipal(tipoUser);
		try {
			telaPrincipal.start(stageAux);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionarEventos() {
		this.btnAdicionarProduto.addEventHandler(ActionEvent.ANY, this);
		this.btnRemoverProduto.addEventHandler(ActionEvent.ANY, this);
		this.btnAdicionarQtd.addEventHandler(ActionEvent.ANY, this);
		this.btnRemoverQtd.addEventHandler(ActionEvent.ANY, this);
		this.btnRealizarVenda.addEventHandler(ActionEvent.ANY, this);
		this.btnPesquisarProduto.addEventHandler(ActionEvent.ANY, this);
		this.btnVoltar.addEventHandler(ActionEvent.ANY, this);
	}
	
	public Venda telaParaVenda() {
		Venda v = new Venda();
		if(cpv.getListaProd() != null) {
			v.setDataVenda(this.tfDataVenda.getText());
			v.setListaProdutoVendido(cpv.getListaProd());
			v.setValorTotal(Double.parseDouble(this.tfValorTotal.getText()));
		}
		return v;
	}
	
	public void incrementarQuantidade() {
		try {
			if(Integer.parseInt(this.tfQtdVendida.getText()) >= 0) {
				int valor = Integer.parseInt(this.tfQtdVendida.getText()) + 1;
				this.tfQtdVendida.setText(""+valor);
			}
		} catch (NumberFormatException e) {
			if(this.tfQtdVendida.getText().equals("")){
				this.tfQtdVendida.setText(""+1);
			}else {
				JOptionPane.showMessageDialog(null, "Você deve digitar um valor numerico");
				//e.printStackTrace();
			}
		}
	}
	
	public void decrementarQuantidade() {
		
		try {
			if (Integer.parseInt(this.tfQtdVendida.getText()) > 0){
				int valor = Integer.parseInt(this.tfQtdVendida.getText()) - 1;
				this.tfQtdVendida.setText(""+valor);
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Você deve digitar um valor numerico");
			//e.printStackTrace();
		} 
	
	}
	
	public void removerProduto() {
		if(this.prodVend != null) {
			this.cpv.remover(prodVend);
		}
		setaObjetoComoNull();
	}
	
	public void setaObjetoComoNull() {
		if(this.cpv.getListaProd().isEmpty()) {
			this.prodVend = null;
		}
	}
	
	public void adicionarProduto() {
		if(!this.tfQtdVendida.getText().equals("") && this.comboProd.getValue() != null) {
			ProdutoVendido pv = new ProdutoVendido();
			pv.setProduto(comboProd.getValue());
			pv.setQuantidade(Integer.parseInt(this.tfQtdVendida.getText()));
			cpv.adicionar(pv);
		}else {
			JOptionPane.showMessageDialog(null, "Você não pode adicionar um produto sem inserir sua quantidade");
		}
	}
	
	
	public void limparCampos() {
		this.table.getItems().clear();
		this.tfDataVenda.setText(getDateTime());
		this.tfPesquisarProd.clear();
		this.tfQtdVendida.clear();
		this.tfValorTotal.clear();
	}

	public void pesquisarProduto() {
		if(!this.tfPesquisarProd.getText().equals("")) {
			Produto p  = new Produto();
			p.setNome(this.tfPesquisarProd.getText());
			if(cProduto.produtoExiste(p)) {
				p = cProduto.pesquisarProdutoNome(p);
				this.comboProd.getSelectionModel().select(p);
			}
		}
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
	
	
//	public static void adicionandoLimiteAoTexto(TextField tf, final int maxLength) {
//	    tf.textProperty().addListener(new ChangeListener<String>() {
//	        @Override
//	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
//	            if (tf.getText().length() > maxLength) {
//	                String cortaTexto = tf.getText().substring(0, maxLength);
//	                tf.setText(cortaTexto);
//	            }
//	        }
//	    });
//	}
	
	
//	public void mascaraTfData() {
//		this.tfDataVenda.lengthProperty().addListener(new ChangeListener<Number>() {
//	        @Override
//	        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//	            if (newValue.intValue() < 11) {
//	                String value = tfDataVenda.getText();
//	                value = value.replaceAll("[^0-9]", "");
//	                value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
//	                value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
//	                tfDataVenda.setText(value);
//	            }
//	        }
//	    });
//	}
	
	public void responsividadeLista() {
		this.table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProdutoVendido>() {
			@Override
			public void changed(ObservableValue<? extends ProdutoVendido> arg0, ProdutoVendido selecaoAntes, ProdutoVendido selecaoDepois) {
				prodVend = selecaoDepois;
			}
		});
	}
	
	private String getDateTime() 
	{ 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = new Date();
		return dateFormat.format(date); 
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
	
}
