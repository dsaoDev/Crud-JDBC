package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AlunoDAO;
import dao.ProfessorDAO;
import dao.UsuarioDAO;
import model.Aluno;
import model.Professor;
import model.Usuario;

public class HomePage {
	
	
	public void HomePage() {
		UsuarioDAO uDAO = new UsuarioDAO();
		boolean rodando = true;
		Scanner sc = new Scanner(System.in);
		while (rodando){
			System.out.println();
		System.out.println("[1] -> Realizar cadastro ");
		System.out.println("[2] -> Realizar Login");
		System.out.println("[3] ->     Sair      ");
		System.out.print("Informe sua Opção: ");
		String opcao = sc.nextLine();
		System.out.println();
		
		switch(opcao) {
		case "1":
			Usuario u = new Usuario();
			System.out.print("Login: ");
			u.setLogin(sc.nextLine());
			System.out.print("Senha: ");
			u.setSenha(sc.nextLine());
			
			boolean inserir = uDAO.inserirUsuario(u);
			if(inserir == true) {
				System.out.println("Cadastro Realizado");
			}else {
				System.out.println("Cadastro Falhou");
			}
			break;
			
		case "2": 
			boolean logou = false;
			System.out.print("Login: ");
			String login = sc.nextLine();
			System.out.print("Senha: ");
			String senha = sc.nextLine();
			
			
			u = uDAO.checarLogin(login);
			
			if(u != null && senha.equalsIgnoreCase(u.getSenha())) {
				logou = true;
			}
			
			if(logou == true) {
				System.out.println("\t Bem vindo ");
				System.out.println("Opções para Funcionarios Cadastrados");
				System.out.println("[1] -> Manipular Dados Alunos");
				System.out.println("[2] -> Manipular Dados Professores");
				System.out.println("[3] -> Retornar a Homepage");
				System.out.print("Informe sua opção: ");
				int opcaoZ = sc.nextInt();
				sc.nextLine();
				if(opcaoZ == 1) {
					HomePageAlunos();
				}else if(opcaoZ == 2) {
					HomePageProf();
				}else {
					System.out.println("\t Retornando...");
				}
			}else {
				System.out.println("Login ou Senha Incorretos");
			}
		
			
			break;
			
		case "3": 
			System.out.println("Obrigado por Utilizar nosso Sistema, Até logo... ");
			
			rodando = false;
			break;
			
			
		}
		
		}
	}
	
	public void HomePageProf() {
		Scanner sc = new Scanner(System.in);
		boolean rodando = true;
		ProfessorDAO pDAO = new ProfessorDAO();
		ArrayList<Professor> professores = new ArrayList<>();
		while(rodando) {
			System.out.println("\t Bem vindo ");
			System.out.println("[1] -> Cadastro de Professor");
			System.out.println("[2] -> Listagem de Professores Cadastrados");
			System.out.println("[3] -> Procurar Professor por CPF");
			System.out.println("[4] -> Atualizar Cadastro de Professor via CPF");
			System.out.println("[5] -> Remover Cadastro de Professor via ID");
			System.out.println("[0] -> Logout");
			String opcao = sc.nextLine();
			
			switch(opcao) {
			case "1":
				Professor p = new Professor();
				System.out.print("Nome: ");
				p.setNome(sc.nextLine());
				System.out.println("CPF: ");
				p.setCpf(sc.nextLine());
				System.out.print("Disciplina Lecionada: ");
				p.setDiscEnsinada(sc.nextLine());
				
				boolean inseriu = pDAO.inserir(p);
				if(inseriu ) {
					System.out.println("Professor Cadastrado com Sucesso");
				}else {
					System.out.println("Falha ao Cadastrar Professor, tente novamente");
				}
						
				break;
			case "2":
				professores = pDAO.listar();
				if(professores.size() == 0) {
					System.out.println("Nenhum cadastro consta no Sistema");
				}else {
					System.out.println("\t Lista de Professores Cadastrados ");
					for(int i=0; i<professores.size(); i++) {
						System.out.println("\t    ID [" + professores.get(i).getId() + "]");
						System.out.println("Nome -> " + professores.get(i).getNome());
						System.out.println("CPF -> " + professores.get(i).getCpf());
						System.out.println("Disciplina Lecionada -> " + professores.get(i).getDiscEnsinada());
						System.out.println();
						System.out.println();
					}
				}
				break;
			case "3":
				System.out.print("Informe o CPF do Professor que você está buscando: ");
				p = new Professor();
				p.setCpf(sc.nextLine());
				
				professores = pDAO.buscar(p);
				
				if(professores.size() == 0) {
					System.out.println("CPF Não consta no sistema");
				}else {
					System.out.println("\t Professor encontrado ");
					for(int i=0; i<professores.size(); i++) {
						System.out.println("\t    ID [" + professores.get(i).getId() + "]");
						System.out.println("Nome -> " + professores.get(i).getNome());
						System.out.println("CPF -> " + professores.get(i).getCpf());
						System.out.println("Disciplina Lecionada -> " + professores.get(i).getDiscEnsinada());
						System.out.println();
						System.out.println();
					}
				}
				
				break;
			case "4":
				System.out.print("Informe o CPF do Professor a ter informações atualizadas: ");
				p = new Professor();
				p.setCpf(sc.nextLine());
				System.out.print("Informe o novo nome do Professor: ");
				p.setNome(sc.nextLine());
				System.out.print("Informe a Disciplina lecionada: ");
				p.setDiscEnsinada(sc.nextLine());
				
				boolean atualizou = pDAO.update(p);
				if(atualizou) {
					System.out.println("Atualização bem Sucedida");
				}else {
					System.out.println("Falha ao atualizar tente novamente");
				}
						
				break;
			case "5":
				System.out.print("Informe o ID do professor a ser Removido *REMOVER TODAS INFORMAÇÕES*: ");
				p = new Professor();
				p.setId(sc.nextInt());
				sc.nextLine();
				
				boolean deletou  = pDAO.delete(p);
				if(deletou) {
					System.out.println("Sucesso ao deletar Informações");
				}else {
					System.out.println("Falha ao deletar Informações tente novamente");
				}
				break;
			case "0":
				System.out.println("Deslogando...");
				rodando = false;
				break;
			}
		}
	}
	
	public void HomePageAlunos() {
		boolean rodando = true;
		Scanner sc = new Scanner(System.in);
		AlunoDAO aDAO = new AlunoDAO();
		ArrayList<Aluno> alunos = new ArrayList<>();
		
		while(rodando) {
		System.out.println("[1] -> Cadastrar Aluno (OBS: cadastro de Nome, Matricula, Cpf )");
		System.out.println("[2] -> Listar Cadastro (OBS: listagem de id, nome, matricula , cpf");
		System.out.println("[3] -> Buscar Aluno via Matricula (OBS: retorno de id, nome, matricula , cpf ");
		System.out.println("[4] -> Atualizar Informações de Alunos via ID (OBS: apenas att de nome e matricula permitido)");
		System.out.println("[5] -> Realizar Calculo de Media e situação para o Aluno Cadastrado via ID ");
		System.out.println("[6] -> Deletar Informações de Aluno cadastrado via ID (OBS: TODOS OS DADOS SERÃO PERDIDOS");
		System.out.println("[7] -> Listar MEDIA E SITUAÇÃO DO ALUNO SELECIONADO VIA ID");
		System.out.println("[0] -> Fazer Logout");
		System.out.print("-----> Informe sua Opção: ");
		String opcao = sc.nextLine();
		
		switch(opcao) {
		case "1":
			Aluno a = new Aluno();
			System.out.print("Nome: ");
			a.setNome(sc.nextLine());
			
			System.out.print("CPF: ");
			a.setCpf(sc.nextLine());
			
			System.out.print("Matricula: ");
			a.setMatricula(sc.nextLine());
			
			boolean cadastrou = aDAO.inserir(a);
			
			if(cadastrou) {
				System.out.println("Cadastro Realizado Com Sucesso");
			}else {
				System.out.println("Falha ao cadastrar tente novamente");
			}
			
		break;
		
		case "2":
			alunos = aDAO.listarInfoAluno();
			
			if(alunos.size() == 0) {
				System.out.println("Nenhum aluno cadastrado no sistema");
			}else {
				for(int i=0; i<alunos.size(); i++) {
					System.out.println("\t     ID [" + alunos.get(i).getId() + "]");
					System.out.println("Nome -> " + alunos.get(i).getNome());
					System.out.println("CPF  -> " + alunos.get(i).getCpf());
					System.out.println("Matricula -> " + alunos.get(i).getMatricula());
					System.out.println();
					System.out.println();
				}
			}
			break;
			
		case "3":
			a = new Aluno();
			System.out.print("Informe a Matricula do aluno a ser buscado: ");
			a.setMatricula(sc.nextLine());
			
			alunos = aDAO.buscarAlunoViaMatricula(a);
			
			if(alunos.size() == 0) {
				System.out.println("Matricula não existe no Banco de Dados do Sistema");
			}else {
				System.out.println("\t Aluno encontrado");
			for(int i=0; i<alunos.size(); i++) {
				System.out.println("\t     ID [" + alunos.get(i).getId() + "]");
				System.out.println("Nome -> " + alunos.get(i).getNome());
				System.out.println("CPF  -> " + alunos.get(i).getCpf());
				System.out.println("Matricula -> " + alunos.get(i).getMatricula());
			}
			}
			break;
			
		case "4":
			System.out.print("Informe o ID do aluno que você deseja att as Informações: ");
			a = new Aluno();
			a.setId(sc.nextInt());
			sc.nextLine();
			System.out.print("Informe o novo Nome: ");
			a.setNome(sc.nextLine());
			System.out.print("Informe a nova Matricula: ");
			a.setMatricula(sc.nextLine());
			boolean atualizou = aDAO.updateById(a);
			
			if(atualizou) {
				System.out.println("Atualização Concluida com Sucesso");
			}else {
				System.out.println("Atualização Falhou tente novamente");
			}
			
			break;
			
		case "5":
			a = new Aluno();
			System.out.print("Informe o Id do aluno que deseja calcular a Media: ");
			a.setId(sc.nextInt());
			sc.nextLine();
			System.out.print("Informe a 1ª Nota: ");
			a.setNota1(sc.nextDouble());
			
			System.out.print("Informe a 2ª Nota: ");
			a.setNota2(sc.nextDouble());
			
			double media = (a.getNota1() + a.getNota2() ) /2 ;
			a.setMedia(media);
			
			if(a.getMedia() < 3) {
				a.setSituacaoAluno("Reprovado ");
			}else if(a.getMedia() >= 3 && a.getMedia() < 7) {
				a.setSituacaoAluno("Aguardando Final");
			}else {
				a.setSituacaoAluno("Aprovado");
			}
			
			boolean inseriuMedia = aDAO.inserirMedia(a);
			sc.nextLine();
			
			if(inseriuMedia) {
				System.out.println("Media e Situação inseridas com Sucesso");
			}else {
				System.out.println("Falha ao Inserir Media ");
			}
			
			break;
			
		case "6":
			System.out.print("Informe o ID do aluno p deletar *TODAS INFORMAÇÕES* : ");
			a = new Aluno();
			a.setId(sc.nextInt());
			sc.nextLine();
			
			boolean deletou = aDAO.delete(a);
			if(deletou) {
				System.out.println("Informações do Aluno Removidas do Sistema");
			}else {
				System.out.println("Falha ao tentar Deletar Informações");
			}
			break;
			
		case "7":
			boolean alunoTemMedia = false;
			a = new Aluno();
			System.out.print("Informe o id do Aluno a ser mostrado a situação e media: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			a = aDAO.buscarAlunoComMedia(id);
			
			if(a != null && id == a.getId() && a.getSituacaoAluno() != null) {
				alunoTemMedia = true;
				System.out.println("\t     ID [" + a.getId() + "]");
				System.out.println("Nome -> " + a.getNome());
				System.out.println("CPF  -> " + a.getCpf());
				System.out.println("Matricula -> " + a.getMatricula());
				System.out.printf("Nota 1 -> %.2f%n", a.getNota1());
				System.out.printf("Nota 2 -> %.2f%n", a.getNota2());
				System.out.printf("Media -> %.2f%n", a.getMedia());
				System.out.println("Situação - > " + a.getSituacaoAluno());
			}
			
			if(alunoTemMedia == false) {
				System.out.println("Esse Aluno não possui Situação o ID não existe");
				System.out.println();
			}
			
			break;
			
		case "0":
			System.out.println("Deslogando...");
			rodando  = false;
			break;
		}
		}
	}

}
