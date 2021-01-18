package bank.eltropy.exception;

public class LoginAlreadyExistsException extends RuntimeException {
	public LoginAlreadyExistsException(String message) {
		super(message);
	}
}
