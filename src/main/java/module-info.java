module javafx.game.stick_hero_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.game.stick_hero_project to javafx.fxml;
    exports javafx.game.stick_hero_project;
}