package javaController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;

public class Controller implements Initializable {
    Dictionary list = new Dictionary();
    @FXML
    private TextArea Word_explain;

    @FXML
    private ListView<String> Word_list;

    @FXML
    private TextField Word_target;

    @FXML
    private Label labels;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list.insertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Word_list.getItems().addAll(list.wordtarget);
        Word_list.setOnMouseClicked(e -> {
            labels.setText(Word_list.getSelectionModel().getSelectedItem());
            int selectedID = list.posittion_Word(list.wordtarget,Word_list.getSelectionModel().getSelectedItem());
            Word_explain.setText(list.wordmeaning.get(selectedID));
        });
    }

    public void searchword(MouseEvent mouseEvent) {
        labels.setText("");
        Word_explain.setText("");
        Word_list.getItems().clear();
        Word_list.getItems().addAll(list.WordSearch(Word_target.getText(),list.wordtarget));
    }

    @FXML
    void speak(MouseEvent event) {
       String word =Word_list.getSelectionModel().getSelectedItem();
       if(word != null) {
           list.listen(word);
       }
    }

    public void switchscene(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
        Parent viewadd = loader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.setTitle("AddWord");
        addcontroller addaction = loader.getController();
        stage.setScene(new Scene(viewadd));
        stage.show();
        addaction.ads.setOnAction(ActionEvent -> {
            if(addaction.add_word.getLength() != 0 && addaction.add_meaning.getLength() != 0) {
                Word_list.getItems().add(addaction.add_word.getText());
                list.wordtarget.add(addaction.add_word.getText());
                list.wordmeaning.add(addaction.add_meaning.getText());
                stage.close();
            } else {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(AlertType.WARNING);
                a.setContentText("Word_target and Word_meaning must be inserted !!!!");
                a.show();
            }
        });
    }
    @FXML
    void switchscene2(ActionEvent event) throws IOException {
        int selectedID = Word_list.getSelectionModel().getSelectedIndex();
        if(selectedID < 0) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Forget choosing word to delete !!!!");
            a.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/delete.fxml"));
            Parent viewdelete = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("DeleteWord");
            DeleteControll deleteaction = loader.getController();
            stage.setScene(new Scene(viewdelete));
            stage.show();
            deleteaction.choice1.setOnAction(ActionEvent -> {
                labels.setText("");
                Word_explain.setText("");
                Word_list.getItems().remove(selectedID);
                stage.close();
            });
            deleteaction.choice2.setOnAction(ActionEvent -> stage.close());
        }
    }
    @FXML
    void switchscene3(ActionEvent event) throws IOException {
        int selectedID = Word_list.getSelectionModel().getSelectedIndex();
        if (selectedID < 0) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Forgot choosing word to edit !!!!");
            a.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Edit.fxml"));
            Parent viewedit = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("EditWord");
            EditControll editaction = loader.getController();
            stage.setScene(new Scene(viewedit));
            stage.show();
            editaction.confirm_fix.setOnAction(ActionEvent -> {
                labels.setText("");
                Word_explain.setText("");
                int post = list.posittion_Word(list.wordtarget, Word_list.getSelectionModel().getSelectedItem());
                if (editaction.fix_word.getLength() == 0 && editaction.fix_meaning.getLength() == 0) {
                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(AlertType.WARNING);
                    a.setContentText("Forgot setting information !!!");
                    a.show();
                } else {
                    if (editaction.fix_word.getLength() != 0) {
                        Word_list.getItems().remove(selectedID);
                        Word_list.getItems().add(selectedID, editaction.fix_word.getText());
                        list.editword(list.wordtarget, post, editaction.fix_word.getText());
                    }
                    if (editaction.fix_meaning.getLength() != 0) {
                        list.editword(list.wordmeaning, post, editaction.fix_meaning.getText());
                    }
                    stage.close();
                }
            });
        }
    }
}
