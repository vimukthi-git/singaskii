package com.vimukthi.skii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author vimukthib
 */
public class WhiteWalkerUtil {
    
    private static MountainPeakMap map;
    
    private static List<Path> walkResults;
    
    private static ExecutorService cachedPool;
    
    private static final int MIN_JOB_SIZE = 10000;
    
    public static void startWalking(MountainPeakMap pmap){
        map = pmap;
        walkResults = Collections.synchronizedList(new ArrayList<Path>());
        
        // create thread pool for running WhiteWalkers
        cachedPool = Executors.newCachedThreadPool();        
        List<Integer> nodes = new ArrayList<Integer>();
        // create sets of size of MIN_JOB_SIZE for peaks to be walked 
        // by individual WhiteWalkers
        for (int i = 0; i < map.getPeaks().size(); i++) {
            if(i % MIN_JOB_SIZE == 0){                
                nodes = new ArrayList<Integer>();
            } 
            nodes.add(i);
            if(i % MIN_JOB_SIZE == MIN_JOB_SIZE - 1 || i == map.getPeaks().size() - 1){
                cachedPool.submit(new WhiteWalker(nodes));
            }
            
        }
        cachedPool.shutdown();
        try {
          cachedPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
    }
    
    public static MountainPeakMap getMap(){
        return map;
    }
    
    public static void addResult(Path path){
        walkResults.add(path);
    }
    
    public static List<Path> getResults(){
        return walkResults;
    }
    
    public static void dumpAllPaths(){
        for (Path path : walkResults) {
            System.out.println(path.getPathNodes());
        }
    }
    
    public static Path getLongestSteepestPath(){       
        Path maxPath = walkResults.get(0);
        for (Path path : walkResults) {
            if(path.getLength() > maxPath.getLength()){
                maxPath = path;
            } else if(path.getLength() == maxPath.getLength()){
                if(path.getDrop() > maxPath.getDrop()){
                    maxPath = path;
                }
            }
        }
        return maxPath;
    }
    
}
