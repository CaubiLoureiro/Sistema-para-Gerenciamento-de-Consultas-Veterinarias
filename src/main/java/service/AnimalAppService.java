package service;

import java.util.List;

import excecao.AnimalNaoEncontradoException;
import modelo.Animal;

public interface AnimalAppService {
	
	long inclui(Animal umJogador);
	
	
	void altera(Animal umJogador) throws AnimalNaoEncontradoException;
	
	
	void exclui(Animal umJogador) throws AnimalNaoEncontradoException;
	
	
	Animal recuperaUmAnimal(long numero) throws AnimalNaoEncontradoException;
	
	Animal recuperaUmAnimalEConsultas(long numero) throws AnimalNaoEncontradoException;

	Animal recuperaPrimeiroAnimal() throws AnimalNaoEncontradoException;
	
	List<Animal> recuperaAnimaisEConsultas();
}
