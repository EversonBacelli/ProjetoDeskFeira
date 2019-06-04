package View;


import Control.ControleDeFuncionario;
import Model.Funcionario;
import Model.Produto;
import Model.TipoUsuario;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaAtualizacaoFuncionario extends Application implements EventHandler<ActionEvent>{
	private BorderPane painelPrincipal = new BorderPane();
	private VBox painelTop = new VBox();
	private VBox painelCenter = new VBox();
	private FlowPane painelButtons = new FlowPane();
	private FlowPane painelTop1 = new FlowPane();
	
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	
	private TextField tfPesquisarFunc = new TextField();
	
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnAlterar = new Button("Alterar");
	
	private ImageView img = new ImageView(new Image("file:Images/altfunc.png"));	
	
	private ControleDeFuncionario cf = new ControleDeFuncionario();
	
	private TableView<Funcionario>  tab = new TableView<Funcionario>();
	
	private Funcionario func;
	
	private static Stage stageAux;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;
		
		adicionandoElementos();
		adicionandoEstilos();
		adicionandoMargens();
		adicionarEventos();
		definirColunas();
		responsividadeLista();
		teste();
	
		
		stage.setScene(scn);
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == btnAlterar) {
			alterarFuncionario();
		}else if(e.getTarget() == btnExcluir) {
			excluirFuncionario();
		}else {
			
		}
	}
	
	public void adicionarEventos() {
		this.btnExcluir.addEventFilter(ActionEvent.ANY, this);
		this.btnAlterar.addEventFilter(ActionEvent.ANY, this);
	}
	
	public void adicionandoElementos() {
		this.painelPrincipal.setTop(this.painelTop);
		this.painelPrincipal.setCenter(this.painelCenter);
		
		this.painelTop.getChildren().add(img);
		this.painelTop.getChildren().add(this.painelTop1);
		
		this.painelCenter.getChildren().add(this.tab);
		this.painelCenter.getChildren().add(this.painelButtons);
		
		this.painelTop1.getChildren().add(this.tfPesquisarFunc);
		this.painelTop1.getChildren().add(this.btnPesquisar);
		
		this.painelButtons.getChildren().add(this.btnExcluir);
		this.painelButtons.getChildren().add(this.btnAlterar);
		
	}
	
	public void adicionandoEstilos() {
		this.tfPesquisarFunc.setFocusTraversable(false);
		
		this.tfPesquisarFunc.setPromptText("Pesquisar funcionario");
		
		this.tfPesquisarFunc.setPrefWidth(300);
		
		this.btnExcluir.setPrefWidth(300);
		this.btnAlterar.setPrefWidth(300);
	}
	
	public void adicionandoMargens() {
		this.painelPrincipal.setStyle("-fx-background-color: #FDA50F");
		
		Insets margin = new Insets(20, 20, 20, 20);
		Insets marginTopLeft = new Insets(0, 0, 0, 280);
		Insets marginBotLeft = new Insets(0, 0, 0, 200);

		
		this.painelTop1.setHgap(10);
		
		this.painelTop.setMargin(img, marginTopLeft);
		this.painelTop1.setMargin(this.tfPesquisarFunc, marginTopLeft);
		this.painelButtons.setMargin(this.btnExcluir, marginBotLeft);
	}
	
	public void excluirFuncionario() {
		if(func != null) {
			cf.removerFuncionario(func);
		}
		setarObjetoNull();
	}
	
	public void setarObjetoNull() {
		if(cf.getListaFunc().isEmpty()) {
			func = null;
		}
	}
	
	public void alterarFuncionario() {
		if(func != null) {
			TelaCadastroFuncionario tcf = new TelaCadastroFuncionario(func);
			try {
				tcf.start(stageAux);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void responsividadeLista() {
		this.tab.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Funcionario>() {
			@Override
			public void changed(ObservableValue<? extends Funcionario> arg0, Funcionario arg1, Funcionario arg2) {
				func = arg2;
			}
		});
	}

	
	public void definirColunas() {
		
		TableColumn<Funcionario, Number> colunaId = new TableColumn<>("Id");
		colunaId.setCellValueFactory(itemData -> new ReadOnlyLongWrapper(itemData.getValue().getId()));
		colunaId.setPrefWidth(100);

		TableColumn<Funcionario, String> colunaNome = new TableColumn<>("Nome");
		colunaNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
		colunaNome.setPrefWidth(250);
		
		TableColumn<Funcionario, String> colunaCpf = new TableColumn<>("Cpf");
		colunaCpf.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCpf()));
		colunaCpf.setPrefWidth(150);
		
		TableColumn<Funcionario, String> colunaRg = new TableColumn<>("Rg");
		colunaRg.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getRg()));
		colunaRg.setPrefWidth(150);

		TableColumn<Funcionario, String> colunaEmail = new TableColumn<>("Email");
		colunaEmail.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEmail()));
		colunaEmail.setPrefWidth(250);
		
		TableColumn<Funcionario, Number> colunaTipo = new TableColumn<>("Tipo");
		colunaTipo.setCellValueFactory(itemData -> new ReadOnlyIntegerWrapper(itemData.getValue().getTp().getValor()));
		colunaTipo.setPrefWidth(100);
		
		tab.getColumns().addAll(colunaId, colunaNome, colunaCpf, colunaRg, colunaEmail, colunaTipo);

		tab.setItems(cf.getListaFunc());
		
	}
	
	public void teste() {
		Funcionario f = new Funcionario();
		f.setId(1);
		f.setNome("Helio Pinto");
		f.setLogin("adm");
		f.setSenha("adm");
		f.setCpf("122321-3");
		f.setRg("342423");
		f.setEmail("heliopinto24@gmail.com");
		f.setTp(TipoUsuario.ADMINISTRADOR);
		cf.inserirFuncionario(f);
	}
	
//	Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
//    dialogoErro.setTitle("Diálogo de Error");
//    dialogoErro.setHeaderText("Esse é o cabeçalho...");
//    dialogoErro.setContentText("UM ERROR!!! UM ERRO ACONTECEU!!! GEZUIS!");
//    dialogoErro.showAndWait();
//    
//    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
//    dialogoAviso.setTitle("Diálogo de Aviso");
//    dialogoAviso.setHeaderText("Esse é o cabeçalho...");
//    dialogoAviso.setContentText("AVISO IMPORTANTE! TENHA EM MENTE ISSO!");
//    dialogoAviso.showAndWait();
//	
//    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
//    dialogoInfo.setTitle("Diálogo de informação");
//    dialogoInfo.setHeaderText("Esse é o cabeçalho...");
//    dialogoInfo.setContentText("Informação importante!");
//    dialogoInfo.showAndWait();
}
