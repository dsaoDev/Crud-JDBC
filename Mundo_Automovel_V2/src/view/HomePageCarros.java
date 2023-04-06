package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.LojaDAO;
import model.Carro;
import model.Pessoa;

public class HomePageCarros {

	
	
	
	
	public void homePageCarros() {
		boolean codigoroda = true;
		Carro carro = new Carro();
		Pessoa pessoa = new Pessoa();
		LojaDAO loja = new LojaDAO();
	 ArrayList<Carro> listaCarros = new ArrayList<>();
	 
		Scanner sc = new Scanner(System.in);
		
		while(codigoroda) {
			
			System.out.println();
			System.out.println("[1] Inserir Carros");
			System.out.println("[2] Deletar Carros");
			System.out.println("[3] Update Carros");
			System.out.println("[4] Listar Carros");
			System.out.println("[5] Fazer Logout");
			System.out.print("Informe sua Opção: ");
			int opcao = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			switch(opcao) {
			case 1:
				System.out.print("Placa: ");
				carro.setPlaca(sc.nextLine());
				System.out.print("Marca: ");
				carro.setMarca(sc.nextLine());
				System.out.print("Modelo: ");
				carro.setModelo(sc.nextLine());
				System.out.print("Valor: ");
				carro.setValorAproximado(sc.nextDouble());
				System.out.print("Ano: ");
				carro.setAno(sc.nextInt());
				sc.nextLine();
				System.out.print("Nome do Proprietario do carro: ");
				pessoa.setNome(sc.nextLine());
				System.out.print("CPF: ");
				pessoa.setCpf(sc.nextLine());
				System.out.print("Telefone: ");
				pessoa.setTelefone(sc.nextLine());
				carro.setDonoDoCarro(pessoa);
				
				boolean cadastroCarro = loja.inserirCarros(carro);
				if(cadastroCarro == true) {
					System.out.println("Carro cadastrado com Sucesso");
				}else {
					System.out.println("Falha ao Cadastrar carro");
				}
				
				break;
				
			case 2:
				listaCarros = loja.listarCarros();
				for(int i=0; i<listaCarros.size(); i++) {
					System.out.println("Id -> " + listaCarros.get(i).getId() + " Proprietario -> " + listaCarros.get(i).getDonoDoCarro().getNome() + " CPF -> " + listaCarros.get(i).getDonoDoCarro().getCpf() + " Modelo do Carro:  " + listaCarros.get(i).getModelo());

				}
				
				System.out.print("Informe o id do Carro que deseja deletar: ");
				int id = sc.nextInt();
				sc.nextLine();
				
				carro.setId(id);
				boolean deletado = loja.deletarCarro(carro);
				
				if(deletado) {
					System.out.println("Deletado");
				}else {
					System.out.println("Falha ao deletar");
				}
				
				break;
				
			case 3:
				listaCarros = loja.listarCarros();
				for(int i=0; i<listaCarros.size(); i++) {
					System.out.println("Id -> " + listaCarros.get(i).getId() + " Proprietario -> " + listaCarros.get(i).getDonoDoCarro().getNome() + " CPF -> " + listaCarros.get(i).getDonoDoCarro().getCpf() + " Modelo do Carro:  " + listaCarros.get(i).getModelo());

				}
				
				System.out.print("Informe o id do Carro que deseja atualizar: ");
				id = sc.nextInt();
				sc.nextLine();
				System.out.print("Informe o novo proprietario: ");
				String novoDono = sc.nextLine();
				System.out.print("Informe o novo CPF: ");
				String novoCpf = sc.nextLine();
				
				pessoa.setNome(novoDono);
				pessoa.setCpf(novoCpf);
				carro.setId(id);
				carro.setDonoDoCarro(pessoa);
				
				boolean atualizado = loja.UpdateCarro(carro);
				
				if(atualizado) {
					System.out.println("atualizado");
				}else {
					System.out.println("Falha ao atualizar");
				}

				break;
				
			case 4:
				listaCarros = loja.listarCarros();
				
				for(int i=0; i<listaCarros.size(); i++) {
					System.out.println("ID " + listaCarros.get(i).getId());
					System.out.println("Nome proprietario " + listaCarros.get(i).getDonoDoCarro().getNome());
					System.out.println("CPF " + listaCarros.get(i).getDonoDoCarro().getCpf());
					System.out.println("Modelo do carro: " + listaCarros.get(i).getModelo());
					System.out.println("Marca: " + listaCarros.get(i).getMarca());
					System.out.println();
				}
				break;
				
			case 5:
				System.out.println("Logout...");
				codigoroda = false;
				
				break;
			
			}
		
	
	
	}
	
	}
}

