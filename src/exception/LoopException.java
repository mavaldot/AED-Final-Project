package exception;

public class LoopException extends RuntimeException {

	public LoopException() {
		super();
	}
	
	public LoopException(String msg) {
		super(msg);
	}
	
	public LoopException(String msg, Throwable err) {
		super(msg, err);
	}
	
}
