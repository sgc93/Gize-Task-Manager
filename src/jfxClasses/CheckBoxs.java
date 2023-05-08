package jfxClasses;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;

public class CheckBoxs extends CheckBox{
    public CheckBoxs(String id){
        setAlignment(Pos.CENTER);
        setId(id);
        setPrefWidth(25);
        setPrefHeight(25);
    }

}
