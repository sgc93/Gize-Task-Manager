import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        new_note_Scene = new Scene(Notepad.getNewNotePage(), 650, 700);
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
            TaskDetail.taskDetailStage.close();
            ToDo.task_list.getItems().clear();
            ToDo.displayTask();
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
            newNoteStage.setScene(new_task_Scene);
            newNoteStage.show();
        });
        
        // Notepad.add_btn.setOnAction(event -> {
        //     Notepad.addNewTask();
        //     newTaskStage.close();
        //     TaskDetail.taskDetailStage.close();
        //     Notepad.task_list.getItems().clear();
        //     Notepad.displayTask();
        // });

    }    
}
