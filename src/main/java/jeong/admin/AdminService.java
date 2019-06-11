package jeong.admin;

import java.util.List;

import jeong.login.MemberDetail;
import jeong.member.MemberVo;
import jeong.menu.MenuVo;

public interface AdminService {

	public List<MemberDetail> adminMemberList(MemberDetail user) throws Exception;
	
	public MemberVo adminMemberInfoDetail(MemberVo user) throws Exception;
	
	public int adminMemberInfoUpdate(MemberVo memberVo) throws Exception;

	public List<AdminVo> authManagerList() throws Exception;
	
	public List<MenuVo> menuManagerList(MenuVo menuvo) throws Exception;
	
	public void adminMemberDelete(MemberVo memberVo) throws Exception;
}
