package de.oose.webservice.client;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestAPIClientTest {

    @Test
    @DisplayName("register")
    void register() {
        assertTrue(RestAPIClient.register("testRegister","test"));
        assertFalse(RestAPIClient.register("testRegister","test"));
        RestAPIClient.login("testRegister","test");
        RestAPIClient.deleteUser();
    }

    @Test
    void login() {
        assertTrue(RestAPIClient.login("timon","123"));
        assertFalse(RestAPIClient.login("test","testqfefafs"));
    }

    @Test
    void getBalance() {
        RestAPIClient.register("balanceTest", "affasfafs");
        RestAPIClient.login("balanceTest", "affasfafs");
        assertEquals(100, RestAPIClient.getBalance());
        RestAPIClient.deleteUser();
    }

    @Test
    void setMoney() {
        RestAPIClient.register("setMoneyTest", "affasfafs");
        RestAPIClient.login("setMoneyTest", "affasfafs");
        RestAPIClient.setMoney(300);
        assertEquals(300, RestAPIClient.getBalance());
        RestAPIClient.deleteUser();
    }

    @Test
    void deleteUser() {
        RestAPIClient.register("deleteUserTest", "test");
        assertTrue(RestAPIClient.login("deleteUserTest", "test"));
        RestAPIClient.deleteUser();
        assertFalse(RestAPIClient.login("deleteUserTest", "test"));
    }

    @Test
    void getProducts() {
        RestAPIClient.register("getProductsTest", "affasfafs");
        RestAPIClient.login("getProductsTest", "affasfafs");
        assertNotNull(RestAPIClient.getProducts());
        RestAPIClient.deleteUser();
    }

    @Test
    void getDepot() {
        RestAPIClient.register("getDepotTest", "affasfafs");
        RestAPIClient.login("getDepotTest", "affasfafs");
        assertNotNull(RestAPIClient.getDepot());
        RestAPIClient.deleteUser();
    }

    @Test
    void buy() {
        RestAPIClient.register("getBuyTest", "affasfafs");
        RestAPIClient.login("getBuyTest", "affasfafs");
        RestAPIClient.addProductToMarket("test", 20);
        RestAPIClient.buy("test", 1);
        JsonObject depot = RestAPIClient.getDepot();
        int tmp = depot.get("test").getAsJsonObject().get("count").getAsInt();
        assertEquals(1,tmp);
        RestAPIClient.sell("test", 1);
        RestAPIClient.deleteUser();
    }

    @Test
    void sell() {
        RestAPIClient.register("getSellTest", "affasfafs");
        RestAPIClient.login("getSellTest", "affasfafs");
        RestAPIClient.addProductToMarket("test2", 20);
        RestAPIClient.buy("test2", 2);
        RestAPIClient.sell("test2", 1);
        JsonObject depot = RestAPIClient.getDepot();
        int tmp = depot.get("test2").getAsJsonObject().get("count").getAsInt();
        assertEquals(1,tmp);
        RestAPIClient.sell("test2", 1);
        RestAPIClient.deleteUser();
    }
}