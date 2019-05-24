package View;



import java.text.ParseException;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.rowset.internal.InsertRow;

import Control.ControleProduto;
import Model.Produto;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaProduto extends Application implements EventHandler<ActionEvent>{
	
	BorderPane painelPrincipal = new BorderPane();
	GridPane painelLbl = new GridPane();
	GridPane painelManipulavel = new GridPane();
	FlowPane painelBtn = new FlowPane();
	Scene scn = new Scene(painelPrincipal, 800, 900);
	TilePane topo = new TilePane();
	Text textoCabecalho = new Text("Gerenciamento de produto");
	
	Label lblId = new Label("ID : ");
	Label lblNome = new Label("NOME : ");
	Label lblDescricao = new Label("DESCRICAO : ");
	Label lblMax = new Label("Quantidade Maxima");
	Label lblMin = new Label("Quantidade Minima");
	Label lblTempoVida = new Label("Tempo de Vida");
	
	
	TextField tfId = new TextField();
	TextField tfNome = new TextField();
	TextArea tfDescricao = new TextArea();
	TextArea tfMax = new TextArea();
	TextArea tfMin = new TextArea();
	TextArea tfTempoVida = new TextArea();
	
    Button btnPesquisar = new Button("PESQUISAR");
    Button btnSalvar = new Button("SALVAR");
    Button btnExcluir = new Button("EXCLUIR");
	
	ControleProduto cp = new ControleProduto();

	@Override
	public void start(Stage stage) throws Exception {
		
		adicionandoEstiloElementos();
		adicionandoFilhosPainel();
		adicionandoMarginsPainel();
		adicionarEventos();
		
		
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
		
		painelPrincipal.setMargin(topo, marginTop);
		painelPrincipal.setMargin(painelBtn, marginBot);
		
		painelLbl.setMargin(lblId, margin);
		painelLbl.setMargin(lblNome, margin);
		painelLbl.setMargin(lblDescricao, margin);
		painelLbl.setMargin(lblMax, margin);
		painelLbl.setMargin(lblMin, margin);
		painelLbl.setMargin(lblTempoVida, margin);
		
		painelManipulavel.setMargin(tfId, margin);
		painelManipulavel.setMargin(tfNome, margin);
		painelManipulavel.setMargin(tfDescricao, margin);
		painelManipulavel.setMargin(tfMax, margin);
		painelManipulavel.setMargin(tfMin, margin);
		painelManipulavel.setMargin(tfTempoVida, margin);
		
		painelBtn.setMargin(btnPesquisar, margin);
		painelBtn.setMargin(btnSalvar, margin);
		painelBtn.setMargin(btnExcluir, margin);
	}

	public void adicionandoFilhosPainel() {
		topo.getChildren().add(textoCabecalho);
		
		painelPrincipal.setTop(topo);
		painelPrincipal.setLeft(painelLbl);
		painelPrincipal.setCenter(painelManipulavel);
		painelPrincipal.setBottom(painelBtn);
		
		
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
}
