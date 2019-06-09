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

public class LoteProdutoDAOImpl implements LoteProdutoDAO{

	public LoteProdutoDAOImpl() {
	}
	
	@Override
	public void adicionar(LoteProduto lp) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			System.out.println("Conectado no servidor");
			String sql = "INSERT INTO loteProduto"
					+ " (quantidade, dataValidade, dataEntrada, "
					+ "id_produto) VALUES (?, ?, ?, ?)";
			System.out.println("Query de inserção: " + sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt          (1, lp.getQuantidade());
			stmt.setString       (2, lp.getDataValidade());
			stmt.setString       (3,  lp.getDataEntrada());
			stmt.setLong       (4, lp.getProduto().getId());
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
//update produto set nome = ?, descricao = ?, qtdMax = ?, qtdMin = ?, qtdTempoVida = ?, preco = ? where id = ?
	@Override
	public void alterar(LoteProduto lp) throws DAOException {
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "update loteProduto set quantidade = ?, dataValidade = ?, dataEntrada= ?, id_produto = ? where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, lp.getQuantidade());
			stmt.setString(2, lp.getDataValidade());
			stmt.setString(3, lp.getDataEntrada());
			stmt.setInt(4, lp.getProduto().getId());
			stmt.setInt(5, lp.getId());
			stmt.execute();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public List<LoteProduto> listar() throws DAOException {
		
		List<LoteProduto> lista = new ArrayList<>();
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		try {
			Connection con = GerenciamentoConexao.getInstance().getConnection();
			String sql = "select lp.id, lp.quantidade, lp.dataValidade, lp.dataEntrada, pd.id as idProduto, pd.nome, pd.descricao, pd.qtdMax, pd.qtdMin, pd.qtdTempoVida, pd.preco\n" + 
					"	from loteProduto lp inner join produto pd\n" + 
					"	on lp.id_produto = pd.id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			// System.out.println(rs+"--"+ rs.getObject(1));
			while (rs.next()) {
				Produto p = new Produto();
				LoteProduto lp = new LoteProduto();
				lp.setId(rs.getInt("id"));
				lp.setQuantidade(rs.getInt("quantidade"));
				lp.setDataValidade(rs.getString("dataValidade"));
				lp.setDataEntrada(rs.getString("dataEntrada"));
				p.setId(rs.getInt("idProduto"));
				p.setNome(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdMax(rs.getInt("qtdMax"));
				p.setQtdMax(rs.getInt("qtdMin"));
				p.setPreco(rs.getDouble("preco"));
				p.setQtdTempoVida(rs.getInt("qtdTempoVida"));
				lp.setProduto(p);
				//n = GerenciamentoConexao.getInstance().getConnection();
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
