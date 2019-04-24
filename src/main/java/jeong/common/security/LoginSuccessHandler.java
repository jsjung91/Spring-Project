package jeong.common.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jeong.login.LoginService;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	//Logger log = LoggerFactory.getLogger(getClass());
	
	//private RequestCache requestCache = new HttpSessionRequestCache();
	//private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	
	//private String loginid;
	//private String defaultUrl;
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
		//String memberid = request.getParameter(loginid);
		
		response.sendRedirect(request.getContextPath() + "/main.do");
		/*
		 * try { loginService.resetFailureCnt(memberid); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } try {
		 * loginService.updateAccessDate(memberid); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * //에러 세션 지우기 clearAuthenticationAttributes(request);
		 * 
		 * //Redirect 작업 resultRedirectStrategy(request, response, authentication);
		 */		
	}
	
	/*
	 * protected void clearAuthenticationAttributes(HttpServletRequest request) { //
	 * TODO Auto-generated method stub HttpSession session =
	 * request.getSession(false); if(session == null) return;
	 * session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); }
	 * 
	 * protected void resultRedirectStrategy(HttpServletRequest request,
	 * HttpServletResponse response, Authentication authentication) throws
	 * IOException, ServletException{ // TODO Auto-generated method stub
	 * //SavedRequest savedRequest = requestCache.getRequest(request, response);
	 * 
	 * if(savedRequest!=null) { log.debug("권한이 필요한 페이지에 접근했을 경우");
	 * useSessionUrl(request, response); }else { log.debug("직접 로그인 url로 이동했을 경우");
	 * useDefaultUrl(request, response); } }
	 * 
	 * protected void useDefaultUrl(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { // TODO Auto-generated method stub
	 * redirectStratgy.sendRedirect(request, response, defaultUrl); }
	 * 
	 * protected void useSessionUrl(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { // TODO Auto-generated method stub
	 * SavedRequest savedRequest = requestCache.getRequest(request, response);
	 * String targetUrl = savedRequest.getRedirectUrl();
	 * redirectStratgy.sendRedirect(request, response, targetUrl); }
	 * 
	 * public String getLoginid() { return loginid; }
	 * 
	 * public void setLoginid(String loginid) { this.loginid = loginid; }
	 * 
	 * public String getDefaultUrl() { return defaultUrl; }
	 * 
	 * public void setDefaultUrl(String defaultUrl) { this.defaultUrl = defaultUrl;
	 * }
	 */
}
