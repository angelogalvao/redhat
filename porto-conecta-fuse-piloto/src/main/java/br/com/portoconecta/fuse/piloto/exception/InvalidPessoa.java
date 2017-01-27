package br.com.portoconecta.fuse.piloto.exception;

/**
 * Exceção lançada quando os dados da pessoa esta inválido.
 * @author asouza
 *
 */
public class InvalidPessoa extends PortoException {

	private static final long serialVersionUID = -8005280896744683612L;

	public InvalidPessoa(int statusCode, String message) {
		super(statusCode, message);
	}

	

}
