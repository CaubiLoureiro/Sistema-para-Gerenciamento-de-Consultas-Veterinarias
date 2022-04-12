package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class EspecieNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public EspecieNaoEncontradoException(String msg) {
		super(msg);
	}
}