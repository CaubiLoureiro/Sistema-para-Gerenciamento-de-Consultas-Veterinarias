package dao;

import java.util.List;
import java.util.Set;

import modelo.Animal;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface AnimalDAO extends DaoGenerico<Animal, Long> {
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Animal recuperaUmAnimalEConsultas(long numero) throws ObjetoNaoEncontradoException;
	
	@RecuperaObjeto
	Animal recuperaUmAnimalEItens(long numero) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Animal> recuperaListaDeAnimais();

	@RecuperaUltimoOuPrimeiro
	Animal recuperaPrimeiroAnimal() throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Animal> recuperaListaDeAnimaisEConsultas();
	
	@RecuperaConjunto
	Set<Animal> recuperaConjuntoDeAnimaisEConsultas();

	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}
