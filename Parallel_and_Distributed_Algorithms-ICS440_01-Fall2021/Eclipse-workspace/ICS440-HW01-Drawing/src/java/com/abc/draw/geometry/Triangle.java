package com.abc.draw.geometry;

import java.awt.*;

import com.abc.draw.*;

public class Triangle implements Drawable{
    private Point p1;
    private Point p2;
    private Point p3;
    public Triangle(Point point, Point point2, Point point3) {
        p1 = point;
        p2 = point2;
        p3 = point3;
    }

    public Point getP1() {
        return p1;
    }
    public Point getP2() {
        return p2;
    }
    public Point getP3() {
        return p3;
    }

    @Override
    public void draw(Graphics2D g2) {
        Line l1 = new Line(p1, p2);
        Line l2 = new Line(p2, p3);
        Line l3 = new Line(p3, p1);
        l1.draw(g2);
        l2.draw(g2);
        l3.draw(g2);

    }

}
