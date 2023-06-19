package com.ncu.bbs.dao;

import com.ncu.bbs.pojo.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao {
    public void addPost(Post post);

    public Post findPostByPostId(int postId);

    public List<Post> listAllPostInfo(int postBoardId);

    public void deletePostById(int postId);

    public void updatePostByPost(Post post);
}
