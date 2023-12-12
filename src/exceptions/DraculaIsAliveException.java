package exceptions;

public class DraculaIsAliveException extends CommandExecuteException {
	private static final long serialVersionUID = 1L;
	private static boolean writeableStackTrace;

	//·Los constructores
	public DraculaIsAliveException() {
		super(); 
	}
	public DraculaIsAliveException(String message){ 
		super(message);
	}
	
	public DraculaIsAliveException(String message, Throwable cause){
	 super(message, cause);
	}
	
	public DraculaIsAliveException(Throwable cause){
		super(cause); 
	}
	
	DraculaIsAliveException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace){
	 super(message, cause, enableSuppression, writeableStackTrace);
	}
}
