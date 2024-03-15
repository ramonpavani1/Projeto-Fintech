package fintech;

public class Conta {
	
	private String nome;
	private String sobrenome;
	protected static int CPF;
	private String telefone;
	private String E_mail;
	private String dateNasc;
	protected static int senha;
	private String gasto;
	private int meta;
	private int valorInicial;
	private int valorGasto;
    private int valorEcon;
    
	public String getNome() {
		return this.nome;
	}

	public static void setNome(String nome) {
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public static void setSobrenome(String sobrenome) {
	}

	public int getCPF() {
		return CPF;
	}

	public static void setCPF(int CPF) {
	}

	public String getTelefone() {
		return telefone;
	}

	public static void setTelefone(String telefone) {
	}

	public String getE_mail() {
		return E_mail;
	}

	public static void setE_mail(String E_mail) {
	}

	public String getDateNasc() {
		return dateNasc;
	}

	public static void setDateNasc(String dateNasc) {
	}

	public int getSenha() {
		return senha;
	}
	
	public static void setSenha(int senha) {
	}
	public String getGasto() {
		return gasto;
	}
	
	public static void setSenha(String gasto) {
	}
	public int getMeta() {
		return meta;
	}
	
	public static void setMeta(int meta) {
	}
	public int getValorGasto() {
		return valorGasto;
	}
	
	public static void setValorGasto(int valorGasto) {
	}
	public int getValorEcon() {
		return valorEcon;
	}
	
	public static void setValorEcon(int valorEcon) {
	}
	public int getValorInicial() {
		return valorInicial;
	}
	
	public static void setValorInicial(int valorInicial) {
	}
}
