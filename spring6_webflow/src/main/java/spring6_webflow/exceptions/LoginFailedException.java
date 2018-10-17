package spring6_webflow.exceptions;

@SuppressWarnings("serial")
public class LoginFailedException extends Exception{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "登录异常，原因："+super.getMessage();
	}
	
}
