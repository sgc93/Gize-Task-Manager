package jfxClasses;

import javafx.scene.control.TextField;

public class TextFields extends TextField{
    public TextFields(){
    }

    public TextFields(String id) {
        setId(id);
    }

    public TextFields(String txt, String id) {
        setText(txt);
        setId(id);
    }
}
