package jeong.login;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jeong.admin.AdminService;
import jeong.common.common.Globals;
import jeong.member.MemberVo;

@Controller
public class LoginController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final String KAKAO_REST_API = Globals.KAKAO_REST_API;
	private final String KAKAO_JS_API = Globals.KAKAO_JS_API;
	private final String KAKAO_NATIVE_API = Globals.KAKAO_NATIVE_API;
	private final String KAKAO_ADMIN_API = Globals.KAKAO_ADMIN_API;
	private final String KAKAO_REDIRECT_URL = Globals.KAKAO_REDIRECT_URL;
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	@Resource(name="kakaoApi")
	private KakaoApi kakaoApi;
	
	@RequestMapping(value="/join/login.do")
	public ModelAndView login(HttpServletRequest requset, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/user/loginform");
		
		mv.addObject("kakaoRestApi", KAKAO_REST_API);
		mv.addObject("kakaoJsApi", KAKAO_JS_API);
		mv.addObject("kakaoNativeApi", KAKAO_NATIVE_API);
		mv.addObject("kakaoAdminApi", KAKAO_ADMIN_API);
		mv.addObject("kakaoRdUrl", KAKAO_REDIRECT_URL);
		
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
	
	// ȸ������ â
	@RequestMapping(value="/join/memberRegister.do")
	public ModelAndView memberRegister(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/user/homeJoin");
		
		return mv;
	}
	
	// ȸ������ 
	@RequestMapping(value="/join/memberInsert.do")
	@ResponseBody // �ڹ� ��ü�� HTTP ���� ��ü�� ����
	public int memberInsert(HttpServletRequest request, @RequestBody MemberVo memberVo) throws Exception {
		memberVo.setMember_role("ROLE_USER");
		memberVo.setMember_type("Ȩ������");
		memberVo.setStatus("insert");
		
		int result = adminService.adminMemberInfoUpdate(memberVo);
	
		return result;
	}
	
	@RequestMapping(value="/join/access_denied")
	public ModelAndView accessDenied() throws Exception {
		ModelAndView mv = new ModelAndView("/user/access");
		
		mv.addObject("msg", "���� ������ �����ϴ�.");
		mv.addObject("url", "/");
		
		return mv;
	}
	
	// ����� ���� �˾�
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
	
	// īī���� �α��� �˾�
	@RequestMapping(value="/kako/kakoAuth.do")
	public ModelAndView kakaoAuth(@RequestParam("code") String code, HttpServletRequest request) throws Exception {
		
		String access_Token = kakaoApi.getAccessToken(code);
		
		HashMap<String, Object> userInfo = kakaoApi.getUserInfo(access_Token);
				
		System.out.println("login Controller : " + userInfo);
		
		// ���ǿ� ����(��ū, īī���� ����� ����)
		HttpSession session = request.getSession();
		
		// Ŭ���̾�Ʈ�� �̸����� ������ �� ���ǿ� �ش� �̸��ϰ� ��ū ���
		if(userInfo.get("email") != null) {
			session.setAttribute("userId", userInfo.get("email"));
			session.setAttribute("userName", userInfo.get("nickname"));
			session.setAttribute("access_Token", access_Token);
		}
		
		MemberDetail memberDetail = new MemberDetail();
		
		memberDetail.setId((userInfo.get("email")).toString());		
		memberDetail.setNickname((userInfo.get("nickname")).toString());
		
		session.setAttribute("kakao_info", memberDetail);
		
		ModelAndView mv = null;
		if(request.getParameter("state").equals("join")) {
			mv = new ModelAndView("forward:/kakao/kakaoJoin.do");
			//mv.addObject("kakaoUserId", meObject.get("id").toString());
			//mv.addObject("kakaoUserName", meProperties.get("nickname").toString());			
		} else if(request.getParameter("state").equals("login")) {
			mv = new ModelAndView("forward:/kakao/kakaoLogin.do");
		}
		return mv;
	}
	
	// īī���� �α���
	@RequestMapping(value = "/kakao/kakaoLogin.do")
	public ModelAndView kakoLogin(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/login/kakaoLogin");
		
		HttpSession session = request.getSession();
		
		MemberDetail kakao_info = (MemberDetail) session.getAttribute("kakao_info");
		
		// īī�� ���̵� DB���� ��ȸ, Ȩ������ ȸ�� ���ԵǾ��ִ��� Ȯ�� 
		int result = loginService.checkMemberIdCnt(kakao_info.getId());
		
		MemberVo memberVo = null;
		
		if(result > 0) {
			memberVo = loginService.checkKakaoMember(kakao_info);
			memberVo.setMember_id(kakao_info.getId());
			mv.addObject("memberVo", memberVo);
			mv.addObject("member_yn", "Y");
		} else {
			mv.addObject("member_yn", "N");
		}
		
		return mv;
	}
}
