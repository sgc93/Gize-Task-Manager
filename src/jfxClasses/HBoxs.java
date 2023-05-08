package jfxClasses;

import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

public class HBoxs extends HBox{

    public HBoxs(ImageViews img_view, Texts txt, String classname) {
        getChildren().addAll(img_view, txt);
        getStyleClass().addAll(classname);
        setAlignment(Pos.CENTER);
        setSpacing(15);
    }

    public HBoxs(Buttons home_btn,Buttons  new_btn,Buttons  open_btn,Buttons search_btn,Buttons  mode_btn, String id) {
        setId(id);
        getChildren().addAll(home_btn, new_btn, open_btn, search_btn, mode_btn);
        setAlignment(Pos.CENTER);
        setSpacing(50);
    }

    public HBoxs(ImageViews gize_logo_view, VBoxs logo_vbox) {
        getChildren().addAll(gize_logo_view, logo_vbox);
        setAlignment(Pos.CENTER);
        setSpacing(10);
    }

    public HBoxs(Buttons about_btn, Buttons contact_btn, Buttons help_btn) {
        getChildren().addAll(about_btn, contact_btn, help_btn);
        setAlignment(Pos.CENTER);
        setSpacing(50);
    }

    public HBoxs(Labels date_label, Labels time_label, String id) {
        setId(id);
        getChildren().addAll(date_label, time_label);
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPrefWidth(400);
    }

    public HBoxs(Buttons back_btn, Buttons home_btn) {
        setAlignment(Pos.CENTER);
        setSpacing(30);
        getChildren().addAll(back_btn, home_btn);
    }

    public HBoxs(Buttons new_task_btn, Buttons com_btn, Buttons all_btn, VBoxs thur_box, Buttons week_btn, Buttons month_btn, Buttons year_btn, String id) {
        setId(id);
        getChildren().addAll(new_task_btn, com_btn, all_btn, thur_box, week_btn, month_btn, year_btn);
        setAlignment(Pos.CENTER);
        setSpacing(40);
        setPrefHeight(60);
    }


    public HBoxs(String id) {
        setId(id);
    }

    public HBoxs(TextFields task_field, DatePicker date, String classname) {
        getChildren().addAll(task_field, date);
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    public HBoxs(Labels p_label, CheckBoxs completed, Labels task_label, VBoxs task_time_box, String string) {
        setId(string);
        getChildren().addAll(p_label, completed, task_label, task_time_box);
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

}
