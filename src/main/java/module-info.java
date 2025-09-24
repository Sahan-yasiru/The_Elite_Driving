module org.example.orm_final {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires static lombok;
    requires bcrypt;
    requires java.desktop;

    opens org.example.orm_final to javafx.fxml;
    opens org.example.orm_final.controller to javafx.fxml;
    opens org.example.orm_final.model;
    opens org.example.orm_final.entity.user;
    opens org.example.orm_final.view.user to javafx.base;
    opens org.example.orm_final.view to javafx.base;
    opens  org.example.orm_final.entity to org.hibernate.orm.core;

    exports org.example.orm_final;
    opens org.example.orm_final.view.instructor to javafx.base;
    opens org.example.orm_final.view.label to javafx.base;

}