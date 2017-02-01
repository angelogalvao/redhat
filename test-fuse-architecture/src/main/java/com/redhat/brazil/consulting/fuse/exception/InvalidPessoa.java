package com.redhat.brazil.consulting.fuse.exception;

/**
 * Exceção de negócio lançada quando os dados da pessoa esta inválido.
 * 
 * Ela extende a exceção pardão {@link ApplicationException}
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class InvalidPessoa extends ApplicationException {

	private static final long serialVersionUID = -8005280896744683612L;

	public InvalidPessoa(int statusCode, String message) {
		super(statusCode, message);
	}

	

}
