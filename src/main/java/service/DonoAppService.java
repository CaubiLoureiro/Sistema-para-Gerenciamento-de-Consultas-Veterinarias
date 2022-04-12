package service;

import java.util.List;

import excecao.DonoNaoEncontradoException;
import modelo.Dono;

public interface DonoAppService {
	long inclui(Dono umNpc);
	
	void altera(Dono umNpc) throws DonoNaoEncontradoException;
	
	void exclui(Dono umNpc) throws DonoNaoEncontradoException;
	
	Dono recuperaUmDono(long numero) throws DonoNaoEncontradoException;
	
	Dono recuperaUmDonoEAnimais(long numero) throws DonoNaoEncontradoException;
	
	Dono recuperaPrimeiroDono() throws DonoNaoEncontradoException;
	
	List<Dono> recuperaDonosEAnimais();
}
