package jeong.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jeong.menu.Menu;
import jeong.menu.MenuDao;
import jeong.menu.MenuVo;

public class MemberDetailService implements UserDetailsService{

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	MenuDao menuDao;
	
	@Override
	public MemberDetail loadUserByUsername(String memberid) throws UsernameNotFoundException {
		MemberDetail memberDetail = null;
		try {
			memberDetail = loginDao.checkMemberId(memberid);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return memberDetail;
	}
	
	public List<Menu> menuList(Menu vo) throws UsernameNotFoundException {
		//List<MenuVo> menu = null;
		List<Menu> menu = null;
		try {
			//menu = menuDao.roleMenu(vo);
			menu = menuDao.getMenuList(vo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return menu;
	}
}
