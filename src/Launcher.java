import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
            todoScene = new Scene(ToDo.getToDoBoard(), 1400, 700);
        todoScene.getStylesheets().add(getClass().getResource("css\\todoPage_style.css").toExternalForm());
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
            new_task_Scene = new Scene(ToDo.getNewTaskPage(), 800, 400);
    new_task_Scene.getStylesheets().add(getClass().getResource("css\\todoPage_style.css").toExternalForm());
            Stage newTaskStage = new Stage();
            newTaskStage.setScene(new_task_Scene);
            newTaskStage.show();
        });

        ToDo.add_btn.setOnAction(event -> {
            ToDo.addNewTask();
            HomeStage.close();
            ToDo.getToDoBoard();
            HomeStage.show();
        });

    }

    

    
}
