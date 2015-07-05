package com.vimukthi.skii;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author vimukthib
 */
public class MountainPeakMap {
    
    private final int height;
    
    private final int width;
    
    private final List<MountainPeak> peaks;

    public MountainPeakMap(int height, int width) {
        this.height = height;
        this.width = width;
        // synchronized because of access by WhiteWalkers
        this.peaks = Collections.synchronizedList(new ArrayList<MountainPeak>());
    }
    
    public void addPeak(MountainPeak peak) {
        this.peaks.add(peak);
    }

    public List<MountainPeak> getPeaks() {
        return peaks;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }    
}
