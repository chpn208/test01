package com.oooo.util;

import com.oooo.model.Permissions;

import java.util.Map;

/**
 * Created by chenpan on 17-2-25.
 */
public class JstlUtil {
    public static String getPermissionName(int level){
        Map<Integer, Permissions> permisstionMap = Constant.getInstance().getPermissionsMap();
        Permissions permisstion = permisstionMap.get(level);
        return permisstion.getDesc();
    }
}
