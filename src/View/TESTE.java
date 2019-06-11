package View;

import java.util.ArrayList;
import java.util.List;

import Dao.DAOException;
import Dao.VendaDAO;
import Dao.VendaDAOImpl;
import Model.Venda;

public class TESTE {
	public static void main(String[] args) {
		VendaDAO vDao = new VendaDAOImpl();
		List<Venda> v = new ArrayList<>();
		try {
			v.addAll(vDao.listar());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
