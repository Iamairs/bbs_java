package com.ncu.bbs.service.Impl;


import com.ncu.bbs.dao.BoardDao;
import com.ncu.bbs.dao.PostDao;
import com.ncu.bbs.pojo.Board;
import com.ncu.bbs.pojo.Post;
import com.ncu.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;

    @Autowired
    BoardDao boardDao;

    @Override
    public void addPostByPost(Post post) {
        postDao.addPost(post);
    }

    @Override
    public Post listPostContent(int postId) {
        return postDao.findPostByPostId(postId);
    }

    @Override
    public List<Post> listAllPost(int postBoardId) {
        return postDao.listAllPostInfo(postBoardId);
    }


    @Override
    public void deletePost(int postId) {
        // 更新Board数据
        int boardId = postDao.findPostByPostId(postId).getPostBoardId();
        Board board = boardDao.findBoardByBoardId(boardId);
        board.setBoardPostNum(board.getBoardPostNum() - 1);
        boardDao.updateBoardByBoard(board);

        // 删除post
        postDao.deletePostById(postId);
    }

    @Override
    public void updatePost(Post post) {
        postDao.updatePostByPost(post);
    }

}
