package model;

public class Professor extends Pessoa{
	private int id;
	private String discEnsinada;
	
	
	public Professor() {
		
	}
	
	public Professor( String nome, String cpf, int id,  String discEnsinada) {
	super(nome, cpf);
	this.id = id;
	this.discEnsinada = discEnsinada;
		
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiscEnsinada() {
		return discEnsinada;
	}

	public void setDiscEnsinada(String discEnsinada) {
		this.discEnsinada = discEnsinada;
	}
	
	
	

}
