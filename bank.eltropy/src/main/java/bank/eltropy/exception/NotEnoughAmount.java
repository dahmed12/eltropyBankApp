package bank.eltropy.exception;

public class NotEnoughAmount extends RuntimeException {
    public NotEnoughAmount(String message) {
        super(message);
    }
}
