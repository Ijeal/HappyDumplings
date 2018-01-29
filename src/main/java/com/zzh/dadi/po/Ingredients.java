package com.zzh.dadi.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ingredients")
public class Ingredients {
    @Id
    private int ingredientsId;
    private String ingredientsname;
    private int usergroup;
    private int isused;
    private String description;
    public int getIngredientsId() {
        return ingredientsId;
    }
    public void setIngredientsId(int ingredientsId) {
        this.ingredientsId = ingredientsId;
    }
    public String getIngredientsname() {
        return ingredientsname;
    }
    public void setIngredientsname(String ingredientsname) {
        this.ingredientsname = ingredientsname;
    }
    public int getUsergroup() {
        return usergroup;
    }
    public void setUsergroup(int usergroup) {
        this.usergroup = usergroup;
    }
    public int getIsused() {
        return isused;
    }
    public void setIsused(int isused) {
        this.isused = isused;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
