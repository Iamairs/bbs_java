package com.ncu.bbs.pojo;

import java.util.List;

public class PostAndReply {
    private final Post post;
    private final List<Reply> reply;


    public PostAndReply(Post post,List<Reply> reply) {
        this.post = post;
        this.reply = reply;
    }

    public Post getPost() {
        return post;
    }

    public List<Reply> getReply() {
        return reply;
    }
}
