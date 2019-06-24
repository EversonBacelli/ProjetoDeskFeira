package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.GerenciamentoConexao;
import Model.LoteProduto;
import Model.Produto;
import Model.ProdutoVendido;
import Model.Venda;

public class VendaDAOImpl implements VendaDAO{

	public VendaDAOImpl() {
	}
	
	@Override
	public void adicionar(Venda v) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "INSERT INTO venda (dataVenda, valorTotal) VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, v.getDataVenda());
			stmt.setDouble(2, v.getValorTotal());
			stmt.executeUpdate();
			
			String sqlBusca = "SELECT max(venda.id) FROM venda";
			PreparedStatement stmtBusca = con.prepareStatement(sqlBusca);
			ResultSet rs = stmtBusca.executeQuery();
			while (rs.next()) {
				v.setId(rs.getInt(1));
			}
			
			List<ProdutoVendido> listaLote = v.getListaProdutoVendido();
			
			for (ProdutoVendido produtoVendido : listaLote) {				
			String sql2 = "INSERT INTO produtoVendido (quantidade,id_produto, id_venda) VALUES (?, ?, ?)";
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
	public List<Venda> listar() throws DAOException {
		List<Venda> lista = new ArrayList<>();
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "select  vd.id, vd.valorTotal, vd.dataVenda from venda vd";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				List<ProdutoVendido> listaProdVend = new ArrayList<ProdutoVendido>();
				Venda v = new Venda();
				v.setId(rs.getInt("id"));
				v.setDataVenda(rs.getString("dataVenda"));
				v.setValorTotal(rs.getDouble("valorTotal"));
				String sql2 = "\n" + 
						"select pv.id_venda, pd.id, pd.descricao, "
						+ "pd.nome, pd.preco, pd.qtdMax, pd.qtdMin,"
						+ " pd.qtdTempoVida, pv.quantidade \n" + 
						"from produtoVendido pv inner join produto pd \n" + 
						"on pd.id = pv.id_produto \n" + 
						"where pv.id_venda = ?";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, v.getId());
				ResultSet rs2 = stmt2.executeQuery();
				while(rs2.next()) {
					ProdutoVendido pv = new ProdutoVendido();
					Produto p = new Produto();
					pv.setIdVenda(rs2.getInt("id_venda"));
					p.setId(rs2.getInt("id"));
					p.setNome(rs2.getString("nome"));
					p.setDescricao(rs2.getString("descricao"));
					p.setPreco(rs2.getDouble("preco"));
					p.setQtdMax(rs2.getInt("qtdMax"));
					p.setQtdMin(rs2.getInt("qtdMin"));
					p.setQtdTempoVida(rs2.getInt("qtdTempoVida"));
					pv.setProduto(p);
					pv.setQuantidade(rs2.getInt("quantidade"));
					listaProdVend.add(pv);
				}
				v.setListaProdutoVendido(listaProdVend);
				lista.add(v);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return lista;
	}
	
	


	
}
