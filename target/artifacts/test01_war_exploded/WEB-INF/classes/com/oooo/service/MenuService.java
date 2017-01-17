package com.oooo.service;

import com.google.common.collect.Lists;
import com.oooo.dao.MenuDao;
import com.oooo.model.Menu;
import com.oooo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/27.
 */
@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;
    public List<Menu>  getMenusByUser(User user){
        List<Menu> menus = menuDao.getMenusByLevel(user.getLevel());
        Map<Integer,Menu> menusMap = new HashMap<>();
        for (Menu menu : menus) {
            if (menu.getMenuLevel() == 1){
                menusMap.put(menu.getId(),menu);
            }else {
                Menu parentMenu = menusMap.get(menu.getParentId());
                if (parentMenu != null){
                    parentMenu.getChildren().add(menu);
                }
            }
        }
        List<Menu> menuView = Lists.newArrayList(menusMap.values());
        return menuView;
    }
}
