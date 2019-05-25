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
import javafx.application.Application;
import javafx.collections.ObservableList;
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
	private TilePane tpane;
	
	public static void main(String[] args) 
	{
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
		
		
		Produto p1= new Produto();
		p1.setId(1);
		p1.setNome("Helio Pinto");
		p1.setDescricao("Helio Pinto Pequeno");
		p1.setQtdMax(12);
		p1.setQtdMin(2);
		p1.setQtdTempoVida(5);
		p1.setPreco(100);
		controlProd.inserirProduto(p1);
		Produto p2= new Produto();
		p2.setId(2);
		p2.setNome("Helio Pinto2");
		p2.setDescricao("Cabra safado, usuário habitual do badoo e do tinder");
		p2.setQtdMax(12);
		p2.setQtdMin(2);
		p2.setQtdTempoVida(5);
		p2.setPreco(50);
		controlProd.inserirProduto(p2);
		Produto p3= new Produto();
		p3.setId(1);
		p3.setNome("Helio Pinto");
		p3.setDescricao("Cabra safado, usuário habitual do badoo e do tinder");
		p3.setQtdMax(12);
		p3.setQtdMin(2);
		p3.setQtdTempoVida(5);
		p3.setPreco(400);
		controlProd.inserirProduto(p3);
		Produto p4= new Produto();
		p4.setId(2);
		p4.setNome("Helio Pinto2");
		p4.setDescricao("Cabra safado, usuário habitual do badoo e do tinder");
		p4.setQtdMax(12);
		p4.setQtdMin(2);
		p4.setQtdTempoVida(5);
		p4.setPreco(500);
		controlProd.inserirProduto(p4);
		
		ObservableList<Produto> data = comboNome.getItems();
		
		for(Produto x: controlProd.getListaProd()) 	{data.add(x);}
		
		
		marginPaine();
		adicionandoEstiloElementos();
		
		Scene scn = new Scene(pane, 1000, 563);
		EventHandler<ActionEvent> manipulador = new ManipuladorMouse();
		pane.addEventFilter(ActionEvent.ANY, manipulador);

		comboNome.addEventHandler(ActionEvent.ANY, manipulador);
		
		tela.setTitle("TELA DE ENTRADA DE ESTOQUE");
		tela.setScene(scn);
		tela.show();
	}
	
	public class ManipuladorMouse implements EventHandler<ActionEvent> {
		boolean campos;
		
		@Override
		public void handle(ActionEvent e) 
		{
			if(e.getTarget() == comboNome) {
				if(comboNome.getValue() != null) {
					p = comboNome.getValue();
					preencherDadosProdutoSelecionado();
				}
			}else 
				if(e.getTarget() == cadastrar) {
					if(verificarCampos()) {
						ControleLote.inserirLoteProduto(loteProduto);
						limparCampos();
					}
				}
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

   }

	// --------------- METODOS  --------------------//

	private void marginPaine() {
		Insets marginTop = new Insets(20, 20, 20, 150);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginBot = new Insets(0, 0, 250,300);
		
		pane.setMargin(lblNome, margin);
		pane.setMargin(comboNome, margin);
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
		//pane.setMargin(cadastrar, margin);
		
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
	
	private void inserirObjetosTela(GridPane pane, TilePane tpane) 
	{
		pane.add(lblNome           , 0, 0);
		pane.add(comboNome           , 1, 0);
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
		comboNome.setMaxSize(400,30);
		txtDescricao.setMaxSize(200, 100);
		txtQtdMax.setMaxSize(50,40);
		txtQtdMin.setMaxSize(50,40);
		txtPreco.setMaxSize(50,40);
		txtTempoVida.setMaxSize(50,40);
		txtQtdDisponivel.setMaxSize(50,40);
		txtdataEntrada.setMaxSize(80,80);
		txtvalidade.setMaxSize(80,80);
	}
	
	private void iniciarObjetos() {
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
		txtID.setText("");
		comboNome.setPromptText("");
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
	
}
