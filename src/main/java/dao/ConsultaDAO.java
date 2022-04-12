package dao;

import java.util.List;

import modelo.Consulta;
import modelo.Animal;
import anotacao.RecuperaLista;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;

public interface ConsultaDAO extends DaoGenerico<Consulta, Long> {
	@RecuperaLista
	List<Consulta> recuperaListaDeConsultas();

	
	@RecuperaUltimoOuPrimeiro
	Consulta recuperaUltimoConsulta(Animal jogador) throws ObjetoNaoEncontradoException;
}
