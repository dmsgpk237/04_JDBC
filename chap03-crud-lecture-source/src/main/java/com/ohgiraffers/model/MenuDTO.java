package com.ohgiraffers.model;

public class MenuDTO {

    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderable;

    public MenuDTO() {
    }

    public MenuDTO(int menuCode, String menuName, int menuPrice, int categoryCode, String orderable) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderable = orderable;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderable() {
        return orderable;
    }

    public void setOrderable(String orderable) {
        this.orderable = orderable;
    }


    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderable='" + orderable + '\'' +
                '}';
    }

}
