package jeong.common.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jeong.common.dao.AbstractDao;

@Repository("commonDao")
public class CommonDao extends AbstractDao{
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		return (Map<String, Object>)selectOne("common.selectFileInfo", map);
	}

	public int selectSeq(String code) throws Exception{
	    return (int) selectOne("common.selectSeq", code);
	}
	
	public void updateSeq(String code) throws Exception{
	    update("common.updateSeq", code);
	}
}
