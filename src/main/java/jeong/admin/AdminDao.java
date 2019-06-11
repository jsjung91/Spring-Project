package jeong.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import jeong.common.dao.AbstractDao;
import jeong.login.MemberDetail;
import jeong.member.MemberVo;
import jeong.menu.MenuVo;

@Repository("adminDao")
public class AdminDao extends AbstractDao {

	@SuppressWarnings("unchecked")
	public List<MemberDetail> adminMemberList(MemberDetail user) throws Exception {
		return selectList("admin.adminMemberList", user);
	}
	
	public MemberVo adminMemberInfoDetail(MemberVo user) throws Exception{
		return (MemberVo) selectOne("admin.adminMemberInfoDetail", user);
	}
	
	public void adminMemberInfoInsert(MemberVo user) throws Exception{
		insert("admin.memberInsert", user);
    }
	
	public void adminMemberDetailInfoInsert(MemberVo user) throws Exception{
		insert("admin.memberDetailInsert", user);
    }
	
	public void adminMemberInfoUpdate(MemberVo user) throws Exception{
		update("admin.memberUpdate", user);
    }
	
	public void adminMemberDelete(MemberVo user) throws Exception {
		update("admin.memberDelete", user);
	}

	public void adminMemberDetailInfoUpdate(MemberVo user) throws Exception{
		update("admin.memberDetailUpdate", user);
    }
	
	public void adminMemberDetailDelete(MemberVo user) throws Exception {
		update("admin.memberDetailDelete", user);
	}
	
	@SuppressWarnings("unchecked")
	public List<AdminVo> authManagerList() throws Exception {
		return selectList("admin.authManagerList");
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuVo> menuManagerList(MenuVo menu) throws Exception {
		return selectList("admin.menuManagerList", menu);
	}
	
	// 사용자 no 조회
	public MemberVo selMemberNo(MemberVo user) throws Exception {
		return (MemberVo) selectOne("admin.selMemberNo", user);
	}
}
