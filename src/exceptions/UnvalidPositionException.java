package exceptions;

public class UnvalidPositionException extends CommandExecuteException {
	private static final long serialVersionUID = 1L;
	private static boolean writeableStackTrace;

	//·Los constructores
	public UnvalidPositionException() {
		super(); 
	}
	public UnvalidPositionException(String message){ 
		super(message);
	}
	
	public UnvalidPositionException(String message, Throwable cause){
	 super(message, cause);
	}
	
	public UnvalidPositionException(Throwable cause){
		super(cause); 
	}
	
	UnvalidPositionException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace){
	 super(message, cause, enableSuppression, writeableStackTrace);
	}
}
