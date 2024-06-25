package fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fintech.bean.EntradaModel;
import fintech.dao.EntradaDAO;
import fintech.exception.DBException;
import fintech.singleton.ConnectionManager;

public class OracleEntradaDAO implements EntradaDAO {

	private Connection conexao;
	
	@Override
	public void cadastrar(EntradaModel entrada) throws DBException {
		PreparedStatement stmt = null;

		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "INSERT INTO TB_ENTRADA (CD_PRODUTO, NM_ENTRADA, VL_ENTRADA, DT_ENTRADA) VALUES (SQ_TB_ENTRADA.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, entrada.getNome());
			stmt.setDouble(2, entrada.getValor());
			java.sql.Date dataEnt = new java.sql.Date(entrada.getData().getTimeInMillis());
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
	public void atualizar(EntradaModel entrada) throws DBException {
		PreparedStatement stmt = null;

		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "UPDATE TB_ENTRADA SET NM_ENTRADA = ?, VL_ENTRADA = ?, DT_ENT = ? WHERE CD_ENTRADA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, entrada.getNome());
			stmt.setDouble(2, entrada.getValor());
			java.sql.Date dataEnt = new java.sql.Date(entrada.getData().getTimeInMillis());
			stmt.setDate(3, dataEnt);
			stmt.setInt(4, entrada.getCodigo());

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
				String sql = "DELETE FROM TB_ENTRADA WHERE CD_ENTRADA = ?";
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
	public EntradaModel buscar(int id) {
		EntradaModel entrada = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_PRODUTO WHERE CD_PRODUTO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int codigo = rs.getInt("CD_ENTRADA");
				String nome = rs.getString("NM_ENTRADA");
				double valor = rs.getDouble("VL_ENTRADA");
				java.sql.Date data = rs.getDate("DT_ENT");
				Calendar dataEnt = Calendar.getInstance();
				dataEnt.setTimeInMillis(data.getTime());
				
			 entrada = new EntradaModel(codigo, nome, valor, dataEnt);
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
		return entrada;
	}

	@Override
	public List<EntradaModel> listar() {
		List<EntradaModel> lista = new ArrayList<EntradaModel>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_ENTRADA");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int codigo = rs.getInt("CD_ENTRADA");
				String nome = rs.getString("NM_ENTRADA");
				double valor = rs.getDouble("VL_ENTRADA");
				java.sql.Date data = rs.getDate("DT_ENT");
				Calendar dataEnt = Calendar.getInstance();
				dataEnt.setTimeInMillis(data.getTime());
				
				EntradaModel entrada = new EntradaModel(codigo, nome, valor, dataEnt);
				lista.add(entrada);
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