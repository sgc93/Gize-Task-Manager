import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxClasses.*;

public class Email {
    static Labels err_label = new Labels("_err_label");
    static Labels sender_label = new Labels("Your Full Name : ", "_email_label");
    static Labels subject_label = new Labels("Email Subject : ", "_email_label");
    static Labels body_label = new Labels("Email Body : ", "_email_label");
    
    static TextFields sender_field = new TextFields("_sender");
    static TextFields subject_field = new TextFields("_subject");
    static TextAreas body_field = new TextAreas("_body");
    
    static Buttons send_btn = new Buttons("Send","email_btn","send your data.");
    static Buttons reset_btn = new Buttons("Reset", "email_btn","clear your all inputs.");
    
    
    public static VBoxs getEmailRoot(){
        Labels topic = new Labels("Send an emai For Me:- ", "_email_topic");

        HBoxs sender_box = new HBoxs(sender_label, sender_field);
        HBoxs subject_box = new HBoxs(subject_label, subject_field);
        HBoxs body_box = new HBoxs(body_label, body_field);
        HBoxs btn_box = new HBoxs(reset_btn, send_btn);

        VBoxs email_root = new VBoxs(err_label,topic, sender_box, subject_box, body_box, btn_box, "_email_root");

        reset_btn.setOnAction(event -> {
            sender_field.setText("");
            subject_field.setText("");
            body_field.setText("");
        });
        
        return email_root;
    }
}
