package com.vimukthi.skii;

import java.util.List;

/**
 * This is a runnable class which will walkdown all lower adjacent peaks 
 * in the MountainPeakMap for the given set of peak indexes 
 * provided at the construction. 
 * @author vimukthib
 */
public class WhiteWalker implements Runnable {

    private final List<Integer> nodes;

    public WhiteWalker(List<Integer> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void run() {
        for (Integer peakIndex : nodes) {
            MountainPeak peak = WhiteWalkerUtil.getMap().getPeaks().get(peakIndex);
            Path path = new Path();
            path.addNode(peak.getHeight());
            walkDown(peakIndex, peak, path);
            WhiteWalkerUtil.addResult(path);
        }
    }

    /**
     * This is a recursive function to walk the surrounding peaks of a 
     * given peak in descending order while updating global path results
     * when there's no other nodes remaining to travel down.
     * @param peakIndex starting peak index
     * @param peak starting peak object
     * @param path path traveled upto now
     */
    private void walkDown(Integer peakIndex, MountainPeak peak, Path path) {
        List<Integer> adjPeaks = peak.getAdjacentPeaks();
        // other WhiteWalkers could be iterating this
        synchronized (adjPeaks) {
            //int i = 0;
            boolean pathAdded = false;
            for (Integer adjPeakIndex : adjPeaks) {                
                if (adjPeakIndex > -1) {
                    MountainPeak adjPeak
                            = WhiteWalkerUtil.getMap().getPeaks().get(adjPeakIndex);
                    if (peak.getHeight() > adjPeak.getHeight()) {
                        // we copy the path each time recursive 
                        // walk down is required
                        Path copiedPath = path.copy();
                        copiedPath.addNode(adjPeak.getHeight());
                        walkDown(adjPeakIndex, adjPeak, copiedPath);                
                    } else {
                        // if the path had not been added previously for the 
                        // current set of iterations add it to results
                        if(!pathAdded){
                            WhiteWalkerUtil.addResult(path);
                            pathAdded = true;
                        }                        
                    }
                }
            }
        }
    }

}
