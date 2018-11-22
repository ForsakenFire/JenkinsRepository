package spring13_rest.exception;

public class MyException extends Exception{
	
	public MyException(String message) {
		super("MyException：Message="+message);
	}

}
