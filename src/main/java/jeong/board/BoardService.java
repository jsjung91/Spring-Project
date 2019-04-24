package jeong.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {

	//public Map<String, Object> selectBoardList(Map<String, Object> map) throws Exception;

	//public Map<String, Object> selectBoardList(BoardVo boardVo) throws Exception;
	
	public void boardInsert(BoardVo boardVo, BoardFileVo fileVo, MultipartHttpServletRequest request) throws Exception;
	
	public Map<String, Object> boardDetail(BoardVo boardVo, BoardFileVo fileVo) throws Exception;

	public void boardUpdate(BoardVo boardVo, BoardFileVo fileVo, HttpServletRequest request) throws Exception;
	
	public void boardDelete(BoardVo boardVo) throws Exception;

	List<Map<String, Object>> selectBoardList(Map<String, Object> map, int start, int end, String searchType, String searchText) throws Exception;
	
	int boardListGetCount(String searchType, String searchText) throws Exception;
	
}
