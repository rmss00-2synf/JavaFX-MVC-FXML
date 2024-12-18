package org.javafxproject.javafxatelier1.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.javafxproject.javafxatelier1.Main;
import org.javafxproject.javafxatelier1.dao.FormationDao;
import org.javafxproject.javafxatelier1.dao.StudentDao;
import org.javafxproject.javafxatelier1.entities.Formation;
import org.javafxproject.javafxatelier1.entities.Student;

import java.io.IOException;
import java.util.stream.Collectors;
public class MainController {
    @FXML
    private ComboBox<String> formationComboBox;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> CNE;

    @FXML
    private TableColumn<Student, String> NOM;

    @FXML
    private TableColumn<Student, Float> MOYENNE;

    @FXML
    private TableColumn<Student, Float> MOYENNE_FORMATION;


    @FXML
    public void initialize() {
        FormationDao formationDao = new FormationDao() {};

        CNE.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        NOM.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        MOYENNE.setCellValueFactory(data -> new javafx.beans.property.SimpleFloatProperty((float) data.getValue().getMoyenne()).asObject());
//        MOYENNE_FORMATION.setCellValueFactory(data -> new javafx.beans.property.SimpleFloatProperty((float) data.
//                .getValue().moyeF());
            ObservableList<String> formations = FXCollections.observableArrayList(formationDao.getAll(new Formation()).stream().map(e->e.getName()).collect(Collectors.toList()));
            formationComboBox.setItems(formations);
            formationComboBox.setOnAction(event -> loadStudents());

    }

    private void loadStudents() {
        String selectedFormation = formationComboBox.getValue();
        StudentDao studentDao = new StudentDao() {};
        if (selectedFormation != null) {
                ObservableList<Student> students = FXCollections.observableArrayList(studentDao.findByFormation(selectedFormation));
                studentTable.setItems(students);

        }
    }

    @FXML
    private void handleAddStudent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/add_student_dialog.fxml"));
            Parent root = loader.load();

            Object controller = loader.getController();
            System.out.println("Contrôleur chargé : " + controller);

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajouter un étudiant");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(root));

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger la boîte de dialogue : " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void init(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);  // Définir la taille initiale de la fenêtre
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setWidth(710);
            stage.setHeight(500);
            stage.show();
        }
}
