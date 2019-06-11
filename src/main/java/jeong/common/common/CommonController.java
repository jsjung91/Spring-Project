package jeong.common.common;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	private static final String filePath = "C:\\Users\\doanq\\Documents\\workspace-sts-3.9.7.RELEASE\\SpringJeongWeb\\src\\main\\webapp\\WEB-INF\\uploadFiles\\";
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/downloadFile.do")
	public void downloadFile(HttpServletRequest request, CommandMap commandMap, HttpServletResponse response) throws Exception {
		
		Map<String, Object> map = commonService.selectFileInfo(commandMap.getMap());
		String stored_file_name = (String)map.get("stored_file_name");
		String ori_file_name = (String)map.get("ori_file_name");
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File(filePath + stored_file_name));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(ori_file_name,"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value="/selectCommonCode.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> selectCommonCode(@RequestBody Map<String, Object> map) throws Exception {
		List<Map<String, Object>> list = commonService.selectCommonCode(map);
		
		return list;
	}
}
