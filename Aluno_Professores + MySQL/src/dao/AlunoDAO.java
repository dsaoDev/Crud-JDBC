package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

import model.Aluno;

public class AlunoDAO {
	private final String DB_URL = "jdbc:mysql://localhost:3306/escola";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "root";
	Connection conn;
	
	private final String INSERT_SQL = "INSERT INTO escola.aluno (matricula, nome, cpf) VALUES (?, ?, ?)";
	private final String SELECT_SQL = "SELECT * FROM escola.aluno";
	

	public AlunoDAO() {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean inserir(Aluno aluno) {
		boolean cadastrouSucesso = false;
		try {
			PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
			statement.setString(1, aluno.getMatricula());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getCpf());
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas > 0 ) {
				cadastrouSucesso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cadastrouSucesso;
		
	}
	
	public ArrayList<Aluno> listarInfoAluno (){
		ArrayList<Aluno> alunos = new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement(SELECT_SQL);
			ResultSet resultado = statement.executeQuery();

			while(resultado.next()) {
				Aluno aluno= new Aluno();
				aluno.setId(resultado.getInt("id"));
				aluno.setNome(resultado.getString("nome"));
				aluno.setCpf(resultado.getString("cpf"));
				aluno.setMatricula(resultado.getString("matricula"));

				
				alunos.add(aluno);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alunos;
	}
	
	public ArrayList<Aluno> buscarAlunoViaMatricula (Aluno aluno) {
		ArrayList<Aluno> alunos = new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM escola.aluno WHERE matricula = ?");
			statement.setString(1, aluno.getMatricula());
			ResultSet resultado = statement.executeQuery();
			
			if(resultado.next()) {
				aluno.setId(resultado.getInt("id"));
				aluno.setNome(resultado.getString("nome"));
				aluno.setCpf(resultado.getString("cpf"));
				aluno.setMatricula(aluno.getMatricula());
				
				alunos.add(aluno);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alunos;
	}
	
	public boolean updateById (Aluno aluno) {
		boolean atualizado  = false;
		try {
			PreparedStatement statement = conn.prepareStatement("UPDATE escola.aluno SET nome = ?, matricula = ? WHERE id = ?");
			statement.setString(1, aluno.getNome());
			statement.setString(2, aluno.getMatricula());
			statement.setInt(3, aluno.getId());
			
			int linhasAfetadas = statement.executeUpdate();
			
			if(linhasAfetadas > 0) {
				atualizado = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizado;
	}
	
	public boolean inserirMedia (Aluno a) {
		boolean  inseriuMedia = false;
		try {
			PreparedStatement statement = conn.prepareStatement("UPDATE escola.aluno SET nota1 =?, nota2 = ?, media =?, situacao = ? WHERE id = ?");
			statement.setDouble(1, a.getNota1());
			statement.setDouble(2, a.getNota2());
			statement.setDouble(3, a.getMedia());
			statement.setString(4, a.getSituacaoAluno());
			statement.setInt(5, a.getId());
			
			int linhasAtualizada = statement.executeUpdate();
			if(linhasAtualizada > 0) {
				inseriuMedia = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriuMedia;
	}
	
	public boolean delete(Aluno aluno) {
		boolean deletou = false;
		try {
		PreparedStatement statement = conn.prepareStatement("DELETE FROM escola.aluno WHERE id = ?");	
				statement.setInt(1, aluno.getId());
				
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected > 0) {
				deletou = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deletou;
	}
	
	public Aluno buscarAlunoComMedia(int id){
		
		Aluno a = null;
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM escola.aluno WHERE id = ?");
			statement.setInt(1, id);
			ResultSet resultado = statement.executeQuery();

			if(resultado.next()) {
				 a = new Aluno();
				a.setId(id);
				a.setNome(resultado.getString("nome"));
				a.setCpf(resultado.getString("cpf"));
				a.setMatricula(resultado.getString("matricula"));
				a.setNota1(resultado.getDouble("nota1"));
				a.setNota2(resultado.getDouble("nota2"));
				a.setMedia(resultado.getDouble("media"));
				a.setSituacaoAluno(resultado.getString("situacao"));
				
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	
	

}
