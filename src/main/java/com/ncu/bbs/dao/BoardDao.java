package com.ncu.bbs.dao;

import com.ncu.bbs.pojo.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDao {
    public void addBoard(Board board);

    public List<Board> listAllBoard();

    public Board listAllPostsOfBoard(int boardId);

    public Board findBoardByBoardId(int boardId);

    public Board findBoardByBoardName(String boardName);

    public void updateBoardByBoard(Board board);

    public void deleteBoardById(int boardId);
}
