package jeong.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jeong.common.common.Globals;
import jeong.login.MemberDetail;

public class LoggerInterceptor extends HandlerInterceptorAdapter{

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final String KAKAO_JS_API = Globals.KAKAO_JS_API;
	// client -> controller 로 요청할 때 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("=============== START ================");
			log.debug(" Request URI \t: " + request.getRequestURI());
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth != null) {
				if(auth.getCredentials() != null || auth.getCredentials() != "") {
					Object pri = auth.getPrincipal();
					if(pri != null) {
						if(auth.getName() != "anonymousUser") {
							MemberDetail memberVo = (MemberDetail) auth.getDetails();
							
							request.setAttribute("user", memberVo);
							request.setAttribute("menuvo", memberVo.getMenuvo());
							request.setAttribute("menu", memberVo.getMenu());
							request.setAttribute("cur_menu_id", request.getParameter("menu_id"));
							request.setAttribute("kakaoJsApi", KAKAO_JS_API);
						}
					}
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
	// controller -> client 로 응답할 때
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("=============== END ================");
		}
		//super.postHandle(request, response, handler, modelAndView);
	}
}
