package jfxClasses;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class Buttons extends Button{
    public Buttons(){

    }

    public Buttons(String id, String classname) {
        setId(id);
        getStyleClass().addAll(classname);
    }

    public Buttons(String id) {
        setId(id);
    }

    public Buttons(String txt, String classname, String tooltip) {
        setText(txt);
        setTooltip(new Tooltip(tooltip));
        getStyleClass().add(classname);
    }

    public Buttons(String txt, String classname, String tooltip, String id) {
        setText(txt);
        setTooltip(new Tooltip(tooltip));
        getStyleClass().add(classname);
        setId(id);
    }
}
