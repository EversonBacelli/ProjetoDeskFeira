package Control;

import javax.swing.JOptionPane;

import Model.Funcionario;
import Model.Produto;
import Model.TipoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class ControleDeFuncionario {
	


	private ObservableList<Funcionario> listaFunc = FXCollections.observableArrayList();
	private ObservableList<Funcionario> listaFunc2 = FXCollections.observableArrayList();
	private ObservableList<TipoUsuario> listaTipo = FXCollections.observableArrayList(TipoUsuario.values());
	
	
	public void inserirFuncionario(Funcionario f) {
		this.listaFunc.add(f);
		JOptionPane.showMessageDialog(null,  "Funcionario adicionado com sucesso");
	}
	
	public Funcionario pesquisarFuncionarioNome(Funcionario func) {
		Funcionario retornado = new Funcionario();
		for(Funcionario f : listaFunc) {
			if(f.getNome().equals(func.getNome())) {
				JOptionPane.showMessageDialog(null,  "Funcionario Encontrado");
				return f;
			}
		}
		if(retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null,"Funcionario N�O ENCONTRADO");
		}
		return retornado;
	}
	
	public void alterarFuncionario(Funcionario func) {
		for(Funcionario f : listaFunc) {
			if(f.getNome().equals(func.getNome())) {
				listaFunc.remove(f);
				listaFunc.add(func);
				JOptionPane.showMessageDialog(null,  "Funcionario alterado com sucesso");
			}
		}
	}
	
	public void removerFuncionario(Funcionario func) {
		for(Funcionario f : listaFunc) {
			if(f.getNome().equals(func.getNome())) {
				listaFunc.remove(f);
				JOptionPane.showMessageDialog(null,  "Funcionario removido com sucesso");
				break;
			}
		}
	}
	

	
	public boolean funcionarioExiste(Funcionario func) {
		boolean existe = false;
		for(Funcionario f : listaFunc) {
			if(f.getNome().equals(func.getNome())) {
				existe = true;
			}
		}
		return existe;
	}

	public Funcionario pesquisarFuncionarioId(Funcionario func) {
		Funcionario retornado = new Funcionario();
		for(Funcionario f : listaFunc) {
			if(f.getId() == func.getId()) {
				JOptionPane.showMessageDialog(null,  "Funcionario Encontrado");
				return f;
			}
		}
		if(retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null,"FUNCIONARIO N�O ENCONTRADO");
		}
		return retornado;
	}
	
	public void preencheLista2() {
		if(this.listaFunc2.isEmpty()) {
			this.listaFunc2.setAll(getListaFunc());
		}
	}
	
	public void pesquisarFuncionario(String value) {
		this.listaFunc2.clear();
		for(Funcionario f : this.listaFunc) {
			if(f.getNome().contains(value)) {
				this.listaFunc2.add(f);
			}
		}
	}
	
	public ObservableList<Funcionario> getListaFunc() {
		return listaFunc;
	}
	public void setListaFunc(ObservableList<Funcionario> listaFunc) {
		this.listaFunc = listaFunc;
	}
	public ObservableList<TipoUsuario> getListaTipo() {
		return listaTipo;
	}
	public void setListaTipo(ObservableList<TipoUsuario> listaTipo) {
		this.listaTipo = listaTipo;
	}
	public ObservableList<Funcionario> getListaFunc2() {
		return listaFunc2;
	}

	public void setListaFunc2(ObservableList<Funcionario> listaFunc2) {
		this.listaFunc2 = listaFunc2;
	}
}
