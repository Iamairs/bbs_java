package com.ncu.bbs.service;

import com.ncu.bbs.pojo.Post;

import java.util.List;

public interface PostService {
    public void addPostByPost(Post post);

    public Post listPostContent(int postId);

    public List<Post> listAllPost(int postBoardId);

    public void deletePost(int postId);

    public void updatePost(Post post);

}
