package jfxClasses;

import javafx.scene.image.ImageView;

public class ImageViews extends ImageView{

    public ImageViews(Images img) {
        setImage(img);
        setFitHeight(20);
        setFitWidth(20);;
    }

    public ImageViews(Images gize_logo, int width, int height) {
        setImage(gize_logo);
        setFitHeight(height);
        setFitWidth(width);
    }

}
