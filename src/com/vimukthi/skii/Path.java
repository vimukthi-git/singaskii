package com.vimukthi.skii;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vimukthib
 */
public class Path {
    private int length;
    
    private int drop;
    
    private final List<Integer> pathNodes;

    public Path() {
        this.length = 0;
        this.drop = 0;
        this.pathNodes = new ArrayList<Integer>();
    }   

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
    
    public void addNode(Integer height){        
        length++;
        if(length != 1){
            drop += (pathNodes.get(pathNodes.size() - 1) - height);
        }
        pathNodes.add(height);
    }
    
    public Path copy(){
        return new Path(this.length, this.drop, new ArrayList<Integer>(this.pathNodes));
    }
}
