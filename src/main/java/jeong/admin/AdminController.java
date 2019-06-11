package jeong.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jeong.login.LoginService;
import jeong.login.MemberDetail;
import jeong.member.MemberVo;
import jeong.menu.MenuService;
import jeong.menu.MenuVo;

@Controller
public class AdminController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="adminService")
	private AdminService adminService;
	
	// 관리자 페이지 
	@RequestMapping(value = "/admin/adminMain.do")
	public ModelAndView adminMain(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/adminMain");
		
		MemberDetail user = (MemberDetail) request.getAttribute("user");
		
		MenuVo vo = new MenuVo();
		vo.setRole_char(user.getMember_role().toString());
		vo.setAdmin_yn("Y");
		
		List<MenuVo> menuVo = menuService.roleMenuAdmin(vo);
		
		mv.addObject("admin_menuVo", menuVo);
		
		return mv;
	}
	
	// 사용자 정보 리스트
	@RequestMapping(value="/admin/member/memberInfo.do")
	public ModelAndView memberInfo(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/admin/member/memberInfo");
		
		MemberDetail user = new MemberDetail();
		
		List<MemberDetail> userlist = adminService.adminMemberList(user);
		
		mv.addObject("userlist", userlist);
		
		return mv;
	}
	
	// 사용자 상세 정보
	@RequestMapping(value="/admin/member/memberInfoDetail.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberInfoDetail(HttpServletRequest request, @RequestBody MemberVo memberVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberVo user = adminService.adminMemberInfoDetail(memberVo);
		
		map.put("memberVo", user);
		
		return map;
	}
	
	// 권한 관리 화면, 리스트
	@RequestMapping(value = "/admin/authManager.do") 
	public ModelAndView authManager(AdminVo adminVo) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/auth/authManager");
		
		List<AdminVo> list = adminService.authManagerList();
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	// 관리자 -> 메뉴 관리 리스트
	@RequestMapping(value="/admin/menuList.do")
	public ModelAndView menuList(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/menu/menuManager");
		
		MenuVo vo = new MenuVo();
		
		String redirect_menu = request.getParameter("rd");
		String redirect_menu_name = request.getParameter("rdnm");
		
		List<MenuVo> menuVo = adminService.menuManagerList(vo);
		
		mv.addObject("rdmenu", redirect_menu);
		mv.addObject("rdmenunum", redirect_menu_name);
		mv.addObject("menuVo", menuVo);
		
		return mv;
	}
	
	// 사용자 정보 업데이트
	@RequestMapping(value = "/admin/member/memberInfoUpdate.do", method = RequestMethod.POST) 
	public ModelAndView memberInfoupdate(MemberVo memberVo) throws Exception {
		int result = adminService.adminMemberInfoUpdate(memberVo);
		
		ModelAndView mv = new ModelAndView("redirect:/admin/member/memberInfo.do");
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	// 사용자 아이디 중복 체크
	@RequestMapping(value = "/admin/member/memberIdCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int memberIdCtn(@RequestBody String member_id) throws Exception {
		int result = loginService.checkMemberIdCnt(member_id);
		
		return result;
	}
	
	// 사용자 정보 삭제
	@RequestMapping(value = "/admin/member/memberDelete.do", method = RequestMethod.POST)
	public ModelAndView memberDelete(MemberVo memberVo) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/member/memberInfo.do");
		
		adminService.adminMemberDelete(memberVo);
		
		return mv;
	}
}
