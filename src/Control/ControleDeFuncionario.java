package Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Dao.DAOException;
import Dao.FuncionarioDAOImpl;
import Dao.FuncionarioDAO;
import Model.Funcionario;
import Model.TipoUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControleDeFuncionario {

	private ObservableList<Funcionario> listaFunc = FXCollections.observableArrayList();
	private ObservableList<Funcionario> listaFunc2 = FXCollections.observableArrayList();
	private ObservableList<TipoUsuario> listaTipo = FXCollections.observableArrayList(TipoUsuario.values());

	public void inserirFuncionario(Funcionario f) {
		this.listaFunc.add(f);
		FuncionarioDAO fDao = new FuncionarioDAOImpl();
		try {
			fDao.inserir(f);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public Funcionario pesquisarFuncionarioNome(Funcionario func) {
		Funcionario retornado = new Funcionario();
		for (Funcionario f : listaFunc) {
			if (f.getNome().equals(func.getNome())) {
				JOptionPane.showMessageDialog(null, "Funcionario Encontrado");
				return f;
			}
		}
		if (retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null, "Funcionario N�O ENCONTRADO");
		}
		return retornado;
	}

	public void alterarFuncionario(Funcionario f) {
		if (f.getId() != 0) {
			FuncionarioDAO fDao = new FuncionarioDAOImpl();
			try {
				fDao.alterar(f);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Funcionario alterado com sucesso");
		}
	}

	public void removerFuncionario(Funcionario func) {
		FuncionarioDAO fDao = new FuncionarioDAOImpl();
		try {
			fDao.excluir(func);
			listaFunc.clear();
			listaFunc.setAll(fDao.listar());
			listaFunc2.clear();
			listaFunc2.setAll(listaFunc);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
	public boolean funcionarioExiste(Funcionario func) {
		boolean existe = false;
		for (Funcionario f : listaFunc) {
			if (f.getNome().equals(func.getNome())) {
				existe = true;
			}
		}
		return existe;
	}

	public Funcionario pesquisarFuncionarioId(Funcionario func) {
		Funcionario retornado = new Funcionario();
		for (Funcionario f : listaFunc) {
			if (f.getId() == func.getId()) {
				JOptionPane.showMessageDialog(null, "Funcionario Encontrado");
				return f;
			}
		}
		if (retornado.getId() == 0) {
			JOptionPane.showMessageDialog(null, "FUNCIONARIO N�O ENCONTRADO");
		}
		return retornado;
	}

	public void preencheLista2() {
		if (this.listaFunc2.isEmpty()) {
			this.listaFunc2.setAll(getListaFunc());
		}
	}

	public void pesquisarFuncionario(String value) {
		this.listaFunc2.clear();
		for (Funcionario f : this.listaFunc) {
			if (f.getNome().contains(value)) {
				this.listaFunc2.add(f);
			}
		}
	}

	public ObservableList<Funcionario> getListaFunc() {
		FuncionarioDAO fDAO = new FuncionarioDAOImpl();
		try {
			listaFunc.addAll(fDAO.listar());
		} catch (DAOException e) {
			e.printStackTrace();
		}
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
