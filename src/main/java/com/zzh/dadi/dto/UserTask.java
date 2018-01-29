package com.zzh.dadi.dto;

public class UserTask {
    private String userId;
    private String username;
    private int usergroup;
    private String usergroupName;
    private int ingredients;
    private String ingredientsName;
    private String otheringredients;
    private String otheringredientsStr;
    private int isvote;
    private int taskcardenable;
    private String timestampstart;
    private String timestampend;
    private String timeused;
    private int iscorrect;
    
    public String getTimestampstart() {
        return timestampstart;
    }
    public void setTimestampstart(String timestampstart) {
        this.timestampstart = timestampstart;
    }
    public String getTimestampend() {
        return timestampend;
    }
    public void setTimestampend(String timestampend) {
        this.timestampend = timestampend;
    }
    public String getTimeused() {
        return timeused;
    }
    public void setTimeused(String timeused) {
        this.timeused = timeused;
    }
    public int getIscorrect() {
        return iscorrect;
    }
    public void setIscorrect(int iscorrect) {
        this.iscorrect = iscorrect;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getUsergroup() {
        return usergroup;
    }
    public void setUsergroup(int usergroup) {
        this.usergroup = usergroup;
    }
    public String getUsergroupName() {
        return usergroupName;
    }
    public void setUsergroupName(String usergroupName) {
        this.usergroupName = usergroupName;
    }
    public int getIngredients() {
        return ingredients;
    }
    public void setIngredients(int ingredients) {
        this.ingredients = ingredients;
    }
    public String getIngredientsName() {
        return ingredientsName;
    }
    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }
    public String getOtheringredients() {
        return otheringredients;
    }
    public void setOtheringredients(String otheringredients) {
        this.otheringredients = otheringredients;
    }
    public String getOtheringredientsStr() {
        return otheringredientsStr;
    }
    public void setOtheringredientsStr(String otheringredientsStr) {
        this.otheringredientsStr = otheringredientsStr;
    }
    public int getIsvote() {
        return isvote;
    }
    public void setIsvote(int isvote) {
        this.isvote = isvote;
    }
    public int getTaskcardenable() {
        return taskcardenable;
    }
    public void setTaskcardenable(int taskcardenable) {
        this.taskcardenable = taskcardenable;
    }
    
}
