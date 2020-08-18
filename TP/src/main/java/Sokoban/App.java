package Sokoban;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Searchs.Factories.SearchFactory;

import java.io.*;
import java.time.Instant;
import java.time.Period;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args ) throws IOException {

        File file = new File("../Conf.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int count = 1;
        int index = 0;
        String[] params = new String[3];


        while ((st = br.readLine()) != null) {
            if(count % 2 == 0)
                params[index++] = st;
            count++;
        }

        SearchMethod searchMethod = SearchFactory.searchMethod(params);

        Game game = new Game();

        game.start(searchMethod,Integer.parseInt(params[2]));

//    Benchmark benchmark = new Benchmark();
//        benchmark.runBenchmark();
    }

}
