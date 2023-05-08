import jfxClasses.*;

public class Home {
    public static Images gize_logo;

    public static AnchorPanes getHomeRoot() {

        // contet area
        gize_logo = new Images("resources\\icons\\red_gize.png");
        ImageViews gize_logo_view = new ImageViews(gize_logo, 60, 60);
        Texts logo_txt = new Texts("Gize Task Manager", "_todolist");
        Texts ver_txt = new Texts("v 1.0.0   |   May, 2023", "_ver_text");

        VBoxs logo_vbox = new VBoxs(logo_txt,ver_txt);
        HBoxs logo_hbox = new HBoxs(gize_logo_view, logo_vbox);

        String wellcome_content = "             FOR INDIVIDUALS:\n ✔️ Fully Functional To Do List \n ✔️  Interactive Note Taking Pade \n             FOR TEAM WORK:\n ✔️  Task Classfication Board \n ✔️  Notification Board \n ✔️  Group Chat Board";

        Labels content_label = new Labels(wellcome_content, "_content_label");

        Buttons about_btn = new Buttons("ABOUT US", "home_btn", "See Some Detail about this app");
        Buttons contact_btn = new Buttons("CONTACT US", "home_btn", "contact us for some reasen, call, mail, message, social meadia chat, ... addresses");
        Buttons help_btn = new Buttons("GET HELP", "home_btn", "get some help");

        HBoxs btn_box = new HBoxs(about_btn, contact_btn, help_btn);

        Buttons se_btn = new Buttons("For Software Engineering Students", "home_btn", "contact us and learn, do projects, ... with us");
        se_btn.setId("_se_btn");

        VBoxs content_area = new VBoxs(logo_hbox, content_label,btn_box, se_btn, "_content_area");
        AnchorPanes homeRoot = new AnchorPanes(NavArea.getNavHbox(), NavArea.getLogoBtn(), content_area, "_homeRoot");

        return homeRoot;
    }
}