package com.berkgokden;

import com.berkgokden.chess.Board;
import com.berkgokden.chess.PieceType;
import com.berkgokden.chess.solver.ChessSolverHelper;
import org.apache.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        logger.info( "Chess Challenge" );
        Scanner sc = new Scanner(System.in);
        System.out.println("Please insert M:");
        // TODO: apply input validation
        int m = sc.nextInt();
        System.out.println("Please insert N:");
        int n = sc.nextInt();
        Map<PieceType, Integer> resources = new EnumMap<PieceType, Integer>(PieceType.class);
        for (PieceType type : PieceType.values()) {
            System.out.println("Please number of Piece Type "+type.name()+":");
            int count = sc.nextInt();
            resources.put(type, count);
        }
        Set<Board> solutionSet = ChessSolverHelper.solve(m, n, resources);
        System.out.println("Solutions: Size("+solutionSet.size()+")");
        for (Board board : solutionSet) {
            board.prettyPrint();
        }
    }

}
