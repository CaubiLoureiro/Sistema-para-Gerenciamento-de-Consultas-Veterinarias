package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class AnimalNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public AnimalNaoEncontradoException(String msg) {
		super(msg);
	}
}