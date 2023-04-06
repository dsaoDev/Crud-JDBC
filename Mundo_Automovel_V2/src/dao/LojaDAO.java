package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

import model.Carro;
import model.Pessoa;

public class LojaDAO {
	
 private final String DB_URL =  "jdbc:mysql://localhost:3306/loja";
 private final String DB_USER = "root";
 private final String DB_PASSWORD = "root";
 
 private final String INSERT_SQL = "INSERT INTO loja.carros (placa, marca, modelo, ano, valorAproximado, nome_Pessoa,cpf_pessoa, telefone) VALUES (?, ?, ?, ? , ? , ? ,?, ?) ";
 private final String SELECT_SQL = "SELECT * FROM loja.carros";	
 private final String DELETE_SQL = "DELETE FROM loja.carros WHERE id = ?";
 private final String UPDATE_SQL = "UPDATE loja.carros SET nome_Pessoa = ?, cpf_pessoa = ? WHERE id = ? ";
		 
public boolean inserirCarros(Carro carro) {
	
         boolean sucesso = false;	
	try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection conn  = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement statement = conn.prepareStatement(INSERT_SQL);
				
	
		statement.setString(1, carro.getPlaca());
		statement.setNString(2, carro.getMarca());
		statement.setNString(3, carro.getModelo());
		statement.setInt(4, carro.getAno());
		statement.setDouble(5, carro.getValorAproximado());
		statement.setString(6, carro.getDonoDoCarro().getNome());
		statement.setNString(7, carro.getDonoDoCarro().getCpf());
		statement.setString(8, carro.getDonoDoCarro().getTelefone());
		
		int affectedRows = statement.executeUpdate();
		
		if(affectedRows > 0) {
			sucesso = true;
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sucesso;
	 
 }

public ArrayList<Carro> listarCarros(){
	
	ArrayList<Carro> listaCarros = new ArrayList<Carro>();
	try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection(DB_URL,DB_USER, DB_PASSWORD);
		
		PreparedStatement statement = conn.prepareStatement(SELECT_SQL);
		
		ResultSet resultado = statement.executeQuery();
	
		while(resultado.next()) {
			
			//int id = resultado.getInt("id");
			//String placa = resultado.getString("placa");
			//String marca = resultado.getString("marca");
			//String modelo = resultado.getString("modelo");
			//int ano = resultado.getInt("ano");
			//double valorAproximado = resultado.getDouble("valorAproximado");
			//String nome_Pessoa = resultado.getString("nome_Pessoa");
			//String cpf_pessoa = resultado.getString("cpf_pessoa");
		//	String telefone = resultado.getString("telefone");
			
			Carro carro = new Carro();
			Pessoa pessoa = new Pessoa();
			pessoa.setNome(resultado.getString("nome_P	essoa"));
			carro.setId(resultado.getInt("id"));
			
			//pessoa.setNome(nome_Pessoa);
			//pessoa.setCpf(cpf_pessoa);
			//pessoa.setTelefone(telefone);
			//carro.setId(id);
			//carro.setPlaca(placa);
			//carro.setMarca(marca);
			//carro.setModelo(modelo);
			//carro.setAno(ano);
			//carro.setValorAproximado(valorAproximado);
			//carro.setDonoDoCarro(pessoa);
			
			listaCarros.add(carro);
		}
		
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return listaCarros;
	
}
public boolean deletarCarro(Carro carro) {
	
	boolean sucesso = false;
	try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		PreparedStatement statement = conn.prepareStatement(DELETE_SQL);
		
		statement.setInt(1, carro.getId());
		int rowsAffected = statement.executeUpdate();
		
		if(rowsAffected > 0) {
			sucesso = true;
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return sucesso;
}

public boolean UpdateCarro(Carro carro) {
	
	boolean sucesso = false;
	try {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
		PreparedStatement statement = conn.prepareStatement(UPDATE_SQL);
		
		statement.setString(1, carro.getDonoDoCarro().getNome());
		statement.setString(2, carro.getDonoDoCarro().getCpf());
		statement.setInt(3, carro.getId());
		
		int rowsAffected = statement.executeUpdate();
		if(rowsAffected > 0) {
			sucesso = true;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sucesso;
	
}
 


 
}
