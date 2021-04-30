package controller.menu;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.paint.Color;
import model.Filter;
import view.ApplicationMenu;
import view.Workspace;

public class FilterMenu {
    private List<Filter> filters;
    ApplicationMenu am;
    Workspace ws;
    public FilterMenu(ApplicationMenu am, Workspace ws){
        this.am = am;
        this.ws = ws;
    }
    public FilterMenu() {
        initFiters();
    }

    public void initFiters() {
        filters = Arrays.asList(
                new Filter("Invert", c -> c.invert()),
                new Filter("Nuance de gris", c -> c.grayscale()),
                new Filter("Noir et blanc", c -> sumOfColor(c) < 1.5 ? Color.BLACK : Color.WHITE),
                new Filter("Rouge", c -> Color.color(1.0, c.getGreen(), c.getBlue())),
                new Filter("Vert", c -> Color.color(c.getRed(), 1.0, c.getBlue())),
                new Filter("Bleu", c -> Color.color(c.getRed(), c.getGreen(), 1.0)),
                new Filter("Saturé",c->c.saturate()),
                new Filter("Désaturé",c->c.desaturate())
        );
    }

    private double sumOfColor(Color c) {
        return c.getRed() + c.getGreen() + c.getBlue();
    }

    public List<Filter> getFilters() {
        return this.filters;
    }
}
