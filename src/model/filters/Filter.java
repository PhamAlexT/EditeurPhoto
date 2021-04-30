package model.filters;

import javafx.scene.paint.Color;

import java.util.function.Function;

public abstract class Filter<T> {
    private String name;

    public String getName(){
        return this.name;
    }
}
