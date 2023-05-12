package note;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxClasses.*;

public class NoteDetail {
    Note Note = new Note();

    public static LocalTime now = LocalTime.now();
    public static LocalDate today = LocalDate.now();
    public static String formattedtime;
    public static String formatteddate;

    Labels topic_label = new Labels("Note Topic", "label_txt");
    Labels detail_label = new Labels("Note Detail", "label_txt");
    Labels stDate_len_label = new Labels("_note_date_len");
    Labels stDate_label = new Labels("_note_date");
    Labels stTime_label = new Labels("_note_time");
    Labels edDate_len_label = new Labels("_note_date_len");
    Labels edDate_label = new Labels("_note_date");
    Labels edTime_label = new Labels("_note_time");

    public static TextFields topic_field = new TextFields("name_field");
    public static TextAreas detail_field = new TextAreas("detail_field");

    public static CheckBoxs imp_check = new CheckBoxs("_status_cheker");
    public static CheckBoxs normal_check = new CheckBoxs("_status_cheker");

    public static Buttons edit_btn = new Buttons("_edit_btn", "tsk_btn");
    public static Buttons delete_btn = new Buttons("_delete_btn", "tsk_btn");
    public static Buttons back_btn = new Buttons("_back_btn", "tsk_btn");
    public static Buttons save_btn = new Buttons("Save Change","_back_btn", "tsk_btn");

    public static Stage NoteDetailStage = new Stage();
    public static String updated_NoteName;
    
    public NoteDetail() {
        detail_field.setWrapText(true);
        imp_check.setText("Important");
        // metadata boxes
        VBoxs stDate_board = new VBoxs(stDate_len_label, stDate_label, stTime_label);
        VBoxs edDate_board = new VBoxs(edDate_len_label, edDate_label, edTime_label);
        HBoxs noteDate_board = new HBoxs(stDate_board, edDate_board, "_noteDate_board");
        // edit btn
        topic_field.setDisable(true);
        detail_field.setDisable(true);
        save_btn.setDisable(true);
        imp_check.setDisable(true);

        Images edit_img = new Images("resources\\icons\\edit.png");
        ImageViews edit_img_view = new ImageViews(edit_img);
        edit_btn.setGraphic(edit_img_view);
        edit_btn.setOnAction(event -> {
            updated_NoteName = topic_field.getText();
            topic_field.setDisable(false);
            detail_field.setDisable(false);
            save_btn.setDisable(false);
            imp_check.setDisable(false);
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
        back_btn.setOnAction(event -> NoteDetailStage.close());
        HBoxs nav_board = new HBoxs(noteDate_board, tsk_btn, "_nav_board");
        HBoxs detail_nav = new HBoxs(back_btn, nav_board, "_detail_nav");

        VBoxs Note_vbox = new VBoxs(topic_label, topic_field, detail_label, detail_field, "_Note_vbox");

        VBoxs detail_root = new VBoxs(detail_nav, Note_vbox,imp_check,  "_detail_root");
        Scene detailScene = new Scene(detail_root, 1000, 600);

        detailScene.getStylesheets().add(getClass().getResource("..\\css\\detail_page.css").toExternalForm());
        NoteDetailStage.setScene(detailScene);
        NoteDetailStage.setResizable(false);
        NoteDetailStage.setAlwaysOnTop(true);
    }

    public void setNoteDetails(String NoteName) {
        String sql = "SELECT * FROM Note WHERE note_name = '" + NoteName + "'";
        System.out.println(sql);
        try(
            Connection con = Note.connect();
            Statement st = con.createStatement();
        ){  
            ResultSet rs = st.executeQuery(sql);
            rs.next();

            
            topic_field.setText(rs.getString(2));
            detail_field.setText(rs.getString(3));
            // stDate_len_label.setText("-> taken, " + Notepad.calcNumDays(today,rs.getString(6)) + " day ago.");
            // edDate_len_label.setText("-> edited, " + Notepad.calcNumDays(today,rs.getString(6)) + " day ago.");
            stDate_label.setText(rs.getString(4));
            stTime_label.setText(rs.getString(5));
            edDate_label.setText(rs.getString("ed_date"));
            edTime_label.setText(rs.getString("ed_time"));
            
            if(rs.getString(8) == "important"){
                imp_check.setSelected(true);
            }

            String stday_len = "-> It has been taken today";
            String st_time = "at " + rs.getString(5);
            String st_date = "on " + rs.getString(4);
            String edday_len = "-> It has been edited today";
            String ed_time = "at " + rs.getString(7);
            String ed_date = "on " + rs.getString(6);
            if((rs.getString(6)).equals("") && (rs.getString(7)).equals("")) {
                long numOfDay = Notepad.calcNumDays(today, rs.getString(4));
                if(numOfDay == 1){
                    stday_len = "-> It was taken 1 day ago";
                    st_date = "on " + rs.getString(4);
                } else if(numOfDay > 1){
                    stday_len = "-> It was taken " + numOfDay + " days ago";
                    st_date = "on " + rs.getString(4);
                }
                stDate_len_label.setText(stday_len);
                edDate_len_label.setText("Unedited!");
                // stDate_label.setText(st_date);
                // stTime_label.setText(st_time);
                
            } else{
                long numOfDay = Notepad.calcNumDays(today, rs.getString(4));
                long numEditDay = Notepad.calcNumDays(today, rs.getString(6));
                if(numOfDay == 1){
                    stday_len = "-> It was taken 1 day ago";
                    st_date = "on " + rs.getString(4);
                } else if(numOfDay > 1){
                    stday_len = "-> It was taken " + numOfDay + " days ago";
                    st_date = "on " + rs.getString(4);
                }
                if(numEditDay == 1){
                    stday_len = "-> It was edited 1 day ago";
                    ed_date = "on " + rs.getString(6);
                } else if(numEditDay > 1){
                    edday_len = "-> It was edited " + numEditDay + " days ago";
                    ed_date = "on " + rs.getString(6);
                }
                stDate_len_label.setText(stday_len);
                edDate_len_label.setText(edday_len);
                stDate_label.setText(st_date);
                stTime_label.setText(st_time);
                edDate_label.setText(ed_date);
                edTime_label.setText(ed_time);
            }

            NoteDetailStage.show();
        } catch(SQLException e){
            System.err.println(e);
        }
    }
}
