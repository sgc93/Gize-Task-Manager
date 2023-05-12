package todo;
import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jfxClasses.*;

public class TaskDetail extends Dialog<Void> {
    Task task = new Task();
    Labels taskLabel = new Labels("Task", "label_txt");
    Labels descriptionLabel = new Labels("Description", "label_txt");
    public static TextFields taskField = new TextFields("task_field");
    public static TextAreas descriptionField = new TextAreas("desc_field");
    public static TextFields priorityLabel = new TextFields("_detail1_txt");
    public static TextFields startDateLabel = new TextFields("_detail2_txt");
    public static TextFields endDateLabel = new TextFields("_detail2_txt");
    public static TextFields startTimeLabel = new TextFields("_detail1_txt");
    public static TextFields endTimeLabel = new TextFields("_detail1_txt");
    public static TextFields statusLabel = new TextFields("_detail1_txt");

    public static Buttons edit_btn = new Buttons("_edit_btn", "tsk_btn");
    public static Buttons delete_btn = new Buttons("_delete_btn", "tsk_btn");
    public static Buttons back_btn = new Buttons("_back_btn", "tsk_btn");
    public static Buttons save_btn = new Buttons("Save Change","_back_btn", "tsk_btn");

    public static Stage taskDetailStage = new Stage();
    public static String updated_task;
    
    public TaskDetail() {
        descriptionField.setWrapText(true);
        // edit btn
        taskField.setDisable(true);
        descriptionField.setDisable(true);
        startDateLabel.setDisable(true);
        endDateLabel.setDisable(true);
        startTimeLabel.setDisable(true);
        endTimeLabel.setDisable(true);
        priorityLabel.setDisable(true);
        statusLabel.setDisable(true);
        save_btn.setDisable(true);

        Images edit_img = new Images("resources\\icons\\edit.png");
        ImageViews edit_img_view = new ImageViews(edit_img);
        edit_btn.setGraphic(edit_img_view);
        edit_btn.setOnAction(event -> {
            updated_task = taskField.getText();
            taskField.setDisable(false);
            descriptionField.setDisable(false);
            startDateLabel.setDisable(false);
            endDateLabel.setDisable(false);
            startTimeLabel.setDisable(false);
            endTimeLabel.setDisable(false);
            priorityLabel.setDisable(false);
            statusLabel.setDisable(false);
            save_btn.setDisable(false);
        });

        // delete btn
        Images delete_img = new Images("resources\\icons\\delete.png");
        ImageViews delete_img_view = new ImageViews(delete_img);
        delete_btn.setGraphic(delete_img_view);

        HBoxs tsk_btn = new HBoxs(edit_btn, delete_btn, save_btn);

        // back btn
        Images back_img = new Images("resources\\icons\\back.png");
        ImageViews back_img_view = new ImageViews(back_img);
        back_btn.setGraphic(back_img_view);
        back_btn.setOnAction(event -> taskDetailStage.close());

        // label title
        Labels pri_label = new Labels("Priority ", "detail_label_txt");
        Labels stDate_label = new Labels("Start Date - Time ", "detail_label_txt");
        Labels endDate_label = new Labels("Due Date - Time ", "detail_label_txt");
        Labels status_label = new Labels("Status ", "detail_label_txt");

        // detal data hbox
        HBoxs st_date = new HBoxs(startDateLabel, startTimeLabel);
        HBoxs end_date = new HBoxs(endDateLabel, endTimeLabel);
        
        HBoxs pri_hbox = new HBoxs(pri_label, priorityLabel);
        HBoxs stDate_hbox = new HBoxs(stDate_label, st_date);
        HBoxs endDate_hbox = new HBoxs(endDate_label, end_date);
        HBoxs status_hbox = new HBoxs(status_label, statusLabel);

        HBoxs detail_btn = new HBoxs(back_btn, tsk_btn);

        VBoxs task_vbox = new VBoxs(taskLabel, taskField, descriptionLabel, descriptionField, "_task_vbox");


        VBoxs detail_vbox = new VBoxs(pri_hbox, stDate_hbox, endDate_hbox, status_hbox, "_detail_box");
        VBoxs detail_root = new VBoxs(detail_btn, task_vbox, detail_vbox, "_detail_root");
        Scene detaiScene = new Scene(detail_root, 1000, 600);

        detaiScene.getStylesheets().add(getClass().getResource("..\\css\\detail_page.css").toExternalForm());
        taskDetailStage.setScene(detaiScene);
        taskDetailStage.setResizable(false);
        taskDetailStage.setAlwaysOnTop(true);
    }

    public void setTaskDetails(String priority, String startDate, String taskName, String description) {
        String sql = "SELECT * FROM task WHERE task_name = '" + taskName + "'";
        System.out.println(sql);
        try(
            Connection con = Task.connect();
            Statement st = con.createStatement();
        ){  
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            priorityLabel.setText(rs.getString(8));
            startDateLabel.setText(rs.getString(3));
            startTimeLabel.setText(rs.getString(4));
            endDateLabel.setText(rs.getString(5));
            endTimeLabel.setText(rs.getString(6));
            taskField.setText(rs.getString(2));
            descriptionField.setText(rs.getString(9));
            statusLabel.setText(rs.getString(7));
            taskDetailStage.show();
        } catch(SQLException e){
            System.err.println(e);
        }
        
    }
}
