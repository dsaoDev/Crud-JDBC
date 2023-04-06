package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.protocol.Resultset;

import model.Usuario;

public class UsuarioDAO {
	//DAO
	private final String DB_URL = "jdbc:mysql://localhost:3306/bibliotecav2";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";
	
	private final String INSERT_SQL = "INSERT INTO bibliotecav2.usuario (email, senha) VALUES (?, ?)";
	
	public boolean realizarCadastro(Usuario usuario) {
		
		boolean cadastradoSucesso = false;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
			
			statement.setString(1, usuario.getEmail());
			statement.setNString(2, usuario.getSenha());
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				cadastradoSucesso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cadastradoSucesso;
	}
	
	public Usuario checarEmail(String email) {		
		Usuario u = null;		
		
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM bibliotecav2.usuario WHERE email = ?");
			
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
