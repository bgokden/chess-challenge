package com.berkgokden.chess.solver;

import com.berkgokden.chess.Board;
import com.berkgokden.chess.Location;
import com.berkgokden.chess.PieceType;
import org.apache.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

/**
 * SolverTask is unit solution for
 * Chess Challenge Problem.
 * A runnable that solves the problem by opening a tree
 * with trying every possible solution in a smart way.
 */
public class SolverTask implements Runnable {
    private static final Logger logger = Logger.getLogger(SolverTask.class.getName());

    Map<PieceType, Integer> resources;
    Board board;
    PieceType pieceType;
    int x;
    int y;

    public SolverTask(PieceType pieceType, int x, int y, Board board, Map<PieceType, Integer> resources) {
        ChessSolverHelper.activeTasks.incrementAndGet();
        this.resources = resources;
        this.board = board;
        this.x = x;
        this.y = y;
        this.pieceType = pieceType;
    }

    /**
     * Solver task runs in a feed forward, share nothing principle
     * Uses only one resource (piece) at a time and
     * creates task for all other possible solutions.
     * If resources depleted, a solution is found and added to solution set.
     * if a piece can not be added, this solution branch is failed.
     */
    @Override
    public void run() {
        logger.debug("A solver run for task with "+pieceType+" at " + x + "," + y);
        if (useResource()) {
            ChessSolverHelper.activeTasks.decrementAndGet();
            return;
        }

        for (PieceType type : resources.keySet()) {
            Location location = board.getLastPositions().get(type);
            for (int xi = location.getX(); xi < board.getWidth(); xi++) {
                for (int yi = location.getY(); yi < board.getHeight(); yi++) {
                    SolverTask task = new SolverTask(type, xi, yi, new Board(board),
                            cloneResources(resources));
                    ChessSolverHelper.executorService.execute(task);
                }
            }
        }
        ChessSolverHelper.activeTasks.decrementAndGet();
    }

    private boolean useResource() {
        Integer resource = resources.get(pieceType);
        if (resource != null && resource.intValue() > 0) {
            boolean result =
                    board.putPieceToFirstAvailableSquare(PieceType.getInstance(pieceType), x, y);
            if (result) {
                if (resource.intValue() == 1) {
                    resources.remove(pieceType);
                } else {
                    resources.put(pieceType, new Integer(resource.intValue() - 1));
                }
                if (resources.isEmpty()) {
                    ChessSolverHelper.solutionSet.add(board);
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private static Map<PieceType, Integer> cloneResources(Map<PieceType, Integer> resources) {
        Map<PieceType, Integer> map = new EnumMap<>(PieceType.class);
        for (Map.Entry<PieceType, Integer> pieceTypeIntegerEntry : resources.entrySet()) {
            map.put(PieceType.valueOf(pieceTypeIntegerEntry.getKey().name()),
                    new Integer(pieceTypeIntegerEntry.getValue()));
        }
        return map;
    }
}

