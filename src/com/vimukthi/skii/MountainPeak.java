package com.vimukthi.skii;

import java.util.Collections;
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
        // synchronized because of access by WhiteWalkers
        this.adjacentPeaks = Collections.synchronizedList(adjacentPeaks);
    }
    
    public int getHeight(){
        return this.height;
    }

    public List<Integer> getAdjacentPeaks() {
        return adjacentPeaks;
    }   
    
}
