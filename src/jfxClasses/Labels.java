package jfxClasses;

import javafx.scene.control.Label;

public class Labels extends Label{
    public Labels(String txt, String id){
        setText(txt);
        setId(id);
    }

    public Labels(String id) {
        setId(id);
    }
}
