package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

import model.Livro;

public class LivroDAO {
	
	private final String DB_URL = "jdbc:mysql://localhost:3306/bibliotecav2";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";
	private Connection conn;
	
	private final String INSERT_SQL = "INSERT INTO bibliotecav2.livro (codigo, titulo, autor, isbn, numPaginas, valorLivro) VALUES (?, ?, ?, ?, ?, ?)";
	private final String DELETE_SQL = "DELETE FROM bibliotecav2.livro WHERE titulo = ?";
	private final String SELECT_SQL = "SELECT * FROM bibliotecav2.livro";
	private final String UPDATE_SQL = "UPDATE bibliotecav2.livro SET autor = ?, isbn = ?, codigo = ?, valorLivro = ?, numPaginas = ?, titulo = ? WHERE titulo = ?";
	
	private final String SELECT_BY_ISBN_SQL = "SELECT * FROM bibliotecav2.livro WHERE isbn = ?";
	private final String SELECT_BY_TITLE_SQL = "SELECT * FROM bibliotecav2.livro WHERE titulo = ?";
	private final String SELECT_BY_AUTOR_SQL = "SELECT * FROM bibliotecav2.livro WHERE autor = ?";
	private final String DELETE_BY_AUTOR_SQL = "DELETE FROM bibliotecav2.livro WHERE autor = ?";
	
	public LivroDAO() {
		try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public boolean cadastrarLivro(Livro livro) {
		
		boolean cadastrou = false;
		try {
			
			PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
			
			statement.setInt(1, livro.getCodigo());
			statement.setString(2, livro.getTitulo());
			statement.setString(3, livro.getAutor());
			statement.setString(4, livro.getIsbn());
			statement.setInt(5, livro.getNumPaginas());
			statement.setDouble(6, livro.getValor());
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas > 0 ) {
				cadastrou = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cadastrou;
		
	}
	
	public boolean removerLivro(Livro livro) {
		boolean removeu = false;
		try {
			
			PreparedStatement statement = conn.prepareStatement(DELETE_SQL);
			
			statement.setString(1, livro.getTitulo());
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas > 0) {
				removeu = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return removeu;
	}
	
	public ArrayList<Livro> listarLivros(){
		ArrayList<Livro> livros = new ArrayList<>();
		try {
		
			PreparedStatement statement = conn.prepareStatement(SELECT_SQL);
			
			ResultSet resultado = statement.executeQuery();
			while(resultado.next()) {
				
				Livro livro = new Livro();
				livro.setId(resultado.getInt("id"));
				livro.setAutor(resultado.getString("autor"));
				livro.setTitulo(resultado.getString("titulo"));
				livro.setCodigo(resultado.getInt("codigo"));
				livro.setIsbn(resultado.getString("isbn"));
				livro.setNumPaginas(resultado.getInt("numPaginas"));
				livro.setValor(resultado.getDouble("valorLivro"));
				
				livros.add(livro);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
	//Metodo Especial
	public ArrayList<Livro>pesquisarLivroviaISBN (Livro livro) {
		ArrayList<Livro> livros = new ArrayList<>();
		
		try {
			
			PreparedStatement statement = conn.prepareStatement(SELECT_BY_ISBN_SQL);
			statement.setString(1, livro.getIsbn());
			ResultSet resultado = statement.executeQuery();
			
			if(resultado.next()) {
				livro.setId(resultado.getInt("id"));
				livro.setAutor(resultado.getString("autor"));
				livro.setTitulo(resultado.getString("titulo"));
				livro.setCodigo(resultado.getInt("codigo"));
				livro.setIsbn(livro.getIsbn());
				livro.setNumPaginas(resultado.getInt("numPaginas"));
				livro.setValor(resultado.getDouble("valorLivro"));
				
				livros.add(livro);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
		
	}
	//Metodo Especial
	public ArrayList<Livro>pesquisarLivroviaTitulo (Livro livro) {
		ArrayList<Livro> livros = new ArrayList<>();
		
		try {
			
			PreparedStatement statement = conn.prepareStatement(SELECT_BY_TITLE_SQL);
			
			
			statement.setString(1, livro.getTitulo());
			ResultSet resultado = statement.executeQuery();
			
			if(resultado.next()) {
			
				
				livro.setId(resultado.getInt("id"));
				livro.setAutor(resultado.getString("autor"));
				livro.setTitulo(livro.getTitulo());
				livro.setCodigo(resultado.getInt("codigo"));
				livro.setIsbn(resultado.getString("isbn"));
				livro.setNumPaginas(resultado.getInt("numPaginas"));
				livro.setValor(resultado.getDouble("valorLivro"));
				
				livros.add(livro);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	
	
	}
	//Metodo Especial
	public boolean updateLivro(Livro livro, String novoTitulo) {
		boolean atualizouSucesso = false;
		
		try {
			
			PreparedStatement statement = conn.prepareStatement(UPDATE_SQL);
			
			
			statement.setString(1, livro.getAutor());
			statement.setNString(2, livro.getIsbn());
			statement.setInt(3, livro.getCodigo());
			statement.setDouble(4, livro.getValor());
			statement.setInt(5, livro.getNumPaginas());
			statement.setString(6, novoTitulo);
			statement.setString(7, livro.getTitulo());
			
			int linhasAtualizadas = statement.executeUpdate();
			
			if(linhasAtualizadas > 0 ) {
				atualizouSucesso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizouSucesso;
	}
	
	//Metodo Especial
	public ArrayList<Livro> pesquisarLivroviaAutor (Livro livro) {
		ArrayList<Livro> livros = new ArrayList<>();
		
		try {
			
			PreparedStatement statement = conn.prepareStatement(SELECT_BY_AUTOR_SQL);
			
			
			statement.setString(1, livro.getAutor());
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
			
			
				
				livro.setId(resultado.getInt("id"));
				livro.setAutor(livro.getAutor());
				livro.setTitulo(resultado.getString("titulo"));
				livro.setCodigo(resultado.getInt("codigo"));
				livro.setIsbn(resultado.getString("isbn"));
				livro.setNumPaginas(resultado.getInt("numPaginas"));
				livro.setValor(resultado.getDouble("valorLivro"));
				
				livros.add(livro);
			
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	
	}
	//Metodo Especial
	public boolean deletarViaAutor(Livro livro) {
		boolean deletados = false;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER,DB_PASSWORD);
			PreparedStatement statement = conn.prepareStatement(DELETE_BY_AUTOR_SQL);
			
			statement.setString(1, livro.getAutor());
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected > 0) {
				deletados = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletados;
	}
	
	
		
	
	}


