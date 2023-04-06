package model;

public class Livro {
	private int id;
 private int codigo;
 private String titulo;
 private String autor;
 private String isbn;
 private int numPaginas;
 private double valor;
 
 
 public Livro() {
	 
 }
 
 public Livro(int id, int codigo, String titulo, String autor, String isbn, int numPaginas, double valor) {
	 this.id = id;
	 this.codigo=  codigo;
	 this.titulo = titulo;
	 this.isbn = isbn;
	 this.numPaginas = numPaginas;
	 this.valor = valor;
 }
 

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getAutor() {
	return autor;
}

public void setAutor(String autor) {
	this.autor = autor;
}

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public int getNumPaginas() {
	return numPaginas;
}

public void setNumPaginas(int numPaginas) {
	this.numPaginas = numPaginas;
}

public double getValor() {
	return valor;
}

public void setValor(double valor) {
	this.valor = valor;
}
 
 
}
