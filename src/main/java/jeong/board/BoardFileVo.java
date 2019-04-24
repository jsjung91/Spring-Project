package jeong.board;

import java.io.Serializable;

public class BoardFileVo implements Serializable{

	private static final long serialVersionUID = 8323187657227494729L;
	
	private Integer bno=0;
	private String brdid="";
	private String ori_file_name="";
	private String stored_file_name="";
	private Long file_size=0L;
	private String reg_id="";
	private String del_gb="";
	private String regid="";
	
	private Integer f_idx=0;

	private String is_new="";

	private Integer maxidx=0;

	private String fileext="";
	
	public Integer getBrdidx() {
		return bno;
	}

	public void setBrdidx(Integer bno) {
		this.bno = bno;
	}

	public String getBrdid() {
		return brdid;
	}

	public void setBrdid(String brdid) {
		this.brdid = brdid;
	}

	public String getOri_file_name() {
		return ori_file_name;
	}

	public void setOri_file_name(String ori_file_name) {
		this.ori_file_name = ori_file_name;
	}

	public String getStored_file_name() {
		return stored_file_name;
	}

	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}

	public Long getFile_size() {
		return file_size;
	}

	public void setFile_size(Long file_size) {
		this.file_size = file_size;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public String getDel_gb() {
		return del_gb;
	}

	public void setDel_gb(String del_gb) {
		this.del_gb = del_gb;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}
	
	public Integer getF_idx() {
		return f_idx;
	}

	public void setF_idx(Integer f_idx) {
		this.f_idx = f_idx;
	}

	public String getIs_new() {
		return is_new;
	}

	public void setIs_new(String is_new) {
		this.is_new = is_new;
	}

	public Integer getMaxidx() {
		return maxidx;
	}

	public void setMaxidx(Integer maxidx) {
		this.maxidx = maxidx;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
