package xyz.chengzi.halma.listener;

import xyz.chengzi.halma.model.ChessBoardLocation;

public interface MoveCallBack {
    public void getPosition(ChessBoardLocation src, ChessBoardLocation dest);
}
