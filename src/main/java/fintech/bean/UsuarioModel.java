package fintech.bean;

import fintech.util.CriptografiaUtils;

public class UsuarioModel {
	
	private String email;
	
	private String senha;
	
	public UsuarioModel(String email, String senha) {
		super();
		this.email = email;
		setSenha(senha);
	}

	public UsuarioModel() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}