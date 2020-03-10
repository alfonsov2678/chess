package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Queen;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;
public class Board {
    private final List<Tile> gameBoard;
    private final ArrayList<Piece> whitePieces;
    private final ArrayList<Piece> blackPieces;
    public static class Builder {
        Map<Integer, Piece> boardConfiguration;
        Alliance nextMoveMmaker;
        public Builder(){

        }

        public Builder setPiece(final Piece piece){
            this.boardConfiguration.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMmaker){
            this.nextMoveMmaker = nextMoveMmaker;
            return this;
        }
        public Board build(){
            return new Board(this);
        }
    }
    private Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
    }

    public Tile getTile(final int tileCoordinate){
        return gameBoard.get(tileCoordinate);
    }

    public static List<Tile> createGameBoard(Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfiguration.get(i));
        }
        List<Tile> t1 = new ArrayList<>();
        for(Tile t: tiles){
            t1.add(t);
        }
        return Collections.unmodifiableList(t1);
    }

    public static Board createStandardBoard(){
        Builder builder = new Builder();
        builder.setPiece(new Rook(0, Alliance.BLACK));
        builder.setPiece(new Knight(1, Alliance.BLACK));
        builder.setPiece(new Bishop(2, Alliance.BLACK));
        builder.setPiece(new Queen(3, Alliance.BLACK));
        builder.setPiece(new King(4, Alliance.BLACK));
        builder.setPiece(new Bishop(5, Alliance.BLACK));
        builder.setPiece(new Knight(6, Alliance.BLACK));
        builder.setPiece(new Rook(7, Alliance.BLACK));
        builder.setPiece(new Pawn(8, Alliance.BLACK));
        builder.setPiece(new Pawn(9, Alliance.BLACK));
        builder.setPiece(new Pawn(10, Alliance.BLACK));
        builder.setPiece(new Pawn(11, Alliance.BLACK));
        builder.setPiece(new Pawn(12, Alliance.BLACK));
        builder.setPiece(new Pawn(13, Alliance.BLACK));
        builder.setPiece(new Pawn(14, Alliance.BLACK));
        builder.setPiece(new Pawn(15, Alliance.BLACK));

        builder.setPiece(new Rook(0, Alliance.WHITE));
        builder.setPiece(new Knight(1, Alliance.WHITE));
        builder.setPiece(new Bishop(2, Alliance.WHITE));
        builder.setPiece(new Queen(3, Alliance.WHITE));
        builder.setPiece(new King(4, Alliance.WHITE));
        builder.setPiece(new Bishop(5, Alliance.WHITE));
        builder.setPiece(new Knight(6, Alliance.WHITE));
        builder.setPiece(new Rook(7, Alliance.WHITE));
        builder.setPiece(new Pawn(8, Alliance.WHITE));
        builder.setPiece(new Pawn(9, Alliance.WHITE));
        builder.setPiece(new Pawn(10, Alliance.WHITE));
        builder.setPiece(new Pawn(11, Alliance.WHITE));
        builder.setPiece(new Pawn(12, Alliance.WHITE));
        builder.setPiece(new Pawn(13, Alliance.WHITE));
        builder.setPiece(new Pawn(14, Alliance.WHITE));
        builder.setPiece(new Pawn(15, Alliance.WHITE));

        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();
    }

    private static ArrayList<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance){
        final ArrayList<Piece> activePieces = new ArrayList<>();
        for(final Tile t: gameBoard){
            if(t.isTileOccupied()){
                final Piece p = t.getPiece();
                if(p.getPieceAlliance() == alliance){
                    activePieces.add(p);
                }
            }
        }
    }

}
