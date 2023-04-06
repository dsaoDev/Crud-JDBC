package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UsuarioDAO;
import model.Usuario;

public class SwitchCaseLogarECadastrar {

	
	
	
	
	public void switchCaseGeral() {
		
		boolean rodando = true;
		Scanner sc = new Scanner(System.in);
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		//Usuario usuario = new Usuario();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		
		while(rodando) {
			try {
			System.out.println();
		System.out.println("[1] -> Realizar Cadastro");
		System.out.println("[2] -> Fazer Loguin");
		System.out.println("[3] -> Sair");
		System.out.print("Informe sua opção --> ");
		int opcao = sc.nextInt();
		sc.nextLine();
		
		switch(opcao) {
		case 1:
			Usuario usuario = new Usuario();
			System.out.print("Email: ");
			usuario.setEmail(sc.nextLine());
			System.out.print("Senha: ");
			usuario.setSenha(sc.nextLine());
			boolean cadastradoComSucesso = usuarioDAO.cadastrarUsuario(usuario);
			
			if(cadastradoComSucesso == true) {
				System.out.println("Usuario Cadastrado com Sucesso");
			}else {
				System.out.println("Falha ao cadastrar Usuario");
			}
			break;
			
		case 2:
			boolean loginSucesso = false;
			
		
			Usuario u = new Usuario();
			
			System.out.println("==== LOGIN ==== ");
			System.out.print("Email: ");
			String email = sc.nextLine();
			System.out.print("Senha: ");
			String senha = sc.nextLine();
			
			//Checar email
			u = usuarioDAO.ChecarEmailDoUsuario(email);
			
			
			if(u != null && u.getSenha().equals(senha)) {
				loginSucesso = true;
			}
			
			if(loginSucesso == true) {
				System.out.println("-----> Login Feito com Sucesso");
				HomePageCarros homePage = new HomePageCarros();
				homePage.homePageCarros();
			}else {
				System.out.println("Email/senha incorretos");
			}
				
			break;
			
		case 3:
	System.out.println("Finishing...");
	System.out.println("\t Program Ended");
	rodando = false;
	break;
		}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
