package com.oooo.dao;

import com.oooo.model.Menu;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */
public interface MenuDao {
    public List<Menu> getMenusByLevel(int level);
}
