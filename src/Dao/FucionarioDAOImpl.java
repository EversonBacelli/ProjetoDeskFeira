package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.GerenciamentoConexao;
import Model.Funcionario;
import Model.Produto;
import Model.TipoUsuario;

public class FucionarioDAOImpl  {
	public FucionarioDAOImpl() {
	}

	public void adicionar(Funcionario f) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			System.out.println("Conectado no servidor");
			String sql = "INSERT INTO funcionario" + " (id, nome, cpf, rg, "
					+ "email, tp, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("Query de inserção: " + sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, f.getId());
			stmt.setString(2, f.getNome());
			stmt.setString(3, f.getCpf());
			stmt.setString(4, f.getRg());
			stmt.setString(5, f.getEmail());
			stmt.setInt(6, f.getTp().getValor());
			stmt.setString(7, f.getLogin());
			stmt.setString(8, f.getSenha());
			stmt.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	public void excluir(Funcionario f) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "delete funcionario where id like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	public void alterar(Funcionario f) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "update funcionario set nome = ?, cpf = ?, rg = ?, email = ?, tp = ?, login = ?, sennha = ? where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, f.getNome());
			stmt.setString(2, f.getCpf());
			stmt.setString(3, f.getRg());
			stmt.setString(4, f.getEmail());
			stmt.setInt(5, f.getTp().getValor());
			stmt.setString(6, f.getLogin());
			stmt.setString(7, f.getSenha());
			stmt.setInt(8, f.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	public List<Funcionario> listar(Funcionario f) throws DAOException {
		List<Funcionario> lista = new ArrayList<>();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM funcionario";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				f.setId(rs.getInt("id"));
				f.setNome(rs.getString("nome"));
				f.setCpf(rs.getString("cpf"));
				f.setRg(rs.getString("rg"));
				f.setEmail(rs.getString("email"));
				if (rs.getInt("tp") == TipoUsuario.ADMINISTRADOR.getValor()) {
					f.setTp(TipoUsuario.ADMINISTRADOR);
				} else if ((rs.getInt("tp") == TipoUsuario.ESTOQUISTA.getValor())) {
					f.setTp(TipoUsuario.ESTOQUISTA);
				} else if ((rs.getInt("tp") == TipoUsuario.VENDEDOR.getValor())) {
					f.setTp(TipoUsuario.VENDEDOR);
				}
				f.setLogin(rs.getString("login"));
				f.setSenha(rs.getString("Senha"));
				lista.add(f);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

}
