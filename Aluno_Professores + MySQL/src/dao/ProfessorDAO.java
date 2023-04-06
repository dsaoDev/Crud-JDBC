package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

import model.Professor;

public class ProfessorDAO {
	private final String DB_URL = "jdbc:mysql://localhost:3306/escola";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";
	Connection conn;
	
	public ProfessorDAO() {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean inserir(Professor professor) {
		boolean inseriu = false;
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO escola.professor (nome, cpf, discEnsinada) VALUES (?, ?, ?)");
			statement.setString(1, professor.getNome());
			statement.setString(2, professor.getCpf());
			statement.setString(3, professor.getDiscEnsinada());
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0 ) {
				inseriu = true;
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}
	
	public ArrayList<Professor> listar(){
		ArrayList<Professor> professores = new ArrayList<Professor>();
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM escola.professor");
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				Professor professor = new Professor();
				professor.setId(resultado.getInt("id"));
				professor.setNome(resultado.getString("nome"));
				professor.setCpf(resultado.getString("cpf"));
				professor.setDiscEnsinada(resultado.getString("discEnsinada"));
				
				professores.add(professor);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professores;
	}
	
	public ArrayList<Professor> buscar (Professor professor) {
		ArrayList<Professor> professores = new ArrayList<Professor>();
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM escola.professor WHERE cpf = ?");
			statement.setString(1, professor.getCpf());
			ResultSet resultado = statement.executeQuery();
			
			if(resultado.next()) {
				 professor.setId(resultado.getInt("id"));
				 professor.setNome(resultado.getString("nome"));
				 professor.setCpf(professor.getCpf());
				 professor.setDiscEnsinada(resultado.getString("discEnsinada"));
				 
				 professores.add(professor);
				 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professores;
	}
	
	public boolean update (Professor professor) {
		boolean atualizou = false;
		try {
			PreparedStatement statement = conn.prepareStatement("UPDATE escola.professor SET nome = ?,  discEnsinada = ? WHERE id =?");
			statement.setString(1, professor.getNome());
			statement.setString(2, professor.getDiscEnsinada());
			statement.setInt(3, professor.getId());
			
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0) {
				atualizou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizou;
	}
	
	public boolean delete (Professor professor) {
		boolean deletou = false;
		try {
			PreparedStatement statement = conn.prepareStatement("DELETE FROM escola.professor WHERE id = ?");
			statement.setInt(1, professor.getId());
			int rowsAffected = statement.executeUpdate();
			
			if(rowsAffected > 0 ) {
				deletou = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deletou;
	}
	
	

}
