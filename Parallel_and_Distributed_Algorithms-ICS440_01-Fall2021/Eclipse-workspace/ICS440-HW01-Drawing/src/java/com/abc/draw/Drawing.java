package com.abc.draw;

import java.awt.*;
import java.util.*;

// TODO - add code to the methods, fields, and constructor below to allow multiple Drawable shapes
// to be held and drawn.

public class Drawing extends Object {
    private final ArrayList<Drawable>  list;
    public Drawing() {
        list = new ArrayList<>();
    }

    public void drawAll(Graphics2D g2) {
        // call draw(g2) on each of the Drawable's.
        for (Drawable drawable : list) {
            drawable.draw(g2);
        }
    }

    public void append(Drawable drawable) {
        // add the specified drawable to a array/list to be able to call draw() later
        list.add(drawable);
    }
}
