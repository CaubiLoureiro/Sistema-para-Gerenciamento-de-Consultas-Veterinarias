package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class DonoNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public DonoNaoEncontradoException(String msg) {
		super(msg);
	}
}