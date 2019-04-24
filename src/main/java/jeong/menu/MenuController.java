package jeong.menu;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@RequestMapping(value="/")
	public ModelAndView menu(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	@RequestMapping(value="/menu/getMenuList.do")
	public ModelAndView getMenuList(Menu menu) throws Exception {
		ModelAndView mv = new ModelAndView("/admin/menu/menuList");
		
		Map<String, Object> result = new HashMap<String, Object>();	
		
		try {
			result.put("menuList", menuService.getMenuList(menu));
			result.put("status", "OK");
		} catch (Exception e) {
			result.put("status", "False");
			log.debug(e.getMessage());
		}
		
		mv.addObject("result", result);
				
		return mv;
	}
	
	@RequestMapping(value="/menu/saveMenu.do")
	public ModelAndView saveMenu(Menu menu) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/menu/getMenuList.do");
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		log.debug("menu : " + menu.toString());
		
		try {
			menuService.saveMenu(menu);
			result.put("status", "OK");			
		} catch(Exception e) {
			result.put("status", "False");
			log.debug(e.getMessage());
		}
		
		mv.addObject("result", result);
		
		return mv;
	}
	
	@RequestMapping(value="/menu/updateMenu.do")
	public ModelAndView updateMenu(Menu menu) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/menu/getMenuList.do");
		
		menuService.updateMenu(menu);
		
		return mv;
		
	}
	
	@RequestMapping(value="/menu/deleteMenu.do")
	public ModelAndView deleteMenu(@RequestParam("code") String code) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/menu/getMenuList.do");
		
		menuService.deleteMenu(code);
		
		return mv;
	}
}
