package jeong.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jeong.common.dao.AbstractDao;

@Repository("boardDao")
public class BoardDao extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map, int start, 
			int end, String searchType, String searchText) throws Exception{ 
		
		map.put("start", start);
		map.put("end", end);
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		
		List<Map<String, Object>> list = selectList("board.selectBoardList", map);
		
		return list;
		
		//return (Map<String, Object>)selectPagingList("board.selectBoardList", map);
	}
	
	public int boardListGetCount(String searchType, String searchText) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		
		return Integer.parseInt(selectOne("board.boardListGetCount", map).toString());
	}
	
	public void boardInsert(BoardVo boardVo) throws Exception{
		// TODO Auto-generated method stub
		insert("board.boardInsert", boardVo);
	}
	
	public void updateHitCnt(BoardVo boardVo) throws Exception {
		update("board.updateHitCnt", boardVo);
	}
	
	 public BoardVo selectBoardDetail(BoardVo boardVo) throws Exception { 
		 return (BoardVo)selectOne("board.boardDetail", boardVo); 
	}
	 
	 public void fileInsert(BoardFileVo fileVo) throws Exception {
		 insert("board.fileInsert", fileVo);
	 }
	 
	 public void boardUpdate(BoardVo boardVo) throws Exception {
		 update("board.boardUpdate", boardVo);
	 }
	 
	 public void boardDelete(BoardVo boardVo) throws Exception {
		 delete("board.boardDelete", boardVo);
	 }
	 
	 public void deleteFileList(BoardFileVo fileVo) throws Exception {
		update("board.deleteFileList", fileVo); 
	 }
	 
	 public void fileUpdate(BoardFileVo fileVo) throws Exception {
		 update("board.updateFile", fileVo);
	 }
	 
	 @SuppressWarnings("unchecked")
	 public List<BoardFileVo> selectFileList(BoardFileVo fileVo) throws Exception{
		  return selectList("board.selectFileList", fileVo);
	 }
	 
	 public BoardFileVo selectFileMaxIdx(BoardFileVo fileVo) throws Exception {
		 return (BoardFileVo) selectOne("board.selectFileMaxIdx", fileVo);
		
	 }

}
