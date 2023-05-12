package jfxClasses;

import javafx.geometry.Insets;
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

    public HBoxs(Buttons home_btn,Buttons  new_btn,Buttons  open_btn,HBoxs search_box,Buttons  mode_btn, String id) {
        setId(id);
        getChildren().addAll(home_btn, new_btn, open_btn, mode_btn, search_box);
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


    public HBoxs(VBoxs status_pri_box, CheckBoxs completed, Labels task_label, VBoxs task_time_box, String string) {
        setId(string);
        getChildren().addAll(status_pri_box, completed, task_label, task_time_box);
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    public HBoxs(Buttons back_btn, HBoxs tsk_btn) {
        getChildren().addAll(back_btn, tsk_btn);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(300);
        setPadding(new Insets(0, 0, 0, 50));
    }

    public HBoxs(TextFields dateLabel, TextFields timeLabel) {
        getChildren().addAll(dateLabel, timeLabel);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(10);
    }

    public HBoxs(Labels task_label, TextFields task_field, String classname) {
        getChildren().addAll(task_label, task_field);
        getStyleClass().add(classname);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);
    }

    public HBoxs(Labels desc_label, TextAreas desc_field, String classname) {
        getChildren().addAll(desc_label, desc_field);
        getStyleClass().add(classname);
        setAlignment(Pos.CENTER);
        setSpacing(10);
    }

    public HBoxs(Labels dueDate_label, DatePickers date, String classname) {
        getChildren().addAll(dueDate_label, date);
        getStyleClass().add(classname);
        setAlignment(Pos.CENTER);
        setSpacing(10);
    }

    public HBoxs(Labels label, TextFields field) {
        getChildren().addAll(label, field);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(15);
    }

    public HBoxs(Labels label, HBoxs date_box) {
        getChildren().addAll(label, date_box);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(15);
    }

    public HBoxs(Buttons search_btn, TextFields search_field, String classname) {
        getChildren().addAll(search_btn, search_field);
        setAlignment(Pos.CENTER);
        setSpacing(10);
    }

    public HBoxs(Buttons new_note_btn, Buttons imp_btn, Buttons all_btn, String id) {
        getChildren().addAll(new_note_btn, imp_btn, all_btn);
        setAlignment(Pos.CENTER);
        setSpacing(15);
        setPadding(new Insets(15));
    }

    public HBoxs(Labels status_label, Labels name_label, VBoxs task_time_box, String id) {
        getChildren().addAll(status_label, name_label, task_time_box);
        setAlignment(Pos.CENTER);
        setSpacing(30);
        setId(id);
    }

    public HBoxs(Labels status_label, CheckBoxs status_checker, String classname) {
        getChildren().addAll(status_label, status_checker);
        setAlignment(Pos.CENTER);
        setSpacing(10);
    }

    public HBoxs(VBoxs stDate_board, VBoxs edDate_board, String id) {
        getChildren().addAll(stDate_board, edDate_board);
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setId(id);
    }

    public HBoxs(Buttons back_btn, HBoxs noteDate_board, String id) {
        getChildren().addAll(back_btn, noteDate_board);
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setId(id);
        setMargin(noteDate_board, new Insets(20));
    }

    public HBoxs(HBoxs noteDate_board, HBoxs tsk_btn, String classname) {
        setId(classname);
        setAlignment(Pos.CENTER);
        getChildren().addAll(noteDate_board, tsk_btn);
        setSpacing(20);
        setPadding(new Insets(20));
    }
}
