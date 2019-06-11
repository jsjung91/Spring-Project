package jeong.login;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jeong.admin.AdminDao;
import jeong.member.MemberVo;

@Service("loginService")
public class LoginServiceImpl implements LoginService{

	@Resource(name="loginDao")
	private LoginDao loginDao;
	
	@Resource(name="adminDao")
	private AdminDao adminDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public MemberDetail checkMemberId(String memberid) throws Exception {
		return loginDao.checkMemberId(memberid);
	}
	
	@Override
	public int checkMemberIdCnt(String memberid) throws Exception {
		return loginDao.checkMemberIdCnt(memberid);
	}
	
	@Override
	public MemberVo checkKakaoMember(MemberDetail memberDetail) throws Exception {
		// īī�� �α��� Ȯ���ϱ�
		MemberVo memberVo = new MemberVo();
		
		// īī���� �������� ���� ����� ���� get
		memberVo.setMember_id(memberDetail.getId());
		//memberVo.setMember_nick()
		memberVo.setMember_name(memberDetail.getNickname());
		memberVo.setDel_yn("N");
		memberVo.setUse_yn("Y");
		
		MemberVo member = adminDao.selMemberNo(memberVo);
		
		return member;
	}
	
	@Override
	public void countFailure(String memberid) throws Exception{
		loginDao.updateFailureCount(memberid);
	}

	@Override
	public int checkFailureCount(String memberid) throws Exception{
		return loginDao.checkFailureCount(memberid);
	}

	@Override
	public void disabledUserid(String memberid) throws Exception{
		loginDao.updateDisabled(memberid);
	}

	@Override
	public void resetFailureCnt(String memberid) throws Exception{
		loginDao.updateFailureCountReset(memberid);
	}
	
	@Override
	public void updateAccessDate(String memberid) throws Exception{
		loginDao.updateNewAccessDate(memberid);
	}
}
