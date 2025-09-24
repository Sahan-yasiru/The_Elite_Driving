package org.example.orm_final.view.label;

import javafx.scene.control.Label;

import static javafx.geometry.Pos.CENTER;

public class TMLBL extends Label  {

    public TMLBL(String text) {
        super(text);
        setAlignment(CENTER);
        setStyle(this.getStyle() + "-fx-font-size: 20px;" + "-fx-background-color: #6e00ff;" + "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" + "-fx-text-fill: white;");
    }

}
