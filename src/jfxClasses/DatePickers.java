package jfxClasses;

import javafx.scene.control.DatePicker;

public class DatePickers extends DatePicker{
    public DatePickers(){
        
    }

    public DatePickers(String id, String pro_txt) {
        setId(id);
        setPromptText(pro_txt);
    }
}
