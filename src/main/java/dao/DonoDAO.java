package dao;

import java.util.List;
import java.util.Set;

import modelo.Dono;
import anotacao.RecuperaConjunto;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface DonoDAO extends DaoGenerico<Dono, Long> {
	/* ****** Métodos Genéricos ******* */

	@RecuperaObjeto
	Dono recuperaUmDonoEAnimais(long numero) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Dono> recuperaListaDeDonos();

	@RecuperaUltimoOuPrimeiro
	Dono recuperaPrimeiroDono() throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Dono> recuperaListaDeDonosEAnimais();
	
	@RecuperaConjunto
	Set<Dono> recuperaConjuntoDeDonosEAnimais();

	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}
