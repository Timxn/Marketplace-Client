package de.oose.webservice.client.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.oose.webservice.client.Main;
import de.oose.webservice.client.RestAPIClient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class MenuC {
    long selectedBuyingProductOwnedId = 0;
    long selectedSellingProductId = 0;
    private ArrayList<String> prices;
    private ArrayList<String> names;
    private ArrayList<String> depotPrices;
    private ArrayList<String> depotNames;


    @FXML
    private ListView offer_List, inventory_list;
    @FXML
    private Label acc_money;

    @FXML
    private TableColumn<?, ?> available_markt;

    @FXML
    private Spinner<?> counter;

    @FXML
    private TextField counter_2;

    @FXML
    private TableColumn<?, ?> in_stock_inventory;

    @FXML
    private AnchorPane into_inventory1;

    @FXML
    private Button into_inventory2;

    @FXML
    private Button log_out;

    @FXML
    private Tab photo_type1;

    @FXML
    private ChoiceBox<?> photo_type2;

    @FXML
    private TextField photo_type_2;

    @FXML
    private TableColumn<?, ?> photo_type_Inventory;

    @FXML
    private TableColumn<?, ?> photo_type_markt;

    @FXML
    private Label price;

    @FXML
    private TableColumn<?, ?> price_markt;

    @FXML
    private Button sell;
    @FXML
    TextField numberToBuy, newProductName, newProductCount, amount_money;

    @FXML
    void logOutSystem() {
        RestAPIClient.resetToken();
        Stage stage = (Stage) log_out.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Anmeldung.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 960, 540);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public void delete_acc() {
        if (RestAPIClient.deleteUser()) {
            Stage stage = (Stage) log_out.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Anmeldung.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 960, 540);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene);
            stage.show();
        }
    }

    public void initialize (){
        offer_List.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int tmp = offer_List.getSelectionModel().getSelectedIndex();
                price.setText(prices.get(tmp));
            }
        });

        inventory_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String[] arr = newValue.toString().split(":");
                photo_type_2.setText(arr[0]);
            }
        });
        setBalance();
        setTableValues();
        setInventory();

    }
     private void setBalance(){
        acc_money.setText(String.valueOf(RestAPIClient.getBalance()));
     }


    public void setTableValues(){
        prices = new ArrayList<>();
        names = new ArrayList<>();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        JsonObject tmp = RestAPIClient.getProducts();
        for (Map.Entry<String, JsonElement> att: tmp.entrySet()) {
            JsonObject tmpObject = tmp.get(att.getKey()).getAsJsonObject();
            observableList.add(att.getKey() + ": " + tmpObject.get("count") + " x " + tmpObject.get("price"));
            prices.add(tmpObject.get("price").toString());
            names.add(att.getKey());
        }
        offer_List.setItems(observableList);
    }

    public void setInventory(){
        depotNames = new ArrayList<>();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        JsonObject tmp = RestAPIClient.getDepot();
        for (Map.Entry<String, JsonElement> att: tmp.entrySet()) {
            JsonObject tmpObject = tmp.get(att.getKey()).getAsJsonObject();
            observableList.add(att.getKey() + ": " + tmpObject.get("count"));
            depotNames.add(att.getKey());
        }
        if (!observableList.isEmpty()) inventory_list.setItems(observableList);
    }

    public void buy_item(ActionEvent actionevent) {
        String name = offer_List.getSelectionModel().toString();
        RestAPIClient.buy(names.get(offer_List.getSelectionModel().getSelectedIndex()), Integer.valueOf(numberToBuy.getText()));
        setTableValues();
        setBalance();
        setInventory();
    }

    public void sell() {
        if (inventory_list.getSelectionModel().getSelectedIndex() >= 0) {
            RestAPIClient.sell(depotNames.get(inventory_list.getSelectionModel().getSelectedIndex()), Integer.parseInt(counter_2.getText()));
            setTableValues();
            setBalance();
            setInventory();
        }
    }
    public void addProduktToMarket() {
        RestAPIClient.addProductToMarket(newProductName.getText(), Integer.parseInt(newProductCount.getText()));
        setTableValues();
        setBalance();
        setInventory();
        newProductName.setText("");
        newProductCount.setText("");
     }

     public void einzahlen() {
        RestAPIClient.setMoney(Double.parseDouble(amount_money.getText()));
        setBalance();
     }
}



