package View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import Control.ControleDeLoteProduto;
import Control.ControleProduto;
import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaEntradaLoteProduto extends Application implements EventHandler<ActionEvent>{
	private ControleProduto controlProd = new ControleProduto();
	private ControleDeLoteProduto ControleLote = new ControleDeLoteProduto(); 
	private Produto p;
	private LoteProduto loteProduto;
	
	// Objetos que Fazem parte do Produto
	private Label lblID;
	
	private TextField txtID;
	//
	private Label lblNome;
	private ComboBox<Produto> comboNome;
	//
	private Label lblDescricao;
	private TextArea txtDescricao;
	//
	private TableView<LoteProduto> table = new TableView<>();
	// ----------------------------------
	// Objetos que fazem parte do Lote Produto
	private Label lblQtdMax;
	private TextField txtQtdMax;
	//
	private Label lblQtdMin;
	private TextField txtQtdMin;
	//
	private Label lblPreco;
	private TextField txtPreco;
	//
	private Label lblTempoVida;
	private TextField txtTempoVida;
	//
	private Label lblQtdDisponivel;
	private TextField txtQtdDisponivel;
	//
	private Label lbldataEntrada;
	private TextField txtdataEntrada;
	//
	private Label lblvalidade;
	private TextField txtvalidade; 
	//
	private Button cadastrar;
	//---------------------------
	
	// Estrutura
	private GridPane pane;
	private BorderPane panePrincipal;
	private GridPane topo;
	private VBox paneRight;
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage tela) throws Exception {

		iniciarObjetos();
		inserirObjetosTela();
		editarTamanhoTXT();
		adicionandoProdutosTeste();
		marginPaine();
		adicionandoEstiloElementos();
		definirColunas();
		responsividadeLista();
				
		ObservableList<Produto> data = comboNome.getItems();	
		for(Produto x: controlProd.getListaProd()) 	{data.add(x);}
		
		Scene scn = new Scene(panePrincipal, 1000, 563);
		
		pane.addEventFilter(ActionEvent.ANY, this);

		comboNome.addEventHandler(ActionEvent.ANY, this);
		
		tela.setTitle("TELA DE ENTRADA DE ESTOQUE");
		tela.setScene(scn);
		tela.show();
	}

	public void adicionandoProdutosTeste() {
		Produto p1= new Produto();
		p1.setId(1);
		p1.setNome("Banana");
		p1.setDescricao("Produto amarelo de aproximadamente de 30 gramas");
		p1.setQtdMax(12);
		p1.setQtdMin(2);
		p1.setQtdTempoVida(5);
		p1.setPreco(100);
		controlProd.inserirProduto(p1);
		Produto p2= new Produto();
		p2.setId(2);
		p2.setNome("Maça");
		p2.setDescricao("Fruta vermelha de aparência próxima uma pequena esfera, de aproximadamente de 50 gramas");
		p2.setQtdMax(12);
		p2.setQtdMin(2);
		p2.setQtdTempoVida(5);
		p2.setPreco(50);
		controlProd.inserirProduto(p2);
		Produto p3= new Produto();
		p3.setId(3);
		p3.setNome("Larajna");
		p3.setDescricao("Produto Laranja de 100 gramas");
		p3.setQtdMax(12);
		p3.setQtdMin(2);
		p3.setQtdTempoVida(5);
		p3.setPreco(400);
		controlProd.inserirProduto(p3);
		Produto p4= new Produto();
		p4.setId(4);
		p4.setNome("Abacaxi");
		p4.setDescricao("xxxxxxxxxxxxxxxxxxxxxxxx");
		p4.setQtdMax(12);
		p4.setQtdMin(2);
		p4.setQtdTempoVida(5);
		p4.setPreco(500);
		controlProd.inserirProduto(p4);
		
	
		LoteProduto lote1 = new LoteProduto();
		lote1.setProduto(p1);
		lote1.setId(1);
		lote1.setQuantidade(50);
		lote1.setDataValidade ("10/06/2019");
		lote1.setDataEntrada  ("31/05/2019");
		ControleLote.inserirLoteProduto(lote1);
		
		LoteProduto lote2 = new LoteProduto();
		lote2.setProduto(p1);
		lote2.setId(2);
		lote2.setQuantidade(50);
		lote2.setDataEntrada("31/05/2019");
		lote2.setDataValidade("10/06/2019");	
		ControleLote.inserirLoteProduto(lote2);
		
		LoteProduto lote3 = new LoteProduto();
		lote3.setDataEntrada("31/05/2019");
		lote3.setDataValidade("10/06/2019");
		lote3.setId(3);
		lote3.setProduto(p2);
		lote3.setQuantidade(50);
		ControleLote.inserirLoteProduto(lote3);
		
	}
	

	// --------------- METODOS  --------------------//

	private void marginPaine() {
		Insets marginPane = new Insets(0, 20, 20, 20);
		Insets margin = new Insets(20, 20, 60, 20);
		Insets marginRight = new Insets(0, 120, 40,20);
		
		topo.setMargin(lblNome, margin);
		topo.setMargin(comboNome, margin);
		topo.setMargin(lblID, margin);
		topo.setMargin(txtID, margin);
		topo.setMargin(lblDescricao, margin);
		topo.setMargin(txtDescricao, margin);
		pane.setMargin(lblQtdMax, marginPane);
		pane.setMargin(txtQtdMax, marginPane);
		pane.setMargin(lblQtdMin, marginPane);
		pane.setMargin(txtQtdMin, marginPane);
		pane.setMargin(lblPreco, marginPane);
		pane.setMargin(txtPreco, marginPane);
		pane.setMargin(lblTempoVida, marginPane);
		pane.setMargin(txtTempoVida, marginPane);
		pane.setMargin(lblQtdDisponivel, marginPane);
		pane.setMargin(txtQtdDisponivel, marginPane);
		pane.setMargin(lbldataEntrada, marginPane);
		pane.setMargin(txtdataEntrada, marginPane);
		pane.setMargin(lblvalidade, marginPane);
		pane.setMargin(txtvalidade, marginPane);
		paneRight.setMargin(table, marginRight);
	}
	
	public void telaParaLoteProduto() {
		loteProduto.setProduto(p);
		loteProduto.setId(Integer.parseInt(txtID.getText()));
		loteProduto.setDataValidade(txtvalidade.getText());
		loteProduto.setDataEntrada(txtdataEntrada.getText());
		loteProduto.setQuantidade(Integer.parseInt(txtQtdDisponivel.getText()));
	}

	public void preencherDadosProdutoSelecionado() {
		txtID.setText(Integer.toString(p.getId()));
		txtDescricao.setText(p.getDescricao());
		txtQtdMax.setText(Integer.toString(p.getQtdMax()));
		txtQtdMin.setText(Integer.toString(p.getQtdMin()));
		txtTempoVida.setText(Integer.toString(p.getQtdTempoVida()));
		txtPreco.setText(Double.toString(p.getPreco()));
		String data = getDateTime();
		txtdataEntrada.setText(data);
		txtvalidade.setText(getDataVencimento());
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
		
		//cadastrar.setStyle("-fx-font-weight: bold");
		//cadastrar.setFont(new Font(20));
	}
	
	private void inserirObjetosTela() 
	{
		// Objetos do Topo
		topo.add(lblID             , 0, 0);
		topo.add(txtID             , 1, 0);
		topo.add(lblNome           ,2, 0 );
		topo.add(comboNome         , 3, 0);
		topo.add(lblDescricao      , 4, 0);
		topo.add(txtDescricao      , 5, 0);
		//---------------------------------
		
		//Objetos da Direita
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
		//---------------------------------
		
		// Obsjeto da Direita
		paneRight.getChildren().add(table);
		
		// inserir objetos na tela
		panePrincipal.setTop(topo);
		panePrincipal.setLeft(pane);
		panePrincipal.setRight(paneRight);
	}
	
	
	void editarTamanhoTXT()
	{
		txtID.setMaxSize(50,100);
		comboNome.setMaxSize(400,30);
		txtDescricao.setMaxSize(200, 100);
		txtQtdMax.setMaxSize(50,40);
		txtQtdMin.setMaxSize(50,40);
		txtPreco.setMaxSize(70,40);
		txtTempoVida.setMaxSize(50,40);
		txtQtdDisponivel.setMaxSize(50,40);
		txtdataEntrada.setMaxSize(80,80);
		txtvalidade.setMaxSize(80,80);
	}
	
	private void iniciarObjetos() {
		//paineis
		pane = new GridPane();
		panePrincipal = new BorderPane();
		topo = new GridPane();
		paneRight = new VBox();
		
		// Objetos da Tela
		lblNome = new Label("Nome do Produto");
		comboNome = new ComboBox<Produto>();
		//
		lblID = new Label("Numero de Identificação");
		txtID = new TextField();
		txtID.setEditable(false);
		//
		lblDescricao = new Label("Descricao Produto");
		txtDescricao = new TextArea();
		txtDescricao.setEditable(false);
		//
		lblQtdMax = new Label("Quantidade Máxima");
		txtQtdMax = new TextField();
		txtQtdMax.setEditable(false);
		//
		lblQtdMin = new Label("Quantidade Minima");
		txtQtdMin = new TextField();
		txtQtdMin.setEditable(false);
		//
		lblPreco = new Label("Preco");
		txtPreco = new TextField();
		txtPreco.setEditable(false);
		//
		lblTempoVida = new Label("Tempo de vida");
		txtTempoVida = new TextField();
		txtTempoVida.setEditable(false);
		//
		lblQtdDisponivel = new Label("Quantidade de Entrada");
		txtQtdDisponivel = new TextField("");
		//
		lbldataEntrada = new Label("Data de Entrada");
		txtdataEntrada = new TextField();
		txtdataEntrada.setEditable(false);
		txtdataEntrada.setEditable(false);
		//
		lblvalidade = new Label("Validade");
		txtvalidade = new TextField();
		//
		cadastrar = new Button("Cadastrar");
		// ------------------------------------------------------
	}
	
	void preencherInformacaoProduto() 
	{
		txtID.appendText(Integer.toString(p.getId()));
		txtDescricao.appendText(p.getDescricao());
		txtQtdMax.appendText(Integer.toString(p.getQtdMax()));
		txtQtdMin.appendText(Integer.toString(p.getQtdMin()));
		txtTempoVida.appendText(Integer.toString(p.getQtdTempoVida()));
	}
	
	
	boolean verificarCampos() 
	{
		System.out.println("Passei aqui");
		if(txtID.getText().equals("") ) 
		{
			JOptionPane.showMessageDialog(null, "Preencher todos os campos");
			return false;
			
		} else
		{
			if(txtQtdDisponivel.getText().equals("")) 
			{
				JOptionPane.showMessageDialog(null, "Preencher todos os campos");
				return false;
			} else 
			{
				return true;
			}
		} 
	}
	
	void limparCampos() 
	{
		comboNome.getSelectionModel().clearSelection();
		txtID.setText("");
		txtDescricao.setText(""); 
		txtQtdMax.setText("");
	    txtQtdMin.setText("");
	    txtTempoVida.setText("");
	    txtPreco.setText("");
	    txtQtdDisponivel.setText("");
		txtdataEntrada.setText("");
		txtvalidade.setText("");
	}
	
	private String getDateTime() { 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		Date date = new Date();
		return dateFormat.format(date); 
	}
	
	private String getDataVencimento() 
	{
		GregorianCalendar vencimento = new GregorianCalendar();
		DateFormat dateFormatada = new SimpleDateFormat("dd/MM/yyyy"); 
		int inteiro = Integer.parseInt(txtTempoVida.getText());
		vencimento.add(Calendar.DAY_OF_MONTH, inteiro);
		String dataFutura = dateFormatada.format(vencimento.getTime());
		return dataFutura;
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == comboNome) {
			if(comboNome.getValue() != null) {
				p = comboNome.getValue();
				preencherDadosProdutoSelecionado();
			}
		}else 
			if(e.getTarget() == cadastrar) {
				if(verificarCampos()) {
					LoteProduto lote = new LoteProduto();
					lote.setProduto(comboNome.getValue());
					lote.setId(Integer.parseInt(txtID.getText())); 
					lote.setQuantidade(Integer.parseInt(txtQtdDisponivel.getText()));
					lote.setDataValidade(txtTempoVida.getText());
					lote.setDataEntrada(txtdataEntrada.getText());
					ControleLote.inserirLoteProduto(lote);
					limparCampos();
				}
			}
	}
	
	
	public void responsividadeLista() {
		this.table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LoteProduto>() {
			@Override
			public void changed(ObservableValue<? extends LoteProduto> arg0, LoteProduto arg1, LoteProduto arg2) {
				
			}
		});
	}
	
	public void definirColunas() {
		
		TableColumn<LoteProduto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(250);

		TableColumn<LoteProduto, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(90);
		
		/*
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(80);
		*/
		
		table.getColumns().addAll(colunaNome, colunaQuantidade);

		table.setItems(ControleLote.getListItem());
	}
	
}
