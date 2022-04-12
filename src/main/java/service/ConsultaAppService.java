package service;

import java.util.List;

import excecao.ConsultaNaoEncontradoException;
import excecao.AnimalNaoEncontradoException;
import modelo.Consulta;

public interface ConsultaAppService {
	long inclui(Consulta umInventario) 
		throws AnimalNaoEncontradoException;

	void exclui(Consulta umInventario) throws ConsultaNaoEncontradoException;
	
	Consulta recuperaUmConsulta(long numero) throws ConsultaNaoEncontradoException;
	
	List<Consulta> recuperaConsultas();
	
	
}
