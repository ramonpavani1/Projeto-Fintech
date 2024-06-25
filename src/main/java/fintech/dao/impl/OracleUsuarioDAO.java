package fintech.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import fintech.bean.UsuarioModel;
import fintech.dao.UsuarioDAO;
import fintech.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO{
	
	private Connection conexao;

	@Override
	public boolean validarUsuario(UsuarioModel usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				return true;
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
		return false;
	}
	public boolean usuarioExiste(String email) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
            stmt = conexao.prepareStatement("SELECT 1 FROM usuarios WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        } finally {
            ConnectionManager.close(conexao, stmt, rs);
        }
    }
    public void cadastrarUsuario(UsuarioModel usuario) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getConnection();
            stmt = conexao.prepareStatement("INSERT INTO usuarios (email, senha) VALUES (?, ?)");
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(conexao, stmt);
        }
    }
    

}