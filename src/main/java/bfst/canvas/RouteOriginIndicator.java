package bfst.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteOriginIndicator extends Pin {

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public void changeRadius(float r){
        radius = r;
    }

    public RouteOriginIndicator(float centerX, float centerY, float radius) {
        super(centerX,centerY,radius);
    }

    public void draw(GraphicsContext gc) {
        draw(gc, 1);
    }

    public void draw(GraphicsContext gc, double size) {
        double factor = size*0.6;

        gc.setFill(Color.BLACK);
        gc.beginPath();
        String translated = circlePath(centerX,centerY,(float) factor*12);
        gc.appendSVGPath(translated);
        gc.closePath();
        gc.fill();

        gc.setFill(Color.WHITE);
        gc.beginPath();
        translated = circlePath(centerX,centerY,(float) factor*8);
        gc.appendSVGPath(translated);
        gc.closePath();
        gc.fill();

    }

    String circlePath(float cx, float cy, float r) {
        return "M " + cx + " " + cy + " m -" + r + ", 0 a " + r + "," + r + " 0 1,0 " + (r * 2) + ",0 a " + r + "," + r + " 0 1,0 -" + (r * 2) + ",0";
    }

    public String scaleSvgPath(String text, double factor) {
        return RouteInstructionIndicator.getScaledString(text, factor);
    }

}