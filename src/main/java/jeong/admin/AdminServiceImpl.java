package jeong.admin;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jeong.common.common.CommonDao;
import jeong.login.MemberDetail;
import jeong.member.MemberVo;
import jeong.menu.MenuVo;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Resource(name="adminDao")
	private AdminDao adminDao;
	
	@Resource
	private CommonDao commonDao;
	
	@Override
	public List<MemberDetail> adminMemberList(MemberDetail user) throws Exception {
		return adminDao.adminMemberList(user);
	}
	
	@Override
	public MemberVo adminMemberInfoDetail(MemberVo user) throws Exception{
		return adminDao.adminMemberInfoDetail(user);
	}
	
	@Override
	public int adminMemberInfoUpdate(MemberVo user) throws Exception {
		int result = 0;

		if(user.getStatus().equals("insert")){
			//commonDao.updateSeq("MEMBER_NO");
			//int member_no = commonDao.selectSeq("MEMBER_NO");
			//user.setMember_no(member_no);
			adminDao.adminMemberInfoInsert(user);
			//adminDao.adminMemberDetailInfoInsert(user); 
			result = 1;
		}

		if(user.getStatus().equals("update")){
			if(!user.getMember_pwd().equals(user.getMember_pwd_cur())){
				user.setMember_pwd(passwordEncoder.encode(user.getMember_pwd()));
			}
			adminDao.adminMemberInfoUpdate(user);
			adminDao.adminMemberDetailInfoUpdate(user);
			result = 2;
		}

		return result;
	}
	
	@Override
	public void adminMemberDelete(MemberVo user) throws Exception {
		adminDao.adminMemberDetailDelete(user);
		adminDao.adminMemberDelete(user);
	}
	
	@Override
	public List<AdminVo> authManagerList() throws Exception {
		return adminDao.authManagerList();
	}
	
	@Override
	public List<MenuVo> menuManagerList(MenuVo menuvo) throws Exception {
		return adminDao.menuManagerList(menuvo);
	}
	
}
