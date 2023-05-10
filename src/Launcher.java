import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todo.Task;
import todo.TaskDetail;
import todo.ToDo;

public class Launcher extends Application {
    Scene homeScene;
    static Scene newScene;
    Scene todoScene;
    Scene new_task_Scene;
    public static void main(String[] args) throws Exception {
        
        launch(args);
    }

    @Override
    public void start(Stage HomeStage) throws Exception {
    
        newScene = new Scene(NewPage.getNewPage(), 1400, 700);
        newScene.getStylesheets().add(getClass().getResource("css\\newPage_style.css").toExternalForm());
        homeScene = new Scene(Home.getHomeRoot(), 1400, 700);
        homeScene.getStylesheets().add(getClass().getResource("css\\home_style.css").toExternalForm());
        todoScene = new Scene(ToDo.getToDoBoard(), 1400, 700);
        
            new_task_Scene = new Scene(ToDo.getNewTaskPage(), 650, 700);
    new_task_Scene.getStylesheets().add(getClass().getResource("css\\todoPage_style.css").toExternalForm());
            Stage newTaskStage = new Stage();
    todoScene.getStylesheets().add(getClass().getResource("css\\todoPage_style.css").toExternalForm());
        HomeStage.setScene(homeScene);
        HomeStage.getIcons().add(Home.gize_logo);
        HomeStage.setTitle("Wellcome to Gize Manager    |   Home Page");
        HomeStage.setOnCloseRequest(event -> System.out.println("Are you sure?"));
        HomeStage.show();
        
        NavArea.new_btn.setOnAction(event -> {
            HomeStage.setScene(newScene);
            HomeStage.setTitle("Add New ...");
        });

        NewPage.home_btn.setOnAction(event -> {
            HomeStage.setScene(homeScene);
            HomeStage.setTitle("Wellcome to Gize Manager    |   Home Page");
        });

        NewPage.logo_btn.setOnAction(event -> {
            HomeStage.setScene(homeScene);
            HomeStage.setTitle("Wellcome to Gize Manager    |   Home Page");
        });

        NewPage.todo_btn.setOnAction(event -> {
            HomeStage.setScene(todoScene);
            HomeStage.setTitle("Add a new To Do task");
        });

        ToDo.back_btn.setOnAction(event -> {
            HomeStage.setScene(newScene);
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
            Task.updateRow(TaskDetail.updated_task,task_name, desc, stDate, stTime, endDate, endTime, pri, status);
            TaskDetail.taskDetailStage.close();
            ToDo.task_list.getItems().clear();
            ToDo.displayTask();
        });
    }    
}
