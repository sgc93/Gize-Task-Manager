import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import note.Note;
import note.NoteDetail;
import note.Notepad;
import todo.Task;
import todo.TaskDetail;
import todo.ToDo;

public class Launcher extends Application {
    Scene homeScene;
    static Scene noteScene;
    Scene todoScene;
    Scene new_task_Scene;
    Scene new_note_Scene;
    public static void main(String[] args) throws Exception {
        
        launch(args);
    }

    @Override
    public void start(Stage HomeStage) throws Exception {
    
        noteScene = new Scene(Notepad.getToDoBoard(), 1400, 700);
        noteScene.getStylesheets().add(getClass().getResource("css\\notepad.css").toExternalForm());
        homeScene = new Scene(Home.getHomeRoot(), 1400, 700);
        homeScene.getStylesheets().add(getClass().getResource("css\\home_style.css").toExternalForm());
        todoScene = new Scene(ToDo.getToDoBoard(), 1400, 700);
        
        new_task_Scene = new Scene(ToDo.getNewTaskPage(), 650, 700);
        new_note_Scene = new Scene(Notepad.getNewNotePage(), 1000, 700);
    new_task_Scene.getStylesheets().add(getClass().getResource("css\\todoPage_style.css").toExternalForm());
    new_note_Scene.getStylesheets().add(getClass().getResource("css\\notepad.css").toExternalForm());
        Stage newTaskStage = new Stage();
        Stage newNoteStage = new Stage();
    todoScene.getStylesheets().add(getClass().getResource("css\\todoPage_style.css").toExternalForm());
        HomeStage.setScene(homeScene);
        HomeStage.getIcons().add(Home.gize_logo);
        HomeStage.setTitle("Wellcome to Gize Manager    |   Home Page");
        HomeStage.setOnCloseRequest(event -> System.out.println("Are you sure?"));
        HomeStage.show();
        
        Home.todo_btn.setOnAction(event -> {
            HomeStage.setScene(todoScene);
            HomeStage.setTitle("Add New ...");
        });
        
        Home.note_btn.setOnAction(event -> {
            HomeStage.setScene(noteScene);
            HomeStage.setTitle("Add New ...");
        });

        // for todo list board buttons

        ToDo.back_btn.setOnAction(event -> {
            HomeStage.setScene(homeScene);
            HomeStage.setTitle("Add New ...");
        });
        ToDo.home_btn.setOnAction(event -> {
            HomeStage.setScene(homeScene);
            HomeStage.setTitle("Wellcome to Gize Manager    |   Home Page");
        });

        ToDo.new_task_btn.setOnAction(event -> {
            newTaskStage.setScene(new_task_Scene);
            newTaskStage.show();
        });

        ToDo.addNew_btn.setOnAction(event -> {
            newTaskStage.setScene(new_task_Scene);
            newTaskStage.show();
        });
        
        ToDo.add_btn.setOnAction(event -> {
            ToDo.addNewTask();
            newTaskStage.close();
            ToDo.task_list.getItems().clear();
            ToDo.displayTask();
        });

        ToDo.com_btn.setOnAction(event -> {
            ToDo.com_btn.setStyle("-fx-backgournd-color: blue;");
            ToDo.task_list.getItems().clear();
            ToDo.sql_board = "SELECT * FROM task WHERE  iscompleted = 'done'";
            ToDo.displayTask();
        });
        ToDo.all_btn.setOnAction(event -> {
            ToDo.com_btn.setStyle("-fx-backgournd-color: blue;");
            ToDo.task_list.getItems().clear();
            ToDo.sql_board = "SELECT * FROM task";
            ToDo.displayTask();
        });
        ToDo.high_btn.setOnAction(event -> {
            ToDo.task_list.getItems().clear();
            ToDo.sql_board = "SELECT  * FROM task WHERE priority = 'High'";
            ToDo.displayTask();
        });
        ToDo.low_btn.setOnAction(event -> {
            ToDo.com_btn.setStyle("-fx-backgournd-color: blue;");
            ToDo.task_list.getItems().clear();
            ToDo.sql_board = "SELECT * FROM task WHERE  priority= 'Low'";
            ToDo.displayTask();
        });
        ToDo.med_btn.setOnAction(event -> {
            ToDo.com_btn.setStyle("-fx-backgournd-color: blue;");
            ToDo.task_list.getItems().clear();
            ToDo.sql_board = "SELECT * FROM task WHERE priority = 'Medium'";
            ToDo.displayTask();
        });

        ToDo.search_btn.setOnAction(event -> {
            ToDo.task_list.getItems().clear();
            ToDo.getSearchedTask();
        });
        

        
        // for task detail window buttons

        TaskDetail.delete_btn.setOnAction(event -> {
            String txt = TaskDetail.taskField.getText();
            Task.delRow(txt);
            TaskDetail.taskDetailStage.close();
            ToDo.task_list.getItems().clear();
            ToDo.displayTask();
        });

        TaskDetail.save_btn.setOnAction(event -> {
            String task_name = TaskDetail.taskField.getText();
            String desc = TaskDetail.descriptionField.getText();
            String stDate = TaskDetail.startDateLabel.getText();
            String endDate = TaskDetail.endDateLabel.getText();
            String stTime = TaskDetail.startTimeLabel.getText();
            String endTime = TaskDetail.endTimeLabel.getText();
            String pri = TaskDetail.priorityLabel.getText();
            String status = TaskDetail.statusLabel.getText();
            String updated = TaskDetail.updated_task;

            String sql = "UPDATE task SET task_name = '" + task_name + "', st_date = '" + stDate + "', st_time = '"+ stTime + "', end_date = '"+ endDate + "', end_time = '" + endTime + "', iscompleted = '" + status + "', priority = '" + pri + "', task_des = '" + desc + "' WHERE task_name = '" + updated + "'";

            Task.updateRow(sql);
            TaskDetail.taskDetailStage.close();
            ToDo.task_list.getItems().clear();
            ToDo.displayTask();
        });

        // for note list board buttons

        Notepad.back_btn.setOnAction(event -> {
            HomeStage.setScene(homeScene);
            HomeStage.setTitle("Add New ...");
        });
        Notepad.home_btn.setOnAction(event -> {
            HomeStage.setScene(homeScene);
            HomeStage.setTitle("Wellcome to Gize Manager    |   Home Page");
        });

        Notepad.new_note_btn.setOnAction(event -> {
            newNoteStage.setScene(new_note_Scene);
            newNoteStage.show();
        });

        Notepad.addNew_btn.setOnAction(event -> {
            newNoteStage.setScene(new_note_Scene);
            newNoteStage.show();
        });
        
        Notepad.add_btn.setOnAction(event -> {
            Notepad.addNewnote();
            newNoteStage.close();
            Notepad.note_list.getItems().clear();
            Notepad.displayNote();
        });

        Notepad.imp_btn.setOnAction(event -> {
            Notepad.note_list.getItems().clear();
            Notepad.sql_board = "SELECT * FROM note WHERE isimportant = 'important'";
            Notepad.displayNote();
        });

        Notepad.all_btn.setOnAction(event -> {
            Notepad.imp_btn.setStyle("-fx-backgournd-color: blue;");
            Notepad.note_list.getItems().clear();
            Notepad.sql_board = "SELECT * FROM note";
            Notepad.displayNote();
        });

        Notepad.search_btn.setOnAction(event -> {
            Notepad.note_list.getItems().clear();
            Notepad.getSearchedNOte();
        });

        // for note detail buttons

        // for task detail window buttons

        NoteDetail.delete_btn.setOnAction(event -> {
            String txt = NoteDetail.topic_field.getText();
            Note.delRow(txt);
            NoteDetail.NoteDetailStage.close();
            Notepad.note_list.getItems().clear();
            Notepad.displayNote();
        });

        NoteDetail.save_btn.setOnAction(event -> {
            String note_name = NoteDetail.topic_field.getText();
            String note_detail = NoteDetail.detail_field.getText();
            String editDate = NoteDetail.today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            String editTime = NoteDetail.now.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
            String status = "normal";
            if(NoteDetail.imp_check.isSelected()){
                status = "important";
            }
            System.out.println("status: " + status);

            String sql = "UPDATE note SET note_name = '" + note_name + "', note_detail = '" + note_detail + "', ed_date = '"+ editDate + "', ed_time = '" + editTime + "', isimportant = '" + status + "' WHERE note_name = '" + NoteDetail.updated_NoteName + "'";
            System.out.println(sql);

            Note.updateRow(sql);
            NoteDetail.NoteDetailStage.close();
            Notepad.note_list.getItems().clear();
            Notepad.displayNote();
        });
    }    
}
