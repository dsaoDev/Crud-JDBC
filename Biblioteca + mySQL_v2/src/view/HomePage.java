package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.LivroDAO;
import dao.UsuarioDAO;
import model.Livro;
import model.Usuario;

public class HomePage {
	
	
	
	public void homePage() {
		Scanner sc = new Scanner(System.in);
		boolean rodando = true;
		
		
		LivroDAO livroDAO = new LivroDAO();
		
		while(rodando) {
		
			System.out.println();
		System.out.println("\t   # Bem vindo a Biblioteca #");
		System.out.println("[1] -> Cadastrar Livro na Biblioteca");
		System.out.println("[2] -> Remover Livro partindo do *TITULO*");
		System.out.println("[3] -> Listar todos os Livros cadastrado");
		System.out.println("[4] -> Pesquisar Livro partindo do *ISBN*");
		System.out.println("[5] -> Pesquisar Livro partindo do *TITULO*");
		System.out.println("[6] -> Atualizar Livro partindo do *TITULO*");
		System.out.println("[7] -> Pesquisar Livro partindo do *AUTOR* --- OBS: * Listando todos os Livros do autor selecionado *");
		System.out.println("[8] -> Remover Livro partindo do *AUTOR*   --- OBS: * Apagando todos os Livros do autor selecionado *");
		System.out.println("[9] -> Fazer Logout do Sistema");
		System.out.print("Digite sua opção -> ");
		int opcao = sc.nextInt();
		sc.nextLine();
		
		switch(opcao) {

		case 1:
			Livro livro = new Livro();

			System.out.print("Informe o Titulo ");
			livro.setTitulo(sc.nextLine());
			System.out.print("Informe o Autor ");
			livro.setAutor(sc.nextLine());
			System.out.print("Informe o Isbn ");
			livro.setIsbn(sc.nextLine());
			System.out.print("Informe o Codigo ");
			livro.setCodigo(sc.nextInt());
			sc.nextLine();
			System.out.print("Informe o Número de Paginas ");
			livro.setNumPaginas(sc.nextInt());
			sc.nextLine();
			System.out.print("Informe o valor Aproximado: ");
			livro.setValor(sc.nextDouble());
			
			boolean funcionou = livroDAO.cadastrarLivro(livro);
			
			if(funcionou) {
				System.out.println("Livro Cadastrado Com Sucesso");
			}else {
				System.out.println("Falha ao Cadastrar, tente novamente");
			}
			break;
			
		case 2:
			livro = new Livro();
			System.out.print("Informe o Titulo do livro a ser removido: ");
			livro.setTitulo(sc.nextLine());
			
			boolean funcionouRemocao = livroDAO.removerLivro(livro);
			
			if(funcionouRemocao) {
				System.out.println("Livro removido Com Sucesso");
			}else {
				System.out.println("Falha ao Remover, tente novamente");
			}
			break;

			
		case 3:
			ArrayList<Livro> livros = new ArrayList<>();
			
			livros = livroDAO.listarLivros();
			for(int i=0; i<livros.size(); i++) {
				System.out.println("\t  ID " + livros.get(i).getId());
				System.out.println("Titulo " + livros.get(i).getTitulo());
				System.out.println("Autor " + livros.get(i).getAutor());
				System.out.println("Isbn " + livros.get(i).getIsbn());
				System.out.println("Codigo " + livros.get(i).getCodigo());
				System.out.println("Número de Paginas " + livros.get(i).getNumPaginas());
				System.out.printf("ValorAproximado R$ %.2f%n",livros.get(i).getValor());
				System.out.println();
			}
	
	break;

	
		case 4:
			
			boolean livroexiste = false;
			livro = new Livro();
			System.out.print("Informe o Isbn do Livro a ser pesquisado: ");
			livro.setIsbn(sc.nextLine());
			
			livros = livroDAO.pesquisarLivroviaISBN(livro);
			
			
			
			for(int i=0; i<livros.size(); i++) {
				if(livro.getIsbn().equals(livros.get(i).getIsbn())) {
					livroexiste = true;
				System.out.println("ID " + livros.get(i).getId());
				System.out.println("Titulo " + livros.get(i).getTitulo());
				System.out.println("Autor " + livros.get(i).getAutor());
				System.out.println("Isbn " + livros.get(i).getIsbn());
				System.out.println("Codigo " + livros.get(i).getCodigo());
				System.out.println("Número de Paginas " + livros.get(i).getNumPaginas());
				System.out.printf("ValorAproximado R$ %.2f%n",livros.get(i).getValor());
				System.out.println();
				
			}
			}
			if(livroexiste == false) {
				System.out.println("Isbn Informado não consta no banco de dados");
			}
	
	break;

	
		case 5:
			 livroexiste = false;
			livro = new Livro();
			System.out.println("Informe o titulo do Livro a ser pesquisado: ");
			livro.setTitulo(sc.nextLine());
			
			livros = livroDAO.pesquisarLivroviaTitulo(livro);
			
			for(int i=0; i<livros.size(); i++) {
				if(livro.getTitulo().equals(livros.get(i).getTitulo())) {
					livroexiste = true;
				System.out.println("ID " + livros.get(i).getId());
				System.out.println("Titulo " + livros.get(i).getTitulo());
				System.out.println("Autor " + livros.get(i).getAutor());
				System.out.println("Isbn " + livros.get(i).getIsbn());
				System.out.println("Codigo " + livros.get(i).getCodigo());
				System.out.println("Número de Paginas " + livros.get(i).getNumPaginas());
				System.out.printf("ValorAproximado R$ %.2f%n",livros.get(i).getValor());
				System.out.println();
				
			}
			}
			if(livroexiste == false) {
				System.out.println("Titulo informado não consta no banco de dados");
			}
	
	break;

	
		case 6:
			livro = new Livro();
			System.out.print("Informe o Titulo do Livro a receber o Update: ");
			livro.setTitulo(sc.nextLine());
			
			System.out.print("Informe o novo Titulo: ");
			String novoTitulo = sc.nextLine();
			
			System.out.print("Informe o novo Autor: ");
			livro.setAutor(sc.nextLine());
			
			System.out.print("Informe o novo Isbn: ");
			livro.setIsbn(sc.nextLine());
			
			System.out.print("Informe o novo Codigo: ");
			livro.setCodigo(sc.nextInt());
			sc.nextLine();
			
			System.out.print("Informe o novo Valor: ");
			livro.setValor(sc.nextDouble());
			
			System.out.print("Informe o Número de Paginas: ");
			livro.setNumPaginas(sc.nextInt());
			sc.nextLine();
			
			boolean atualizado  = livroDAO.updateLivro(livro, novoTitulo);
			if(atualizado) {
				System.out.println("Update realizado com Sucesso");
			}else {
				System.out.println("Falha ao tentar dar Update");
			}
	break;

		case 7:
			livroexiste = false;
			 livro = new Livro();
			System.out.print("Informe o nome do Autor que deseja Listar os livros: ");
			livro.setAutor(sc.nextLine());
			
			
			livros = livroDAO.pesquisarLivroviaAutor(livro);
			
			for(int i=0; i<livros.size(); i++) {
				if(livro.getAutor().equals(livros.get(i).getAutor())) {
					livroexiste = true;
				System.out.println("ID " + livros.get(i).getId());
				System.out.println("Titulo " + livros.get(i).getTitulo());
				System.out.println("Autor " + livros.get(i).getAutor());
				System.out.println("Isbn " + livros.get(i).getIsbn());
				System.out.println("Codigo " + livros.get(i).getCodigo());
				System.out.println("Número de Paginas " + livros.get(i).getNumPaginas());
				System.out.printf("ValorAproximado R$ %.2f%n",livros.get(i).getValor());
				System.out.println();
				
			}
			}
			if(livroexiste == false) {
				System.out.println("Titulo não consta no nosso banco de dados");
			}
	
	break;

	
		case 8:
			livro = new Livro();
			System.out.print("Informe o nome do Autor *Obs vc ira deletar todos os Livros desse Autor* -> ");
			livro.setAutor(sc.nextLine());
			
			 funcionou = livroDAO.deletarViaAutor(livro);
			if(funcionou) {
				System.out.println("Livro(s) Deletados com Sucesso");
			}else {
				System.out.println("Falha ao tentar Deletar os Livro(s)");
			}
	
	break;
	
		case 9:
			System.out.println("Deslogando...");
			rodando = false;
			break;
			
			default:
				System.out.println(" *** Selecione um Opção Valida *** ");
				break;


		}//switchcase
		
		}//while
	}//fim do metodo
	public void homePageUsuario() {
		boolean rodando = true;
		Scanner sc = new Scanner(System.in);
		
		UsuarioDAO uDAO = new UsuarioDAO();
		System.out.println("\t Bem vindo");
		while(rodando) {
			try {
			System.out.println();
			System.out.println("[1] -> Realizar Cadastro");
			System.out.println("[2] -> Fazer Login ");
			System.out.println("[3] -> Sair");
			System.out.print("Digite sua opção: ");
			int opcao = sc.nextInt();
			sc.nextLine();
			
			switch(opcao) {
			case 1:
				System.out.println("==== Cadastro de Usuario ====");
				Usuario u = new Usuario();
				System.out.print("Email: ");
				u.setEmail(sc.nextLine());
				System.out.print("Senha: ");
				u.setSenha(sc.nextLine());
				
				boolean cadastrado = uDAO.realizarCadastro(u);
				if(cadastrado) {
					System.out.println("Cadastrado");
				}else {
					System.out.println("Falha");
				}
				break;
				
			case 2:
				boolean logarSucesso = false;
				System.out.println("==== Logar ==== ");
				System.out.print("Email: ");
				String email = sc.nextLine();
				System.out.print("Senha: ");
				String senha = sc.nextLine();
				
				 u = uDAO.checarEmail(email);
				 
				
				 
				 if(u != null && u.getSenha().equals(senha)) {
					 logarSucesso = true;
				 }
				 
				 if(logarSucesso) {
					 System.out.println("Logando...");
					 homePage();
				 }else {
					 System.out.println("Login ou Senha Incorretos");
				 }
				 
				
				 
				
				break;
				
			case 3:
				System.out.println();
				System.out.println("Saindo... ");
				System.out.println("\t Programa Finalizado");
				rodando = false;
				break;
				
				default:
					System.out.println("\t Opção Invalida ");
					break;
			}
		}catch(Exception e) {
			System.out.println("Algo deu errado");
			System.out.println("Retornado a HomePage");
		}
			
		}//while

		}
	
	
}
