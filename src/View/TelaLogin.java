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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaLogin extends 	Application implements EventHandler<ActionEvent>{
	
	GridPane pane;
	Label lblLogin;
	TextField txtLogin;
	Label lblSenha;
	TextField txtSenha;
	Button btnEntrar;
	Button btnInsetirEstoque;
	Button btnRealizarVenda;
	Button btnConsultarRelatorio;
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void start(Stage telaCadastro) throws Exception 
	{
		// Elementos de Estrutura
		pane = new GridPane();
		//-------------------------------------
		
		// Elementos da tela
		lblLogin   =  new Label(" LOGIN ");
		txtLogin  =  new TextField("");
		
		lblSenha = new Label(" SENHA ");
		txtSenha = new TextField("");
		
		btnEntrar = new Button(" ENTRAR ");
		btnInsetirEstoque = new Button     ("  -- INSERIR ESTOQUE --");
		btnInsetirEstoque.setVisible(false);
		btnRealizarVenda = new Button      ("  --  INSERIR VENDA -- ");
		btnRealizarVenda.setVisible(false);
		btnConsultarRelatorio = new Button (" -CONSULTAR RELATÓRIO -");
		btnConsultarRelatorio.setVisible(false);
		// -----------------------------------------------------------
		
		// Inserindo elementos
		pane.add(lblLogin, 0, 0);
		pane.add(lblSenha, 0, 2);
		
		pane.add(txtLogin, 2, 0);
		pane.add(txtSenha, 2, 2);
		
		pane.add(btnEntrar, 2, 8);
		pane.add(btnInsetirEstoque, 4, 0);
		pane.add(btnRealizarVenda, 4, 2);
		pane.add(btnConsultarRelatorio, 4, 4);
		// -------------------------------------
		marginPaine();
		adicionandoEstiloElementos();

		pane.addEventFilter(ActionEvent.ACTION, this);
		Scene scn = new Scene(pane, 1000, 563);
		telaCadastro.setTitle("                                              TELA DE CADASTRO DE FUNCIONARIO  ");
		telaCadastro.setScene(scn);
		telaCadastro.show();	
	}
	
	
	@Override
	public void handle(ActionEvent arg0) {
		if(verificarCampos()) 
		{
			btnInsetirEstoque.setVisible(true);
			btnRealizarVenda.setVisible(true);
			btnConsultarRelatorio.setVisible(true);
			limparCampos();
			ocultarCampos();
		}
	}
	
	
	boolean verificarCampos() 
	{
		if(txtLogin.getText().equals("")) 
		{
			JOptionPane.showMessageDialog(null, "Insira o Login");
			return false;
		} else 
		{
			if(txtSenha.getText().equals("")) 
			{
				JOptionPane.showMessageDialog(null, "Insira a Senha");
				return false;
			} else {return true;}
		}
	}
	
	void limparCampos() 
	{
		txtSenha.setText("");
		txtLogin.setText("");
	}
	
	void ocultarCampos() 
	{
		lblLogin.setVisible(false);
		lblSenha.setVisible(false);
		txtLogin.setVisible(false);
		txtSenha.setVisible(false);
		btnEntrar.setVisible(false);
	}
	
	// Estilos dos Objetos  ------------------------------------------
	private void marginPaine() {
		Insets marginTop = new Insets(50, 30, 0, 0);
		Insets margin = new Insets(20, 20, 20, 40);
		Insets marginBot = new Insets(0, 0, 20,20);
		Insets margin1 = new Insets(50, 30, 0, 60);
		//
		pane.setMargin(lblLogin, margin);
		pane.setMargin(lblLogin, marginTop);
		//
		pane.setMargin(lblSenha, margin);
		pane.setMargin(lblSenha, marginTop);
		//
		pane.setMargin(txtLogin, margin);
		pane.setMargin(txtLogin, marginTop);
		//
		pane.setMargin(txtSenha, margin);
		pane.setMargin(txtSenha, marginTop);
		//
		pane.setMargin(btnEntrar, marginTop);
		//
		pane.setMargin(btnInsetirEstoque, margin);
		pane.setMargin(btnInsetirEstoque, margin1);
		//
		pane.setMargin(btnRealizarVenda, margin);
		pane.setMargin(btnRealizarVenda, margin1);
		//
		pane.setMargin(btnConsultarRelatorio, margin);
		pane.setMargin(btnConsultarRelatorio, margin1);
	}
	
	public void adicionandoEstiloElementos() {

		lblLogin.setFont(new Font(15));
		lblSenha.setFont(new Font(15));
		btnEntrar.setFont(new Font(20));
		btnInsetirEstoque.setFont(new Font(18));
		btnRealizarVenda.setFont(new Font(18));
		btnConsultarRelatorio.setFont(new Font(18));

		
		lblLogin.setStyle("-fx-font-weight: bold");
		lblSenha.setStyle("-fx-font-weight: bold");
		btnEntrar.setStyle("-fx-font-weight: bold");
	}



}
