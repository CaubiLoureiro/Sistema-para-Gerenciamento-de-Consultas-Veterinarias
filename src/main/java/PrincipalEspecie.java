import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.EspecieNaoEncontradoException;
import modelo.Animal;
import modelo.Especie;
import modelo.SingletonPerfis;
import service.EspecieAppService;

public class PrincipalEspecie {
	public static void main(String[] args) {
		String nome;
		Especie umaEspecie;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		EspecieAppService especieAppService = (EspecieAppService) fabrica.getBean("especieAppService");
		
		 SingletonPerfis singletonPerfis = SingletonPerfis.getSingletonPerfis();
		
	     singletonPerfis.setPerfis(new String[] {"user"});

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma espécie");
			System.out.println("2. Alterar uma espécie");
			System.out.println("3. Remover uma espécie");
			System.out.println("4. Listar uma espécie e seus animais");
			System.out.println("5. Listar todas as espécies e seus animais");
			System.out.println("6. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

			switch (opcao) {
			case 1: {
				nome = Console.readLine('\n' + "Informe o nome da espécie: ");
				
				Especie especie = new Especie(nome);

				long numero = especieAppService.inclui(especie);

				System.out.println('\n' + "Espécie número " + numero + " incluída com sucesso!");

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número da espécie que você deseja alterar: ");

				try {
					umaEspecie = especieAppService.recuperaUmEspecie(resposta);
				} catch (EspecieNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaEspecie.getId() + 
						              "    Nome = " + umaEspecie.getNome());

				System.out.println(" ");

				int opcaoAlteracao = 1;

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umaEspecie.setNome(novoNome);

					try {
						especieAppService.altera(umaEspecie);

						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (EspecieNaoEncontradoException e) {
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
					umaEspecie = especieAppService.recuperaUmEspecie(resposta);
				} catch (EspecieNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaEspecie.getId() + 
			              "    Nome = " + umaEspecie.getNome());

				String resp = Console.readLine('\n' + "Confirma a remoção da espécie?");

				if (resp.equals("s")) {
					try {
						especieAppService.exclui(umaEspecie);
						System.out.println('\n' + "Espécie removido com sucesso!");
					} catch (EspecieNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Espécie não removida.");
				}

				break;
			}
			
			
			
			

			case 4: {
				long numero = Console.readInt('\n' + "Informe o número da espécie: ");

				try {
					umaEspecie = especieAppService.recuperaUmEspecieEAnimais(numero);
				} catch (EspecieNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaEspecie.getId() + 
			              "    Nome = " + umaEspecie.getNome());

				List<Animal> animais = umaEspecie.getAnimais();

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
				List<Especie> especies = especieAppService.recuperaEspeciesEAnimais();

				if (especies.size() != 0) {
					System.out.println("");

					for (Especie especie : especies) {
						System.out.println('\n' + "Número = " + especie.getId() + 
					              "    Nome = " + especie.getNome());

						List<Animal> animais = especie.getAnimais();

						for (Animal animal : animais) {
							System.out.println( '\n'+"Número = " + animal.getId() + 
						              "   Nome = " + animal.getNome() + 
						              "   Data de Nascimento = " + animal.getDataNasc() +
						              "   Raça = " + animal.getRaca()
										);

						}
					}
				} else {
					System.out.println('\n' + "Não há espécies cadastrados.");
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
