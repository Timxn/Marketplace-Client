package de.oose.webservice.client;

import java.io.IOException;
import java.util.UUID;

import okhttp3.*;
import com.google.gson.*;

public class RestAPIClient {
    private static UUID token = null;

    public static void resetToken() {
        RestAPIClient.token = null;
    }

    private static final String url = "http://localhost:4567";
    private static OkHttpClient client = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");

    public static boolean register(String username, String password) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("mail", username);
        requestJson.addProperty("password", password);
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/user/register")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 201) return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public static boolean login(String username, String password) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("mail", username);
        requestJson.addProperty("password", password);
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/user/login")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 201) {
                    JsonObject requestJSON = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    token = UUID.fromString(String.valueOf(requestJSON.get("token")).replace("\"",""));
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public static double getBalance() {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/user/balance")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JsonObject requestJSON = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    double tmp= Double.valueOf(String.valueOf(requestJSON.get("balance")).replace("\"",""));
                    return tmp;
                }
            }
        } catch (IOException e) {
        }
        return 0;
    }

    public static void setMoney(double money) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("token", String.valueOf(token));
        requestJson.addProperty("value", money);
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/user/addMoney")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                }
            }
        } catch (IOException e) {
        }
    }

    public static boolean deleteUser() {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/user/removeUser")
                    .delete(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    token = null;
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }

    public static JsonObject getProducts() {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/market/products")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JsonObject requestJSON = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    return requestJSON;
                }
            }
        } catch (IOException e) {
        }
        return null;
    }

    public static JsonObject getDepot() {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/user/depot")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 200) {
                    JsonObject requestJSON = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    return requestJSON;
                }
            }
        } catch (IOException e) {
        }
        return null;
    }

    public static void buy(String productname, int count) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("productname", productname);
        requestJson.addProperty("count", count);
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/market/buy")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
            }
        } catch (IOException e) {
        }
    }

    public static void addProductToMarket(String productname, int count) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("productname", productname);
        requestJson.addProperty("count", count);
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/market/addProductToMarket")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
            }
        } catch (IOException e) {
        }
    }

    public static void sell(String productname, int count) {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("productname", productname);
        requestJson.addProperty("count", count);
        requestJson.addProperty("token", String.valueOf(token));
        try {
            Request request = new Request.Builder()
                    .url("http://localhost:4567/market/sell")
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestJson.toString()))
                    .build();

            try (Response response = client.newCall(request).execute()) {
            }
        } catch (IOException e) {
        }
    }



    private static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            return response.body().string();
        }
    }

    private static String post(String url, String requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private static int delete(String url, String requestBody) {
        Request request = new Request.Builder()
                .url(url)
                .delete(RequestBody.create(MEDIA_TYPE_MARKDOWN, requestBody))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.code();
        } catch (IOException e) {
            return 404;
        }
    }

}
