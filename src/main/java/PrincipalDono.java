import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.DonoNaoEncontradoException;
import modelo.Animal;
import modelo.Dono;
import service.DonoAppService;

public class PrincipalDono {
	public static void main(String[] args) {
		String nome;
		String telefone;
		String email;
		String endereco;
		Dono umDono;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		DonoAppService donoAppService = (DonoAppService) fabrica.getBean("donoAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um dono");
			System.out.println("2. Alterar um dono");
			System.out.println("3. Remover um dono");
			System.out.println("4. Listar um dono e seus animais");
			System.out.println("5. Listar todos os donos e seus animais");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome do Dono: ");
				telefone = Console.readLine('\n' + "Informe o telefone do Dono: ");
				email = Console.readLine('\n' + "Informe o email do Dono: ");
				endereco = Console.readLine('\n' + "Informe o endereço do Dono: ");

				Dono dono = new Dono(nome, telefone, email, endereco);

				long numero = donoAppService.inclui(dono);

				System.out.println('\n' + "Dono número " + numero + " incluído com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do dono que você deseja alterar: ");

				try {
					umDono = donoAppService.recuperaUmDono(resposta);
				} catch (DonoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umDono.getId() + 
						              "    Nome = " + umDono.getNome() + 
						              "    Telefone = " + umDono.getTelefone() + 
						              "    Email = " + umDono.getEmail() + 
						              "    Endereco = " + umDono.getEndereco());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Telefone");
				System.out.println("3. Email");
				System.out.println("4. Endereco");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 4:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umDono.setNome(novoNome);

					try {
						donoAppService.altera(umDono);

						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (DonoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novoTelefone = Console.readLine("Digite o novo telefone: ");
					umDono.setTelefone(novoTelefone);

					try {
						donoAppService.altera(umDono);

						System.out.println('\n' + "Alteração do telefone efetuada " + "com sucesso!");
					} catch (DonoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
					
				case 3:
					String novoEmail = Console.readLine("Digite o novo Email: ");
					umDono.setEmail(novoEmail);

					try {
						donoAppService.altera(umDono);

						System.out.println('\n' + "Alteração do Email efetuada " + "com sucesso!");
					} catch (DonoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
					
				case 4:
					String novoEndereco = Console.readLine("Digite o novo endereço: ");
					umDono.setEndereco(novoEndereco);

					try {
						donoAppService.altera(umDono);

						System.out.println('\n' + "Alteração do endereço efetuada " + "com sucesso!");
					} catch (DonoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
				
				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}
			

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do dono que você deseja remover: ");

				try {
					umDono = donoAppService.recuperaUmDono(resposta);
				} catch (DonoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umDono.getId() + 
			              "    Nome = " + umDono.getNome() + 
			              "    Telefone = " + umDono.getTelefone() + 
			              "    Email = " + umDono.getEmail() + 
			              "    Endereço = " + umDono.getEndereco());

				String resp = Console.readLine('\n' + "Confirma a remoção do dono?");

				if (resp.equals("s")) {
					try {
						donoAppService.exclui(umDono);
						System.out.println('\n' + "Dono removido com sucesso!");
					} catch (DonoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Dono não removido.");
				}

				break;
			}

			case 4: {
				long numero = Console.readInt('\n' + "Informe o número do dono: ");

				try {
					umDono = donoAppService.recuperaUmDonoEAnimais(numero);
				} catch (DonoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umDono.getId() + 
			              "    Nome = " + umDono.getNome() + 
			              "    Telefone = " + umDono.getTelefone() + 
			              "    Email = " + umDono.getEmail() + 
			              "    Endereço = " + umDono.getEndereco());

				List<Animal> animais = umDono.getAnimais();

				for (Animal animal : animais) {
					System.out.println( '\n'+"Número = " + animal.getId() + 
				              "   Nome = " + animal.getNome() + 
				              "   Data de Nascimento = " + animal.getDataNasc() +
				              "   Raça = " + animal.getRaca()
								);

				}

				break;
			}

			case 5: {
				List<Dono> donos = donoAppService.recuperaDonosEAnimais();

				if (donos.size() != 0) {
					System.out.println("");

					for (Dono dono : donos) {
						System.out.println('\n' + "Número = " + dono.getId() + 
					              "    Nome = " + dono.getNome() + 
					              "    Telefone = " + dono.getTelefone() + 
					              "    Email = " + dono.getEmail() + 
					              "    Endereço = " + dono.getEndereco());

						List<Animal> animais = dono.getAnimais();

						for (Animal animal : animais) {
							System.out.println( '\n'+"Número = " + animal.getId() + 
						              "   Nome = " + animal.getNome() + 
						              "   Data de Nascimento = " + animal.getDataNasc() +
						              "   Raça = " + animal.getRaca()
										);

						}
					}
				} else {
					System.out.println('\n' + "Não há donos cadastrados.");
				}

				break;
			}

			case 6: {
				continua = false;
				break;
			}

			
			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
