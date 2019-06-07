package View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import Control.ControleDeLoteProduto;
import Control.ControleEstoqueResumo;
import Control.ControleProduto;
import Model.EstoqueResumo;
import Model.Funcionario;
import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TelaEntradaLoteProduto extends Application implements EventHandler<ActionEvent>{
	
	// Instancia--------------------------------------------------------------
	private ControleProduto controlProd = new ControleProduto();
	private ControleDeLoteProduto ControleLote = new ControleDeLoteProduto(); 
	private Produto p;
	private LoteProduto loteProduto;
	private LoteProduto lote;
	ControleEstoqueResumo controleResumo = new ControleEstoqueResumo();
	private ImageView img = new ImageView(new Image("file:Images/entrad_lote_prod.png"));	
	
	// Elementos de Estrutura ------------------------------------------------
	GridPane pane = new GridPane();
	BorderPane panePrincipal = new BorderPane();
	GridPane topo = new GridPane();
	VBox paneDetalhes = new VBox();
	VBox paneResumo = new VBox();
	VBox paneTop = new VBox();
	VBox paneBot = new VBox();
	FlowPane paneLinhaTop = new FlowPane();
	FlowPane paneLinhaBot = new FlowPane();
	FlowPane masterDetalhe = new FlowPane();
	
	// Tabelas-----------------------------------------------
	private TableView<LoteProduto> table = new TableView<>();
	TableView<EstoqueResumo> table1 = new TableView<>();
	
	
	// Objetos da Tela ---------------------------------------
	Label lblNome = new Label("Nome do Produto");
	ComboBox <Produto>comboNome = new ComboBox<Produto>();
	//
	Label lblID = new Label("Numero de Identifica��o");
	TextField txtID = new TextField();
	//
	Label lblDescricao = new Label("Descricao Produto");
	TextArea txtDescricao = new TextArea();
	//
	Label lblQtdMax = new Label("Quantidade M�xima");
	TextField txtQtdMax = new TextField();
	//
	Label lblQtdMin = new Label("Quantidade Minima");
	TextField txtQtdMin = new TextField();
	//
	Label lblPreco = new Label("Preco");
	TextField txtPreco = new TextField();
	//
	Label lblTempoVida = new Label("Tempo de vida");
	TextField txtTempoVida = new TextField();
	//
	Label lblQtdDisponivel = new Label("Quantidade de Entrada");
	TextField txtQtdDisponivel = new TextField("");
	//
	Label lbldataEntrada = new Label("Data de Entrada");
	TextField txtdataEntrada = new TextField();
	//
	Label lblvalidade = new Label("Validade");
	TextField txtvalidade = new TextField();
	//
	Button cadastrar = new Button("Cadastrar");
	Button excluir   = new Button(" Excluir ");
	Button btnEstoqueResumo   = new Button("Resumo");
	Button btnEstoqueDetalhes = new Button("Detalhes"); 

	private Line linha1 = new Line();
	private Line linha2 = new Line();
	
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage tela) throws Exception {
		bloquear();
		inserirObjetosTela();
		editarTamanhoTXT();
		adicionandoProdutosTeste();
		marginPaine();
		adicionandoEstiloElementos();
		definirColunas();
		definirColunasResumo();
		responsividadeLista();
		
		controleResumo.calcularResumo(controlProd, ControleLote);
		
		
		ObservableList<Produto> listProduto = comboNome.getItems();	
		for(Produto x: controlProd.getListaProd()) 	{listProduto.add(x);}
		
		Scene scn = new Scene(panePrincipal, 1100, 563);
		
		pane.addEventFilter(ActionEvent.ANY, this);

		comboNome.addEventHandler(ActionEvent.ANY, this);
		
		table.addEventHandler(ActionEvent.ANY, this);
		
		btnEstoqueResumo.addEventHandler(ActionEvent.ANY, this);
		
		btnEstoqueDetalhes.addEventHandler(ActionEvent.ANY, this);
		
		tela.setTitle("TELA DE ENTRADA DE ESTOQUE");
		tela.setScene(scn);
		tela.show();
	}

	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == comboNome) 
		{
			if(comboNome.getValue() != null) 
			{
				p = comboNome.getValue();
				preencherDadosProdutoSelecionado();
			}
		}
		
		if(e.getTarget() == cadastrar) 
		{
				if(verificarCampos()) 
				{
					lote = new LoteProduto();
					lote.setProduto(comboNome.getValue());
					lote.setId(Integer.parseInt(txtID.getText())); 
					lote.setQuantidade(Integer.parseInt(txtQtdDisponivel.getText()));
					lote.setDataValidade(txtTempoVida.getText());
					lote.setDataEntrada(txtdataEntrada.getText());
					ControleLote.inserirLoteProduto(lote);
					limparCampos();
	      	    }
				controleResumo.limpar();
				controleResumo.calcularResumo(controlProd, ControleLote);
				table1.setItems(controleResumo.getListaResumo());
		}
		
		if(e.getTarget() == excluir) 
		{
			if(this.lote.equals(null) )
			{
				JOptionPane.showMessageDialog(null, "Selecione um lote");
			} else 
			{
				ControleLote.removerLoteProduto(lote);
			}
		}
		
		if(e.getTarget() == btnEstoqueResumo) 
		{
			panePrincipal.setRight(paneResumo);
			
		}
		
		if(e.getTarget() == btnEstoqueDetalhes) 
		{
			panePrincipal.setRight(paneDetalhes);
		}
	}	
	
	
	
	// --------------- METODOS  --------------------//
	
	public void telaParaLoteProduto() 
	{
		loteProduto.setProduto(p);
		loteProduto.setId(Integer.parseInt(txtID.getText()));
		loteProduto.setDataValidade(txtvalidade.getText());
		loteProduto.setDataEntrada(txtdataEntrada.getText());
		loteProduto.setQuantidade(Integer.parseInt(txtQtdDisponivel.getText()));
	}

	public void preencherDadosProdutoSelecionado() 
	{
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

	
	private void inserirObjetosTela() 
	{
		// Objetos do Topo ------------------
		topo.add(lblID             , 0, 0);
		topo.add(txtID             , 1, 0);
		topo.add(lblNome           ,2, 0 );
		topo.add(comboNome         , 3, 0);
		topo.add(lblDescricao      , 4, 0);
		topo.add(txtDescricao      , 5, 0);
		paneLinhaTop.getChildren().add(linha1);

		
		//Objetos da Direita ----------------
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
		pane.add(cadastrar         , 1, 9);
		pane.add(excluir           ,2 ,9);
		//---------------------------------
		
		masterDetalhe.getChildren().add(btnEstoqueResumo);
		masterDetalhe.getChildren().add(btnEstoqueDetalhes);
		
		// Obsjeto da Direita --------------
		paneDetalhes.getChildren().add(table);
		paneResumo.getChildren().add(table1);
		paneTop.getChildren().add(topo);
		paneTop.getChildren().add(paneLinhaTop);
		paneBot.getChildren().add(linha2);
		paneBot.getChildren().add(img);
		
		// inserir objetos na tela
		panePrincipal.setTop(paneTop);
		panePrincipal.setLeft(pane);
		panePrincipal.setCenter(masterDetalhe);
		panePrincipal.setRight(paneResumo);
		panePrincipal.setBottom(paneBot);
	}
	
	
	public void adicionandoEstiloElementos() 
	{
		panePrincipal.setStyle("-fx-background-color: #ADFF2F;");
		lblID.setFont(new Font(12));
		lblNome.setFont(new Font(12));
		lblDescricao.setFont(new Font(12));
		
		lblID.setStyle("-fx-font-weight: bold");
		lblNome.setStyle("-fx-font-weight: bold");
		lblDescricao.setStyle("-fx-font-weight: bold");
		
		txtID.setFont(new Font(12));
		txtDescricao.setFont(new Font(12));
		
		this.linha1.setStroke(Color.LIGHTSKYBLUE);
        this.linha1.setStrokeWidth(10.0f);
        this.linha1.setStartX(0);
		this.linha1.setEndX(900);
        
		this.linha2.setStroke(Color.LIGHTSKYBLUE);
        this.linha2.setStrokeWidth(10.0f);
        this.linha2.setStartX(0);
		this.linha2.setEndX(900);
	}	
	
	
	public void responsividadeLista() {
		this.table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LoteProduto>() {
			@Override
			public void changed(ObservableValue<? extends LoteProduto> arg0, LoteProduto arg1, LoteProduto arg2) {
				lote = arg2;
			}
		});
	}
	
	public void definirColunas() {
		
		TableColumn<LoteProduto, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getId()));
		colunaID.setPrefWidth(30);

		
		TableColumn<LoteProduto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProduto().getNome()));
		colunaNome.setPrefWidth(100);

		
		TableColumn<LoteProduto, Number> colunaQuantidade = new TableColumn<>("Qtd");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQuantidade()));
		colunaQuantidade.setPrefWidth(40);
		
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Pre�o Unit");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getProduto().getPreco()));
		colunaPreco.setPrefWidth(80);
		
		
		TableColumn<LoteProduto, Number> colunaTotal = new TableColumn<>("Valor Total");
		colunaTotal.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getProduto().getPreco()*
		itemData.getValue().getQuantidade()));
		colunaTotal.setPrefWidth(80);
		
		TableColumn<LoteProduto, String> colunaEntrada = new TableColumn<>("DataEntrada");
		colunaEntrada.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataEntrada()));
		colunaEntrada.setPrefWidth(80);
		
		TableColumn<LoteProduto, String> colunaValidade = new TableColumn<>("DataEntrada");
		colunaValidade.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataEntrada()));
		colunaValidade.setPrefWidth(80);
		
		
		table.getColumns().addAll(colunaID, colunaNome, colunaQuantidade,colunaPreco,colunaTotal, colunaValidade);

		table.setItems(ControleLote.getListItem());
	}
	
	public void definirColunasResumo() {
		
		TableColumn<EstoqueResumo, Number> colunaID = new TableColumn<>("ID");
		colunaID.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getP().getId()));
		colunaID.setPrefWidth(50);

		
		TableColumn<EstoqueResumo, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getP().getNome()));
		colunaNome.setPrefWidth(200);

		
		TableColumn<EstoqueResumo, Number> colunaQuantidade = new TableColumn<>("Quantidade");
		colunaQuantidade.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtd()));
		colunaQuantidade.setPrefWidth(90);
		
		/*
		TableColumn<LoteProduto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getValorTortal()));
		colunaPreco.setPrefWidth(80);
		*/
		
		table1.getColumns().addAll(colunaID, colunaNome, colunaQuantidade);

		table1.setItems(controleResumo.getListaResumo());
	}
	
	
	// ---------------------------------------------------------------
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
	// -----------------------------------------------------------------------
	
	private String getDateTime() 
	{ 
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
	
	@SuppressWarnings("static-access")
	private void marginPaine() {
		Insets marginPane = new Insets(0, 20, 20, 20);
		Insets margin = new Insets(20, 20, 30, 20);
		Insets marginRight = new Insets(0, 120, 40,20);
		
		topo.setMargin(lblNome, margin);
		topo.setMargin(comboNome, margin);
		topo.setMargin(lblID, margin);
		topo.setMargin(txtID, margin);
		topo.setMargin(lblDescricao, margin);
		topo.setMargin(txtDescricao, margin);
		paneLinhaTop.setMargin(linha1,margin);
		paneLinhaBot.setMargin(linha2, margin);
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
		paneDetalhes.setMargin(table, marginRight);
		paneResumo.setMargin(table1, marginRight);
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
		p2.setNome("Ma�a");
		p2.setDescricao("Fruta vermelha de apar�ncia pr�xima uma pequena esfera, de aproximadamente de 50 gramas");
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
		lote1.setDataValidade ("5");
		lote1.setDataEntrada  ("31/05/2019");
		ControleLote.inserirLoteProduto(lote1);
		
		LoteProduto lote2 = new LoteProduto();
		lote2.setProduto(p1);
		lote2.setId(2);
		lote2.setQuantidade(50);
		lote2.setDataEntrada("31/05/2019");
		lote2.setDataValidade("10");	
		ControleLote.inserirLoteProduto(lote2);
		
		LoteProduto lote3 = new LoteProduto();
		lote3.setDataEntrada("31/05/2019");
		lote3.setDataValidade("15");
		lote3.setId(3);
		lote3.setProduto(p2);
		lote3.setQuantidade(50);
		ControleLote.inserirLoteProduto(lote3);		
	}
	
	public void bloquear() 
	{
	txtTempoVida.setEditable(false);
	txtdataEntrada.setEditable(false);
	txtPreco.setEditable(false);
	txtQtdMin.setEditable(false);
	txtQtdMax.setEditable(false);
	txtDescricao.setEditable(false);
	txtID.setEditable(false);
	}
}
