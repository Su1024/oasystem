package com.oasystem.model;

import lombok.Data;

@Data
public class SysMenu {
    private Integer id;

    /**
    * 菜单图标
    */
    private String menuIcon;

    /**
    * 菜单名称
    */
    private String menuName;

    /**
    * 菜单路径
    */
    private String menuUrl;

    /**
    * 父级菜单
    */
    private Integer parentId;

    /**
    * 排序
    */
    private Integer sortId;

    /**
    * 是否显示 1显示 0 隐藏
    */
    private Integer isShow;
}