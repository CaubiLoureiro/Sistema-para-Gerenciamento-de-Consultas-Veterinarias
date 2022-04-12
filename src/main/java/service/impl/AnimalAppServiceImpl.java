package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.AnimalDAO;
import excecao.AnimalNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Animal;
import service.AnimalAppService;

public class AnimalAppServiceImpl implements AnimalAppService {

	@Autowired
	private AnimalDAO animalDAO;

	@Transactional
	public long inclui(Animal umAnimal) {
		animalDAO.inclui(umAnimal);
		return umAnimal.getId();
	}

	
	
	
	
	
	
	@Transactional
	public void altera(Animal umAnimal) throws AnimalNaoEncontradoException {
		try {
			animalDAO.getPorIdComLock(umAnimal.getId());
			animalDAO.altera(umAnimal);
		} catch (ObjetoNaoEncontradoException e) {
			throw new AnimalNaoEncontradoException("Animal não encontrado");
		}
	}
	
	
	
	
	
	
	

	@Transactional
	public void exclui(Animal umAnimal) throws AnimalNaoEncontradoException {
		try {
			Animal animal = animalDAO.recuperaUmAnimalEConsultas(umAnimal.getId());

			if(animal.getConsultas().size() > 0)
			{	throw new AnimalNaoEncontradoException("Este animal possui consultas e não pode ser removido");
			}

			animalDAO.exclui(animal);
		} catch (ObjetoNaoEncontradoException e) {
			throw new AnimalNaoEncontradoException("Animal não encontrado");
		}
	}

	
	
	
	
	
	
	
	public Animal recuperaUmAnimal(long numero) throws AnimalNaoEncontradoException {
		try {
			return animalDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new AnimalNaoEncontradoException("Animal não encontrado");
		}
	}

	
	
	public Animal recuperaUmAnimalEConsultas(long numero) throws AnimalNaoEncontradoException {
		try {
			return animalDAO.recuperaUmAnimalEConsultas(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new AnimalNaoEncontradoException("Animal não encontrado");
		}
	}
	
	
	
	
	
	
	

	public Animal recuperaPrimeiroAnimal() throws AnimalNaoEncontradoException {
		try {
			System.out.println("Vai executar animalDAO.recuperaPrimeiroAnimal()");
			Animal animal = animalDAO.recuperaPrimeiroAnimal();
			System.out.println("Executou animalDAO.recuperaPrimeiroAnimal()");
			return animal;
		} catch (ObjetoNaoEncontradoException e) {
			throw new AnimalNaoEncontradoException("Animal não encontrado");
		}
	}
	
	
	

	public List<Animal> recuperaAnimaisEConsultas() {
		System.out.println(animalDAO.getClass().getName() + " - " + animalDAO.getClass().hashCode());

		return animalDAO.recuperaListaDeAnimaisEConsultas();
	}

}