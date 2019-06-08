package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ConnectionFactory.GerenciamentoConexao;
import Model.Produto;

public class ProdutoDAOImpl implements ProdutoDAO{
	public ProdutoDAOImpl() { 
	}
	
	@Override
	public void adicionar(Produto p) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			System.out.println("Conectado no servidor");
			String sql = "INSERT INTO produto"
					+ " (nome, descricao, qtdMax, "
					+ "qtdMin, qtdTempoVida, preco) VALUES (?, ?, ?, ?, ?, ?)";
			System.out.println("Query de inserção: " + sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getDescricao());
			stmt.setInt(3,  p.getQtdMax());
			stmt.setInt(4, p.getQtdMin());
			stmt.setInt(5,  p.getQtdTempoVida());
			stmt.setDouble(6,  p.getPreco());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Produto pesquisarNome(String nome) throws DAOException {
		Produto p = new Produto();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM produto WHERE nome = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdMax(rs.getInt("qtdMax"));
				p.setQtdMin(rs.getInt("qtdMin"));
				p.setQtdTempoVida(rs.getInt("qtdTempoVida"));
				p.setPreco(rs.getInt("preco"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return p;
	}

	@Override
	public Produto pesquisarId(int id) throws DAOException {
		Produto p = new Produto();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM produto WHERE id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdMax(rs.getInt("qtdMax"));
				p.setQtdMin(rs.getInt("qtdMin"));
				p.setQtdTempoVida(rs.getInt("qtdTempoVida"));
				p.setPreco(rs.getInt("preco"));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return p;
	}

	@Override
	public void excluir(Produto p) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "delete produto where id like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, p.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	@Override
	public void alterar(Produto p) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "update produto set nome = ?, descricao = ?, qtdMax = ?, qtdMin = ?, qtdTempoVida = ?, preco = ? where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getDescricao());
			stmt.setInt(3, p.getQtdMax());
			stmt.setInt(4, p.getQtdMin());
			stmt.setInt(5, p.getQtdTempoVida());
			stmt.setDouble(6, p.getPreco());
			stmt.setInt(7, p.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	@Override
	public List<Produto> listar() throws DAOException {
		List<Produto> lista = new ArrayList<>();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM produto";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdMax(rs.getInt("qtdMax"));
				p.setQtdMin(rs.getInt("qtdMin"));
				p.setQtdTempoVida(rs.getInt("qtdTempoVida"));
				p.setPreco(rs.getInt("preco"));
				lista.add(p);
			}
			rs.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public int proximoId() throws DAOException {
		Connection con;
		int proxId = 1;
		try {
			con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "select max(id) + 1 AS proximo_id from produto";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				proxId = rs.getInt("proximo_id");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxId;
	}

}