package com.abc.draw.geometry;

import java.awt.*;

import com.abc.draw.*;

public class Rectangle implements Drawable {
    private Point upperLeftPoint;
    private double width;
    private double height;

    public Rectangle(Point point, double width, double height) {
        upperLeftPoint = point;
        setWidth(width);
        setWidth(height);
    }
    public Point getUpperLeftPoint() {
        return upperLeftPoint;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        if(width>0) {
            this.width = width;
        } else {
            System.out.println("Width should bigger than 0!");
            this.width = 0;
        }
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        if(height>0) {
            this.height = height;
        } else {
            System.out.println("height should bigger than 0!");
            this.height = 0;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        double x1 = upperLeftPoint.getX();
        double y1 = upperLeftPoint.getY();
        Line l = new Line(upperLeftPoint, new Point(x1+ this.width, y1));
        l.draw(g2);
        l = new Line(new Point(x1+ this.width, y1), new Point(x1+ this.width, y1+this.height));
        l.draw(g2);
        l = new Line(new Point(x1 + this.width, y1 + this.height), new Point(x1, y1 + this.height));
        l.draw(g2);
        l = new Line(new Point(x1, y1 + this.height), upperLeftPoint);
        l.draw(g2);
    }

}
