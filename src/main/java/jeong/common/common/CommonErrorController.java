package jeong.common.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonErrorController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*
	 * @RequestMapping(value="/common/503error.do") public ModelAndView
	 * common503Error(HttpServletRequest request, HttpServletResponse response)
	 * throws Exception{ ModelAndView mv = new ModelAndView("/error/error");
	 * 
	 * String returnUrl = request.getHeader("referer"); String urlSub = null;
	 * 
	 * if(returnUrl == null) { returnUrl = "/main.do"; } }
	 */
	
}
