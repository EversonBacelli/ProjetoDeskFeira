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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaPrincipal extends Application implements EventHandler<ActionEvent> {

	private static Stage stageAux;
	private BorderPane borderPane1;
	private VBox box1;
	private MenuBar barra_Menu;
	private Menu menu_Funcio;
	private Menu menu_Produ_Lote;
	private Menu menu_Relatorio;
	private MenuItem item_Relatorio;
	private MenuItem item_Funcio_Cad;
	private MenuItem item_Funcio_List;
	private MenuItem item_Produ;
	private MenuItem item_Lote;
	
	// MenuItem menuItem1;
	// listar funcionario
	//

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageAux = stage;
		ImageView img = new ImageView(new Image("file:Images/MAIN.png"));
		
		inicializarObjetos();
		adicionandoElementos(img);
		adicionandoEventos();
		
		stage.setResizable(false);
		Scene scn = new Scene(borderPane1, 1000, 563);
		stage.setTitle(" TELA PRINCIPAL ");
		stage.setScene(scn);
		stage.show();
	}

	public void adicionandoEventos() {
		item_Funcio_Cad.addEventHandler(ActionEvent.ANY, this);
		item_Produ.addEventHandler(ActionEvent.ANY, this);
		item_Lote.addEventHandler(ActionEvent.ANY, this);
		item_Funcio_List.addEventHandler(ActionEvent.ANY, this);
		item_Relatorio.addEventHandler(ActionEvent.ANY, this);
	}

	public void adicionandoElementos(ImageView img) {
		menu_Funcio.getItems().add(item_Funcio_Cad);
		menu_Funcio.getItems().add(item_Funcio_List);
		menu_Produ_Lote.getItems().add(item_Produ);
		menu_Produ_Lote.getItems().add(item_Lote);
		menu_Relatorio.getItems().add(item_Relatorio);
		// -----------------------------------------------------------
		barra_Menu.getMenus().add(menu_Funcio);
		barra_Menu.getMenus().add(menu_Produ_Lote);
		barra_Menu.getMenus().add(menu_Relatorio);
		// -----------------------------------------------------------
		box1.getChildren().add(barra_Menu);

		// -----------------------------------------------------------
		borderPane1.setTop(box1);
		this.borderPane1.setStyle("-fx-background-color: #ffa323;");
		borderPane1.setCenter(img);
	}

	public void inicializarObjetos() {
		borderPane1 = new BorderPane();
		box1 = new VBox();
		barra_Menu = new MenuBar();
		
		
		menu_Funcio = new Menu("Funcionário");
		menu_Produ_Lote = new Menu(" Produto e Lote ");
		menu_Produ_Lote = new Menu(" Produto e Lote ");
		menu_Relatorio = new Menu (" Relatórios ");
		
		item_Funcio_Cad    = new MenuItem(" Cadastrar ");
		item_Funcio_List   = new MenuItem(" Listar ");
		item_Produ         = new MenuItem(" Produto ");
		item_Lote          = new MenuItem(" Lote ");
		item_Relatorio     = new MenuItem(" Relatório ");
	}
//
	@Override
	public void handle(ActionEvent e) {
		try {
			if (e.getTarget() == item_Funcio_Cad) {
				Tela_Funcionario_Cadastro tela_Funcio_Cad = new Tela_Funcionario_Cadastro();
				tela_Funcio_Cad.start(stageAux);
			}else if (e.getTarget() == item_Funcio_List) {
				TelaListaFuncionario telaListaFuncionario = new TelaListaFuncionario();
				telaListaFuncionario.start(stageAux);
			}else if (e.getTarget() == item_Produ) {
				TelaProduto telaProduto = new  TelaProduto();
				telaProduto.start(stageAux);
			}else if (e.getTarget() == item_Lote) {
				TelaEntradaLoteProduto entradaLoteProduto = new TelaEntradaLoteProduto();
				entradaLoteProduto.start(stageAux);
			} else if (e.getTarget() == item_Relatorio) {
				TelaRelatorio entradaRelatorio = new TelaRelatorio();
				entradaRelatorio.start(stageAux);
			} 
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
