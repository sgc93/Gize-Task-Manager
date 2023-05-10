package todo;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxClasses.*;

public class ToDo {
    public static Buttons home_btn = new Buttons("_home_btn", "nav_btn");
    public static Buttons  back_btn = new Buttons("_back_btn", "nav_btn");
    
    public static Buttons new_task_btn = new Buttons("New Task", "task_btn", "Add New Task");
    public static Buttons com_btn = new Buttons("Completed", "task_btn", "See All Completed tasks");
    public static Buttons all_btn = new Buttons("All Tasks", "task_btn", "Sell All Pending tasks");
    public static Buttons week_btn = new Buttons("Weekly", "task_btn", "Add Weekly tasks");
    public static Buttons month_btn = new Buttons("Monthly", "task_btn", "Add Monthly tasks");
    public static Buttons year_btn = new Buttons("Yearly", "task_btn", "Add Yearly tasks");
    
    public static HBoxs date_time_box;
    
    static Task task = new Task();
    static LocalTime stTime = LocalTime.now();
    static LocalDate today = LocalDate.now();
    static DatePickers date = new DatePickers("_new_task", today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
    static TextFields task_field = new TextFields("_new_task");
    static TextFields priority = new TextFields("_new_task");
    static TextAreas desc_field = new TextAreas("_task_desc");
    static TextFields status_field = new TextFields("Uncompleted", "_new_task");
    static TextFields dueTime_field = new TextFields(stTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")), "_new_task");
    public static Buttons add_btn = new Buttons("Add", "task_btn", "Add this task");
    public static Buttons addNew_btn = new Buttons("_newTsk_btn");
    
    static String formattedtime;
    static String formatteddate;

    public static ObservableList<HBox> tasks = FXCollections.observableArrayList(); 
    public static ListView<HBox> task_list = new ListView<>();
    static VBoxs task_box = new VBoxs(task_list, addNew_btn, "_task_box");

    public static AnchorPanes getToDoBoard() {
        // add new btn (at the buttom)
        Images add_img = new Images("resources\\icons\\add.png");
        ImageViews add_img_view = new ImageViews(add_img, 50, 50);
        addNew_btn.setGraphic(add_img_view);
        // home button
        Images home_img = new Images("resources\\icons\\sgc_home.png");
        ImageViews home_img_view = new ImageViews(home_img);
        
        Texts home_txt = new Texts("Home", "btn_txt");
        HBoxs home_hbox = new HBoxs(home_img_view, home_txt, "nav_grid");

        home_btn.setGraphic(home_hbox);
        
        // Nb button
        Images back_img = new Images("resources\\icons\\back.png");
        ImageViews back_img_view = new ImageViews(back_img);
        Texts back_txt = new Texts("Back", "btn_txt");
        HBoxs back_hbox = new HBoxs(back_img_view, back_txt, "nav_grid");

        back_btn.setGraphic(back_hbox);

        HBoxs head_btn = new HBoxs(back_btn, home_btn);

        HBoxs head_hbox = new HBoxs("_head_hbox");
        head_hbox.getChildren().addAll(head_btn, getTimeBox());
        head_hbox.setAlignment(Pos.CENTER_LEFT);
        head_hbox.setSpacing(700);

        // contet area

        Labels today_label = new Labels("Your Today's Task List", "_today_task");
        Labels today = new Labels("_today");
        LocalDate today_date = LocalDate.now();
        DayOfWeek dayOfWeek = today_date.getDayOfWeek();
        String day_name = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        today.setText(day_name);
        VBoxs today_box = new VBoxs(today_label, today, "_today_box");

        HBoxs date_box = new HBoxs(new_task_btn, com_btn, all_btn, today_box, week_btn, month_btn, year_btn, "_date_hbox");
    
        VBoxs content_area = new VBoxs(date_box,task_box, "_content_area");
        AnchorPanes todoRoot = new AnchorPanes(head_hbox, content_area, "_todoRoot");
        displayTask();
        return todoRoot;
    }
    
    public static void displayTask(){

        // task.createTable("task");
        // task.insertValues("task", "Reading About JDBC and Working Project on it, Like to Do List or contact Manager", "May 02, 2023", "02:03:04 PM", "May 14, 2023", "05:04:05 PM", "Uncompleted", "High", "with some projects");
        // task.insertValues("task", "Deploy projects with finished Implementation", "May 03, 2023", "02:03:04 PM", "May 13, 2023", "05:04:05 PM", "No", "Midium", "all finished Projects");
        // task_list.setId("_task_list_view");
        
        // task_list.setPrefWidth(1000);
        String sql = "SELECT * FROM task";
        try(
            Connection con = task.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ){
                while(rs.next()){
                    
                    Labels p_label = new Labels("_pri_txt");
                    CheckBoxs completed = new CheckBoxs("_completed"); 
                    Labels task_label = new Labels("_task_txt");
                    
                    Labels time_label = new Labels("_time_txt");
                    Labels time_len_label = new Labels("_time_txt");
                    VBoxs task_time_box = new VBoxs(time_len_label, time_label);

                    p_label.setText(rs.getString(8));
                    time_label.setText(rs.getString(4));
                    task_label.setText(rs.getString(2));
                    time_len_label.setText(calcNumDays(today, rs.getString(3)));
                    
                    HBoxs task_board = new HBoxs(p_label, completed,task_label, task_time_box, "_task_board");
                    task_board.setOnMouseClicked(event -> {
                        HBox clickedItem = (HBox) task_list.getSelectionModel().getSelectedItem();

                        String priority = ((Label) clickedItem.getChildren().get(0)).getText();
                        String taskName = ((Label) clickedItem.getChildren().get(2)).getText();
                        
                        TaskDetail taskDetail = new TaskDetail();
                        taskDetail.setTaskDetails(priority, "2023-04-6", taskName, "");
                    });
                    
                    tasks.add(task_board);
                }

                task_list.setItems(tasks);

                
                int size = task_list.getItems().size();
                System.out.println(size);
                if(size < 5){
                    double height = size * 85;
                    task_list.setPrefHeight(height);
                }
            } catch(SQLException e){
                System.err.println(e.getMessage());
        }
    }

    private static String calcNumDays(LocalDate today, String str_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(str_date, formatter);
        long numOfDay = ChronoUnit.DAYS.between(date, today);
        String day_len = "today";
        if(numOfDay == 1){
            day_len = "1 day ago";
        } else if(numOfDay > 1){
            day_len = numOfDay + " days ago";
        }
        return day_len;
    }

    public static AnchorPanes getNewTaskPage(){
        Labels task_label = new Labels("Task", "_new_task_label");
        Labels dueDate_label = new Labels("Due Date", "_new_task_label");
        Labels dueTime_label = new Labels("Due Time", "_new_task_label");
        Labels pri_label = new Labels("Priority", "_new_task_label");
        Labels desc_label = new Labels("Description", "_new_task_label");
        Labels status_label = new Labels("Status", "_new_task_label");

        HBoxs tsk_box = new HBoxs(task_label, task_field, "_tsk_box");
        HBoxs desc_box = new HBoxs(desc_label, desc_field, "_tsk_box");
        HBoxs dueDate_box = new HBoxs(dueDate_label, date, "_tsk_box");
        HBoxs dueTime_box = new HBoxs(dueTime_label, dueTime_field, "_tsk_box");
        HBoxs pri_box = new HBoxs(pri_label, priority, "_tsk_box");
        HBoxs status_box = new HBoxs(status_label, status_field, "_tsk_box");
        VBoxs content_area = new VBoxs(tsk_box, desc_box, dueDate_box, dueTime_box, pri_box, status_box, add_btn, "_content_area");
        AnchorPanes todoRoot = new AnchorPanes(getTimeBox(), content_area, "_todoRoot");
        return todoRoot;
    }

    public static void addNewTask(){
        LocalDate endDate = date.getValue();
        String task_name = task_field.getText();
        String st_time = stTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        String st_date = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        String end_date = endDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        String end_time = st_time;
        String completed = status_field.getText();
        String pri = priority.getText();
        String desc = desc_field.getText();
        task.insertValues("task", task_name, st_date, st_time, end_date, end_time, completed, pri, desc);
    }

    public static HBoxs getTimeBox(){
        Labels date_label = new Labels("_date_time");
        Labels time_label = new Labels("_date_time");
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    LocalTime time = LocalTime.now();
                    formattedtime = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    LocalDate date = LocalDate.now();
                    formatteddate = date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            time_label.setText(formattedtime);
                            date_label.setText(formatteddate);
                        }
                    });
                    
                    // Sleep for one second
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        
        date_time_box = new HBoxs(date_label, time_label, "_date_time_box");
        return date_time_box;
    }
}
