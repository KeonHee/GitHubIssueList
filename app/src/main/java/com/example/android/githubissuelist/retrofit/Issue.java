package com.example.android.githubissuelist.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 2017-01-21.
 */

public class Issue {
    @SerializedName("avatar_url") String userImage;
    @SerializedName("user") User user;
    @SerializedName("number") int IssueNumber;
    @SerializedName("title") String IssueTitle;
    @SerializedName("comments") int commentCount;


    public Issue() {
    }

    public Issue(String userImage, User user, int issueNumber, String issueTitle, int commentCount) {
        this.userImage = userImage;
        this.user = user;
        IssueNumber = issueNumber;
        IssueTitle = issueTitle;
        this.commentCount = commentCount;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getIssueNumber() {
        return IssueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        IssueNumber = issueNumber;
    }

    public String getIssueTitle() {
        return IssueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        IssueTitle = issueTitle;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
