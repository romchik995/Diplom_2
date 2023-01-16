module com.example.diplom_2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.diplom_2 to javafx.fxml;
    exports com.example.diplom_2;
}