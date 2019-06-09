package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.GerenciamentoConexao;
import Model.LoteProduto;
import Model.ProdutoVendido;
import Model.Venda;

public class VendaDAOImpl implements VendaDAO{

	public VendaDAOImpl() {
	}
	
	@Override
	public void adicionar(Venda v) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "INSERT INTO venda (dataVenda, valorTotal, qtdVenda) VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, v.getDataVenda());
			stmt.setDouble(2, v.getValorTotal());
			stmt.setInt(3,  v.getQtdVenda());
			stmt.executeUpdate();
			
			String sqlBusca = "SELECT max(venda.id) FROM venda";
			PreparedStatement stmtBusca = con.prepareStatement(sqlBusca);
			ResultSet rs = stmtBusca.executeQuery();
			while (rs.next()) {
				v.setId(rs.getInt(1));
			}
			
			List<ProdutoVendido> listaLote = v.getListaProdutoVendido();
			
			for (ProdutoVendido produtoVendido : listaLote) {				
			String sql2 = "INSERT INTO produtoVendido (quantidade, id_produto, id_venda) VALUES (?, ?, ?)";
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			stmt2.setInt(1, produtoVendido.getQuantidade());
			stmt2.setInt(2, produtoVendido.getProduto().getId());			
			stmt2.setInt(3, v.getId());			
			stmt2.executeUpdate();
			}
			
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public List<LoteProduto> listar(Venda v) throws DAOException {
		List<LoteProduto> lista = new ArrayList<>();
//		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
//		try {
//			Connection con = GerenciamentoConexao.getInstance().getConnection();
//			String sql = "SELECT * FROM loteProduto";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				lp.setId(rs.getInt("id"));
//				lp.setQuantidade(rs.getInt("quantidade"));
//				lp.setDataValidade(rs.getString("dataValidade"));
//				lp.setDataEntrada(rs.getString("dataEntrada"));
//				lp.setProduto(produtoDAO.pesquisarId(rs.getInt("id_produto")));
//				lista.add(lp);
//			}
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DAOException(e);
//		}
		return lista;
	}


	
}
