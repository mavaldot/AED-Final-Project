package exception;

public class MultipleEdgesException extends RuntimeException {

	public MultipleEdgesException() {
		super();
	}
	
	public MultipleEdgesException(String msg) {
		super(msg);
	}
	
	public MultipleEdgesException(String msg, Throwable err) {
		super(msg, err);
	}
	
}
