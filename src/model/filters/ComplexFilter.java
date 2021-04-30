package model.filters;

import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.scene.paint.Color;

public class ComplexFilter extends Filter<BiFunction<Color, Double, Color>> implements BiFunction<Image, Double, Image> {

    double vmin;
    double vmax;
    private String name;
    private BiFunction<Color, Double, Color> colorMap;

    public ComplexFilter(String name, BiFunction<Color, Double, Color> colorMap) {
        this.name = name;
        this.colorMap = colorMap;

        switch (this.name) {
            case "Noir et blanc":
                vmin = 0;
                vmax = 3;
                break;
            default:
                vmin = 0;
                vmax = 1;
        }
    }

    @Override
    public Image apply(Image source, Double s) {
        int w = (int) source.getWidth();
        int h = (int) source.getHeight();

        WritableImage image = new WritableImage(w, h);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                Color c1 = source.getPixelReader().getColor(x, y);
                Color c2 = colorMap.apply(c1, s);
                image.getPixelWriter().setColor(x, y, c2);
            }
        }
        return image;
    }

    public String getName() {
        return name;
    }

}