package jfxClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VBoxs extends VBox{

    public VBoxs(HBoxs nav_hbox, VBoxs content_area, HBoxs foot_hbox) {
        getChildren().addAll(nav_hbox, content_area, foot_hbox);
        setAlignment(Pos.CENTER);
    }

    public VBoxs(HBoxs logo_hbox, Labels content_label,HBoxs btn_box, Buttons se_btn, String id) {
        setId(id);
        getChildren().addAll(logo_hbox, content_label, btn_box, se_btn);
        setAlignment(Pos.CENTER);
        setSpacing(60);
    }

    public VBoxs(Texts logo_txt, Texts ver_txt) {
        getChildren().addAll(logo_txt, ver_txt);
        setAlignment(Pos.CENTER);
    }

    public VBoxs(Buttons todo_btn, Buttons note_btn, Buttons team_btn, String id) {
        getChildren().addAll(todo_btn, note_btn, team_btn);
        setId(id);
        setSpacing(50);
        setAlignment(Pos.CENTER);
    }

    public VBoxs(String string) {
        setId(string);
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    public VBoxs(Labels day_date, Labels day_name, String id) {
        setId(id);
        getChildren().addAll(day_date, day_name);
        setAlignment(Pos.CENTER);
        setSpacing(5);
    }

    public VBoxs(HBoxs date_box, VBoxs task_box, String id) {
        setId(id);
        getChildren().addAll(date_box, task_box);
        setAlignment(Pos.TOP_CENTER);
        setSpacing(20);
    }

    public VBoxs(Labels time_board, Labels task_board) {
        getChildren().addAll(time_board, task_board);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(20);
    }

    public VBoxs(HBoxs task_board, String string) {
        setId(string);
        getChildren().addAll(task_board);
        setAlignment(Pos.CENTER);
        setSpacing(15);
    }

    public VBoxs() {
    }

    public VBoxs(TextFields task_field, DatePickers date, Buttons add_btn, String string) {
        getChildren().addAll(task_field, date, add_btn);
    }

    public VBoxs(ListView<HBox> task_list, Buttons add_btn, String string) {
        setId(string);
        getChildren().addAll(task_list, add_btn);
        setAlignment(Pos.CENTER);
        setSpacing(50);
        setPrefWidth(800);
    }

    public VBoxs(HBoxs detail_btn, VBox task_vbox, VBox detail_vbox, String id) {
        setId(id);
        getChildren().addAll(detail_btn, task_vbox, detail_vbox);
        setAlignment(Pos.CENTER);
        setSpacing(20);
    }

    public VBoxs(HBoxs priorityLabel, HBoxs startDateLabel, HBoxs endDateLabel, HBoxs statusLabel, String id) {
        setId(id);
        getChildren().addAll(priorityLabel, startDateLabel, endDateLabel, statusLabel);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(7);
        setPrefWidth(300);
        setPadding(new Insets(0, 0, 0, 50));
    }

    public VBoxs(Label taskLabel, TextField taskField, Label descriptionLabel, TextArea descriptionField, String string) {
        setId(string);
        getChildren().addAll(taskLabel, taskField, descriptionLabel, descriptionField);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(15);
    }

    public VBoxs(HBoxs tsk_box, HBoxs desc_box, HBoxs dueDate_box, HBoxs dueTime_box, HBoxs pri_box, HBoxs status_box, Buttons add_btn, String id) {
        setId(id);
        getChildren().addAll(tsk_box, desc_box, dueDate_box, dueTime_box, pri_box, status_box, add_btn);
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    public VBoxs(HBoxs topic_box, HBoxs detail_box, HBoxs status_box, Buttons add_btn, String string) {
        getChildren().addAll(topic_box, detail_box, status_box, add_btn);
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }
}