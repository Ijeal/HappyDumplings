package com.zzh.dadi.po;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "questionnaire")
public class Questionnaire {
    @Id
    private int questionnaireId;
    private String answername;
    private String answer;
    private int usergroup;

    public int getQuestionnaireId() {
        return questionnaireId;
    }
    public void setQuestionnaireId(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswername() {
        return answername;
    }
    public void setAnswername(String answername) {
        this.answername = answername;
    }
    public int getUsergroup() {
        return usergroup;
    }
    public void setUsergroup(int usergroup) {
        this.usergroup = usergroup;
    }
    
}
