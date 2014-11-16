package org.jmeld.ui.tree;

/**
 * Created by alberto on 16/11/14.
 */
public class JMChange {
    private int initLine;
    private int numLines;
    private int startCol;
    private int endCol;

    public JMChange(int initLine, int numLines, int startCol, int endCol) {
        this.initLine = initLine;
        this.numLines = numLines;
        this.startCol = startCol;
        this.endCol = endCol;
    }

    @Override
    public String toString() {
        return "L:"+initLine+"-"+(initLine+(numLines-1))+",C:"+startCol+"-"+endCol;
    }
}
