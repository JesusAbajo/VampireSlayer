package exceptions;

public class NotEnoughCoinsException extends CommandExecuteException {
	private static final long serialVersionUID = 1L;
	private static boolean writeableStackTrace;

	//·Los constructores
	public NotEnoughCoinsException() {
		super(); 
	}
	public NotEnoughCoinsException(String message){ 
		super(message);
	}
	
	public NotEnoughCoinsException(String message, Throwable cause){
	 super(message, cause);
	}
	
	public NotEnoughCoinsException(Throwable cause){
		super(cause); 
	}
	
	NotEnoughCoinsException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace){
	 super(message, cause, enableSuppression, writeableStackTrace);
	}
}