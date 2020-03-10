package com.chess.engine.pieces;

import com.chess.engine.board.*;
import com.chess.engine.Alliance;

import java.util.List;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;


    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = false;
    }

    public abstract List<Move> calculateLegalMoves(final Board board);

    public int getPiecePosition(){
        return this.piecePosition;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }
    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }
}
