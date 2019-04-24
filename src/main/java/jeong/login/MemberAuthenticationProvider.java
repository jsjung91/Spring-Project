package jeong.login;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jeong.menu.Menu;
import jeong.menu.MenuVo;

public class MemberAuthenticationProvider implements AuthenticationProvider{
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	MemberDetailService memberDetailService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String memberid = (String)authentication.getPrincipal();
		String memberpwd = (String)authentication.getCredentials();
		
		log.debug("AuthenticationProvider :::::: 1");
		
		MemberDetail memberDetail = memberDetailService.loadUserByUsername(memberid);
		
		if(memberDetail == null) {
			//throw new UsernameNotFoundException("[ "+ memberid +" ] 사용자를 찾을 수 없습니다.");
			throw new AuthenticationCredentialsNotFoundException(memberid);
		}
		
		if(!passwordEncoder.matches(memberpwd, memberDetail.getPassword())) {
			//throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
			throw new BadCredentialsException(memberid);
		}
		
		//MenuVo vo = new MenuVo();
		Menu vo = new Menu();
		//vo.setLogin_id(memberid);
		//vo.setRole_char(memberDetail.getMember_role().toString());
		//vo.setAdmin_yn("N");
		
		//List<MenuVo> menuVo = memberDetailService.menuList(vo);
		List<Menu> menu = memberDetailService.menuList(vo);
		memberDetail.setMenu(menu);
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		
		roles.add(new SimpleGrantedAuthority(memberDetail.getMember_role()));
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(memberid, memberDetail.getPassword(), roles);
		result.setDetails(memberDetail);
		
		return result;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	//private boolean matchPassword(String memberpwd, String memberpwd)
}
