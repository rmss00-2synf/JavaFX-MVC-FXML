package org.javafxproject.javafxatelier1.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.javafxproject.javafxatelier1.dao.FormationDao;
import org.javafxproject.javafxatelier1.dao.StudentDao;
import org.javafxproject.javafxatelier1.entities.Formation;
import org.javafxproject.javafxatelier1.entities.Student;

import java.io.IOException;
import java.util.List;

public class StudentController {
    @FXML
    private TextField studentIdField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField studentMoyenneField;

    @FXML
    private ComboBox<String> formationComboBox;

    private final StudentDao studentDao = new StudentDao(){};
    private final FormationDao formationDao = new FormationDao(){};

    @FXML
    public void initialize() {
        List<Formation> formations = formationDao.getAll(new Formation());
        List<String> formationNames = formations.stream().map(Formation::getName).toList();
        formationComboBox.setItems(FXCollections.observableArrayList(formationNames));
    }

    @FXML
    private void handleSaveStudent() {
        String id = studentIdField.getText();
        String name = studentNameField.getText();
        String moyenneText = studentMoyenneField.getText();
        String selectedFormation = formationComboBox.getValue();

        if (id.isEmpty() || name.isEmpty() || moyenneText.isEmpty() || selectedFormation == null) {
            showAlert("Erreur", "Tous les champs sont obligatoires.");
            return;
        }

        try {
            float moyenne = Float.parseFloat(moyenneText);

            if (moyenne < 0 || moyenne > 20) {
                showAlert("Erreur", "La moyenne doit être comprise entre 0 et 20.");
                return;
            }

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setMoyenne(moyenne);

            Formation formation = formationDao.findByName(selectedFormation);
            if (formation == null) {
                showAlert("Erreur", "La formation sélectionnée est invalide.");
                return;
            }
            student.setFormation_id(formation.getId());
            studentDao.create(student);

            showAlert("Succès", "Étudiant enregistré avec succès !");
            clearForm();
            Stage stage = (Stage) studentIdField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer un nombre valide pour la moyenne.");
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur est survenue lors de l'enregistrement.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() throws IOException {
        clearForm();
    }

    private void clearForm() throws IOException {
        studentIdField.clear();
        studentNameField.clear();
        studentMoyenneField.clear();
        formationComboBox.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
