package model;

public class Pessoa {
	private String nome;
	private String cpf;
	private String telefone;
	private String cnpj;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome,String cpf, String telefone, String cnpj) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	

}
