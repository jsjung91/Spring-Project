package jeong.common.common;

import java.util.List;
import java.util.Map;

public interface CommonService {

	Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	List<Map<String, Object>> selectCommonCode(Map<String, Object> map) throws Exception;
	
}
