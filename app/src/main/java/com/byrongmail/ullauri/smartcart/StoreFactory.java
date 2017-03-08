package com.byrongmail.ullauri.smartcart;

/**
 * Created by ullauri on 10/2/16.
 */

public final class StoreFactory {
    private static String name, url, mainColor, subColor, APIKEY;

    private StoreFactory() {
    }

    public static void createStore(String store) {
        switch (store) {
            case "best buy":
                APIKEY = "";
                name = "Best Buy";
                url = "https://api.bestbuy.com/v1/products(upc=UPCX)?" +
                        "apiKey=" + APIKEY +
                        "&sort=name.asc&show=name,salePrice&pageSize=1" +
                        "&callback=JSON_CALLBACK&format=json";
                mainColor = "#FEF100";
                subColor = "#003b64";
                break;
            default:

                break;
        }
    }

    public static String getUrl(String upc) {
        return url.replace("UPCX", upc);
    }

    public static String getName() {
        return name;
    }

    public static String getMainColor() {
        return mainColor;
    }


}
