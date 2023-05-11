package note;
import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jfxClasses.*;

public class NoteDetail {
    Note Note = new Note();
    Labels nameLabel = new Labels("Note Name", "label_txt");
    Labels detailLabel = new Labels("Note", "label_txt");
    public static TextFields nameField = new TextFields("name_field");
    public static TextAreas descriptionField = new TextAreas("detail_field");

    public static Buttons edit_btn = new Buttons("_edit_btn", "tsk_btn");
    public static Buttons delete_btn = new Buttons("_delete_btn", "tsk_btn");
    public static Buttons back_btn = new Buttons("_back_btn", "tsk_btn");
    public static Buttons save_btn = new Buttons("Save Change","_back_btn", "tsk_btn");

    public static Stage NoteDetailStage = new Stage();
    public static String updated_Note;
    
    public NoteDetail() {
        // edit btn
        nameField.setDisable(true);
        descriptionField.setDisable(true);
        save_btn.setDisable(true);

        Images edit_img = new Images("resources\\icons\\edit.png");
        ImageViews edit_img_view = new ImageViews(edit_img);
        edit_btn.setGraphic(edit_img_view);
        edit_btn.setOnAction(event -> {
            updated_Note = nameField.getText();
            nameField.setDisable(false);
            descriptionField.setDisable(false);
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
        back_btn.setOnAction(event -> NoteDetailStage.close());

        HBoxs detail_btn = new HBoxs(back_btn, tsk_btn);

        VBoxs Note_vbox = new VBoxs(nameLabel, nameField, detailLabel, descriptionField, "_Note_vbox");


        VBoxs detail_root = new VBoxs(detail_btn, Note_vbox, "_detail_root");
        Scene detailScene = new Scene(detail_root, 1000, 600);

        detailScene.getStylesheets().add(getClass().getResource("..\\css\\detail_page.css").toExternalForm());
        NoteDetailStage.setScene(detailScene);
        NoteDetailStage.setResizable(false);
        NoteDetailStage.setAlwaysOnTop(true);
    }

    public void setNoteDetails(String NoteName) {
        String sql = "SELECT * FROM Note WHERE Note_name = '" + NoteName + "'";
        System.out.println(sql);
        try(
            Connection con = Note.connect();
            Statement st = con.createStatement();
        ){  
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            priorityLabel.setText(rs.getString(8));
            at_date_Label.setText(rs.getString(3));
            startTimeLabel.setText(rs.getString(4));
            endDateLabel.setText(rs.getString(5));
            endTimeLabel.setText(rs.getString(6));
            nameField.setText(rs.getString(2));
            descriptionField.setText(rs.getString(3));
            statusLabel.setText(rs.getString(7));
            NoteDetailStage.show();
        } catch(SQLException e){
            System.err.println(e);
        }
        
    }
}
