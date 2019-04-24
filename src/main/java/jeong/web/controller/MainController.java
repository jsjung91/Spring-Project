package jeong.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	//Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/main.do")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("main");
		
		return mav;
	}
}
