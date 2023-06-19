package com.ncu.bbs.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public class Post implements Comparable<Post>{

    // 文章属性
    private User user;
    private int postId;
    private int postBoardId;
    private String postUserName;
    private String postTitle;
    private String postContent;
    private int postGoodCount;
    private int postBadCount;
    private int postViewCount;
    private int postReplyCount;
    private int postStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp postCreateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp postUpdateTime;
    private List<Reply> replies;
    private int refinement;
    private int top;
    private int credit;
    private int isadopt;

    public int getIsadopt() {
        return isadopt;
    }

    public void setIsadopt(int isadopt) {
        this.isadopt = isadopt;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getRefinement() {
        return refinement;
    }

    public void setRefinement(int refinement) {
        this.refinement = refinement;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostBoardId() {
        return postBoardId;
    }

    public void setPostBoardId(int postBoardId) {
        this.postBoardId = postBoardId;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getPostGoodCount() {
        return postGoodCount;
    }

    public void setPostGoodCount(int postGoodCount) {
        this.postGoodCount = postGoodCount;
    }

    public int getPostBadCount() {
        return postBadCount;
    }

    public void setPostBadCount(int postBadCount) {
        this.postBadCount = postBadCount;
    }

    public int getPostViewCount() {
        return postViewCount;
    }

    public void setPostViewCount(int postViewCount) {
        this.postViewCount = postViewCount;
    }

    public int getPostReplyCount() {
        return postReplyCount;
    }

    public void setPostReplyCount(int postReplyCount) {
        this.postReplyCount = postReplyCount;
    }

    public int getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(int postStatus) {
        this.postStatus = postStatus;
    }

    public Timestamp getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(Timestamp postCreateTime) {
        this.postCreateTime = postCreateTime;
    }

    public Timestamp getPostUpdateTime() {
        return postUpdateTime;
    }

    public void setPostUpdateTime(Timestamp postUpdateTime) {
        this.postUpdateTime = postUpdateTime;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postBoardId=" + postBoardId +
                ", postUserName='" + postUserName + '\'' +
                ", postTitle='" + postTitle + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postGoodCount=" + postGoodCount +
                ", postBadCount=" + postBadCount +
                ", postViewCount=" + postViewCount +
                ", postReplyCount=" + postReplyCount +
                ", postStatus=" + postStatus +
                ", postCreateTime=" + postCreateTime +
                ", postUpdateTime=" + postUpdateTime + '}';
    }
    @Override
    public int compareTo(Post post){
        long t1=this.getPostCreateTime().getTime();
        long t2=post.getPostCreateTime().getTime();
        return (int)(t1-t2);
    }
}
