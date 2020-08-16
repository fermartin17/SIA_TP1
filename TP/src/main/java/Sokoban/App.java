package Sokoban;

import Sokoban.Interfaces.Heuristic;

import java.time.Instant;
import java.time.Period;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {

        long startTime = System.currentTimeMillis();

        TimeUnit.SECONDS.sleep(5);
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("Transcurrieron " + elapsedTime + " milisegundos");
    }
}
