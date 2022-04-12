package dao;

import java.util.List;
import java.util.Set;

import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Especie;

public interface EspecieDAO extends DaoGenerico<Especie, Long> {
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Especie recuperaUmEspecieEAnimais(long numero) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Especie> recuperaListaDeEspecies();

	@RecuperaUltimoOuPrimeiro
	Especie recuperaPrimeiroEspecie() throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Especie> recuperaListaDeEspeciesEAnimais();
	
	@RecuperaConjunto
	Set<Especie> recuperaConjuntoDeEspeciesEAnimais();

	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}
