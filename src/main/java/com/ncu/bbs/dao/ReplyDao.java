package com.ncu.bbs.dao;

import com.ncu.bbs.pojo.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyDao {
    public void addReply(Reply reply);

    public List<Reply> listReplyByPostId(int postId);

    public void deleteReplyById(int postId);

    public Reply findReplyByReplyId(int replyId);

    public List<Reply> listAllReply();

    public void adoptReply(Reply reply);
}
