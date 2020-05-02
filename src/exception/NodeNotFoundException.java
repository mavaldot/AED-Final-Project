package exception;

public class NodeNotFoundException extends RuntimeException {

	public NodeNotFoundException() {
		super();
	}
	
	public NodeNotFoundException(String msg) {
		super(msg);
	}
	
	public NodeNotFoundException(String msg, Throwable err) {
		super(msg, err);
	}
	
}
