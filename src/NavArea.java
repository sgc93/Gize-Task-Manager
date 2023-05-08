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
        // home button
        Images home_img = new Images("resources\\icons\\sgc_home.png");
        ImageViews home_img_view = new ImageViews(home_img);
        Texts home_txt = new Texts("Home", "btn_txt");
        HBoxs home_hbox = new HBoxs(home_img_view, home_txt, "nav_grid");

        home_btn = new Buttons("_home_btn", "nav_btn");
        home_btn.setGraphic(home_hbox);
        
        // New button
        Images new_img = new Images("resources\\icons\\new.png");
        ImageViews new_img_view = new ImageViews(new_img);
        Texts new_txt = new Texts("New", "btn_txt");
        HBoxs new_hbox = new HBoxs(new_img_view, new_txt, "nav_grid");

        new_btn = new Buttons("_new_btn", "nav_btn");
        new_btn.setGraphic(new_hbox);
        
        // Open button
        Images open_img = new Images("resources\\icons\\book.png");
        ImageViews open_img_view = new ImageViews(open_img);
        Texts open_txt = new Texts("Open", "btn_txt");
        HBoxs open_hbox = new HBoxs(open_img_view, open_txt, "nav_grid");

        open_btn = new Buttons("_open_btn", "nav_btn");
        open_btn.setGraphic(open_hbox);

        // search button
        Images search_img = new Images("resources\\icons\\search.png");
        ImageViews search_img_view = new ImageViews(search_img);
        Texts search_txt = new Texts("Search", "btn_txt");
        HBoxs search_hbox = new HBoxs(search_img_view, search_txt, "nav_grid");

        search_btn = new Buttons("_search_btn", "nav_btn");
        search_btn.setGraphic(search_hbox);

        // mode button
        Images mode_img = new Images("resources\\icons\\sgc_mode.png");
        ImageViews mode_img_view = new ImageViews(mode_img);
        Texts mode_txt = new Texts("Mode", "btn_txt");
        HBoxs mode_hbox = new HBoxs(mode_img_view, mode_txt, "nav_grid");

        mode_btn = new Buttons("_mode_btn", "nav_btn");
        mode_btn.setGraphic(mode_hbox);

        HBoxs nav_hbox = new HBoxs(home_btn, new_btn, open_btn, search_btn, mode_btn, "_nav_hbox");

        
        return nav_hbox;
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
               

