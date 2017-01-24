package br.com.portoconecta.fuse.piloto.exception;

/**
 * Exceção lançada quando os dados da pessoa esta inválido.
 * @author asouza
 *
 */
public class InvalidPessoa extends Exception {

	private static final long serialVersionUID = -8005280896744683612L;

	public InvalidPessoa(String message) {
		super(message);
	}

}
