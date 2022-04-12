import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.EspecieNaoEncontradoException;
import excecao.AnimalNaoEncontradoException;
import excecao.DonoNaoEncontradoException;
import modelo.Animal;
import modelo.Consulta;
import modelo.Dono;
import modelo.Especie;
import service.AnimalAppService;
import service.ConsultaAppService;
import service.DonoAppService;
import service.EspecieAppService;

public class PrincipalAnimal {
	public static void main(String[] args) {
		String nome;
		String dataNasc;
		String raca;
		Animal umAnimal;
		Dono umDono;
		Especie umaEspecie;
		@SuppressWarnings({ "resource" })
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		AnimalAppService animalAppService = (AnimalAppService) fabrica.getBean("animalAppService");
		ConsultaAppService consultaAppService = (ConsultaAppService) fabrica.getBean("consultaAppService");
		DonoAppService donoAppService = (DonoAppService) fabrica.getBean("donoAppService");
		EspecieAppService especieAppService = (EspecieAppService) fabrica.getBean("especieAppService");
		
		
		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um animal");
			System.out.println("2. Alterar um animal");
			System.out.println("3. Remover um animal");
			System.out.println("4. Listar um animal e suas Consultas");
			System.out.println("5. Listar todos os animais e suas consultas");
			System.out.println("6. Listar primeiro animal");
			System.out.println("7. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");

			switch (opcao) {
			case 1: {
				long idDono = Console.readInt('\n' + "Informe o número do Dono: ");

				try {
					umDono = donoAppService.recuperaUmDono(idDono);
				} catch (DonoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				long idEspecie = Console.readInt('\n' + "Informe o número da Espécie: ");

				try {
					umaEspecie = especieAppService.recuperaUmEspecie(idEspecie);
				} catch (EspecieNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				
				nome = Console.readLine('\n' + "Informe o nome do animal: ");
				dataNasc = Console.readLine('\n' + "Informe o data de nascimento do animal: ");
				raca = Console.readLine('\n' + "Informe a raça do animal: ");

				Animal animal = new Animal(nome, dataNasc, raca, umDono, umaEspecie);
				
				long numero = animalAppService.inclui(animal);
				System.out.println('\n' + "Animal número " + numero + " incluído com sucesso!");
				
				
				break;
				
			}
			
			
			
			

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número do animal que você deseja alterar: ");

				try {
					umAnimal = animalAppService.recuperaUmAnimal(resposta);
				} catch (AnimalNaoEncontradoException e ) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umAnimal.getId() + 
						              "    Nome = " + umAnimal.getNome() + 
						              "    Data de Nascimento = " + umAnimal.getDataNasc() +
						              "    Raça = " + umAnimal.getRaca());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1. Nome");
				System.out.println("2. Data de Nascimento");
				System.out.println("3. Raça");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 3:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					umAnimal.setNome(novoNome);

					try {
						animalAppService.altera(umAnimal);

						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (AnimalNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novaData = Console.readLine("Digite a nova data: ");
					umAnimal.setDataNasc(novaData);

					try {
						animalAppService.altera(umAnimal);

						System.out.println('\n' + "Alteração da data de nascimento efetuada " + "com sucesso!");
					} catch (AnimalNaoEncontradoException e ) {
						System.out.println('\n' + e.getMessage());
					}

					break;
					
				case 3:
					String novaRaca = Console.readLine("Digite a nova raça: ");
					umAnimal.setRaca(novaRaca);

					try {
						animalAppService.altera(umAnimal);

						System.out.println('\n' + "Alteração da raça efetuada com sucesso!");
					} catch (AnimalNaoEncontradoException e ) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}

			case 3: {
				int resposta = Console.readInt('\n' + "Digite o número do animal que você deseja remover: ");

				try {
					umAnimal = animalAppService.recuperaUmAnimal(resposta);
				} catch (AnimalNaoEncontradoException e ) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umAnimal.getId() + 
						              "    Nome = " + umAnimal.getNome() + 
						              "    Data de Nascimento = " + umAnimal.getDataNasc() +
						              "    Raça = " + umAnimal.getRaca());

				String resp = Console.readLine('\n' + "Confirma a remoção do animal?");

				if (resp.equals("s")) {
					try {
						animalAppService.exclui(umAnimal);
						System.out.println('\n' + "Animal removido com sucesso!");
					} catch (AnimalNaoEncontradoException e ) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Animal não removido.");
				}

				break;
			}

			case 4: {
				long numero = Console.readInt('\n' + "Informe o número do jogador: ");

				try {
					umAnimal = animalAppService.recuperaUmAnimalEConsultas(numero);
				} catch (AnimalNaoEncontradoException e ) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Id = " + umAnimal.getId() + 
						                "  Nome = " + umAnimal.getNome() + 
						                "  Data de Nascimento = " + umAnimal.getDataNasc() + 
						                "  Raça = " + umAnimal.getRaca());

				List<Consulta> consultas = umAnimal.getConsultas();

				for (Consulta consulta : consultas) {
					System.out.println('\n'  +    "Consulta número = " + consulta.getId() + 
												 "  Data da Consulta = " + consulta.getDataConsulta() + 
							                     "  Horario da Consulta = " + consulta.getHorario() + 
							                     "  Protocolo = " + consulta.getProtocolo());
				}

				break;
			}

			case 5: {
				List<Animal> animais = null;
			
				animais = animalAppService.recuperaAnimaisEConsultas();
				

				if (animais.size() != 0) {
					System.out.println("");

					for (Animal animal : animais) {
						System.out.println('\n' + "Animal numero = " + animal.getId() + 
								                "  Nome = " + animal.getNome() + 
								                "  Data de Nascimento = " + animal.getDataNasc() + 
								                "  Raça = " + animal.getRaca());

						List<Consulta> consultas = animal.getConsultas();

						for (Consulta consulta : consultas) {
							System.out.println('\n'  +    "Consulta número = " + consulta.getId() + 
									 					  "  Data da Consulta = " + consulta.getDataConsulta() + 
									 					  "  Horario da Consulta = " + consulta.getHorario() + 
									 					  "  Protocolo = " + consulta.getProtocolo());
						}
					}
				} else {
					System.out.println('\n' + "Não há animais cadastrados.");
				}

				break;
			}

			case 6: {
				try {
					Animal animal = animalAppService.recuperaPrimeiroAnimal();
					System.out.println('\n' + "Animal numero = " + animal.getId() + 
			                "  Nome = " + animal.getNome() + 
			                "  Data de Nascimento = " + animal.getDataNasc() + 
			                "  Raça = " + animal.getRaca());
				} catch (AnimalNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				break;
			}
			
			
			case 7: {
				continua = false;
				break;
			}
			
			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
