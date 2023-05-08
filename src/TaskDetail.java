import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import jfxClasses.AnchorPanes;

public class TaskDetail extends Dialog<Void> {
    Task task = new Task();
    Label nameLabel = new Label();
    Label priorityLabel = new Label();
    Label startDateLabel = new Label();
    Label descriptionLabel = new Label();
    Stage taskDetailStage = new Stage();
    
    public TaskDetail() {

        VBox vbox = new VBox(nameLabel, priorityLabel, startDateLabel, descriptionLabel);
        AnchorPanes detail_root = new AnchorPanes();
        detail_root.getChildren().add(vbox);
        Scene detaiScene = new Scene(detail_root, 600, 300);
        taskDetailStage.setScene(detaiScene);
    }

    public void setTaskDetails(String priority, String startDate, String taskName, String description) {
        priorityLabel.setText(priority);
        startDateLabel.setText(startDate);
        nameLabel.setText(taskName);
        descriptionLabel.setText(description);
        taskDetailStage.show();
    }
}
