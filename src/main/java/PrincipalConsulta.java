import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ConsultaNaoEncontradoException;
import excecao.AnimalNaoEncontradoException;
import modelo.Animal;
import modelo.Consulta;
import service.AnimalAppService;
import service.ConsultaAppService;

public class PrincipalConsulta {
	public static void main(String[] args) {
		String dataConsulta;
		String horario;
		String protocolo;
		Animal umAnimal;
		Consulta umaConsulta;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		AnimalAppService animalAppService = (AnimalAppService) fabrica.getBean("animalAppService");
		ConsultaAppService consultaAppService = (ConsultaAppService) fabrica.getBean("consultaAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma consulta de um animal");
			System.out.println("2. Remover uma consulta");
			System.out.println("3. Recuperar todas as consultas");;
			System.out.println("4. Sair");

			int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

			switch (opcao) {
			
			case 1: {
				long idAnimal = Console.readInt('\n' + "Informe o número do animal: ");

				try {
					umAnimal = animalAppService.recuperaUmAnimal(idAnimal);
				} catch (AnimalNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				dataConsulta = Console.readLine("Informe a data da Consulta: ");
				horario = Console.readLine('\n' + "Informe o horario da consulta: ");
				protocolo = Console.readLine("Informe o protocolo: ");

				umaConsulta = new Consulta(dataConsulta, horario, protocolo, umAnimal);

				try {
					consultaAppService.inclui(umaConsulta);

					System.out.println('\n' + "Consulta adicionado com sucesso");
				} catch (AnimalNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}

				break;
			}

			case 2: {
				int resposta = Console.readInt('\n' + "Digite o número da consulta que você deseja remover: ");

				try {
					umaConsulta = consultaAppService.recuperaUmConsulta(resposta);
				} catch (ConsultaNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println('\n' + "Número = " + umaConsulta.getId() + 
						              "    Data da Consulta = " + umaConsulta.getDataConsulta() + 
						              "   Horario da Consulta = " + umaConsulta.getHorario() +
						              "    Protocolo = " + umaConsulta.getProtocolo());

				String resp = Console.readLine('\n' + "Confirma a remoção do inventario?");

				if (resp.equals("s")) {
					try {
						consultaAppService.exclui(umaConsulta);
						System.out.println('\n' + "Consulta removido com sucesso!");
					} catch (ConsultaNaoEncontradoException e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println('\n' + "Consulta não removido.");
				}

				break;
			}

			case 3: {
				List<Consulta> arrayConsultas = consultaAppService.recuperaConsultas();

				if (arrayConsultas.size() == 0) {
					System.out.println('\n' + "Nao há consultas cadastrados.");
					break;
				}

				System.out.println("");
				for (Consulta consulta : arrayConsultas) {
					System.out.println('\n' + "Número = " + consulta.getId() + 
				              "    Data da Consulta = " + consulta.getDataConsulta() + 
				              "   Horario da Consulta = " + consulta.getHorario() +
				              "    Protocolo = " + consulta.getProtocolo());
				}

				break;
			}

			
			case 4: {
				continua = false;
				break;
			}

			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
	}
}
