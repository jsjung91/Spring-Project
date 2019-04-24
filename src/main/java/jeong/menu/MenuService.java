package jeong.menu;

import java.util.List;

public interface MenuService {

	public List<MenuVo> roleMenu(MenuVo menu) throws Exception;
	
	public List<MenuVo> roleMenuAdmin(MenuVo menu) throws Exception;
	
	public List<Menu> getMenuList(Menu menu) throws Exception;
	
	public void saveMenu(Menu menu) throws Exception;
	
	public void updateMenu(Menu menu) throws Exception;
	
	public void deleteMenu(String code) throws Exception;
	
}
