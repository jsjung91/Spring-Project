package jeong.menu;

import java.util.List;

import org.springframework.stereotype.Repository;

import jeong.common.dao.AbstractDao;

@Repository("menuDao")
public class MenuDao extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<MenuVo> roleMenu(MenuVo menu) throws Exception {
		return selectList("menu.roleMenu", menu);
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuVo> roleMenuAdmin(MenuVo menu) throws Exception {
		return selectList("menu.roleMenuAdmin", menu);
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenuList(Menu menu) throws Exception {
		return selectList("menu.getMenuList", menu);
	}
	
	public void saveMenu(Menu menu) throws Exception {
		insert("menu.insertMenu", menu);
	}
	
	public void updateMenu(Menu menu) throws Exception {
		update("menu.updateMenu", menu);
	}
	
	public void deleteMenu(String code) throws Exception {
		delete("menu.deleteMenu", code);
	}
} 
