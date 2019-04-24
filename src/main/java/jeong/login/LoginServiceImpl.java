package jeong.login;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jeong.admin.AdminDao;

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
