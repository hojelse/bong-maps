package bfst.canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteInstructionIndicator extends Pin {

    public RouteInstructionIndicator(float centerX, float centerY, float radius) {
        super(centerX,centerY,radius);
    }

    public void draw(Drawer gc) {
        draw(gc, 1);
    }

    public void draw(Drawer gc, double size) {
        double factor = size*0.6;

        gc.setFill(Color.BLACK);
        gc.beginPath();
        String translated = circlePath(super.getCenterX(),super.getCenterY(),(float) factor*5);
        gc.appendSVGPath(translated);
        gc.closePath();
        gc.fill();

        gc.setFill(Color.WHITE);
        gc.beginPath();
        translated = circlePath(super.getCenterX(),super.getCenterY(),(float) (factor*4));
        gc.appendSVGPath(translated);
        gc.closePath();
        gc.fill();

    }

    String circlePath(float cx, float cy, float r) {
        return "M " + cx + " " + cy + " m -" + r + ", 0 a " + r + "," + r + " 0 1,0 " + (r * 2) + ",0 a " + r + "," + r + " 0 1,0 -" + (r * 2) + ",0";
    }

    public String scaleSvgPath(String text, double factor) {
        return getScaledString(text, factor);
    }

    public static String getScaledString(String text, double factor) {
        String out = "";
        String regex = "([Mmcla]{1}[0-9,\\-\\.]+)|(z)";

        return regexedString(text, factor, out, regex);
    }

    public static String regexedString(String text, double factor, String out, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String str = matcher.group();
            String c = str.substring(0,1);

            String regex2 = "(-?\\d+\\.?\\d*)";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(str);

            String out1 = c;
            while (matcher2.find()) {
                double d = Double.parseDouble(matcher2.group());
                d = d * factor;
                out1 += d + " ";
            }
            out += out1;
        }

        return out;
    }

}