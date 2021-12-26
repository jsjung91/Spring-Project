package jeong.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import jeong.common.paging.Paging;

@Controller
@RequestMapping("/board")
public class BoardController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@RequestMapping(value="/boardList.do")
	public ModelAndView boardList(HttpServletRequest request, Map<String, Object> commandMap,
									@RequestParam(value="curPage", defaultValue="1")int curPage,
									@RequestParam(value="searchType", defaultValue="all")String searchType,
									@RequestParam(value="searchText", defaultValue="")String searchText) throws Exception {	
		// 전체 게시물 레코드의 갯수
		int count = boardService.boardListGetCount(searchType, searchText);
		
		Paging page = new Paging(count, curPage);
		// 현재 페이지 번호
		int start = page.getPageBegin();
		// 현재 페이지 끝 번호
		int end = page.getPageEnd();
		
		List<Map<String, Object>> list = boardService.selectBoardList(commandMap, start, end, searchType, searchText);		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("count", count);		
		map.put("searchType", searchType);
		map.put("searchText", searchText);
		map.put("paging", page);
		
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		mv.addObject("map", map);
		
		return mv;
	}
		
	@RequestMapping(value="/boardInsertForm.do")
	public ModelAndView boardWrite() throws Exception {
		
		return new ModelAndView("/board/boardWrite");
	}
	
	@RequestMapping(value="/boardInsert.do")
	public ModelAndView boardInsert(MultipartHttpServletRequest request, BoardVo boardVo, BoardFileVo fileVo) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/board/boardList.do");
		
		//mv.addObject("menu_id", request.getParameter("menu_id"));
		
		//MemberDetail user = (MemberDetail) request.getAttribute("user");
		
		//String regid = user.getMember_id();
		
		//boardVo.setRegid(regid);
		//boardVo.setModid(regid);
		
		//fileVo.setRegid(regid);
		
		boardService.boardInsert(boardVo, fileVo, request);
		
		return mv;
	}
	
	@RequestMapping(value="/boardDetail.do")
	public ModelAndView boardDetail(HttpServletRequest request, BoardVo boardVo, BoardFileVo fileVo) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		Map<String, Object> map = boardService.boardDetail(boardVo, fileVo);
		
		//MemberDetail user = (MemberDetail) request.getAttribute("user");
		
		//String regid = user.getMember_id();
		
		mv.addObject("map", map.get("map")); //게시물 정보
		//mv.addObject("loginId", regid); //사용자 아이디 정보
		mv.addObject("list", map.get("list")); //게시물 파일 정보
		
		return mv;
	}
	
	@RequestMapping(value="/boardUpdateForm.do")
	public ModelAndView boardUpdateForm(HttpServletRequest request, BoardVo boardVo, BoardFileVo fileVo) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardUpdate");
		
		Map<String, Object> map = boardService.boardDetail(boardVo, fileVo);
		
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/boardUpdate.do")
	public ModelAndView boardUpdate(HttpServletRequest request, BoardVo boardVo, BoardFileVo fileVo) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/boardDetail.do");
		
		boardService.boardUpdate(boardVo, fileVo, request);
		
		mv.addObject("bno", boardVo.getBno());
		
		return mv;
	}
	
	@RequestMapping(value="/boardDelete.do")
	public ModelAndView boardDelete(BoardVo boardVo) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/board/boardList.do");
		
		boardService.boardDelete(boardVo);
		
		return mv;
	}
}
