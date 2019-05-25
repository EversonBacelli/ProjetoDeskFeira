package View;



import javax.swing.JOptionPane;

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
import javafx.scene.Node;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaProduto extends Application implements EventHandler<ActionEvent>{
	private TableView<Produto> table = new TableView<>();
	private ImageView img = new ImageView(new Image("file:Images/gerenciamento_prod.png"));		
	private BorderPane painelPrincipal = new BorderPane();
	private GridPane painelLbl = new GridPane();
	private GridPane painelLbl2 = new GridPane();
	private GridPane painelManipulavel = new GridPane();
	private FlowPane painelBtn = new FlowPane();
	private Scene scn = new Scene(painelPrincipal, 1600, 900);
	
	private Label lblId = new Label("ID : ");
	private Label lblNome = new Label("Nome : ");
	private Label lblDescricao = new Label("Descricao : ");
	private Label lblMax = new Label("Quantidade Maxima");
	private Label lblMin = new Label("Quantidade Minima");
	private Label lblTempoVida = new Label("Tempo de Vida");
	private Label lblPreco = new Label("Preco");	
	
	private TextField tfId = new TextField();
	private TextField tfNome = new TextField();
	private TextArea tfDescricao = new TextArea();
	private TextField tfMax = new TextField();
	private TextField tfMin = new TextField();
	private TextField tfTempoVida = new TextField();
	private TextField tfPreco = new TextField();	
	
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
				if (arg2 != null) {
				produtoParaView(arg2);					
				}
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
	
	public void adicionandoFilhosPainel() {

		
		painelPrincipal.setTop(img);
		painelPrincipal.setLeft(painelLbl);
		painelPrincipal.setCenter(painelManipulavel);
		painelPrincipal.setBottom(painelBtn);
		painelPrincipal.setRight(table);
		
		
		painelLbl.add(lblId, 0, 0);
		painelLbl.add(lblNome, 0, 1);
		painelLbl.add(lblDescricao, 0, 2);
		painelLbl.add(painelLbl2, 0, 5);
		
		painelLbl2.add(lblMax, 0, 0);
		painelLbl2.add(lblMin, 0, 1);
		painelLbl2.add(lblTempoVida, 0, 2);
		painelLbl2.add(lblPreco, 0, 3);
		
		
		painelManipulavel.add(tfId, 0, 0);
		painelManipulavel.add(tfNome, 0, 1);
		painelManipulavel.add(tfDescricao, 0, 2);
		painelManipulavel.add(tfMax, 0, 3);
		painelManipulavel.add(tfMin, 0, 4);
		painelManipulavel.add(tfTempoVida, 0, 5);
		painelManipulavel.add(tfPreco, 0, 6);
		
		painelBtn.getChildren().add(btnPesquisar);
		painelBtn.getChildren().add(btnSalvar);
		painelBtn.getChildren().add(btnExcluir);
	}

	public void adicionandoEstiloElementos() {
		painelPrincipal.setStyle("-fx-background-color: #9d95d0;");
		
		lblId.setFont(new Font(20));
		lblNome.setFont(new Font(20));
		lblDescricao.setFont(new Font(20));
		lblMax.setFont(new Font(20));
		lblMin.setFont(new Font(20));
		lblTempoVida.setFont(new Font(20));
		lblPreco.setFont(new Font(20));
		
		lblId.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		lblNome.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		lblDescricao.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		lblMax.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		lblMin.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		lblTempoVida.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		lblPreco.setStyle("-fx-font-weight: bold;-fx-text-fill: #000000;");
		
		btnPesquisar.setFont(new Font(20));
		btnSalvar.setFont(new Font(20));
		btnExcluir.setFont(new Font(20));
	}

	public void adicionandoMarginsPainel() {
		Insets marginTop = new Insets(20, 20, 20, 550);
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginBot = new Insets(0, 0, 150,200);
		Insets marginRight = new Insets(0, 50, 0, 100);
		Insets marginTiraDesc = new Insets(160, 0, 0, 0);
	
		
		this.painelPrincipal.setMargin(painelBtn, marginBot);
		this.painelPrincipal.setMargin(table, marginRight);

		
		this.painelLbl.setMargin(lblId, margin);
		this.painelLbl.setMargin(lblNome, margin);
		this.painelLbl.setMargin(lblDescricao, margin);
		this.painelLbl.setMargin(painelLbl2, marginTiraDesc);
		

		this.painelLbl2.setMargin(lblMax, margin);
		this.painelLbl2.setMargin(lblMin, margin);
		this.painelLbl2.setMargin(lblTempoVida, margin);
		this.painelLbl2.setMargin(lblPreco, margin);
		
		this.painelManipulavel.setMargin(tfId, margin);
		this.painelManipulavel.setMargin(tfNome, margin);
		this.painelManipulavel.setMargin(tfDescricao, margin);
		this.painelManipulavel.setMargin(tfMax, margin);
		this.painelManipulavel.setMargin(tfMin, margin);
		this.painelManipulavel.setMargin(tfTempoVida, margin);
		this.painelManipulavel.setMargin(tfPreco, margin);
		
		this.painelBtn.setMargin(btnPesquisar, margin);
		this.painelBtn.setMargin(btnSalvar, margin);
		this.painelBtn.setMargin(btnExcluir, margin);
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
			// TODO Auto-generated catch block
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
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnPesquisar) {
			Produto p = viewParaProduto();
			Produto p2 = new Produto();
			p2 = cp.pesquisarProdutoNome(p);
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
		colunaId.setPrefWidth(80);

		TableColumn<Produto, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		colunaNome.setPrefWidth(100);
		
		TableColumn<Produto, String> colunaDescricao = new TableColumn<>("Descricao");
		colunaDescricao.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDescricao()));
		colunaDescricao.setPrefWidth(170);

		TableColumn<Produto, Number> qtdMaxColuna = new TableColumn<>("Qtd maxima");
		qtdMaxColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdMax()));
		qtdMaxColuna.setPrefWidth(130);
		
		TableColumn<Produto, Number> qtdMinColuna = new TableColumn<>("Qtd minima");
		qtdMinColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdMin()));
		qtdMinColuna.setPrefWidth(130);
		
		TableColumn<Produto, Number> tempoDeVidaColuna = new TableColumn<>("Tempo de vida");
		tempoDeVidaColuna.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getQtdTempoVida()));
		tempoDeVidaColuna.setPrefWidth(130);
		
		
		TableColumn<Produto, Number> colunaPreco = new TableColumn<>("Preco");
		colunaPreco.setCellValueFactory(itemData -> new ReadOnlyDoubleWrapper(itemData.getValue().getPreco()));
		colunaPreco.setPrefWidth(100);
		
		

		table.getColumns().addAll(colunaId, colunaNome, colunaDescricao, qtdMaxColuna, qtdMinColuna, tempoDeVidaColuna, colunaPreco);

		table.setItems(cp.getListaProd());
		
	}
}
