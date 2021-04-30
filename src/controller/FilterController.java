package controller;

import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;
import model.filters.ComplexFilter;
import model.filters.Filter;
import model.filters.SimpleFilter;
import view.ApplicationMenu;
import view.Workspace;

public class FilterController {
    private List<Filter> filters;
    Workspace ws;

    public FilterController(Workspace ws){
        this.ws = ws;
        initFilters();
    }

    public FilterController() {
        initFilters();
    }

    public void initFilters() {
        filters = Arrays.asList(
                new SimpleFilter("Inverser", c -> c.invert()),
                new SimpleFilter("Nuance de gris", c -> c.grayscale()),
                new SimpleFilter("Saturer", c->c.saturate()),
                new SimpleFilter("Désaturer", c->c.desaturate()),

                new ComplexFilter("Noir et blanc", (c,d) -> sumOfColor(c) < d ? Color.BLACK : Color.WHITE),
                new ComplexFilter("Rouge", (c,d) -> Color.color(d, c.getGreen(), c.getBlue())),
                new ComplexFilter("Vert", (c,d) -> Color.color(c.getRed(), d, c.getBlue())),
                new ComplexFilter("Bleu", (c,d) -> Color.color(c.getRed(), c.getGreen(), d))
        );
    }

    private double sumOfColor(Color c) {
        return c.getRed() + c.getGreen() + c.getBlue();
    }

    public List<Filter> getFilters() {
        return this.filters;
    }
}
