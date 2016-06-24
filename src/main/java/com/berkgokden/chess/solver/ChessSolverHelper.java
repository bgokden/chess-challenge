package com.berkgokden.chess.solver;

import com.berkgokden.chess.Board;
import com.berkgokden.chess.Location;
import com.berkgokden.chess.PieceType;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChessSolverHelper Singleton to help solve chess challange.
 */
public class ChessSolverHelper {
    private static final Logger logger = Logger.getLogger(ChessSolverHelper.class.getName());

    protected static final Set<Board> solutionSet
            = Collections.newSetFromMap(new ConcurrentHashMap<Board, Boolean>());

    private static ChessSolverHelper chessSolverHelper = new ChessSolverHelper();

    private ChessSolverHelper() {
    }

    public static ChessSolverHelper getInstance() {
        return chessSolverHelper;
    }


    /**
     * Solves chess-challeng with finding all permutaions then
     * trying all possible placements with every resource recursively
     *
     * @param m width of the chess board
     * @param n height of the chess board
     * @param resources resource map to be placed on board
     * @return set of boards as solution
     */
    public static Set<Board> solve(int m, int n, Map<PieceType, Integer> resources) {
        solutionSet.clear();
        long startTime = System.currentTimeMillis();
        Set<List<PieceType>> permutations = new HashSet<List<PieceType>>();
        List<PieceType> pieces = new ArrayList<>(resources.size());
        for (PieceType pieceType : resources.keySet()) {
            for (int i = 0; i < resources.get(pieceType).intValue(); i++) {
                pieces.add(pieceType);
            }
        }
        // System.out.println("Pieces:"+pieces);
        ChessSolverHelper.generatePieceTypePermutations(permutations, new ArrayList<PieceType>(), pieces);

        for (List<PieceType> permutation : permutations) {
            solveTask(0, new Board(m,n), permutation);
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        logger.info("Time: "+estimatedTime+" ms");
        System.out.println("Time: "+estimatedTime+" ms");
        return solutionSet;
    }

    /**
     * Solves chess-challenge for a given resource list.
     * Tries to place a piece on board with every possible position
     * then calls itself recursively until the resource list is used.
     *
     * @param index the index where solveTask is currently using
     * @param board board to be used while solving
     * @param pieces list of resource to use
     */
    public static void solveTask(int index, Board board, List<PieceType> pieces) {
        PieceType type = pieces.get(index);
        Location location = board.getLastPositions().get(type);
        int x = location.getX();
        int y = location.getY();
        while (location != null) {
            location = board
                    .putPieceToFirstAvailableSquare(PieceType.getInstance(type), x, y);
            if (location != null) {
                if (index+1 == pieces.size()) {
                    solutionSet.add(new Board(board));
                } else {
                    solveTask(index + 1, new Board(board), pieces);
                }
                board.removePieceAt(location.getX(), location.getY());
                x = location.getX()+1;
                y = location.getY();
                if (x >= board.getWidth()) {
                    x = 0;
                    y++;
                }
            }
        }
    }


    /**
     * Find permutations of a list of chess pieces using a recurrent method.<p>
     *
     * (Source: https://github.com/cemartins/chess-challenge/blob/master/src/main/java/net/martins/samples/chess/ChessBoard.java)
     * @param permutations set of permitations
     * @param temp temporary array start as an empty array
     * @param source source list of array to be computed
     */
    public static void generatePieceTypePermutations(Set<List<PieceType>> permutations, List<PieceType> temp, List<PieceType> source) {
        int n = source.size();
        if(n == 0)
            permutations.add(new ArrayList<>(temp));
        else {
            for(int i = 0; i < n; i++) {
                List<PieceType> copyOfTemp = new ArrayList<>(temp);
                List<PieceType> coptOfSource = new ArrayList<>(source);
                copyOfTemp.add(coptOfSource.remove(i));
                generatePieceTypePermutations(permutations, copyOfTemp, coptOfSource);
            }
        }
    }
}
