package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import model.Usuario;

public class UsuarioDAO {
	
	private final String DB_URL = "jdbc:mysql://localhost:3306/escola";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";
	Connection conn;
	
	public UsuarioDAO() {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean inserirUsuario(Usuario u) {
		boolean inseriu = false;
		
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO escola.usuario (login, senha) VALUES (?, ?)");
			statement.setString(1, u.getLogin());
			statement.setString(2, u.getSenha());
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				inseriu = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}
	public Usuario checarLogin(String login) {
		Usuario u = null;
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM escola.usuario where login = ?");
			statement.setString(1, login);
			
			ResultSet resultado = statement.executeQuery();
			if(resultado.next()) {
				u = new Usuario();
				u.setId(resultado.getInt("id"));
				u.setLogin(login);
				u.setSenha(resultado.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u ;
	}
}
