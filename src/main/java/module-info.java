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

    opens org.example.orm_final to javafx.fxml;
    opens org.example.orm_final.entity;
    exports org.example.orm_final;
}