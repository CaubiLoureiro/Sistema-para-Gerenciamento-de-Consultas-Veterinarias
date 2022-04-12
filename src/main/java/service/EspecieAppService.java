package service;

import java.util.List;

import anotacao.Perfil;
import excecao.EspecieNaoEncontradoException;
import modelo.Especie;

public interface EspecieAppService {
	
	@Perfil(nomes = { "admin" })
	long inclui(Especie umaEspecie);
	
	@Perfil(nomes = { "admin" })
	void altera(Especie umaEspecie) throws EspecieNaoEncontradoException;
	
	@Perfil(nomes = { "admin" })
	void exclui(Especie umaEspecie) throws EspecieNaoEncontradoException;
	
	@Perfil(nomes = { "admin" })
	Especie recuperaUmEspecie(long numero) throws EspecieNaoEncontradoException;
	
	@Perfil(nomes = { "admin" })
	Especie recuperaUmEspecieEAnimais(long numero) throws EspecieNaoEncontradoException;
	
	@Perfil(nomes = { "admin" })
	Especie recuperaPrimeiroEspecie() throws EspecieNaoEncontradoException;
	
	@Perfil(nomes = { "admin" })
	List<Especie> recuperaEspeciesEAnimais();
}
