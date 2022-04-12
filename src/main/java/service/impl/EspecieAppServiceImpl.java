package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.EspecieDAO;
import excecao.EspecieNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Especie;
import service.EspecieAppService;

public class EspecieAppServiceImpl implements EspecieAppService {

	@Autowired
	private EspecieDAO especieDAO;

	@Transactional
	public long inclui(Especie umaEspecie) {
		especieDAO.inclui(umaEspecie);
		return umaEspecie.getId();
	}
	
	
	
	
	
	
	
	
	

	@Transactional
	public void altera(Especie umaEspecie) throws EspecieNaoEncontradoException {
		try {
			especieDAO.getPorIdComLock(umaEspecie.getId());
			especieDAO.altera(umaEspecie);
		} catch (ObjetoNaoEncontradoException e) {
			throw new EspecieNaoEncontradoException("Especie não encontrada");
		}
	}
	
	
	
	
	
	
	
	
	

	@Transactional
	public void exclui(Especie umaEspecie) throws EspecieNaoEncontradoException {
		try {
			Especie especie = especieDAO.recuperaUmEspecieEAnimais(umaEspecie.getId());

			if(especie.getAnimais().size() > 0)
			{	throw new EspecieNaoEncontradoException("Esta especie possui animais e não pode ser removido");
			}

			especieDAO.exclui(especie);
		} catch (ObjetoNaoEncontradoException e) {
			throw new EspecieNaoEncontradoException("Especie não encontrado");
		}
	}
	
	
	
	
	
	
	
	
	

	public Especie recuperaUmEspecie(long numero) throws EspecieNaoEncontradoException {
		try {
			return especieDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new EspecieNaoEncontradoException("Especie não encontrada");
		}
	}
	
	
	
	
	
	
	

	public Especie recuperaUmEspecieEAnimais(long numero) throws EspecieNaoEncontradoException {
		try {
			return especieDAO.recuperaUmEspecieEAnimais(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new EspecieNaoEncontradoException("Especie não encontrado");
		}
	}
	
	
	
	
	
	
	
	
	
	

	public Especie recuperaPrimeiroEspecie() throws EspecieNaoEncontradoException {
		try {
			System.out.println("Vai executar especieDAO.recuperaPrimeiroNpc()");
			Especie especie = especieDAO.recuperaPrimeiroEspecie();
			System.out.println("Executou especieDAO.recuperaPrimeiroNpc()");
			return especie;
		} catch (ObjetoNaoEncontradoException e) {
			throw new EspecieNaoEncontradoException("Especie não encontrado");
		}
	}

	
	
	
	
	
	
	
	
	public List<Especie> recuperaEspeciesEAnimais() {
		System.out.println(especieDAO.getClass().getName() + " - " + especieDAO.getClass().hashCode());

		return especieDAO.recuperaListaDeEspeciesEAnimais();
	}

}