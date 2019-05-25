package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciamentoConexao {
	
	private String url = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=crudTapioca;namedPipes=true";
	private String user = "root";
	private String password = "";
	private Connection con;
	private static GerenciamentoConexao cm;

	private GerenciamentoConexao() { 
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static GerenciamentoConexao getInstance() { 
		if (cm == null) { 
			cm = new GerenciamentoConexao();
		}
		return cm;
	}
	
	public Connection getConnection() throws SQLException { 
		if (con == null || con.isClosed()) {
			System.out.println("Nova conexão criada");
			con = DriverManager.getConnection(url, user, password);
		} else { 
			System.out.println("Usando a mesma conexão");
		}
		return con;
	}

}
