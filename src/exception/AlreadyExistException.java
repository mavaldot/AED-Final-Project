  package exception;

public class AlreadyExistException extends RuntimeException{

	//Constructor
	public AlreadyExistException(){
		super("An object with the same identifier already exists");
	}
	
	public AlreadyExistException(String message){
		super(message);
	}
	
}