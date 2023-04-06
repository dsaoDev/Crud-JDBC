package model;

public class Aluno extends Pessoa{
	private int id;
	private String matricula;
	private double nota1;
	private double nota2;
	private double media;
	private String situacaoAluno;
	
	
	public Aluno() {
		
	}
	
	public Aluno(String nome, String cpf, int id, String matricula, double nota1, double nota2, double media, String situacaoAluno) {
	super(nome, cpf);
	this.matricula = matricula;
	this.nota1  = nota1;
	this.nota2 = nota2;
	this.media = media;
	this.situacaoAluno = situacaoAluno;
	this.id = id;
	}
	

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public String getSituacaoAluno() {
		return situacaoAluno;
	}

	public void setSituacaoAluno(String situacaoAluno) {
		this.situacaoAluno = situacaoAluno;
	}
	
	
	
}
