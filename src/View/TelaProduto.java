package View;



import Control.ControleProduto;
import Model.Produto;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaProduto extends Application implements EventHandler<ActionEvent>{
	private static Stage stageAux;
	private TableView<Produto> table = new TableView<>();
	private ImageView img = new ImageView(new Image("file:Images/gerenciamento_prod.png"));
	private BorderPane painelPrincipal = new BorderPane();
	
	private VBox painelTopo = new VBox();
	private VBox painelLateral = new VBox();
	private VBox painelCentral = new VBox();
	private FlowPane elementosTopo = new FlowPane();
	
	private FlowPane painelLateral1 = new FlowPane();
	private FlowPane painelLateral2 = new FlowPane();
	private FlowPane painelLateral3 = new FlowPane();
	private FlowPane painelLateral4 = new FlowPane();
	private FlowPane painelLateral5 = new FlowPane();
	private FlowPane painelLateral6 = new FlowPane();
	private FlowPane painelLateral7 = new FlowPane();
	private FlowPane painelButtons = new FlowPane();
	private FlowPane painelPesquisarElementos = new FlowPane();
		
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	
	private Label lblId = new Label("ID");
	private Label lblNome = new Label("Nome");
	private Label lblDescricao = new Label("Descricao");
	private Label lblMax = new Label("Qtd Maxima");
	private Label lblMin = new Label("Qtd Minima");
	private Label lblTempoVida = new Label("Tempo de Vida");
	private Label lblPreco = new Label("Preco");	

	private TextField tfId = new TextField();
	private TextField tfNome = new TextField();
	private TextArea tfDescricao = new TextArea();
	private TextField tfMax = new TextField();
	private TextField tfMin = new TextField();
	private TextField tfTempoVida = new TextField();
	private TextField tfPreco = new TextField();	
	private TextField tfPesquisar = new TextField();
	
	private Button btnPesquisar = new Button("PESQUISAR");
	private Button btnSalvar = new Button("SALVAR");
	private Button btnExcluir = new Button("EXCLUIR");
	private Button btnVoltar = new Button(" VOLTAR ");
	
	private Line linha = new Line();
	private Line linha2 = new Line();
	
	ControleProduto cp = new ControleProduto();

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;
		adicionandoEstiloElementos();
		adicionandoFilhosPainel();
		adicionandoMarginsPainel();
		adicionarEventos();
		definirColunas();
		responsividadeLista();
		
		stage.setResizable(false);
		stage.setScene(scn);
		stage.setTitle("Gerenciamento de produto");
		stage.show();
	}
	

	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnPesquisar) {
			pesquisarProduto();
		}else if(e.getTarget() == btnSalvar) {
			Produto p = viewParaProduto();
			if(cp.produtoExiste(p)) {
				cp.alterarProduto(p);
			}else {
				cp.inserirProduto(p);
			}
			limparCampos();
		}else {
			Produto p = viewParaProduto();
			limparCampos();
			cp.removerProduto(p);
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

	
	public void adicionarEventos() 
	{
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnSalvar.addEventHandler(ActionEvent.ANY, this);
		btnExcluir.addEventHandler(ActionEvent.ANY, this);
		btnVoltar.addEventHandler(ActionEvent.ANY, this);
	}
	
	public void adicionandoFilhosPainel() {
		
		this.painelPrincipal.setTop(this.painelTopo);
		this.painelPrincipal.setLeft(this.painelLateral);
		this.painelPrincipal.setCenter(this.painelCentral);
		
		
		this.painelCentral.getChildren().add(this.painelPesquisarElementos);
		this.painelCentral.getChildren().add(linha);
		this.painelCentral.getChildren().add(table);
		
		this.painelLateral.getChildren().add(this.painelLateral1);
		this.painelLateral.getChildren().add(this.painelLateral2);
		this.painelLateral.getChildren().add(this.painelLateral3);
		this.painelLateral.getChildren().add(this.painelLateral4);
		this.painelLateral.getChildren().add(this.painelLateral5);
		this.painelLateral.getChildren().add(this.painelLateral6);
		this.painelLateral.getChildren().add(this.painelLateral7);
		this.painelLateral.getChildren().add(this.painelButtons);
		
		this.painelLateral1.getChildren().add(this.lblId);
		this.painelLateral1.getChildren().add(this.tfId);
		
		this.painelLateral2.getChildren().add(this.lblNome);
		this.painelLateral2.getChildren().add(this.tfNome);
		
		this.painelLateral3.getChildren().add(this.lblDescricao);
		this.painelLateral3.getChildren().add(this.tfDescricao);
		
		this.painelLateral4.getChildren().add(this.lblMax);
		this.painelLateral4.getChildren().add(this.tfMax);
		
		this.painelLateral5.getChildren().add(this.lblMin);
		this.painelLateral5.getChildren().add(this.tfMin);
		
		this.painelLateral6.getChildren().add(this.lblTempoVida);
		this.painelLateral6.getChildren().add(this.tfTempoVida);
		
		this.painelLateral7.getChildren().add(this.lblPreco);
		this.painelLateral7.getChildren().add(this.tfPreco);
		
		this.painelButtons.getChildren().add(btnExcluir);	
		this.painelButtons.getChildren().add(btnSalvar);
		
		this.painelPesquisarElementos.getChildren().add(this.tfPesquisar);
		this.painelPesquisarElementos.getChildren().add(this.btnPesquisar);
		
		this.painelTopo.getChildren().add(elementosTopo);
		this.elementosTopo.getChildren().add(img);
		this.elementosTopo.getChildren().add(btnVoltar);
		this.painelPrincipal.setTop(this.painelTopo);
	}
	
	public void pesquisarProduto() {
		Produto p = new Produto();
		try {
			int id = Integer.parseInt(tfPesquisar.getText());
			p.setId(id);
			p = cp.pesquisarProdutoId(p);
		} catch (NumberFormatException e) {
			p.setNome(tfPesquisar.getText());
			p = cp.pesquisarProdutoNome(p);
		}
		produtoParaView(p);
	}

	public void adicionandoEstiloElementos() {
		painelPrincipal.setStyle("-fx-background-color: #fd8b06;");
		//painelLateral.setStyle("-fx-background-color: #064490;");
		//
		
		lblId.setFont(new Font(20));
		lblNome.setFont(new Font(20));
		lblDescricao.setFont(new Font(20));
		lblMax.setFont(new Font(20));
		lblMin.setFont(new Font(20));
		lblTempoVida.setFont(new Font(20));
		lblPreco.setFont(new Font(20));

		
		lblId.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblNome.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblDescricao.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblMax.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblMin.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblTempoVida.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblPreco.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");

		tfPesquisar.setStyle("-fx-background-radius: 8");
		tfId.setStyle("-fx-background-radius: 4");
		tfNome.setStyle("-fx-background-radius: 4");
		tfMax.setStyle("-fx-background-radius: 4");
		tfMin.setStyle("-fx-background-radius: 4");
		tfTempoVida.setStyle("-fx-background-radius: 4");
		tfPreco.setStyle("-fx-background-radius: 4");
		
		this.btnSalvar.setStyle("-fx-background-color: #ffffff");
		this.btnExcluir.setStyle("-fx-background-color: #ffffff");
		this.btnPesquisar.setStyle("-fx-background-color: #ffffff");
		
		btnPesquisar.setFont(new Font(15));
		btnSalvar.setFont(new Font(15));
		btnExcluir.setFont(new Font(15));
		
		this.tfId.setPrefSize(230, 30);
		this.tfNome.setPrefSize(230, 30);
		this.tfDescricao.setPrefSize(230, 100);
		this.tfMax.setPrefSize(230, 30);
		this.tfMin.setPrefSize(230, 30);
		this.tfTempoVida.setPrefSize(230, 30);
		this.tfPreco.setPrefSize(230, 30);
		this.tfPesquisar.setPrefSize(230, 32);
		
		this.linha.setStroke(Color.WHITE);
        this.linha.setStrokeWidth(5.0f);
        
		this.linha2.setStroke(Color.WHITE);
        this.linha2.setStrokeWidth(5.0f);
        
		this.btnExcluir.setPrefWidth(145);
		this.btnSalvar.setPrefWidth(230);
		this.btnPesquisar.setPrefHeight(10);
		
		this.linha.setStartX(0);
		this.linha.setEndX(530);
		
		this.linha2.setStartX(0);
		this.linha2.setEndX(980);
		
		this.tfId.setEditable(false);
		this.tfId.setStyle("-fx-background-color: #e0e0d1;");
		
	}

	public void adicionandoMarginsPainel() {
//
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginRight = new Insets(0, 50, 0, 0);
		Insets marginLeft = new Insets(0, 0, 0, 15);
		Insets marginBottom = new Insets(0, 0, 20, 0);
		Insets marginTable = new Insets(40, 40, 40, 40);
		
        BorderPane.setMargin(this.painelLateral, margin);
		
		this.painelLateral1.setHgap(140);
		this.painelLateral2.setHgap(104);
		this.painelLateral3.setHgap(71);
		this.painelLateral4.setHgap(45);
		this.painelLateral5.setHgap(49);
		this.painelLateral6.setHgap(20);
		this.painelLateral7.setHgap(109);
		this.painelButtons.setHgap(19);
		this.painelPesquisarElementos.setHgap(5);
		
		
		this.painelLateral.setMargin(this.painelLateral1, marginBottom);
		this.painelLateral.setMargin(this.painelLateral2, marginBottom);
		this.painelLateral.setMargin(this.painelLateral3, marginBottom);
		this.painelLateral.setMargin(this.painelLateral4, marginBottom);
		this.painelLateral.setMargin(this.painelLateral5, marginBottom);
		this.painelLateral.setMargin(this.painelLateral6, marginBottom);
		this.painelLateral.setMargin(this.painelLateral7, marginBottom);

		this.painelCentral.setMargin(this.painelPesquisarElementos, marginBottom);
		this.painelCentral.setMargin(this.linha, marginBottom);
		
		this.painelPrincipal.setMargin(this.painelCentral, margin);
		
		this.painelCentral.setMargin(this.table,  marginBottom);
		
		this.painelTopo.setMargin(this.linha2, marginLeft);

	}

	
	public Produto viewParaProduto() {
		Produto p = new Produto();
		try {
			if(!tfId.getText().equals("")) {
				p.setId(Integer.parseInt(tfId.getText()));
				
			}
			if(!tfMax.getText().equals("")) {
				p.setQtdMax(Integer.parseInt(tfMax.getText()));
				
			}
			if(!tfMin.getText().equals("")) {
				p.setQtdMin(Integer.parseInt(tfMin.getText()));
				
			}
			if(!tfTempoVida.getText().equals("")) {
				
				p.setQtdTempoVida(Integer.parseInt(tfTempoVida.getText()));
				
			}
			if(!tfPreco.getText().equals("")) {
				
				p.setPreco(Double.parseDouble(tfPreco.getText()));
				
			}
			p.setNome(tfNome.getText());
			p.setDescricao(tfDescricao.getText());
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void produtoParaView(Produto p) {
		try {
			tfId.setText(Integer.toString(p.getId()));
			tfNome.setText(p.getNome());
			tfDescricao.setText(p.getDescricao());
			tfMax.setText(Integer.toString(p.getQtdMax()));
			tfMin.setText(Integer.toString(p.getQtdMin()));
			tfTempoVida.setText(Integer.toString(p.getQtdTempoVida()));
			tfPreco.setText(Double.toString(p.getPreco()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void limparCampos(){
		tfId.setText("");
		tfNome.setText("");
		tfDescricao.setText("");
		tfMax.setText("");
		tfMin.setText("");
		tfTempoVida.setText("");
		tfPreco.setText("");
	}
	


	public void responsividadeLista() {
		this.table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>() {
			@Override
			public void changed(ObservableValue<? extends Produto> arg0, Produto arg1, Produto arg2) {
				if (arg2 != null) {
				produtoParaView(arg2);					
				}
			}
		});
	}
	
	public void definirColunas() {

		TableColumn<Produto, Number> colunaId = new TableColumn<>("Id");
		colunaId.setCellValueFactory(itemData -> new ReadOnlyLongWrapper(itemData.getValue().getId()));
		colunaId.setPrefWidth(40);

		TableColumn<Produto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		colunaNome.setPrefWidth(55);
		
		TableColumn<Produto, String> colunaDescricao = new TableColumn<>("Descricao");
		colunaDescricao.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescricao()));
		colunaDescricao.setPrefWidth(110);

		TableColumn<Produto, Number> qtdMaxColuna = new TableColumn<>("Qtd Max");
		qtdMaxColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdMax()));
		qtdMaxColuna.setPrefWidth(75);
		
		TableColumn<Produto, Number> qtdMinColuna = new TableColumn<>("Qtd Min");
		qtdMinColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdMin()));
		qtdMinColuna.setPrefWidth(75);
		
		TableColumn<Produto, Number> tempoDeVidaColuna = new TableColumn<>("T. de Vida");
		tempoDeVidaColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdTempoVida()));
		tempoDeVidaColuna.setPrefWidth(100);
		
		
		TableColumn<Produto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getPreco()));
		colunaPreco.setPrefWidth(80);
		
		

		table.getColumns().addAll(colunaId, colunaNome, colunaDescricao, qtdMaxColuna, qtdMinColuna, tempoDeVidaColuna, colunaPreco);

		table.setItems(cp.getListaProdDAO());
		
	}
}
