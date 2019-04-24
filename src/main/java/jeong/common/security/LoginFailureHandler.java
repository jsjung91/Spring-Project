package jeong.common.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jeong.common.util.MessageUtils;
import jeong.login.LoginService;

public class LoginFailureHandler implements AuthenticationFailureHandler{

	@Resource(name="loginService")
	private LoginService loginService;
	
	private String loginid;
	private String loginpwd;
	private String errormsg;
	private String defaultFailureUrl;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException { 
		
			String memberid = request.getParameter(loginid);
			String memberpwd = request.getParameter(loginpwd);
			String errormsgs = null;
			
			if(exception instanceof BadCredentialsException) {
			//비밀번호가 틀렸을 경우 실패 카운터와 관련된 메서드를 실행
				//loginFailureCount(memberid);
				errormsgs = MessageUtils.getMessage("error.BadCredentials");
			}else if(exception instanceof InternalAuthenticationServiceException) {
				errormsgs = MessageUtils.getMessage("error.BadCredentials");
		} /*
			 * else if(exception instanceof DisabledException) { errormsgs =
			 * MessageUtils.getMessage("error.Disaled"); } else if(exception instanceof
			 * CredentialsExpiredException) { errormsgs =
			 * MessageUtils.getMessage("error.CredentialsExpired"); }
			 */
			
			request.setAttribute(loginid, memberid);
			request.setAttribute(loginpwd, memberpwd);
			request.setAttribute(errormsg, errormsgs);			 
			request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}
	
	protected void loginFailureCount(String memberid) throws Exception {		
		// 실패 카운터 증가
		loginService.countFailure(memberid);  
		// 실패 카운터를 가져온다
		int cnt = loginService.checkFailureCount(memberid);
		// 실패 카운터가 3회일 때 계정을 잠금 처리
		if(cnt==3) {
			loginService.disabledUserid(memberid);
		}
	}

	public String getLoginid() {
		return loginid;
	}
	
	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}
	
	public String getLoginpwd() {
		return loginpwd;
	}
	
	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
	
	public String getErrormsg() {
		return errormsg;
	}
	
	public void setErrormsg(String errormsg) {
	    this.errormsg = errormsg;
	}
	 
	public String getDefaultFailureUrl() {
	    return defaultFailureUrl;
	}
	 
	public void setDefaultFailureUrl(String defaultFailureUrl) {
	    this.defaultFailureUrl = defaultFailureUrl;
	}

}
