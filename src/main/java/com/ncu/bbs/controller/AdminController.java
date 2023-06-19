package com.ncu.bbs.controller;


import com.ncu.bbs.pojo.Board;
import com.ncu.bbs.pojo.Post;
import com.ncu.bbs.pojo.Reply;
import com.ncu.bbs.service.BoardService;
import com.ncu.bbs.service.PostService;
import com.ncu.bbs.service.ReplyService;
import com.ncu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ncu.bbs.util.Result;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    ReplyService replyService;

    // 添加论坛板块
    @PostMapping(value = "/addBoard")
    public Result<List<Board>> addBoard(@RequestBody Board board) {
        if (board != null) {
            boardService.addBoardByBoard(board);
            List<Board> list= boardService.listAllBoard();
            return Result.buildResult(Result.Status.OK,list);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST);
    }

    // 修改板块信息
    @PutMapping(value = "/updateBoard")
    public Result<List<Board>> updateBoard(@RequestBody Board board) {
        if (board != null) {
            boardService.updateBoardInfo(board);
            List<Board> list= boardService.listAllBoard();
            return Result.buildResult(Result.Status.OK,list);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST);
    }

    // 删除板块
    @DeleteMapping(value = "/deleteBoard-{boardId}")
    public Result<List<Board>> deleteBoard(@PathVariable int boardId) {
        List<Post> posts=postService.listAllPost(boardId);
        for(Post post:posts){
            List<Reply> replies = replyService.listReplyByPostId(post.getPostId());
            for(Reply reply:replies){
                replyService.deleteReply(reply.getReplyId());
            }
            postService.deletePost(post.getPostId());
        }
        boardService.deleteBoard(boardId);
        List<Board> list= boardService.listAllBoard();
        return Result.buildResult(Result.Status.OK,list);
    }
}
