package com.example.aloha.snake;

import java.util.LinkedList;
import java.util.List;

public class SnakeBean {
    private List<PointBean> snake  = new LinkedList<PointBean>();
    public List<PointBean> getSnake() {
        return snake;
    }
    public void setSnake(List<PointBean> snake) {
        this.snake = snake;
    }
}
