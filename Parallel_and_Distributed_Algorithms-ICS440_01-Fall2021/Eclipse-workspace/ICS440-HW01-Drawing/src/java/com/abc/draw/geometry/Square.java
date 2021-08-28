package com.abc.draw.geometry;

public class Square extends Rectangle {

    public Square(Point point, double width) {
        super(point, width, width);
    }
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width);
    }

}

