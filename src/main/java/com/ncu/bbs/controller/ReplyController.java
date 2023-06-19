package com.ncu.bbs.controller;

import com.ncu.bbs.pojo.Post;
import com.ncu.bbs.pojo.PostAndReply;
import com.ncu.bbs.pojo.Reply;
import com.ncu.bbs.pojo.User;
import com.ncu.bbs.service.BoardService;
import com.ncu.bbs.service.PostService;
import com.ncu.bbs.service.ReplyService;
import com.ncu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ncu.bbs.util.Result;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/reply")
@CrossOrigin(origins = "*")
public class ReplyController {
    @Autowired
    PostService postService;

    @Autowired
    ReplyService replyService;

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    // 添加回复
    @PostMapping(value = "/addReply")
    public Result<Reply> addReply(@RequestBody Reply reply) {
        Timestamp createLoginTime = new Timestamp(new Date().getTime());
        //添加时间
        reply.setReplyCreateTime(createLoginTime);
        //插入Reply表
        replyService.addReply(reply);
        // 通过用户名查找User对象
        User user = userService.getUserByUserName(reply.getReplyUserName());
        //加积分
        user.setCredit(5 + user.getCredit());
        userService.updateUserByUserName(user);

        return Result.buildResult(Result.Status.OK);
    }

    // 删除回复
    @DeleteMapping(value = "deleteReplyContent-{replyId}")
    public Result<Reply> deleteReply(@PathVariable int replyId) {
        //通过replyId获取表中对象
        Reply reply=replyService.findReplyByReplyId(replyId);
        //获取post对象
        Post post=postService.listPostContent(reply.getReplyPostId());
        //获取user对象
        User user=userService.getUserByUserName(reply.getReplyUserName());
        //判断是否为求助贴以及是否被采纳，若是则需要修改post,以及两个用户的积分
        if(post.getCredit()!=0 && post.getIsadopt()==1){
            post.setIsadopt(0);
            user.setCredit(user.getCredit()- post.getCredit());
            userService.updateUserByUserName(user);
            user=userService.getUserByUserName(post.getPostUserName());
            user.setCredit(user.getCredit()+ post.getCredit());
            userService.updateUserByUserName(user);
        }
        //删除操作
        replyService.deleteReply(replyId);
        post.setPostReplyCount(post.getPostReplyCount()-1);
        postService.updatePost(post);
        return Result.buildResult(Result.Status.OK);
    }
    //采纳回复
    @PutMapping(value = "/adopt")
    public Result<PostAndReply> adopt(@RequestBody Reply reply){
        //通过id获取对象
        Reply newReply=replyService.findReplyByReplyId(reply.getReplyId());
        //修改reply采纳标签
        newReply.setIsadopt(1);
        replyService.adoptReply(newReply);
        //修改post对应标签
        Post post= postService.listPostContent(newReply.getReplyPostId());
        post.setIsadopt(1);
        postService.updatePost(post);
        //添加被采纳者的积分
        User user=userService.getUserByUserName(newReply.getReplyUserName());
        user.setCredit(user.getCredit()+post.getCredit());
        userService.updateUserByUserName(user);
        //减少提问者的积分
        user=userService.getUserByUserName(post.getPostUserName());
        user.setCredit(user.getCredit()-post.getCredit());
        userService.updateUserByUserName(user);
        //返还当前帖子以及其下所有回复
        List<Reply> replies = replyService.listReplyByPostId(post.getPostId());
        PostAndReply postAndReply=new PostAndReply(post,replies);
        if (post == null) {
            return Result.buildResult(Result.Status.UNAUTHORIZED, postAndReply);
        }
        return Result.buildResult(Result.Status.OK, postAndReply);
    }
}
