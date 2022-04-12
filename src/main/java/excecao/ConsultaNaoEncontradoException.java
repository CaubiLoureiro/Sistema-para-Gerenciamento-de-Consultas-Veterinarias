package excecao;

import anotacao.ExcecaoDeAplicacao;

@ExcecaoDeAplicacao
public class ConsultaNaoEncontradoException extends Exception {
	private final static long serialVersionUID = 1;

	public ConsultaNaoEncontradoException(String msg) {
		super(msg);
	}
}