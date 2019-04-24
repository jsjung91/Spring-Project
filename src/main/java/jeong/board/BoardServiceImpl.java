package jeong.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jeong.common.util.FileUtils;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="boardDao")
	private BoardDao boardDao;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map, int start, 
			int end, String searchType, String searchText) throws Exception {	
		return boardDao.selectBoardList(map, start, end, searchType, searchText);
	}
	
	@Override
	public int boardListGetCount(String searchType, String searchText) throws Exception {
		return boardDao.boardListGetCount(searchType, searchText);
	}
	
	@Override
	public void boardInsert(BoardVo boardVo, BoardFileVo fileVo, MultipartHttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		boardDao.boardInsert(boardVo);
		
		List<BoardFileVo> list = fileUtils.parseInsertFileInfo(fileVo, request);
	
		int size;
		
		if(list == null) {
			size = 0;
		} else {
			size = list.size();
		}
		
		for(int i=0; i<size; i++) {
			boardDao.fileInsert(list.get(i));
		}
	}

	  @Override 
	  public Map<String, Object> boardDetail(BoardVo boardVo, BoardFileVo fileVo) throws Exception { 
		  
		  boardDao.updateHitCnt(boardVo);
	  
		  Map<String, Object> resultMap = new HashMap<String, Object>();
	  
		  resultMap.put("map", boardDao.selectBoardDetail(boardVo));
		  resultMap.put("list", boardDao.selectFileList(fileVo));
		  
		  return resultMap;
	  
	  }
	 
	  @Override
	  public void boardUpdate(BoardVo boardVo, BoardFileVo fileVo, HttpServletRequest request) throws Exception {
		  
		  //fileVo.setBrdidx(boardVo.getBno());
		  
		  boardDao.boardUpdate(boardVo);
		  boardDao.deleteFileList(fileVo);
		  
		  List<BoardFileVo> list = fileUtils.parseUpdateFileInfo(fileVo, request);
		  
		  BoardFileVo tempMap;
		  
		  int size;
		  
		  if(list == null || list.equals("")) {
			  size = 0;
		  } else {
			  size = list.size();
		  }
		  
		  for(int i=0; i<size; i++) {
			  tempMap = list.get(i);
			  if(tempMap.getIs_new() == "Y" || tempMap.getIs_new().equals("Y")) {
				  boardDao.fileInsert(tempMap);
			  }
			  else {
				  boardDao.fileUpdate(tempMap);
			  }
		  }
	  }
	  
	  @Override
	  public void boardDelete(BoardVo boardVo) throws Exception {
		  boardDao.boardDelete(boardVo);
	  }
}
