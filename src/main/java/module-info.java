module com.example.database_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.database_final_project to javafx.fxml;
    exports com.example.database_final_project;
}