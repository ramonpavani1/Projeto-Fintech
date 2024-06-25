package fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fintech.bean.GastoModel;
import fintech.dao.GastoDAO;
import fintech.exception.DBException;
import fintech.singleton.ConnectionManager;

public class OracleGastoDAO implements GastoDAO {

	private Connection conexao;
	
	@Override
	public void cadastrar(GastoModel gasto) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "INSERT INTO TB_GASTO (CD_GASTO, NM_GASTO, VL_GASTO, DT_GASTO) VALUES (SQ_TB_GASTO.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, gasto.getNome());
			stmt.setDouble(2, gasto.getValor());
			java.sql.Date dataEnt = new java.sql.Date(gasto.getData().getTimeInMillis());
			stmt.setDate(3, dataEnt);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(GastoModel gasto) throws DBException {
		PreparedStatement stmt = null;

		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "UPDATE TB_GASTO SET NM_GASTO = ?, VL_GASTO = ?, DT_GASTO = ? WHERE CD_GASTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, gasto.getNome());
			stmt.setDouble(2, gasto.getValor());
			java.sql.Date dataEnt = new java.sql.Date(gasto.getData().getTimeInMillis());
			stmt.setDate(3, dataEnt);
			stmt.setInt(4, gasto.getCodigo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void remover(int codigo) throws DBException {
			PreparedStatement stmt = null;

			try {
				ConnectionManager.getInstance();
				conexao = ConnectionManager.getConnection();
				String sql = "DELETE FROM TB_GASTO WHERE CD_GASTO = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigo);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Erro ao remover.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	
	@Override
	public GastoModel buscar(int id) {
		GastoModel gasto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GASTO WHERE CD_GASTO= ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo = rs.getInt("CD_GASTO");
				String nome = rs.getString("NM_GASTO");
				double valor = rs.getDouble("VL_GASTO");
				java.sql.Date data = rs.getDate("DT_GASTO");
				Calendar dataEnt = Calendar.getInstance();
				dataEnt.setTimeInMillis(data.getTime());
				
			  gasto = new GastoModel(codigo, nome, valor, dataEnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return gasto;
	}

	@Override
	public List<GastoModel> listar() {
		List<GastoModel> lista = new ArrayList<GastoModel>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_GASTO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("CD_GASTO");
				String nome = rs.getString("NM_GASTO");
				double valor = rs.getDouble("VL_GASTO");
				java.sql.Date data = rs.getDate("DT_GASTO");
				Calendar dataEnt = Calendar.getInstance();
				dataEnt.setTimeInMillis(data.getTime());
				
				GastoModel gasto = new GastoModel(codigo, nome, valor, dataEnt);
				lista.add(gasto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
}
