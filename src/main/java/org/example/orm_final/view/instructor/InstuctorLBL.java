package org.example.orm_final.view.instructor;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.Getter;

import static javafx.geometry.Pos.CENTER;

public class InstuctorLBL extends Label  {

    public InstuctorLBL(String text){
        super(text);
        setAlignment(CENTER);
        setStyle(this.getStyle()+"-fx-font-size: 20px;"+"-fx-background-color: #6e00ff;"+"-fx-border-radius: 10px;"+
                "-fx-background-radius: 10px;"+"-fx-text-fill: white;");
    }

}
