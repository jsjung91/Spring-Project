package jeong.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Resource(name="menuDao")
	private MenuDao menuDao;
	
	@Override
	public List<MenuVo> roleMenu(MenuVo menu) throws Exception {
		return menuDao.roleMenu(menu);
	}
	
	@Override
	public List<MenuVo> roleMenuAdmin(MenuVo menu) throws Exception {
		return menuDao.roleMenuAdmin(menu);
	}
	
	@Override
	public List<Menu> getMenuList(Menu menu) throws Exception {
		return menuDao.getMenuList(menu);
	}
	
	@Override
	public void saveMenu(Menu menu) throws Exception {
		menuDao.saveMenu(menu);
	}
	
	@Override
	public void updateMenu(Menu menu) throws Exception {
		menuDao.updateMenu(menu);
	}
	
	@Override
	public void deleteMenu(String code) throws Exception {
		menuDao.deleteMenu(code);
	}
}
