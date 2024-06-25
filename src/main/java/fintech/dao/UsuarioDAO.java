package fintech.dao;

import fintech.bean.UsuarioModel;

public interface UsuarioDAO {

	boolean validarUsuario(UsuarioModel usuario);
	void cadastrarUsuario(UsuarioModel usuario);
	boolean usuarioExiste(String email);
	
}
