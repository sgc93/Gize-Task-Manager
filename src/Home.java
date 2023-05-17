import jfxClasses.*;

public class Home {
    public static Images gize_logo;
    
    public static Buttons home_btn;
    public static Buttons todo_btn;
    public static Buttons note_btn;
    public static Buttons logo_btn;
    public static Buttons about_btn = new Buttons("ABOUT US", "home_btn", "See Some Detail about this app");
    public static Buttons contact_btn = new Buttons("CONTACT US", "home_btn", "contact us for some reasen, call, mail, message, social meadia chat, ... addresses");
    
    public static AnchorPanes getHomeRoot() {
        // home button
        Images home_img = new Images("resources\\icons\\sgc_home.png");
        ImageViews home_img_view = new ImageViews(home_img);
        Texts home_txt = new Texts("Home", "btn_txt");
        HBoxs home_hbox = new HBoxs(home_img_view, home_txt, "nav_grid");
        
        home_btn = new Buttons("_home_btn", "nav_btn");
        home_btn.setGraphic(home_hbox);
        
        // todo button
        Images todo_img = new Images("resources\\icons\\list.png");
        ImageViews todo_img_view = new ImageViews(todo_img);
        Texts todo_txt = new Texts("Todo", "btn_txt");
        HBoxs todo_hbox = new HBoxs(todo_img_view, todo_txt, "nav_grid");
        
        todo_btn = new Buttons("_todo_btn", "nav_btn");
        todo_btn.setGraphic(todo_hbox);
        
        // note button
        Images note_img = new Images("resources\\icons\\book.png");
        ImageViews note_img_view = new ImageViews(note_img);
        Texts note_txt = new Texts("Note", "btn_txt");
        HBoxs note_hbox = new HBoxs(note_img_view, note_txt, "nav_grid");

        note_btn = new Buttons("_note_btn", "nav_btn");
        note_btn.setGraphic(note_hbox);


        // about button
        Images about_img = new Images("resources\\icons\\sgc_about.png");
        ImageViews about_img_view = new ImageViews(about_img);
        Texts about_txt = new Texts("About", "btn_txt");
        HBoxs about_hbox = new HBoxs(about_img_view, about_txt, "nav_grid");

        about_btn = new Buttons("_abaut_btn", "nav_btn");
        about_btn.setGraphic(about_hbox);

        HBoxs nav_hbox = new HBoxs(home_btn, todo_btn, note_btn, about_btn, "_nav_hbox");

        // Logo button
        Images logo = new Images("resources\\icons\\sgc_gize.png");
        ImageViews logo_view = new ImageViews(logo);
        logo_btn = new Buttons("_logo_btn");
        logo_btn.setGraphic(logo_view);

        // contet area
        gize_logo = new Images("resources\\icons\\red_gize.png");
        ImageViews gize_logo_view = new ImageViews(gize_logo, 60, 60);
        Texts logo_txt = new Texts("Gize Task Manager", "_todolist");
        Texts ver_txt = new Texts("v 1.0.0   |   May, 2023", "_ver_text");

        VBoxs logo_vbox = new VBoxs(logo_txt,ver_txt);
        HBoxs logo_hbox = new HBoxs(gize_logo_view, logo_vbox);

        String wellcome_content = "             FOR EVERYONE:\n ✔️ Fully Functional To Do List\n ✔️ Simple notepad to take note";

        Labels content_label = new Labels(wellcome_content, "_content_label");

        VBoxs content_area = new VBoxs(logo_hbox, content_label,contact_btn, "_content_area");
        AnchorPanes homeRoot = new AnchorPanes(nav_hbox, logo_btn, content_area, "_homeRoot");

        return homeRoot;
    }
}