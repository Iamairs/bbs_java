package com.ncu.bbs.service;


import com.ncu.bbs.pojo.Reply;

import java.util.List;

public interface ReplyService {
    public void addReply(Reply reply);

    public List<Reply> listReplyByPostId(int postId);

    public void deleteReply(int replyId);

    public void adoptReply(Reply reply);

    public Reply findReplyByReplyId(int replyId);
}
