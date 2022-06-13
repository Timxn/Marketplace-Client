package de.oose.webservice.client.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MenuC {
    long selectedBuyingProductOwnedId = 0;
    long selectedSellingProductId = 0;

    @FXML
    private Label acc_money;

    @FXML
    private TableColumn<?, ?> available_markt;

    @FXML
    private Spinner<?> counter;

    @FXML
    private Spinner<?> counter_2;

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
    private ChoiceBox<?> photo_type_2;

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
    void logOutSystem(ActionEvent event) {

    }

    @FXML
    void sellToMarket(ActionEvent event) {

    }

    public void initialize (){
        //Setting the price
        //Load Data into GUI
    }
    /*
     * private void setBalance(){
     * //get balance
     * //TODO Justus: HTTP request
     * if (error){send error message in error Label}
     * else { //change name balanceLabel.setText(sender[1].toString()+"£");} */

//TODO Justus: public void setTableValues(){
// get all items that are being sold
// get information from every sold product and add to table
//Define which value the Colums store //Bonnie
// add values to table //Bonnie},

    public void setInventory(){
        //get all items the user has
//        Object[] sender = ...;
        //TODO Justus
//        String offers = sender[1].toString();
//        JSONArray productsOwned = new JSONArray(offers);
        counter.setDisable(true);
        counter.setEditable(false);

        //add all user products to product button
        //TODO Justus mit Json ig
        //TODO buttons
        //set max sellable Amount for spinner
        //disable amount input if user has no products

    }

    public void itemClicked(MouseEvent mouseEvent){

    }

    public void buyPressed(ActionEvent actionevent) {
        //...
        //TODO Justus send buy request (product ID and amount)

    }

    public void sellPressed () {
            //..
            // TODO Justus send sell request
            // reset GUI
     }


//do profile actions
}



