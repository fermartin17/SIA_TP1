package Sokoban;

import Sokoban.Interfaces.Heuristic;

import java.time.Instant;
import java.time.Period;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args ) {
        Benchmark benchmark = new Benchmark();
        benchmark.runBenchmark();
    }
}
