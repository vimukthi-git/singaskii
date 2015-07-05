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
    
    private static final boolean TEST = false;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {     
        long startTime = System.currentTimeMillis();
        MountainPeakMap map;
        if(TEST){
            map = readTestMap();
        } else {
            map = readMapFromFile(args[0]);
        }
        //System.out.println(AdaptedFloydWarshall.calculateLongestSteepestPath(map).getPathNodes());
        WhiteWalkerUtil.startWalking(map);
        //WhiteWalkerUtil.dumpAllPaths();
        Path longest = WhiteWalkerUtil.getLongestSteepestPath();
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;        
        System.out.println("Longest path: Length = " + longest.getLength() 
                + ", drop = " + longest.getDrop() + ", Peaks = " 
                + longest.getPathNodes());
        System.out.println("Elapsed Time= " + elapsedTime + "ms");        
    }

    /**
     * process a string with peaks specified in space separated form
     * @param peaksLineStr
     * @param i
     * @param map
     * @return
     * @throws NumberFormatException 
     */
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
    
    /**
     * This function will derive adjacent peak indexes of a given peak 
     * specified by i and j coordinates in the map.
     * @param i
     * @param j
     * @param width
     * @param height
     * @return 
     */
    private static List<Integer> deriveAdjacentNodeIndexes(int i, int j, int width, int height){
        Integer leftTop = -1, top = -1, rightTop = -1, left = -1, right = -1, 
                leftBottom = -1, bottom = -1, rightBottom = -1;
        // top peak
        if(i > 0){
            top = (i - 1) * width + j;
        }
        // left right peaks
        if(i >= 0){
            if(j > 0){
              left = i * width + j - 1;
            }
            if(j < width - 1){
              right = i * width + j + 1;
            }
        }
        // bottom peak
        if(i < height - 1){
            bottom = (i + 1) * width + j;
        }
        List<Integer> adjNodes = new ArrayList<Integer>();
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
    
    /**
     * read the MountainPeakMap from the given file
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static MountainPeakMap readMapFromFile(String file) throws FileNotFoundException, IOException{
        MountainPeakMap map = null;
        BufferedReader br;
        br = new BufferedReader(new FileReader(file));
        String line;
        int i = 0;            
        while ((line = br.readLine()) != null) {
           map = processLine(line, i, map);
           i++;
        }
        return map;
    }
    
    /**
     * 
     * @return 
     */
    private static MountainPeakMap readTestMap(){
        MountainPeakMap map = null;
        String peakStr = "4 4 \n" +
                       "4 8 7 3 \n" +
                       "2 5 9 3 \n" +
                       "6 3 2 5 \n" +
                       "4 4 1 6";
        String[] peaksLineStr = peakStr.split("\n");
        
        for (int i = 0; i < peaksLineStr.length; i++) {
            map = processLine(peaksLineStr[i], i, map);            
        }
        return map;
    }
}