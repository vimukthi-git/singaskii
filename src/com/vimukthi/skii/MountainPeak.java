package com.vimukthi.skii;

import java.util.List;

/**
 *
 * @author vimukthib
 */
public class MountainPeak {
    
    private final int height;
    
    private final List<Integer> adjacentPeaks;

    public MountainPeak(int height, List<Integer> adjacentPeaks) {
        this.height = height;
        this.adjacentPeaks = adjacentPeaks;
    }
    
    public int getHeight(){
        return this.height;
    }

    public List<Integer> getAdjacentPeaks() {
        return adjacentPeaks;
    }   
    
}
