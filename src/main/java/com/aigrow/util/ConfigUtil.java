package com.aigrow.util;

import java.util.ResourceBundle;

public class ConfigUtil {
    public static final ResourceBundle bundle= java.util.ResourceBundle.getBundle("config");
/**
 * 获得sessionInfo名字
 *
 * @return
 */
public static final String getSessionInfoName(){
    return bundle.getString("sessionInfoName");
}
}
