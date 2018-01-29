package com.zzh.dadi.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_userinfo")
public class User {
    @Id
    private String userId;
    private String username;
    private int usergroup;
    private int ingredients;
    private String otheringredients;
    private int isvote;
    private int taskcardenable;
    private String timestampstart;
    private String timestampend;
    private String timeused;
    private int iscorrect;
    private int quizusergroup;


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

    public int getIngredients() {
        return ingredients;
    }

    public void setIngredients(int ingredients) {
        this.ingredients = ingredients;
    }

    public String getOtheringredients() {
        return otheringredients;
    }

    public void setOtheringredients(String otheringredients) {
        this.otheringredients = otheringredients;
    }

    public int getIsvote() {
        return isvote;
    }

    public void setIsvote(int isvote) {
        this.isvote = isvote;
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

    public int getTaskcardenable() {
        return taskcardenable;
    }

    public void setTaskcardenable(int taskcardenable) {
        this.taskcardenable = taskcardenable;
    }

    public int getQuizusergroup() {
        return quizusergroup;
    }

    public void setQuizusergroup(int quizusergroup) {
        this.quizusergroup = quizusergroup;
    }

}

