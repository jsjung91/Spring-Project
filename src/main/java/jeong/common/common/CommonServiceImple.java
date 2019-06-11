package jeong.common.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("commonService")
public class CommonServiceImple implements CommonService{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
			
	@Resource(name="commonDao")
	private CommonDao commonDao;
	
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return commonDao.selectFileInfo(map);
	}
	
	@Override
	public List<Map<String, Object>> selectCommonCode(Map<String, Object> map) throws Exception {
	    return commonDao.selectCommonCode(map);
	}
	
}
