package model;

public class Carro {
	private int id;
	private String placa;
	private String marca;
	private String modelo;
	private int ano;
	private double valorAproximado;
	private Pessoa donoDoCarro;
	
	public Carro() {
		
	}
	
	
	public Carro(int id,String placa, String marca, String modelo, int ano, double valorAproximado, Pessoa donoDoCarro) {
		this.placa=  placa;
		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.valorAproximado = valorAproximado;
		this.donoDoCarro = donoDoCarro;
		this.id = id;
	}


	public String getPlaca() {
		return placa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public double getValorAproximado() {
		return valorAproximado;
	}


	public void setValorAproximado(double valorAproximado) {
		this.valorAproximado = valorAproximado;
	}


	public Pessoa getDonoDoCarro() {
		return donoDoCarro;
	}


	public void setDonoDoCarro(Pessoa donoDoCarro) {
		this.donoDoCarro = donoDoCarro;
	}
	
	

}
