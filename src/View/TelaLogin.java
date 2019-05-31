package View;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaLogin extends Application implements EventHandler<ActionEvent> {

	static Stage stageAux;
	GridPane pane;
	Label lblLogin;
	Label lblSenha;
	TextField txtLogin;
	TextField txtSenha;
	Button btnEntrar;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;
		
		// Elementos de Estrutura
		pane = new GridPane();

		// Elementos da tela
		lblLogin = new Label(" LOGIN ");
		lblSenha = new Label(" SENHA ");
		txtLogin = new TextField("");
		txtSenha = new TextField("");
		btnEntrar = new Button(" ENTRAR ");

		// -----------------------------------------------------------
		// Inserindo elementos
		pane.add(lblLogin, 0, 0);
		pane.add(lblSenha, 0, 1);
		pane.add(txtLogin, 1, 0);
		pane.add(txtSenha, 1, 1);
		pane.add(btnEntrar, 1, 2);
		// -----------------------------------------------------------
		adicionarMargem();
		adicionarEstilo();

		pane.addEventFilter(ActionEvent.ACTION, this);
		Scene scn = new Scene(pane, 1000, 563);
		stage.setTitle(" TELA DE CADASTRO DE FUNCIONARIO  ");
		stage.setScene(scn);
		stage.show();
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
			if (txtLogin.getText().equals("adm") && (txtSenha.getText().equals("123"))) {
				TelaVenda tvenda = new TelaVenda();
				try {
					tvenda.start(stageAux);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Senha ou Login Inválido!!!!!!!!!");
			}
		}
	}

	void limparCampos() {
		txtSenha.setText("");
		txtLogin.setText("");
	}


	// Estilos dos Objetos ------------------------------------------
	private void adicionarMargem() {
		Insets marginTop = new Insets(50, 30, 0, 0);
		Insets margin1 = new Insets(50, 30, 0, 60);
		pane.setMargin(lblLogin, marginTop);
		pane.setMargin(lblSenha, marginTop);
		pane.setMargin(txtLogin, marginTop);
		pane.setMargin(txtSenha, marginTop);
		pane.setMargin(btnEntrar, marginTop);
	}

	public void adicionarEstilo() {
		lblLogin.setFont(new Font(15));
		lblSenha.setFont(new Font(15));
		lblLogin.setStyle("-fx-font-weight: bold");
		lblSenha.setStyle("-fx-font-weight: bold");
	}

}
