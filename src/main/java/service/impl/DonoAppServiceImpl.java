package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.DonoDAO;
import excecao.DonoNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import modelo.Dono;
import service.DonoAppService;

public class DonoAppServiceImpl implements DonoAppService {

	@Autowired
	private DonoDAO donoDAO;

	@Transactional
	public long inclui(Dono umDono) {
		donoDAO.inclui(umDono);
		return umDono.getId();
	}
	
	
	
	
	
	
	
	
	

	@Transactional
	public void altera(Dono umDono) throws DonoNaoEncontradoException {
		try {
			donoDAO.getPorIdComLock(umDono.getId());
			donoDAO.altera(umDono);
		} catch (ObjetoNaoEncontradoException e) {
			throw new DonoNaoEncontradoException("Dono não encontrado");
		}
	}
	
	
	
	
	
	
	
	
	

	@Transactional
	public void exclui(Dono umDono) throws DonoNaoEncontradoException {
		try {
			Dono npc = donoDAO.recuperaUmDonoEAnimais(umDono.getId());

			if(npc.getAnimais().size() > 0)
			{	throw new DonoNaoEncontradoException("Este dono possui animais e não pode ser removido");
			}

			donoDAO.exclui(npc);
		} catch (ObjetoNaoEncontradoException e) {
			throw new DonoNaoEncontradoException("Dono não encontrado");
		}
	}
	
	
	
	
	
	
	
	
	

	public Dono recuperaUmDono(long numero) throws DonoNaoEncontradoException {
		try {
			return donoDAO.getPorId(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new DonoNaoEncontradoException("Dono não encontrado");
		}
	}
	
	

	public Dono recuperaUmDonoEAnimais(long numero) throws DonoNaoEncontradoException {
		try {
			return donoDAO.recuperaUmDonoEAnimais(numero);
		} catch (ObjetoNaoEncontradoException e) {
			throw new DonoNaoEncontradoException("Dono não encontrado");
		}
	}
	
	
	
	
	
	
	
	
	
	

	public Dono recuperaPrimeiroDono() throws DonoNaoEncontradoException {
		try {
			System.out.println("Vai executar donoDAO.recuperaPrimeiroNpc()");
			Dono npc = donoDAO.recuperaPrimeiroDono();
			System.out.println("Executou donoDAO.recuperaPrimeiroNpc()");
			return npc;
		} catch (ObjetoNaoEncontradoException e) {
			throw new DonoNaoEncontradoException("Dono não encontrado");
		}
	}

	
	
	
	
	
	
	
	
	public List<Dono> recuperaDonosEAnimais() {
		System.out.println(donoDAO.getClass().getName() + " - " + donoDAO.getClass().hashCode());

		return donoDAO.recuperaListaDeDonosEAnimais();
	}

}