package fintech.bean;

import java.util.Calendar;

public class EntradaModel {

	private int codigo;
	
	private String nome;
	
	private double valor;
	
	private Calendar dataEnt;
	
	public EntradaModel() {
		super();
	}

	public EntradaModel(int codigo, String nome, double valor, Calendar dataEnt) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.valor = valor;
		this.dataEnt = dataEnt;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return dataEnt;
	}
	
	public void setData(Calendar dataEnt) {
		this.dataEnt = dataEnt;
	}

}