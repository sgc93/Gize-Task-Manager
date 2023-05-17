import java.io.IOException;

import javafx.scene.control.Hyperlink;
import jfxClasses.*;

public class AboutPage {
    static Images gize_logo;
    public static Buttons home_btn;
    static Buttons send_btn = new Buttons("Send Email", "email_btn", "Send an Email for the developer.");
    public static AnchorPanes getAboutPage(){
        // home button
        Images home_img = new Images("resources\\icons\\sgc_home.png");
        ImageViews home_img_view = new ImageViews(home_img);
        Texts home_txt = new Texts("Home", "btn_txt");
        HBoxs home_hbox = new HBoxs(home_img_view, home_txt, "nav_grid");

        home_btn = new Buttons("_home_btn", "nav_btn");
        home_btn.setGraphic(home_hbox);

        HBoxs nav_hbox = new HBoxs(home_btn, "_nav_hbox");

        // contet area

        gize_logo = new Images("resources\\icons\\red_gize.png");
        ImageViews gize_logo_view = new ImageViews(gize_logo, 60, 60);
        Texts logo_txt = new Texts("Gize Task Manager", "_todolist");
        Texts ver_txt = new Texts("v 1.0.0   |   May, 2023", "_ver_text");

        VBoxs logo_vbox = new VBoxs(logo_txt,ver_txt);
        HBoxs logo_hbox = new HBoxs(gize_logo_view, logo_vbox);

        String wellcome_content = "Developed by: Smachew Gedefaw C.\n\nContact me:-";

        Labels content_label = new Labels(wellcome_content, "_content_label");
        // linkedIn link
        Images linkedin_img = new Images("resources\\icons\\bx-linkedin.png");
        Hyperlink linkedin_link = new Hyperlink();
        linkedin_link.setGraphic(new ImageViews(linkedin_img, 30, 30));
        linkedin_link.setOnAction(e -> {
            String url = "https://www.linkedin.com/in/smachew-gedefaw-725923257/";
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        // telegram link
        Images tg_img = new Images("resources\\icons\\bx-telegram.png");
        Hyperlink tg_link = new Hyperlink();
        tg_link.setGraphic(new ImageViews(tg_img, 30, 30));
        tg_link.setOnAction(e -> {
            String url = "https://t.me/wasted_limitted_time";
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        // github link
        Images github_img = new Images("resources\\icons\\bx-github.png");
        Hyperlink github_link = new Hyperlink();
        github_link.setGraphic(new ImageViews(github_img, 30, 30));
        github_link.setOnAction(e -> {
            String url = "https://github.com/sgc93";
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        HBoxs link_box = new HBoxs(linkedin_link, tg_link, github_link);

        Hyperlink repo_link = new Hyperlink("GitHub Repository of this app");
        repo_link.setId("_repo_link");
        repo_link.setOnAction(e -> {
            String url = "https://github.com/sgc93/Gize-Task-Manager";
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        

        VBoxs content_area = new VBoxs(logo_hbox, content_label,link_box, repo_link, send_btn,"_content_area");
        AnchorPanes aboutRoot = new AnchorPanes(nav_hbox, content_area, "_homeRoot");

        return aboutRoot;
    }
}