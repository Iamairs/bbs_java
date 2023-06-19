package com.ncu.bbs.service.Impl;


import com.ncu.bbs.dao.PostDao;
import com.ncu.bbs.dao.ReplyDao;
import com.ncu.bbs.pojo.Post;
import com.ncu.bbs.pojo.Reply;
import com.ncu.bbs.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyDao replyDao;

    @Autowired
    PostDao postDao;

    @Override
    public void addReply(Reply reply) {
        // 更新post信息
        Reply dbReply = reply;
        int postId = reply.getReplyPostId();
        Post post = postDao.findPostByPostId(postId);
        post.setPostReplyCount(post.getPostReplyCount() + 1);
        postDao.updatePostByPost(post);

        // 添加回复
        dbReply.setReplyCreateTime(new Timestamp(new Date().getTime()));
        replyDao.addReply(dbReply);
    }

    @Override
    public List<Reply> listReplyByPostId(int replyPostId) {
        return replyDao.listReplyByPostId(replyPostId);
    }

    @Override
    public void deleteReply(int replyId) {
        // 更新post信息
        Reply reply = replyDao.findReplyByReplyId(replyId);
        Post post = postDao.findPostByPostId(reply.getReplyPostId());
        post.setPostReplyCount(post.getPostReplyCount() - 1);
        postDao.updatePostByPost(post);

        // 删除回复
        replyDao.deleteReplyById(replyId);
    }

    @Override
    public void adoptReply(Reply reply) {
        replyDao.adoptReply(reply);
    }

    @Override
    public Reply findReplyByReplyId(int replyId) {
        return replyDao.findReplyByReplyId(replyId);
    }

}
