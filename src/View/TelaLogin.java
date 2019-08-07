package View;

import javax.swing.JOptionPane;

import Control.ControleDeFuncionario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaLogin extends Application implements EventHandler<ActionEvent> {

	private static Stage stageAux;
	private BorderPane painelPrincipal;
	private GridPane pane;
	private Label lblLogin;
	private Label lblSenha;
	private TextField txtLogin;
	private PasswordField txtSenha;
	private Button btnEntrar;
	private int tipoUser;
	
	private ImageView img = new ImageView(new Image("file:Images/DESKFEIRA.png"));
	
	ControleDeFuncionario cFunc = new ControleDeFuncionario();

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;
		
		inicializandoObjetos();
		adicionandoElementos();
		adicionarMargem();
		adicionarEstilo();
		adicionandoEventos();
		
		Scene scn = new Scene(painelPrincipal, 1000, 563);
		
		//stage.setResizable(false);
		stage.setTitle("Login");
		stage.setScene(scn);
		stage.show();
	}

	public void adicionandoEventos() {
		pane.addEventFilter(ActionEvent.ACTION, this);
	}

	public void adicionandoElementos() {
		// Inserindo elementos
		painelPrincipal.setTop(pane);
		painelPrincipal.setCenter(img);
		
		pane.add(lblLogin, 0, 0);
		pane.add(lblSenha, 0, 1);
		pane.add(txtLogin, 1, 0);
		pane.add(txtSenha, 1, 1);
		pane.add(btnEntrar, 1, 2);

	}

	public void inicializandoObjetos() {
		// Elementos de Estrutura
		painelPrincipal = new BorderPane();
		pane = new GridPane();

		// Elementos da tela
		lblLogin = new Label(" LOGIN ");
		lblSenha = new Label(" SENHA ");
		txtLogin = new TextField();
		txtSenha = new PasswordField();
		btnEntrar = new Button(" ENTRAR ");
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == btnEntrar) {
			validarLogin();
		}
	}

	public void validarLogin() {
		if (txtLogin.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o Login");
		} else if (txtSenha.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira a Senha");
		} else {
			realizarLogin();
		}
	}

	void limparCampos() {
		txtSenha.setText("");
		txtLogin.setText("");
	}


	private void adicionarMargem() {
		Insets marginTop = new Insets(50, 0, 0, 0);
		Insets margin1 = new Insets(50, 30, 0, 60);
		pane.setMargin(lblLogin, marginTop);
		pane.setMargin(lblSenha, marginTop);
		pane.setMargin(txtLogin, marginTop);
		pane.setMargin(txtSenha, marginTop);
		pane.setMargin(btnEntrar, marginTop);
	}

	public void adicionarEstilo() {
		painelPrincipal.setStyle("-fx-background-color: #58b368;");
		lblLogin.setFont(new Font(20));
		lblSenha.setFont(new Font(20));
		lblLogin.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		lblSenha.setStyle("-fx-font-weight: bold;-fx-text-fill: #ffffff;");
		this.btnEntrar.setPrefWidth(200);
		this.btnEntrar.setStyle("-fx-background-color: #ffffff");
	}

	public void realizarLogin() 
	{
		tipoUser = cFunc.verificarLogin(txtLogin.getText(), txtSenha.getText());
		if(tipoUser != 0) {
			TelaPrincipal tPrincipal = new TelaPrincipal(tipoUser);
			try {
				tPrincipal.start(stageAux);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Login e/ou senha invalidos!!");
		}
	}
}
