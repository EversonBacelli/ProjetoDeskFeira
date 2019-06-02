package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaPrincipal extends Application implements EventHandler<ActionEvent> {

	static Stage stageAux;
	BorderPane borderPane1;
	GridPane gridPane1;
	VBox box1;
	MenuBar barra_Menu;
	Menu menu_Funcio;
	Menu menu_Produ;
	MenuItem item_Funcio_Cad;
	MenuItem item_Funcio_List;
	MenuItem item_Produ_Cad;
	MenuItem item_Produ_List;
	// MenuItem menuItem1;
	// MenuItem menuItem2;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;

		// Inicializar
		borderPane1 = new BorderPane();
		gridPane1 = new GridPane();
		box1 = new VBox();
		barra_Menu = new MenuBar();
		
		menu_Funcio = new Menu("Funcionário");
		menu_Produ = new Menu("Produto");
		
		item_Funcio_Cad = new MenuItem("Cadastrar");
		item_Funcio_List = new MenuItem("Listar");
		item_Produ_Cad = new MenuItem("Cadastrar");
		item_Produ_List = new MenuItem("Listar");

		// -----------------------------------------------------------
		menu_Funcio.getItems().add(item_Funcio_Cad);
		menu_Funcio.getItems().add(item_Funcio_List);
		menu_Produ.getItems().add(item_Produ_Cad);
		menu_Produ.getItems().add(item_Produ_List);

		// -----------------------------------------------------------
		barra_Menu.getMenus().add(menu_Funcio);
		barra_Menu.getMenus().add(menu_Produ);

		// -----------------------------------------------------------
		box1.getChildren().add(barra_Menu);

		// -----------------------------------------------------------
		borderPane1.setTop(box1);
		borderPane1.setCenter(gridPane1);
		
		// -----------------------------------------------------------
		item_Funcio_Cad.addEventHandler(ActionEvent.ANY, this);

		Scene scn = new Scene(borderPane1, 1000, 563);
		stage.setTitle(" TELA PRINCIPAL ");
		stage.setScene(scn);
		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getTarget() == item_Funcio_Cad) {
			Tela_Funcio_Cad tela_Funcio_Cad = new Tela_Funcio_Cad();
			try {
				tela_Funcio_Cad.start(stageAux);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void validarLogin() {
		// if (txtLogin.getText().equals("")) {
		// JOptionPane.showMessageDialog(null, "Insira o Login");
		// } else if (txtSenha.getText().equals("")) {
		// JOptionPane.showMessageDialog(null, "Insira a Senha");
		// } else {
		// if (txtLogin.getText().equals("adm") &&
		// (txtSenha.getText().equals("123"))) {
		// TelaVenda tvenda = new TelaVenda();
		// try {
		// tvenda.start(stageAux);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// } else {
		// JOptionPane.showMessageDialog(null, "Senha ou Login
		// Inválido!!!!!!!!!");
		// }
		// }
	}

}
