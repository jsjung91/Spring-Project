package jeong.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jeong.admin.AdminService;
import jeong.member.MemberVo;

@Controller
public class LoginController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	@RequestMapping(value="/join/login.do")
	public ModelAndView login(HttpServletRequest requset, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/user/loginform");
		System.out.println("login 호출");
		return mv;
	}
	
	@RequestMapping(value="/join/loginfail.do")
	public ModelAndView loginfail() {
		ModelAndView mv = new ModelAndView("/user/loginfail");
		
		return mv;
	}
	
	@RequestMapping(value="/join/logoutform.do")
	public String logoutform() {
		return "/user/logoutform";
	}
	
	@RequestMapping(value="/join/loginsuccess.do", method=RequestMethod.GET)
	public ModelAndView loginresult() {
		ModelAndView mv = new ModelAndView("/user/loginsuccess");
		
		return mv;
	}
	
	// 회원가입 창
	@RequestMapping(value="/join/memberRegister.do")
	public ModelAndView memberRegister(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/user/homeJoin");
		
		return mv;
	}
	
	// 회원가입 
	@RequestMapping(value="/join/memberInsert.do")
	@ResponseBody
	public int memberInsert(HttpServletRequest request, @RequestBody MemberVo memberVo) throws Exception {
		memberVo.setMember_role("ROLE_USER");	
		memberVo.setMember_type("홈페이지");
		memberVo.setStatus("insert");
		
		int result = adminService.adminMemberInfoUpdate(memberVo);
	
		return result;
	}
	
	@RequestMapping(value="/join/access_denied")
	public ModelAndView accessDenied() throws Exception {
		ModelAndView mv = new ModelAndView("/user/access");
		
		mv.addObject("msg", "접근 권한이 없습니다.");
		mv.addObject("url", "/");
		
		return mv;
	}
	
	// 사용자 정보 팝업
	@RequestMapping(value="/main/userInfoPop.do")
	public ModelAndView userInfoPop(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/user/userInfoPop");
		
		//String token = null;
		
		/*
		 * if(request.getParameter("type").toString() == "") { HttpSession session =
		 * request.getSession(); token =
		 * session.getAttribute("access_token").toString(); }
		 */
		
		//mv.addObject("token", token);
		
		return mv;
	}
}
