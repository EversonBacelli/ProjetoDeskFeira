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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaProduto extends Application implements EventHandler<ActionEvent>{
	private TableView<Produto> table = new TableView<>();
	
	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelLbl = new GridPane();
	private GridPane painelManipulavel = new GridPane();
	private FlowPane painelBtn = new FlowPane();
	private Scene scn = new Scene(painelPrincipal, 1600, 900);
	private TilePane topo = new TilePane();
	private Text textoCabecalho = new Text("Gerenciamento de produto");
	
	private Label lblId = new Label("ID : ");
	private Label lblNome = new Label("Nome : ");
	private Label lblDescricao = new Label("Descricao : ");
	private Label lblMax = new Label("Quantidade Maxima");
	private Label lblMin = new Label("Quantidade Minima");
	private Label lblTempoVida = new Label("Tempo de Vida");
	
	
	private TextField tfId = new TextField();
	private TextField tfNome = new TextField();
	private TextArea tfDescricao = new TextArea();
	private TextArea tfMax = new TextArea();
	private TextArea tfMin = new TextArea();
	private TextArea tfTempoVida = new TextArea();
	
	private Button btnPesquisar = new Button("PESQUISAR");
	private Button btnSalvar = new Button("SALVAR");
	private Button btnExcluir = new Button("EXCLUIR");
	
	ControleProduto cp = new ControleProduto();

	@Override
	public void start(Stage stage) throws Exception {
		
		adicionandoEstiloElementos();
		adicionandoFilhosPainel();
		adicionandoMarginsPainel();
		adicionarEventos();
		definirColunas();
		this.table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Produto>() {
			@Override
			public void changed(ObservableValue<? extends Produto> arg0, Produto arg1, Produto arg2) {
				produtoParaView(arg2);
			}
		});
		
		stage.setResizable(false);
		stage.setScene(scn);
		stage.setTitle("Gerenciamento de produto");
		stage.show();
	}

	public void adicionarEventos() {
		btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		btnSalvar.addEventHandler(ActionEvent.ANY, this);
		btnExcluir.addEventHandler(ActionEvent.ANY, this);
	}

	public void adicionandoEstiloElementos() {
		textoCabecalho.setStyle("-fx-font-weight: bold");
		textoCabecalho.setFont(new Font(40));
		
		lblId.setFont(new Font(20));
		lblNome.setFont(new Font(20));
		lblDescricao.setFont(new Font(20));
		lblMax.setFont(new Font(20));
		lblMin.setFont(new Font(20));
		lblTempoVida.setFont(new Font(20));
		
		lblId.setStyle("-fx-font-weight: bold");
		lblNome.setStyle("-fx-font-weight: bold");
		lblDescricao.setStyle("-fx-font-weight: bold");
		lblMax.setStyle("-fx-font-weight: bold");
		lblMin.setStyle("-fx-font-weight: bold");
		lblTempoVida.setStyle("-fx-font-weight: bold");
		
		btnPesquisar.setFont(new Font(20));
		btnSalvar.setFont(new Font(20));
		btnExcluir.setFont(new Font(20));
	}

	public void adicionandoMarginsPainel() {
		Insets marginTop = new Insets(20, 20, 20, 150);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginBot = new Insets(0, 0, 250,300);
		Insets marginRight = new Insets(0, 250, 0, 0);
		
		this.painelPrincipal.setMargin(topo, marginTop);
		this.painelPrincipal.setMargin(painelBtn, marginBot);
		this.painelPrincipal.setMargin(table, marginRight);
		
		this.painelLbl.setMargin(lblId, margin);
		this.painelLbl.setMargin(lblNome, margin);
		this.painelLbl.setMargin(lblDescricao, margin);
		this.painelLbl.setMargin(lblMax, margin);
		this.painelLbl.setMargin(lblMin, margin);
		this.painelLbl.setMargin(lblTempoVida, margin);
		
		this.painelManipulavel.setMargin(tfId, margin);
		this.painelManipulavel.setMargin(tfNome, margin);
		this.painelManipulavel.setMargin(tfDescricao, margin);
		this.painelManipulavel.setMargin(tfMax, margin);
		this.painelManipulavel.setMargin(tfMin, margin);
		this.painelManipulavel.setMargin(tfTempoVida, margin);
		
		this.painelBtn.setMargin(btnPesquisar, margin);
		this.painelBtn.setMargin(btnSalvar, margin);
		this.painelBtn.setMargin(btnExcluir, margin);
	}

	public void adicionandoFilhosPainel() {
		topo.getChildren().add(textoCabecalho);
		
		painelPrincipal.setTop(topo);
		painelPrincipal.setLeft(painelLbl);
		painelPrincipal.setCenter(painelManipulavel);
		painelPrincipal.setBottom(painelBtn);
		painelPrincipal.setRight(table);
		
		
		painelLbl.add(lblId, 0, 0);
		painelLbl.add(lblNome, 0, 1);
		painelLbl.add(lblDescricao, 0, 2);
		painelLbl.add(lblMax, 0, 3);
		painelLbl.add(lblMin, 0, 4);
		painelLbl.add(lblTempoVida, 0, 5);
		
		
		painelManipulavel.add(tfId, 0, 0);
		painelManipulavel.add(tfNome, 0, 1);
		painelManipulavel.add(tfDescricao, 0, 2);
		painelManipulavel.add(tfMax, 0, 3);
		painelManipulavel.add(tfMin, 0, 4);
		painelManipulavel.add(tfTempoVida, 0, 5);
		
		painelBtn.getChildren().add(btnPesquisar);
		painelBtn.getChildren().add(btnSalvar);
		painelBtn.getChildren().add(btnExcluir);
	}
	
	public Produto viewParaProduto() {
		Produto p = new Produto();
		try {
			if(!tfId.getText().equals("")) {
				p.setId(Integer.parseInt(tfId.getText()));
			}
			p.setNome(tfNome.getText());
			p.setDescricao(tfDescricao.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public void produtoParaView(Produto p) {
		tfId.setText(Integer.toString(p.getId()));
		tfNome.setText(p.getNome());
		tfDescricao.setText(p.getDescricao());

	}

	public void limparCampos(){
		tfId.setText("");
		tfNome.setText("");
		tfDescricao.setText("");
		tfMax.setText("");
		tfMin.setText("");
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnPesquisar) {
			Produto p = viewParaProduto();
			Produto p2 = new Produto();
			p2 = cp.pesquisarProduto(p);
			produtoParaView(p2);
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
	}
	
	public void definirColunas() {

		
		TableColumn<Produto, Number> colunaId = new TableColumn<>("Id");
		colunaId.setCellValueFactory(itemData -> new ReadOnlyLongWrapper(itemData.getValue().getId()));
		colunaId.setPrefWidth(60);

		TableColumn<Produto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		colunaNome.setPrefWidth(70);
		
		TableColumn<Produto, String> colunaDescricao = new TableColumn<>("Descricao");
		colunaDescricao.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescricao()));
		colunaDescricao.setPrefWidth(70);

		TableColumn<Produto, Number> qtdMaxColuna = new TableColumn<>("Qtd maxima");
		qtdMaxColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdMax()));
		qtdMaxColuna.setPrefWidth(70);
		
		TableColumn<Produto, Number> qtdMinColuna = new TableColumn<>("Qtd minima");
		qtdMinColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdMin()));
		qtdMinColuna.setPrefWidth(100);
		
		TableColumn<Produto, Number> tempoDeVidaColuna = new TableColumn<>("Tempo de vida");
		tempoDeVidaColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdTempoVida()));
		tempoDeVidaColuna.setPrefWidth(100);
		

		table.getColumns().addAll(colunaId, colunaNome, colunaDescricao, qtdMaxColuna, qtdMinColuna, tempoDeVidaColuna);

		table.setItems(cp.getListaProd());
		
	}
}
