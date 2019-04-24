package jeong.board;

import java.util.Date;

public class BoardVo {

	private int bno;
	private String subject;
	private String content;
	private String writer;
	private String delgb = "";
	private Date reg_date;
	private int hit;
	
	private String regid = "";
	private String modid = "";
	
	private Integer start= 0;
	private Integer end= 0;
	private Integer currentPageNo= 0;
	private Integer pagerow= 0;
	private Integer totalcount= 0;
	private Integer paginationInfo= 0;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDelgb() {
		return delgb;
	}
	public void setDelgb(String delgb) {
		this.delgb = delgb;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public Integer getPagerow() {
		return pagerow;
	}
	public void setPagerow(Integer pagerow) {
		this.pagerow = pagerow;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getPaginationInfo() {
		return paginationInfo;
	}
	public void setPaginationInfo(Integer paginationInfo) {
		this.paginationInfo = paginationInfo;
	}
}
