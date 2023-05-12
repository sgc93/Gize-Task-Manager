package note;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import jfxClasses.*;
import todo.ToDo;

public class Notepad {
    public static String sql_board = "SELECT * FROM note";
    public static Buttons home_btn = new Buttons("_home_btn", "nav_btn");
    public static Buttons  back_btn = new Buttons("_back_btn", "nav_btn");

    public static Buttons all_btn = new Buttons("All", "note_btn", "Sell All Pending notes");
    public static Buttons imp_btn = new Buttons("Important", "note_btn", "Add Weekly notes");
    public static Buttons new_note_btn = new Buttons("New", "note_btn", "Add Weekly notes");
    
    public static HBoxs date_time_box;
    
    static Note note = new Note();
    static LocalTime stTime = LocalTime.now();
    static LocalDate today = LocalDate.now();
    public static String formattedtime = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
    public static String formatteddate = stTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

    static DatePickers date = new DatePickers("_new_note", today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));

    static TextFields topic_field = new TextFields("_new_note");
    static TextFields priority = new TextFields("_new_note");
    static TextAreas detail_field = new TextAreas("_note_desc");
    static TextFields status_field = new TextFields("normal", "_new_note");
    static TextFields dueTime_field = new TextFields(stTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a")), "_new_note");
    public static Buttons add_btn = new Buttons("Add", "note_btn", "Add this note");
    public static CheckBoxs status_checker = new CheckBoxs("_status_checker");
    public static Buttons addNew_btn = new Buttons("_newTsk_btn");
    

    public static ObservableList<HBox> notes = FXCollections.observableArrayList(); 
    public static ListView<HBox> note_list = new ListView<>();
    static VBoxs note_box = new VBoxs(note_list, addNew_btn, "_note_box");

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
        head_hbox.getChildren().addAll(head_btn, ToDo.getTimeBox());
        head_hbox.setAlignment(Pos.CENTER_LEFT);
        head_hbox.setSpacing(700);

        // contet area

        HBoxs date_box = new HBoxs(new_note_btn, imp_btn, all_btn, "_date_hbox");
    
        VBoxs content_area = new VBoxs(date_box,note_box, "_content_area");
        AnchorPanes noteRoot = new AnchorPanes(head_hbox, content_area, "_noteRoot");
        displayNote();
        return noteRoot;
    }
    
    public static void displayNote(){

        // note.createTable("note");
        // note.insertValues("note","Minab Tech", "I have to do the given reading assignment in this 4 days, actually including today.", "May 02, 2023", "02:02:02 AM", "", "", "important");
        // note.insertValues("note","Ways of Deploying Desktop app", "1. I WebStart: Java WebStart allows you to deploy your application via a web page. This method is suitable for smaller applications.\n2. Java applet: Java applets are small Java applications that run within a web browser. They are suitable for simple applications.\n3. Java Web Server: Java web servers like Apache Tomcat can be used to deploy Java applications. This method is suitable for larger applications.\n4. Java Web Frameworks: Java web frameworks like Spring and Struts can also be used to deploy Java applications.", "May 11, 2023", "08:37:23 PM", "", "", "normal");
        try(
            Connection con = note.connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql_board);
            ){
                while(rs.next()){
                    Labels status_label = new Labels("_status_label");
                    Labels name_label = new Labels("_name_txt");
                    Labels ed_time_label = new Labels("_time_txt");
                    Labels time_len_label = new Labels("_time_txt");
                    VBoxs note_time_box = new VBoxs(ed_time_label,time_len_label);

                    name_label.setText(rs.getString(2));
                    status_label.setText(rs.getString(8));
                    String st_date = rs.getString(4);
                    String st_time = rs.getString(5);
                    String ed_time = rs.getString(7);
                    String ed_date = rs.getString(6);
                    if(ed_time.equals("") && ed_time.equals("")) {
                        String day_len = "taken, today";
                        long numOfDay = calcNumDays(today, rs.getString(4));
                        if(numOfDay == 1){
                            day_len = "taken, 1 day ago";
                        } else if(numOfDay > 1){
                            day_len = "taken, " + numOfDay + " days ago";
                        }
                        time_len_label.setText(day_len);
                        ed_time_label.setText(rs.getString(5));
                    } else {
                        String day_len = "edited, today";
                        long numOfDay = calcNumDays(today, rs.getString(6));
                        if(numOfDay == 1){
                            day_len = "edited, 1 day ago";
                        }
                        if(numOfDay > 1){
                            day_len = "edited, " + numOfDay + " days ago";
                        }
                        time_len_label.setText(day_len);
                        ed_time_label.setText(rs.getString(7));
                    }


                    HBoxs note_board = new HBoxs(status_label,name_label, note_time_box, "_note_board");
                    note_board.setOnMouseClicked(event -> {
                        HBox clickedItem = (HBox) note_list.getSelectionModel().getSelectedItem();
                        String noteName = ((Label) clickedItem.getChildren().get(1)).getText();

                        NoteDetail noteDetail = new NoteDetail();
                        noteDetail.setNoteDetails(noteName);
                    });
                    
                    notes.add(note_board);
                }
                
                note_list.setItems(notes);
                int size = note_list.getItems().size();
                if(size < 5){
                    double height = size * 85;
                    note_list.setPrefHeight(height);
                }

            } catch(SQLException e){
                System.err.println(e.getMessage());
        }
    }

    static long calcNumDays(LocalDate today, String str_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
        System.out.println(str_date);
        LocalDate date = LocalDate.parse(str_date, formatter);
        long numOfDay = ChronoUnit.DAYS.between(date, today);
        return numOfDay;
    }

    public static AnchorPanes getNewNotePage(){
        detail_field.setWrapText(true);
        
        Labels topic_label = new Labels("Note Topic", "_new_note_label");
        Labels status_label = new Labels("Important", "_status_label");
        Labels detail_label = new Labels("Note Detail", "_new_note_label");

        HBoxs topic_box = new HBoxs(topic_label, topic_field, "_tsk_box");
        HBoxs detail_box = new HBoxs(detail_label, detail_field, "_tsk_box");
        detail_box.setAlignment(Pos.TOP_CENTER);
        HBoxs status_box = new HBoxs(status_label, status_checker, "_tsk_box");
        
        VBoxs content_area = new VBoxs(topic_box, detail_box, status_box, add_btn, "_content_area");
        AnchorPanes noteRoot = new AnchorPanes(ToDo.getTimeBox(), content_area, "_noteRoot");
        return noteRoot;
    }

    public static void addNewnote(){
        String note_topic = topic_field.getText();
        String st_time = stTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        String st_date = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        String ed_time = "";
        String ed_date = "";
        String is_imp = status_field.getText();
        String note_detail = detail_field.getText();
        note.insertValues("note", note_topic, note_detail, st_date, st_time, ed_date, ed_time, is_imp);
    }

}
