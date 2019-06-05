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

public class TelaListaFuncionario extends Application implements EventHandler<ActionEvent> {
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
	private Button btnVoltar = new Button("Voltar");

	private ImageView img = new ImageView(new Image("file:Images/altfunc.png"));

	private ControleDeFuncionario cf = new ControleDeFuncionario();

	private TableView<Funcionario> tab = new TableView<Funcionario>();

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
		cf.preencheLista2();

		stage.setScene(scn);
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnAlterar) {
			alterarFuncionario();
		} else if (e.getTarget() == btnExcluir) {
			excluirFuncionario();
		} else if (e.getTarget() == btnVoltar) {
			TelaPrincipal telaPrincipal = new TelaPrincipal();
			try {
				telaPrincipal.start(stageAux);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			cf.pesquisarFuncionario(this.tfPesquisarFunc.getText());
		}
	}

	public void adicionarEventos() {
		this.btnExcluir.addEventFilter(ActionEvent.ANY, this);
		this.btnAlterar.addEventFilter(ActionEvent.ANY, this);
		this.btnVoltar.addEventFilter(ActionEvent.ANY, this);
		this.btnPesquisar.addEventHandler(ActionEvent.ANY, this);
		this.tfPesquisarFunc.addEventHandler(ActionEvent.ANY, this);
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
		this.painelButtons.getChildren().add(this.btnVoltar);

	}

	public void adicionandoEstilos() {
		this.tfPesquisarFunc.setFocusTraversable(false);

		this.tfPesquisarFunc.setPromptText("Pesquisar funcionario");

		this.tfPesquisarFunc.setPrefWidth(300);

		this.btnExcluir.setPrefWidth(300);
		this.btnAlterar.setPrefWidth(300);
		this.btnVoltar.setPrefWidth(100);
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
		if (func != null) {
			cf.removerFuncionario(func);
		}
		setarObjetoNull();
	}

	public void setarObjetoNull() {
		if (cf.getListaFunc().isEmpty()) {
			func = null;
		}
	}

	public void alterarFuncionario() {
		if (func != null) {
			Tela_Funcionario_Cadastro tcf = new Tela_Funcionario_Cadastro(func);
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

		tab.setItems(cf.getListaFunc2());

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

		Funcionario f2 = new Funcionario();
		f2.setId(2);
		f2.setNome("Jo�o Carlos");
		f2.setLogin("adm");
		f2.setSenha("adm");
		f2.setCpf("122321-3");
		f2.setRg("342423");
		f2.setEmail("heliopinto24@gmail.com");
		f2.setTp(TipoUsuario.ADMINISTRADOR);
		cf.inserirFuncionario(f2);

		Funcionario f3 = new Funcionario();
		f3.setId(3);
		f3.setNome("Jo�o Antonio");
		f3.setLogin("adm");
		f3.setSenha("adm");
		f3.setCpf("122321-3");
		f3.setRg("342423");
		f3.setEmail("heliopinto24@gmail.com");
		f3.setTp(TipoUsuario.ADMINISTRADOR);
		cf.inserirFuncionario(f3);

		Funcionario f4 = new Funcionario();
		f4.setId(2);
		f4.setNome("Lucas Carlos");
		f4.setLogin("adm");
		f4.setSenha("adm");
		f4.setCpf("122321-3");
		f4.setRg("342423");
		f4.setEmail("heliopinto24@gmail.com");
		f4.setTp(TipoUsuario.ADMINISTRADOR);
		cf.inserirFuncionario(f4);
	}

	// Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	// dialogoErro.setTitle("Di�logo de Error");
	// dialogoErro.setHeaderText("Esse � o cabe�alho...");
	// dialogoErro.setContentText("UM ERROR!!! UM ERRO ACONTECEU!!! GEZUIS!");
	// dialogoErro.showAndWait();
	//
	// Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
	// dialogoAviso.setTitle("Di�logo de Aviso");
	// dialogoAviso.setHeaderText("Esse � o cabe�alho...");
	// dialogoAviso.setContentText("AVISO IMPORTANTE! TENHA EM MENTE ISSO!");
	// dialogoAviso.showAndWait();
	//
	// Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	// dialogoInfo.setTitle("Di�logo de informa��o");
	// dialogoInfo.setHeaderText("Esse � o cabe�alho...");
	// dialogoInfo.setContentText("Informa��o importante!");
	// dialogoInfo.showAndWait();
}
