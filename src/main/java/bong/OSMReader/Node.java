package bong.OSMReader;

import bong.canvas.CanvasElement;
import bong.canvas.Drawer;
import bong.canvas.Range;
import javafx.geometry.Point2D;
import java.io.Serializable;
import java.util.function.LongSupplier;

public class Node extends CanvasElement implements LongSupplier, Serializable {
    private long id;
    private float lon;
    private float lat;
    private static final long serialVersionUID = 1L;

    public Node(long id, float lon, float lat){
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    @Override
    public long getAsLong() {
        return id;
    }

    @Override
    public String toString() {
        return "Node, lon:" + lon + " lat:" + lat + " + id:" + id;
    }

    @Override
    public Point2D getCentroid() {
        return new Point2D(this.lon, this.lat);
    }

    @Override
    public Range getBoundingBox() {
        return new Range(this.lon, this.lat, this.lon, this.lat);
    }

    @Override
    public void draw(Drawer gc, double scale, boolean smartTrace) {

    }
}
