import jfxClasses.*;

public class NewPage {
    static Images gize_logo;
    public static Buttons home_btn;
    public static Buttons new_btn;
    public static Buttons open_btn;
    public static Buttons search_btn;
    public static Buttons mode_btn;
    public static Buttons logo_btn;
    public static Buttons todo_btn;
    public static Buttons note_btn;
    public static Buttons team_btn;
    public static AnchorPanes getNewPage(){
        // home button
        Images home_img = new Images("resources\\icons\\sgc_home.png");
        ImageViews home_img_view = new ImageViews(home_img);
        Texts home_txt = new Texts("Home", "btn_txt");
        HBoxs home_hbox = new HBoxs(home_img_view, home_txt, "nav_grid");

        home_btn = new Buttons("_home_btn", "nav_btn");
        home_btn.setGraphic(home_hbox);
        
        // New button
        Images new_img = new Images("resources\\icons\\add.png");
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

        // Logo button
        Images logo = new Images("resources\\icons\\sgc_gize.png");
        ImageViews logo_view = new ImageViews(logo);
        logo_btn = new Buttons("_logo_btn");
        logo_btn.setGraphic(logo_view);

        // contet area

        todo_btn = new Buttons("To Do List", "new_btn", "add new to do list manager");
        note_btn = new Buttons("Take Note", "new_btn", "take a new note");
        team_btn = new Buttons("Team work", "new_btn", "get some help");

        VBoxs content_area = new VBoxs(todo_btn, note_btn, team_btn, "_content_area");
        AnchorPanes newRoot = new AnchorPanes(nav_hbox, logo_btn, content_area, "_newRoot");

        return newRoot;
    }
}
