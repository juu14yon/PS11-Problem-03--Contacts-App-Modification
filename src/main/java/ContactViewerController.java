import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ContactViewerController {
    @FXML private ListView<Contact> ContactListView ;
    @FXML private ImageView imageView;
    @FXML private TextField fnameField;
    @FXML private TextField lnameField;
    @FXML private TextField emailField;
    @FXML private TextField numberField;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private int currentPageIndex = 0;
    Comparator<Contact> comparator = Comparator.comparing(Contact::getLastName);

    public void initialize() {
        saveButton.setDisable(true);
        deleteButton.setDisable(true);

        contacts.add(new Contact("Harris",
                "/images/small/Harris.jpg", "/images/large/Harris.jpg", "Victor", "VictorDHarris@teleworm.us", "413-384-7494"));
        contacts.add(new Contact("Threadgill",
                "/images/small/Threadgill.jpg", "/images/large/Threadgill.jpg", "Richard", "RichThreadgill@gmail.com", "260-708-0372"));
        contacts.add(new Contact("Williams",
                "/images/small/Williams.jpg", "/images/large/Williams.jpg", "Shelli", "ShelliCWilliams@dayrep.com", "858-998-0262"));
        contacts.add(new Contact("Bland",
                "/images/small/Bland.jpg", "/images/large/Bland.jpg", "Fred", "FredRBland@dayrep.com", "313-247-9565"));

        contacts.sort(comparator);
        ContactListView.setItems(contacts);

        ContactListView.getSelectionModel().selectedItemProperty().
                addListener(
                        new ChangeListener<Contact>() {
                            @Override
                            public void changed(ObservableValue<? extends Contact> ov, Contact oldValue, Contact newValue) {
                                saveButton.setDisable(false);
                                deleteButton.setDisable(false);

                                imageView.setImage(new Image(newValue.getLargeImage()));
                                lnameField.setText(newValue.getLastName());
                                fnameField.setText(newValue.getFirstName());
                                emailField.setText(newValue.getEmail());
                                numberField.setText(newValue.getNumber());

                                currentPageIndex = contacts.indexOf(newValue);
                            }
                        }
                );

      ContactListView.setCellFactory(
         new Callback<ListView<Contact>, ListCell<Contact>>() {
            @Override
            public ListCell<Contact> call(ListView<Contact> listView) {
               return new ImageTextCell();
            }
         }
      );   
   }
    @FXML
    void saveButtonPressed(ActionEvent event) {
        contacts.get(currentPageIndex).setLastName(lnameField.getText());
        contacts.get(currentPageIndex).setFirstName(fnameField.getText());
        contacts.get(currentPageIndex).setEmail(emailField.getText());
        contacts.get(currentPageIndex).setNumber(numberField.getText());

    }

    @FXML
    void deleteButtonPressed(ActionEvent event) {
        contacts.remove(currentPageIndex);
    }

    @FXML
    void addButtonPressed(ActionEvent event){
        Stage stage = new Stage();

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(4));
        grid.setHgap(8);
        grid.setVgap(4);

        ArrayList<TextField> fields = new ArrayList<>();

        Label lastName = new Label("Last name:");
        Label firstName = new Label("First name:");
        Label email = new Label("Email: ");
        Label number = new Label("Number: ");

        Button save = new Button("Save");

        grid.getChildren().clear();
        grid.add(lastName, 0, 0);
        grid.add(firstName, 0, 1);
        grid.add(email, 0, 2);
        grid.add(number, 0, 3);

        for (int i = 0; i < 4; i++) {
            TextField temp = new TextField();
            fields.add(temp);
            grid.add(temp, 1, i);
        }

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                contacts.add(new Contact(fields.get(0).getText(), "/images/small/Sample_User_Icon.png",
                        "/images/large/Sample_User_Icon.png", fields.get(1).getText(), fields.get(2).getText(), fields.get(3).getText()));
                stage.close();
            }
        });
        box.getChildren().addAll(grid, save);
        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    void updateButtonPressed(ActionEvent event){
        contacts.sort(comparator);
        ContactListView.setItems(contacts);

    }

}
