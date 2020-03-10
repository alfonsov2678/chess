package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    private final int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};
    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATE) {
            int candidateDestinationCoordinate = this.piecePosition + (currentCandidateOffset* this.getPieceAlliance().getDirection());

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }

            if(currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            } else if(currentCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.pieceAlliance.isBlack())  || (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite())){
                    final int behindCandidateDestinationCoordiante = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                    if(!board.getTile(behindCandidateDestinationCoordiante).isTileOccupied() &&
                            !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
            } else if(currentCandidateOffset == 7 && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) )){
                    if(board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        final Piece pieceOnTile = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnTile.getPieceAlliance()){
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }

                    }
            } else if(currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
                            (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) )){
                if(board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnTile = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnTile.getPieceAlliance()) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }
        }


        return Collections.unmodifiableList(legalMoves);
    }
}
