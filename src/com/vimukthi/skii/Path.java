package com.vimukthi.skii;

import java.util.List;

/**
 *
 * @author vimukthib
 */
public class Path {
    private final int length;
    
    private final int drop;
    
    private final List<Integer> pathNodes;

    public Path(int length, int drop, List<Integer> pathNodes) {
        this.length = length;
        this.drop = drop;
        this.pathNodes = pathNodes;
    }

    public int getLength() {
        return length;
    }

    public int getDrop() {
        return drop;
    }

    public List<Integer> getPathNodes() {
        return pathNodes;
    }
}
