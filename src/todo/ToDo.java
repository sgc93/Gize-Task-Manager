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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jfxClasses.*;

public class ToDo {
    public static String sql_board = "SELECT * FROM task";
    
    public static Buttons home_btn = new Buttons("_home_btn", "nav_btn");
    public static Buttons  back_btn = new Buttons("_back_btn", "nav_btn");
    public static TextFields search_field = new TextFields("_search_field");
    public static Buttons search_btn = new Buttons("_search_btn");
    
    public static Buttons new_task_btn = new Buttons("New Task", "task_btn", "Add New Task");
    public static Buttons com_btn = new Buttons("Completed", "task_btn", "See All Completed tasks");
    public static Buttons all_btn = new Buttons("All Tasks", "task_btn", "Sell All Pending tasks");
    public static Buttons high_btn = new Buttons("High pri.", "task_btn", "Add Weekly tasks");
    public static Buttons med_btn = new Buttons("Medium pri", "task_btn", "Add Monthly tasks");
    public static Buttons low_btn = new Buttons("Low pri", "task_btn", "Add Yearly tasks");
    
    public static HBoxs date_time_box;
    
    static Task task = new Task();
    static LocalTime stTime = LocalTime.now();
    static LocalDate today = LocalDate.now();
    static DatePickers date = new DatePickers("_new_task", today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
    static TextFields task_field = new TextFields("_new_task");
    static TextFields priority = new TextFields("_new_task");
    static TextAreas desc_field = new TextAreas("_task_desc");
    static TextFields status_field = new TextFields("incomplete", "_new_task");
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

        // search button
        Images search_img = new Images("resources\\icons\\search.png");
        ImageViews search_img_view = new ImageViews(search_img);
        search_btn.setGraphic(search_img_view);
        HBoxs search_hbox = new HBoxs(search_field, search_btn, "nav_grid");

        HBoxs head_btn = new HBoxs(back_btn, home_btn);

        HBoxs head_hbox = new HBoxs("_head_hbox");
        head_hbox.getChildren().addAll(head_btn,search_hbox, getTimeBox());
        head_hbox.setAlignment(Pos.CENTER_LEFT);
        head_hbox.setSpacing(200);

        // contet area

        Labels today_label = new Labels("Your Today's Task List", "_today_task");
        Labels today = new Labels("_today");
        LocalDate today_date = LocalDate.now();
        DayOfWeek dayOfWeek = today_date.getDayOfWeek();
        String day_name = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        today.setText(day_name);
        VBoxs today_box = new VBoxs(today_label, today, "_today_box");

        HBoxs date_box = new HBoxs(new_task_btn, com_btn, all_btn, today_box, high_btn, med_btn, low_btn, "_date_hbox");
    
        VBoxs content_area = new VBoxs(date_box,task_box, "_content_area");
        AnchorPanes todoRoot = new AnchorPanes(head_hbox, content_area, "_todoRoot");
        displayTask();
        return todoRoot;
    }
    
    public static void displayTask(){

        // task.createTable("task");
        // task.insertValues("task", "Reading About JDBC and Working Project on it, Like to Do List or contact Manager", "May 02, 2023", "02:03:04 PM", "May 14, 2023", "05:04:05 PM", "done", "High", "with some projects");
        // task.insertValues("task", "Deploy projects with finished Implementation", "May 03, 2023", "02:03:04 PM", "May 13, 2023", "05:04:05 PM", "No", "Midium", "all finished Projects");
        // task_list.setId("_task_list_view");
        
        // task_list.setPrefWidth(1000);
        
        try(
            Connection con = task.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql_board);
            ){
                if(!(rs.next())){
                    Labels empt_label = new Labels("Empty List, sorry😔", "_empt_label");
                    HBoxs empt_box = new HBoxs(empt_label, "_empt_box");
                    tasks.add(empt_box);
                } else {
                    do {
                        Labels p_label = new Labels("_pri_txt");
                        CheckBoxs status_checker = new CheckBoxs("_completed"); 
                        status_checker.setOnAction(event -> {
                            int box_index = 0;
                            Label target_label = new Label();
                            CheckBox target_cb = (CheckBox) event.getSource();
                            for (HBox hbox : task_list.getItems()) {
                                CheckBox checkBox = (CheckBox) hbox.getChildren().get(1);
                                if(target_cb.equals(checkBox)){
                                    target_label = (Label) hbox.getChildren().get(2);
                                    box_index = task_list.getItems().indexOf(hbox);
                                }
                            }
                            
                            String task_txt = target_label.getText();
                            if(target_cb.isSelected() == true){
                                String sql_st = "UPDATE task SET iscompleted = 'done' WHERE task_name = '" + task_txt + "'";
                                Task.updateRow(sql_st);
                                ((Label) ((VBox) task_list.getItems().get(box_index).getChildren().get(0)).getChildren().get(1)).setText("done");
                                target_label.setFont(Font.font(10));
                
                            } else if(target_cb.isSelected() == false){
                                String sql_st = "UPDATE task SET iscompleted = 'incomplete' WHERE task_name = '" + task_txt + "'";
                                Task.updateRow(sql_st);
                                ((Label) ((VBox) task_list.getItems().get(box_index).getChildren().get(0)).getChildren().get(1)).setText("incomplete");
                                target_label.setFont(Font.font(22));
                            }
                        });
                        Labels status_label = new Labels("_time_txt");
                        VBoxs status_pri_box = new VBoxs(p_label, status_label);
                        Labels task_label = new Labels("_task_txt");
                        Labels time_label = new Labels("_time_txt");
                        Labels time_len_label = new Labels("_time_txt");
                        VBoxs task_time_box = new VBoxs(time_len_label, time_label);
                
                        p_label.setText(rs.getString(8));
                        status_label.setText(rs.getString(7));
                        time_label.setText(rs.getString(4));
                        task_label.setText(rs.getString(2));
                        time_len_label.setText(calcNumDays(today, rs.getString(3)));
                
                        if(status_label.getText().equals("done")){
                            status_checker.setSelected(true);
                        } else if(status_label.getText().equals("incomplete")){
                            status_checker.setSelected(false);
                        } else{
                            System.err.println("Status : " + status_label.getText());
                        }
                
                        HBoxs task_board = new HBoxs(status_pri_box, status_checker,task_label, task_time_box, "_task_board");
                        task_board.setOnMouseClicked(event -> {
                            HBox clickedItem = (HBox) task_list.getSelectionModel().getSelectedItem();
                
                String priority = ((Label) ((VBox) clickedItem.getChildren().get(0)).getChildren().get(0)).getText();
                            String taskName = ((Label) clickedItem.getChildren().get(2)).getText();
                
                            TaskDetail taskDetail = new TaskDetail();
                            taskDetail.setTaskDetails(priority, "2023-04-6", taskName, "");
                        });
                        
                        tasks.add(task_board);
                        
                    }while(rs.next());
                }
                
                task_list.setItems(tasks);
                int size = task_list.getItems().size();
                if(size < 5){
                    double height = size * 85;
                    task_list.setPrefHeight(height);
                } else {
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
        desc_box.setAlignment(Pos.TOP_CENTER);
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

    public static void getSearchedTask() {
        String txt = search_field.getText();
        String sql = "SELECT * FROM task";
        try (
            Connection con = Task.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
        ) {
            boolean foundExactMatch = false;
            boolean foundCaseInsensitiveMatch = false;
    
            while (rs.next()) {
                String col_value = rs.getString("task_name");
                System.out.println(col_value);
        
                if (col_value.equals(txt)) {
                    foundExactMatch = true;
                    sql_board = "SELECT * FROM task WHERE task_name = '" + txt + "'";
                    System.out.println("Found exact match: " + txt);
                    displayTask();
                    break;
                } else if (col_value.equalsIgnoreCase(txt)) {
                    foundCaseInsensitiveMatch = true;
                    sql_board = "SELECT * FROM task WHERE task_name = '" + col_value + "'";
                    System.out.println("Found case-insensitive match: " + col_value);
                    break;
                }
            }
            if (!foundExactMatch && foundCaseInsensitiveMatch) {
                displayTask();
            } else if (!foundExactMatch && !foundCaseInsensitiveMatch) {
                System.out.println("No match found for: " + txt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

            

            // ResultSetMetaData md = rs.getMetaData();
            // int col_num  = 0;
            // col_num = md.getColumnCount();
            // if(col_num != 0){
            //     System.out.println("con num: " + col_num);
            //     sql_board = sql;
            // } else {
            //     System.out.println("Threre is no such task in your list!");
            // }
        // } catch(SQLException e){
        //     e.printStackTrace();
        // }
