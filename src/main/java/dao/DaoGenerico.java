package dao;

import java.io.Serializable;

import excecao.ObjetoNaoEncontradoException;

/**
 * A interface GenericDao básica com os métodos CRUD. Os métodos de busca são
 * adicionados por herança de interface.
 */
public interface DaoGenerico<T, PK extends Serializable> {
	T inclui(T obj);

	void altera(T obj);

	void exclui(T obj);

	T getPorId(PK id) throws ObjetoNaoEncontradoException;

	T getPorIdComLock(PK id) throws ObjetoNaoEncontradoException;
}
