package com.vimukthi.skii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author vimukthib
 */
public class AdaptedFloydWarshall {
    
    public static Path calculateLongestSteepestPath(MountainPeakMap map){
        return findLongestSteepestPath(calculateMaxDropsAndPaths(calculateAdjacentDrops(map)), map);
    }
    
    public static Path findLongestSteepestPath(int[][] paths, MountainPeakMap map) {
        int length = 0;
        int drop = 0;
        List<Integer> longestPath = new ArrayList<>();
        for(int i = 0;i < map.getPeaks().size(); i++){
          for(int j = 0;j < map.getPeaks().size(); j++){
            List<Integer> tmpPath = path(i, j, paths, map);
            if(tmpPath != null){
                int tmpDrop = Collections.max(tmpPath) - Collections.min(tmpPath);
                if(tmpPath.size() > length){
                  length = tmpPath.size();
                  drop = tmpDrop;
                  longestPath = tmpPath;
                } else if(tmpPath.size() == length && tmpDrop > drop){
                  drop = tmpDrop;
                  longestPath = tmpPath;
                }
            }
          }
        }
        return new Path(length, drop, longestPath);
    }
    
    private static List<Integer> path(int i, int j, int[][] paths, MountainPeakMap map) {
      if (paths[i][j] == -1){
            return null;
      }
      List<Integer> path = new ArrayList<>();
      path.add(map.getPeaks().get(i).getHeight());
      while(i != j){
        i = paths[i][j];
        path.add(map.getPeaks().get(i).getHeight());
      }
      return path;
    }
    
    private static int[][] calculateMaxDropsAndPaths(Object[] adjacentDrops){
        int[][] maxDrops = (int[][]) adjacentDrops[0];
        int[][] next = (int[][]) adjacentDrops[1];
        for(int k = 0;k < maxDrops.length; k++){
            for(int i = 0;i < maxDrops.length; i++){
              for(int j = 0;j < maxDrops.length; j++){
                if(maxDrops[i][k] > 0 && maxDrops[k][j] > 0 && maxDrops[i][j] < maxDrops[i][k] + maxDrops[k][j]){
                  maxDrops[i][j] = maxDrops[i][k] + maxDrops[k][j];
                  next[i][j] = next[i][k];
                }
              }
            }
        }
        System.out.println("calculateMaxDropsAndPaths Complete");
        return next;
    }
    
    private static Object[] calculateAdjacentDrops(MountainPeakMap map){
        int[][] drops = new int[map.getPeaks().size()][map.getPeaks().size()];
        int[][] next = new int[map.getPeaks().size()][map.getPeaks().size()];
        for (int i = 0; i < map.getPeaks().size(); i++) {
            for(int j = 0;j < map.getPeaks().size(); j++){
                if(map.getPeaks().get(i).getAdjacentPeaks().contains(j) 
                        && map.getPeaks().get(i).getHeight() - map.getPeaks().get(j).getHeight() > 0){
                    // if j is adjacent to i
                    drops[i][j] = 1;
                    next[i][j] = j;
                } else {
                    drops[i][j] = -1;
                    next[i][j] = -1;
                }
            }
        }
        System.out.println("calculateAdjacentDrops Complete");
        return new Object[]{drops, next};
    }   
    
}
