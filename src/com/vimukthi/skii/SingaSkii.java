package com.vimukthi.skii;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vimukthib
 */
public class SingaSkii {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String file = args[0];
        MountainPeakMap map = null;
//        String peakStr = "4 4 \n" +
//                       "4 8 7 3 \n" +
//                       "2 5 9 3 \n" +
//                       "6 3 2 5 \n" +
//                       "4 4 1 6";
//        String[] peaksLineStr = peakStr.split("\n");
//        
//        for (int i = 0; i < peaksLineStr.length; i++) {
//            map = processLine(peaksLineStr, i, map);            
//        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;            
            while ((line = br.readLine()) != null) {
               map = processLine(line, i, map);
               i++;
            }
        }
        System.out.println("MountainPeakMap created");
        System.out.println(AdaptedFloydWarshall.calculateLongestSteepestPath(map).getPathNodes());
    }

    private static MountainPeakMap processLine(String peaksLineStr, int i, MountainPeakMap map) throws NumberFormatException {
        String[] values = peaksLineStr.split(" ");
        if(i == 0) {
            map = new MountainPeakMap(Integer.valueOf(values[0]),
                    Integer.valueOf(values[1]));
        } else {
            for(int j = 0;j < map.getWidth(); j++ ){
                map.addPeak(new MountainPeak(Integer.valueOf(values[j]),
                        deriveAdjacentNodeIndexes(i - 1, j, map.getWidth(), map.getHeight())));
            }            
        }
        return map;
    }
    
    private static List<Integer> deriveAdjacentNodeIndexes(int i, int j, int width, int height){
        Integer leftTop = -1, top = -1, rightTop = -1, left = -1, right = -1, 
                leftBottom = -1, bottom = -1, rightBottom = -1;
        if(i > 0){
            top = (i - 1) * width + j;
        }
        if(i >= 0){
            if(j > 0){
              left = i * width + j - 1;
            }
            if(j < width - 1){
              right = i * width + j + 1;
            }
        }
        if(i < height - 1){
            bottom = (i + 1) * width + j;
        }
        List<Integer> adjNodes = new ArrayList<>();
        adjNodes.add(leftTop);
        adjNodes.add(top);
        adjNodes.add(rightTop);
        adjNodes.add(left);
        adjNodes.add(right);
        adjNodes.add(leftBottom);
        adjNodes.add(bottom);
        adjNodes.add(rightBottom);
        return adjNodes;
    }    
}
