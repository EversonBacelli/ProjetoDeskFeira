package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
					+ " (id, nome, descricao, qtdMax, "
					+ "qtdMin, qtdTempoVida, preco) VALUES (?, ?, ?, ?, ?, ?, ?)";
			System.out.println("Query de inserção: " + sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, p.getId());
			stmt.setString(2, p.getNome());
			stmt.setString(3, p.getDescricao());
			stmt.setInt(4,  p.getQtdMax());
			stmt.setInt(5, p.getQtdMin());
			stmt.setInt(6,  p.getQtdTempoVida());
			stmt.setDouble(7,  p.getPreco());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public List<Produto> pesquisarNome(String nome) throws DAOException {
		List<Produto> lista = new ArrayList<>();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM produto WHERE nome like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + nome + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdMax(rs.getInt("qtdMax"));
				p.setQtdMin(rs.getInt("atdMin"));
				p.setQtdTempoVida(rs.getInt("qtdTempoVida"));
				p.setPreco(rs.getInt("preco"));
				lista.add(p);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public List<Produto> pesquisarId(int id) throws DAOException {
		List<Produto> lista = new ArrayList<>();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM produto WHERE nome like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) { 
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdMax(rs.getInt("qtdMax"));
				p.setQtdMin(rs.getInt("atdMin"));
				p.setQtdTempoVida(rs.getInt("qtdTempoVida"));
				p.setPreco(rs.getInt("preco"));
				lista.add(p);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}

	@Override
	public void excluir(int id) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "delete produto where ra like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	@Override
	public void alterar(int id) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "update produto set ra = 1209 where ra like 12347";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

}
