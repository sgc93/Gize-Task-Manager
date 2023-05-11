import jfxClasses.*;

public class NavArea {
    public static Images gize_logo;
    public static Buttons home_btn;
    public static Buttons new_btn;
    public static Buttons open_btn;
    public static Buttons search_btn;
    public static Buttons mode_btn;
    public static Buttons logo_btn;
    public static HBoxs getNavHbox(){
        

        
        return null;
    }
    
    public static Buttons getLogoBtn(){
        // Logo button
        Images logo = new Images("resources\\icons\\sgc_gize.png");
        ImageViews logo_view = new ImageViews(logo);
        logo_btn = new Buttons("_logo_btn");
        logo_btn.setGraphic(logo_view);

        return logo_btn;
    }


}


// import java.sql.*;
// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.stage.Stage;

// public class TodoListApp extends Application {

//     private static final int PAGE_SIZE = 10; // Number of tasks to display per page
//     private int currentPage = 1; // Current page number

//     @Override
//     public void start(Stage stage) throws Exception {

//         // Create a connection to the database
//         Connection conn = DriverManager.getConnection("jdbc:sqlite:/path/to/database.db");

//         // Create a statement to retrieve data from the task table
//         String query = "SELECT * FROM task LIMIT ? OFFSET ?";
//         PreparedStatement stmt = conn.prepareStatement(query);
//         stmt.setInt(1, PAGE_SIZE);
//         stmt.setInt(2, (currentPage - 1) * PAGE_SIZE);
//         ResultSet rs = stmt.executeQuery();

//         // Create a VBox to hold the task labels
//         VBox taskBox = new VBox(10);
//         taskBox.setPadding(new Insets(10));
//         while (rs.next()) {
//             // Create a Label for each task and add it to the VBox
//             Label taskLabel = new Label(rs.getString("name"));
//             taskBox.getChildren().add(taskLabel);
//         }

//         // Create a HBox to hold the pagination buttons
//         HBox paginationBox = new HBox(10);
//         paginationBox.setPadding(new Insets(10));

//         // Create the "Previous" button
//         Button prevButton = new Button("Previous");
//         prevButton.setDisable(currentPage == 1); // Disable the button if we're on the first page
//         prevButton.setOnAction(event -> {
//             currentPage--;
//             prevButton.setDisable(currentPage == 1); // Disable the button if we're on the first page
//             nextButton.setDisable(false); // Enable the "Next" button
//             refreshTasks(conn, taskBox); // Refresh the task labels
//         });

//         // Create the "Next" button
//         Button nextButton = new Button("Next");
//         nextButton.setDisable(false); // Enable the button by default
//         nextButton.setOnAction(event -> {
//             currentPage++;
//             prevButton.setDisable(false); // Enable the "Previous" button
//             refreshTasks(conn, taskBox); // Refresh the task labels
//             try {
//                 // Check if there are any more rows in the ResultSet
//                 if (!rs.next()) {
//                     nextButton.setDisable(true); // Disable the "Next" button if there are no more rows
//                 }
//             } catch (SQLException e) {
//                 e.printStackTrace();
//             }
//         });

//         paginationBox.getChildren().addAll(prevButton, nextButton);

//         // Create the root layout
//         BorderPane root = new BorderPane();
//         root.setCenter(taskBox);
//         root.setBottom(paginationBox);

//         // Create the scene and show the stage
//         Scene scene = new Scene(root, 400, 400);
//         stage.setScene(scene);
//         stage.setTitle("Todo List App");
//         stage.show();
//     }

//     private void refreshTasks(Connection conn, VBox taskBox) {
//         try {
//             // Clear the task labels
//             taskBox.getChildren().clear();

//             // Create a statement to retrieve data from the task table
//             String query = "SELECT * FROM task LIMIT ? OFFSET ?";
//             PreparedStatement stmt = conn.prepareStatement(query);
//             stmt.setInt(1, PAGE_SIZE);
//             stmt.setInt(2, (currentPage - 1) * PAGE_SIZE);
//             ResultSet rs = stmt.executeQuery();

//             while (rs.next()) {
//                 // Create a Label for each task and add it to the VBox
               

