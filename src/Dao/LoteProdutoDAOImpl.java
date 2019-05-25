package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.GerenciamentoConexao;
import Model.LoteProduto;

public class LoteProdutoDAOImpl implements LoteProdutoDAO{

	public LoteProdutoDAOImpl() {
	}
	
	@Override
	public void adicionar(LoteProduto lp) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			System.out.println("Conectado no servidor");
			String sql = "INSERT INTO loteProduto"
					+ " (id, quantidade, dataValidade, dataEntrada, "
					+ "id_produto) VALUES (?, ?, ?, ?, ?)";
			System.out.println("Query de inserção: " + sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, lp.getId());
			stmt.setInt(2, lp.getQuantidade());
			stmt.setString(3, lp.getDataValidade());
			stmt.setString(4,  lp.getDataEntrada());
			stmt.setObject(5, lp.getProduto().getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}


	@Override
	public void excluir(LoteProduto lp) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "delete loteProduto where id like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, lp.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void alterar(LoteProduto lp) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "update loteProduto set quantidade = ?, dataValidade = ?, dataEntrada= ?, id_produto = ? where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, lp.getQuantidade());
			stmt.setString(1, lp.getDataValidade());
			stmt.setString(2, lp.getDataEntrada());
			stmt.setInt(3, lp.getProduto().getId());
			stmt.setInt(5, lp.getId());
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public List<LoteProduto> listar(LoteProduto lp) throws DAOException {
		List<LoteProduto> lista = new ArrayList<>();
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "SELECT * FROM loteProduto";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				lp.setId(rs.getInt("id"));
				lp.setQuantidade(rs.getInt("quantidade"));
				lp.setDataValidade(rs.getString("dataValidade"));
				lp.setDataEntrada(rs.getString("dataEntrada"));
				lp.setProduto(produtoDAO.pesquisarId(rs.getInt("id_produto")));
				lista.add(lp);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}


	
}
