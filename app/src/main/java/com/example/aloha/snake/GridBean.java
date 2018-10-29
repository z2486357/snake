package com.example.aloha.snake;



public class GridBean {

    public static int screenHeightPixels;
    public static int screenWidthPixels;

    private int offset = 90 ;

    private int gridSize = 30;
    private int lineLength;
    private int gridWidth  ;

    public GridBean() {
        lineLength = screenWidthPixels - offset * 2;
        gridWidth = lineLength / gridSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getLineLength() {
        return lineLength;
    }

    public void setLineLength(int lineLength) {
        this.lineLength = lineLength;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }
}
