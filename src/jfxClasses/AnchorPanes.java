package jfxClasses;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AnchorPanes extends AnchorPane {
    public AnchorPanes(HBoxs nav_area,Buttons logo_btn, VBoxs content_area,String id){
        setId(id);
        getChildren().addAll(nav_area,logo_btn, content_area);

        setTopAnchor(content_area, 90.0);
        setBottomAnchor(content_area, 10.0);
        setLeftAnchor(content_area, 10.0);
        setRightAnchor(content_area, 10.0);

        setTopAnchor(nav_area, 10.0);
        setBottomAnchor(nav_area, 620.0);
        setLeftAnchor(nav_area, 10.0);
        setRightAnchor(nav_area, 70.0);

        setTopAnchor(logo_btn, 10.0);
        setBottomAnchor(logo_btn, 620.0);
        setLeftAnchor(logo_btn, 1340.0);
        setRightAnchor(logo_btn, 10.0);
    }

    public AnchorPanes(HBoxs head_hbox, VBoxs content_area, String id) {
        setId(id);
        getChildren().addAll(head_hbox, content_area);

        setTopAnchor(content_area, 60.0);
        setBottomAnchor(content_area, 10.0);
        setLeftAnchor(content_area, 10.0);
        setRightAnchor(content_area, 10.0);

        setTopAnchor(head_hbox, 10.0);
        setBottomAnchor(head_hbox, 650.0);
        setLeftAnchor(head_hbox, 10.0);
        setRightAnchor(head_hbox, 10.0);
    }

    public AnchorPanes() {
    }

    public AnchorPanes(VBox vbox, String id) {
        setId(id);
        getChildren().addAll(vbox);
    }
}
