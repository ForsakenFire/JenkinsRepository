package spring5.entity;

public class MyException extends Exception{

	public MyException(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "MyException："+super.getMessage();
	}
}
