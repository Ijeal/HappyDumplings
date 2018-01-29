package com.zzh.dadi.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "usergroup")
public class Group {
    @Id
    private int groupId;
    private String groupname;
    private int votecount;
    private String otheringredients;

    public int getGroupId() {
        return groupId;
    }
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
    public int getVotecount() {
        return votecount;
    }
    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }
    public String getOtheringredients() {
        return otheringredients;
    }
    public void setOtheringredients(String otheringredients) {
        this.otheringredients = otheringredients;
    }
    
}
