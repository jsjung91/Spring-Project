package jeong.login;

import org.springframework.stereotype.Repository;

import jeong.common.dao.AbstractDao;

@Repository("loginDao")
public class LoginDao extends AbstractDao{

	public MemberDetail checkMemberId(String memberid) throws Exception{
		return (MemberDetail) selectOne("login.check_MemberId", memberid);
	}
	
	public int checkMemberIdCnt(String memberid) throws Exception {
		return (int) selectOne("login.check_MemberIdCnt", memberid);
	}
	
	public void updateFailureCount(String memberid) throws Exception {
		update("login.updateFailureCount", memberid);
	}
	
	public int checkFailureCount(String memberid) throws Exception {
		return (int) selectOne("login.checkFailureCount", memberid);
	}
	
	public void updateDisabled(String memberid) throws Exception {
		update("login.updateUnenabled", memberid);
	}
	
	public void updateFailureCountReset(String memberid) throws Exception {
		update("login.updateFailureCountReset", memberid);
	}
	
	public void updateNewAccessDate(String memberid) throws Exception {
		update("login.updateNewAccessDate", memberid);
	}
}
