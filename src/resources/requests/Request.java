package resources.requests;

import resources.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Request {
    List<MenuItem> menuList = new ArrayList<>();
    String guideline = "";

    public Request() {
    }

    public List<MenuItem> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuItem> menuList) {
        this.menuList = menuList;
    }

    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(String guideline) {
        this.guideline = guideline;
    }
}
