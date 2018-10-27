package com.example.aloha.snake;



public class GridBean {

    public static int screenHeightPixels;
    public static int screenWidthPixels;

    private int offset = 90 ;//偏移量，就是间距  上 左 右 间距一样

    private int gridSize = 30;//每行格子的数量
    private int lineLength;//线的长度
    private int gridWidth  ;//格子宽

    public GridBean() {
        lineLength = screenWidthPixels - offset * 2;
        gridWidth = lineLength / gridSize;// 格子数量
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
