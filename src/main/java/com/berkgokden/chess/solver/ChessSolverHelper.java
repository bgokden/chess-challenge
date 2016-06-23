package com.berkgokden.chess.solver;

import com.berkgokden.chess.Board;
import com.berkgokden.chess.PieceType;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ChessSolverHelper Singleton to help solve chess challange.
 */
public class ChessSolverHelper {
    private static final Logger logger = Logger.getLogger(ChessSolverHelper.class.getName());

    protected static final AtomicInteger activeTasks = new AtomicInteger(0);
    protected static final ExecutorService executorService
            = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());;
    protected static final Set<Board> solutionSet
            = Collections.newSetFromMap(new ConcurrentHashMap<Board, Boolean>());

    private static ChessSolverHelper chessSolverHelper = new ChessSolverHelper();

    private ChessSolverHelper() {
    }

    public static ChessSolverHelper getInstance() {
        return chessSolverHelper;
    }



    public static Set<Board> solve(int m, int n, Map<PieceType, Integer> resources) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        SolverTask task = new SolverTask(PieceType.NONE, 0, 0, new Board(m,n), resources);
        executorService.execute(task);

        while (activeTasks.get() > 0) {
            logger.debug("Active Tasks: " + activeTasks.get());
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        logger.info("Time: "+estimatedTime+" ms");
        return solutionSet;
    }
}
