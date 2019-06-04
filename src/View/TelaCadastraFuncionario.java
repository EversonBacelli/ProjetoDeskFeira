package View;

import javax.swing.JOptionPane;

import Control.ControleDeFuncionario;
import Model.Funcionario;
import Model.TipoUsuario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaCadastraFuncionario extends Application implements EventHandler<ActionEvent>{

	private Label lblId = new Label("Id");
	private Label lblNome = new Label("Nome");
	private Label lblLogin = new Label("Login");
	private Label lblSenha = new Label("Senha");
	private Label lblTipoUsuario = new Label("Tipo de Usuario");
	private Label lblCpf = new Label("CPF");
	private Label lblRg = new Label("RG");
	private Label lblEmail = new Label("Email");
	
	private TextField tfId = new TextField();
	private TextField tfNome = new TextField();
	private TextField tfLogin = new TextField();
	private PasswordField tfSenha = new PasswordField();
	private TextField tfCpf = new TextField();
	private TextField tfRg = new TextField();
	private TextField tfEmail = new TextField();
	
	private BorderPane painelPrincipal = new BorderPane();
	private VBox painelCentral = new VBox();
	private FlowPane painelCentral1 = new FlowPane();
	private FlowPane painelCentral2 = new FlowPane();
	private FlowPane painelCentral3 = new FlowPane();
	private FlowPane painelCentral4 = new FlowPane();
	private FlowPane painelCentral5 = new FlowPane();
	private FlowPane painelBotoes = new FlowPane();
	private VBox painelTopo = new VBox();
	
	private Button botaoSalvar = new Button("Salvar");

	private ImageView img = new ImageView(new Image("file:Images/cadfunc.png"));	
	
	private Scene scn = new Scene(painelPrincipal, 1000, 563);
	
	private ComboBox<TipoUsuario> comboTipoUsuario = new ComboBox<TipoUsuario>();
	
	ControleDeFuncionario cf = new ControleDeFuncionario();
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	public TelaCadastraFuncionario() {
		
	}
	
	public TelaCadastraFuncionario(Funcionario f, ControleDeFuncionario cf) {
		this.cf = cf;
		funcionarioParaTela(f);
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		adicionandoElementos();
		adicionandoMargens();
		adicionandoEstilos();
		adicionandoEventos();
		
		comboTipoUsuario.setItems(cf.getListaTipo());

		
		stage.setTitle("Cadastro de Funcionario");
		stage.setScene(scn);
		stage.show();
	}
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getTarget() == botaoSalvar) {
			Funcionario f = telaParaFuncionario();
			if(f.getId() != 0) {
				if(!cf.funcionarioExiste(f)) {
					cf.inserirFuncionario(f);
				}else {
					cf.alterarFuncionario(f);
				}
				limparCampos();
			}else {
				System.out.println("não foi possivel adicionar esse funcionario");
			}
		}
	}

	
	public void adicionandoElementos() {
		this.painelPrincipal.setCenter(this.painelCentral);
		this.painelPrincipal.setTop(this.painelTopo);
		
		this.painelCentral.getChildren().add(this.painelCentral1);
		this.painelCentral.getChildren().add(this.painelCentral2);
		this.painelCentral.getChildren().add(this.painelCentral3);
		this.painelCentral.getChildren().add(this.painelCentral4);
		this.painelCentral.getChildren().add(this.painelCentral5);
		this.painelCentral.getChildren().add(this.painelBotoes);

		
		this.painelCentral1.getChildren().add(this.lblId);
		this.painelCentral1.getChildren().add(this.tfId);
		
		this.painelCentral1.getChildren().add(this.lblNome);
		this.painelCentral1.getChildren().add(this.tfNome);
		
		this.painelCentral2.getChildren().add(this.lblLogin);
		this.painelCentral2.getChildren().add(this.tfLogin);
		this.painelCentral2.getChildren().add(this.lblSenha);
		this.painelCentral2.getChildren().add(this.tfSenha);
		
		this.painelCentral3.getChildren().add(this.lblTipoUsuario);
		this.painelCentral3.getChildren().add(this.comboTipoUsuario);
		
		this.painelCentral4.getChildren().add(this.lblCpf);
		this.painelCentral4.getChildren().add(this.tfCpf);
		this.painelCentral4.getChildren().add(this.lblRg);
		this.painelCentral4.getChildren().add(this.tfRg);

		this.painelCentral5.getChildren().add(this.lblEmail);
		this.painelCentral5.getChildren().add(this.tfEmail);
		
		this.painelTopo.getChildren().add(img);
		this.painelBotoes.getChildren().add(this.botaoSalvar);
	}
	
	
	public void adicionandoMargens() {
		Insets marginPainelCentro = new Insets(20, 0, 0, 130);
		Insets marginBotaoSalvar = new Insets(0,0,0,260);
		this.painelCentral1.setHgap(20);
		this.painelCentral2.setHgap(20);
		this.painelCentral3.setHgap(20);
		this.painelCentral4.setHgap(20);
		this.painelCentral5.setHgap(20);
		
		this.painelBotoes.setMargin(this.botaoSalvar, marginBotaoSalvar);
		
		this.painelTopo.setMargin(img, new Insets(0, 0, 0, 80));
		this.painelPrincipal.setMargin(this.painelCentral, marginPainelCentro);
	}
	
	public void adicionandoEstilos() {
		this.painelPrincipal.setStyle("-fx-background-color: #96BD63;");
		
		this.lblId.setFont(new Font(30));
		this.lblNome.setFont(new Font(30));
		this.lblLogin.setFont(new Font(30));
		this.lblSenha.setFont(new Font(30));
		this.lblTipoUsuario.setFont(new Font(30));
		this.lblCpf.setFont(new Font(30));
		this.lblRg.setFont(new Font(30));
		this.lblEmail.setFont(new Font(30));
		
		this.tfId.setPrefWidth(50);
		this.tfNome.setPrefWidth(500);
		this.tfLogin.setPrefWidth(252);
		this.tfSenha.setPrefWidth(252);
		this.comboTipoUsuario.setPrefWidth(490);
		this.tfCpf.setPrefWidth(285);
		this.tfRg.setPrefWidth(285);
		this.tfEmail.setPrefWidth(630);
		
		this.botaoSalvar.setPrefHeight(40);
		this.botaoSalvar.setPrefWidth(200);
	}
	
	public void adicionandoEventos() {
		this.botaoSalvar.addEventHandler(ActionEvent.ANY, this);
	}
	
	public Funcionario telaParaFuncionario() {
		Funcionario f = new Funcionario();
		try {
			f.setId(Integer.parseInt(tfId.getText()));
			f.setNome(tfNome.getText());
			f.setLogin(tfLogin.getText());
			f.setSenha(tfSenha.getText());
			f.setTp(comboTipoUsuario.getValue());
			f.setCpf(tfCpf.getText());
			f.setRg(tfRg.getText());
			f.setEmail(tfEmail.getText());
		} catch (NumberFormatException e) {
			
		}
		
		return f;
	}
	
	public void funcionarioParaTela(Funcionario f) {
		this.tfId.setText(Integer.toString(f.getId()));
		this.tfNome.setText(f.getNome());
		this.tfLogin.setText(f.getLogin());
		this.tfSenha.setText(f.getSenha());
		this.tfEmail.setText(f.getEmail());
		this.comboTipoUsuario.getSelectionModel().select(f.getTp());
		this.tfCpf.setText(f.getCpf());
		this.tfRg.setText(f.getRg());
	}
	
	public void limparCampos() {
		this.tfId.clear();
		this.tfNome.clear();
		this.tfLogin.clear();
		this.tfSenha.clear();
		this.comboTipoUsuario.getSelectionModel().clearSelection();
		this.tfCpf.clear();
		this.tfRg.clear();
		this.tfEmail.clear();
	}
	
}
