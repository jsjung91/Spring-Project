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
	
	// ������ ������ 
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
	
	// ����� ���� ����Ʈ
	@RequestMapping(value="/admin/member/memberInfo.do")
	public ModelAndView memberInfo(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("/admin/member/memberInfo");
		
		MemberDetail user = new MemberDetail();
		
		List<MemberDetail> userList = adminService.adminMemberList(user);
		
		mv.addObject("userList", userList);
		
		return mv;
	}
	
	// ����� �� ����
	@RequestMapping(value="/admin/member/memberInfoDetail.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberInfoDetail(HttpServletRequest request, @RequestBody MemberVo memberVo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberVo user = adminService.adminMemberInfoDetail(memberVo);
		
		map.put("memberVo", user);
		
		return map;
	}
	
	// ���� ���� ȭ��, ����Ʈ
	@RequestMapping(value = "/admin/authManager.do")
	public ModelAndView authManager(AdminVo adminVo) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/auth/authManager");
		
		List<AdminVo> list = adminService.authManagerList();
		
		mv.addObject("list", list);
		
		return mv;
	}
	
	// ������ -> �޴� ���� ����Ʈ
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
}
