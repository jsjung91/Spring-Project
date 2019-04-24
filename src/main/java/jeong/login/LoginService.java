package jeong.login;

public interface LoginService {

	public MemberDetail checkMemberId(String memberid) throws Exception;
	
	public int checkMemberIdCnt(String memberid) throws Exception;
	
	void countFailure(String memberid) throws Exception;
	
	int checkFailureCount(String memberid) throws Exception;
	
	void disabledUserid(String memberid) throws Exception;
	
	void resetFailureCnt(String memberid) throws Exception;
	
	void updateAccessDate(String memberid) throws Exception;
}
