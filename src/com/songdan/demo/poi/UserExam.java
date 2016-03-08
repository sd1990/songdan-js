package com.songdan.demo.poi;

import java.util.Date;

public class UserExam {
    private String username;
    private String identity;
    private String examName;
    private String categoryParentName;
    private String categoryName;
    private String examPaperName;
    private int groupNum;
    private float totalScore;
    private float examScore;
    private String passFlag;
    private Date submitTime;
    private String userSubmitStatus;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getIdentity() {
        return identity;
    }
    
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    
    public String getExamName() {
        return examName;
    }
    
    public void setExamName(String examName) {
        this.examName = examName;
    }
    
    public String getCategoryParentName() {
        return categoryParentName;
    }
    
    public void setCategoryParentName(String categoryParentName) {
        this.categoryParentName = categoryParentName;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getExamPaperName() {
        return examPaperName;
    }
    
    public void setExamPaperName(String examPaperName) {
        this.examPaperName = examPaperName;
    }
    
    public int getGroupNum() {
        return groupNum;
    }
    
    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }
    
    public float getTotalScore() {
        return totalScore;
    }
    
    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }
    
    public float getExamScore() {
        return examScore;
    }
    
    public void setExamScore(float examScore) {
        this.examScore = examScore;
    }
    
    public String getPassFlag() {
        return passFlag;
    }
    
    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
    }
    
    public Date getSubmitTime() {
        return submitTime;
    }
    
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }
    
    public String getUserSubmitStatus() {
        return userSubmitStatus;
    }
    
    public void setUserSubmitStatus(String userSubmitStatus) {
        this.userSubmitStatus = userSubmitStatus;
    }
    
    
}
