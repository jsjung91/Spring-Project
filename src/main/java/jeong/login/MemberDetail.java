package jeong.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jeong.menu.Menu;
import jeong.menu.MenuVo;

public class MemberDetail implements UserDetails{

	private static final long serialVersionUID = 5634620436147793548L;
	
	private Integer member_no;
	private String member_id;
	private String member_pwd;
	private String member_role;
	private String member_name;
	private String member_mail;
	private String member_phon;
	private String member_type;
	private boolean ENABLED;
	private boolean CREDEXPI;
	
	private String del_yn;
	private List<MenuVo> menuvo;
	private List<Menu> menu;
	
	private String msg;
	private Integer code;
	private String id;
	
	private JSONObject properties;
	
	private String profile_image;
	private String nickname;
	
	public Integer getMember_no() {
		return member_no;
	}
	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pwd() {
		return member_pwd;
	}
	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}
	public String getMember_role() {
		return member_role;
	}
	public void setMember_role(String member_role) {
		this.member_role = member_role;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}
	public String getMember_mail() {
		return member_mail;
	}
	public void setMember_mail(String member_mail) {
		this.member_mail = member_mail;
	}
	public String getMember_phon() {
		return member_phon;
	}
	public void setMember_phon(String member_phon) {
		this.member_phon = member_phon;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public List<MenuVo> getMenuvo() {
		return menuvo;
	}

	public void setMenuvo(List<MenuVo> menuvo) {
		this.menuvo = menuvo;
	}
	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public JSONObject getProperties() {
		return properties;
	}
	public void setProperties(JSONObject properties) {
		this.properties = properties;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return getMember_pwd();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getMember_id();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return CREDEXPI;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ENABLED;
	}
	


}
