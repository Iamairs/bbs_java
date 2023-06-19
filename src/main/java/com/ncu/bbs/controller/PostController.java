package com.ncu.bbs.controller;

import com.ncu.bbs.pojo.*;
import com.ncu.bbs.service.BoardService;
import com.ncu.bbs.service.PostService;
import com.ncu.bbs.service.ReplyService;
import com.ncu.bbs.service.UserService;
import com.ncu.bbs.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ncu.bbs.dao.BoardDao;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/post")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    BoardService boardService;

    @Autowired
    ReplyService replyService;

    @Autowired
    UserService userService;

    @Autowired
    BoardDao boardDao;
    // 发布帖子
    @PostMapping(value = "/addPost")
    public Result<Post> addPost(@RequestBody Post post) {
        if (post != null) {
            Post newPost = post;
            //加入创建时间
            Timestamp createLoginTime = new Timestamp(new Date().getTime());
            newPost.setPostCreateTime(createLoginTime);
            newPost.setPostUpdateTime(createLoginTime);
            //查看用户积分是否大于发求助帖所需要的积分
            User user=userService.getUserByUserName(newPost.getPostUserName());
            if(user.getCredit()<newPost.getCredit()){
                return Result.buildResult(Result.Status.POOR,post);
            }
            //对用户加分
            user.setCredit(10 + user.getCredit());
            userService.updateUserByUserName(user);
            //执行SQL语句
            postService.addPostByPost(newPost);
            //总帖子加一
            boardService.updatePostNum(newPost.getPostBoardId());
            //返回成功
            return Result.buildResult(Result.Status.OK,post);
        }
        //返回失败
        return Result.buildResult(Result.Status.BAD_REQUEST,post);
    }

    // 查看帖子
    @GetMapping(value = "postContent-{postId}")
    public Result<PostAndReply> intoPost(@PathVariable int postId) {
        Post post = postService.listPostContent(postId);
        post.setPostViewCount(post.getPostViewCount()+1);
        postService.updatePost(post);
        List<Reply> replies = replyService.listReplyByPostId(postId);
        PostAndReply postAndReply=new PostAndReply(post,replies);
        if (post == null) {
            return Result.buildResult(Result.Status.UNAUTHORIZED, postAndReply);
        }
        return Result.buildResult(Result.Status.OK, postAndReply);
    }

    // 删除帖子
    @DeleteMapping(value = "deletePostContent-{postId}")
    public Result deletePost(@PathVariable int postId) {
            Post post=postService.listPostContent(postId);
            List<Reply> replies = replyService.listReplyByPostId(post.getPostId());
            for(Reply reply:replies){
                replyService.deleteReply(reply.getReplyId());
            }
            postService.deletePost(postId);
            int boardId=post.getPostBoardId();
            //总帖子数减一
            Board board = boardDao.findBoardByBoardId(boardId);
            board.setBoardPostNum(board.getBoardPostNum() - 1);
            boardDao.updateBoardByBoard(board);
            return Result.buildResult(Result.Status.OK);
    }
   // 修改帖子
    @PutMapping(value = "/putPost")
    public Result<Post> putPost(@RequestBody Post post){
        Post newPost=postService.listPostContent(post.getPostId());
        if (newPost != null) {
            //添加修改时间
            Timestamp createLoginTime = new Timestamp(new Date().getTime());
            newPost.setPostUpdateTime(createLoginTime);
            newPost.setPostContent(post.getPostContent());
            newPost.setPostTitle(post.getPostTitle());
            newPost.setCredit(post.getCredit());
            User user=userService.getUserByUserName(newPost.getPostUserName());
            if(user.getCredit()<post.getCredit()){
                return Result.buildResult(Result.Status.POOR,post);
            }
            //执行SQL语句
            postService.updatePost(newPost);
            newPost=postService.listPostContent(newPost.getPostId());
            return Result.buildResult(Result.Status.OK,newPost);
        }
        //返回失败
        return Result.buildResult(Result.Status.BAD_REQUEST,post);
    }

    //帖子加精
    @PutMapping(value = "refinePost-{postId}")
    public Result<Post> refinePost(@PathVariable int postId){
            Post post=postService.listPostContent(postId);
            System.out.println(post);
            if(post!=null){
                post.setRefinement(1);
                postService.updatePost(post);
                return Result.buildResult(Result.Status.OK,post);
            }
        return Result.buildResult(Result.Status.BAD_REQUEST,post);
    }

    //帖子取消加精
    @PutMapping(value = "UnRefinePost-{postId}")
    public Result<Post> UnRefinePost(@PathVariable int postId){
        Post post=postService.listPostContent(postId);
        if(post!=null){
            post.setRefinement(0);
            postService.updatePost(post);
            return Result.buildResult(Result.Status.OK,post);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST,post);
    }
    //帖子置顶
    @PutMapping(value = "topPost-{postId}")
    public Result<Post> topPost(@PathVariable int postId){
        Post post=postService.listPostContent(postId);
        if(post!=null){
            post.setTop(1);
            postService.updatePost(post);
            return Result.buildResult(Result.Status.OK,post);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST,post);
    }

    //帖子取消置顶
    @PutMapping(value = "UnTopPost-{postId}")
    public Result<Post> UnTopPost(@PathVariable int postId){
        Post post=postService.listPostContent(postId);
        if(post!=null){
            post.setTop(0);
            postService.updatePost(post);
            return Result.buildResult(Result.Status.OK,post);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST,post);
    }

}
