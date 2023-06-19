package com.ncu.bbs.controller;

import com.ncu.bbs.pojo.Board;
import com.ncu.bbs.pojo.Post;
import com.ncu.bbs.service.BoardService;
import com.ncu.bbs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import com.ncu.bbs.util.Result;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/board")
@CrossOrigin(origins = "*")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    PostService postService;
    // 显示相应板块的帖子
    @GetMapping(value = "listPosts-{boardId}")
    public Result<Board> intoBoard(@PathVariable int boardId) {
        Board board = boardService.intoBoardByBoardId(boardId);
        List<Post> list= postService.listAllPost(boardId);
        Collections.sort(list);
        board.setPosts(list);
        if(board!=null){
            return Result.buildResult(Result.Status.OK,board);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST);
    }
    //获取所有板块
    @GetMapping(value = "findAllBoard")
    public Result<List<Board>> findAllBoard() {
        List<Board> list=boardService.listAllBoard();
        if(list!=null){
            for(Board board:list){
                List<Post> posts=postService.listAllPost(board.getBoardId());
                Collections.sort(posts);
                board.setPosts(posts);
            }
            return Result.buildResult(Result.Status.OK,list);
        }
        return Result.buildResult(Result.Status.BAD_REQUEST);
    }

}
