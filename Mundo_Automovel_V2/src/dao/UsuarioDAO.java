package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

import model.Usuario;

public class UsuarioDAO {
	
 private final String DB_URL = "jdbc:mysql://localhost:3306/loja";
 private final String DB_USER = "root";
 private final String DB_PASSWORD = "root";
 
 private final String INSERT_SQL = "INSERT INTO loja.usuario (email, senha) VALUES (?, ?)";
 private final String SELECT_SQL = "SELECT * FROM loja.usuario";
 private final String SELECT_BY_EMAIL_SQL = "SELECT * FROM loja.usuario WHERE email = ?";
 
 public boolean cadastrarUsuario(Usuario usuario) {
	 
	 boolean inseridoComSucesso = false;
	 try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
		PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
		statement.setString(1, usuario.getEmail());
		statement.setString(2, usuario.getSenha());
		
		int linhasAfetadas = statement.executeUpdate();
		
		if(linhasAfetadas > 0 ) {
			inseridoComSucesso = true;
		}
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 return inseridoComSucesso;
 }
 
 public ArrayList<Usuario> listarUsuario(){
	 ArrayList<Usuario> usuarios = new ArrayList<>();
	 try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement statement = conn.prepareStatement(SELECT_SQL);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setEmail(resultado.getString("email"));
			usuario.setSenha(resultado.getString("senha"));
			usuario.setId(resultado.getInt("id"));
			
			usuarios.add(usuario);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 return usuarios;
	 
 }
 
 public Usuario ChecarEmailDoUsuario(String email) {
	 Usuario u = null;
	 try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement statement = conn.prepareStatement(SELECT_BY_EMAIL_SQL);
		
		statement.setString(1, email);
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			u = new Usuario();
			u.setId(resultado.getInt("id"));
			u.setSenha(resultado.getString("senha"));
			u.setEmail(email);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 return u;
	 
 }
}
