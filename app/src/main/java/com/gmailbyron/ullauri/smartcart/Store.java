package com.gmailbyron.ullauri.smartcart;

public enum Store {
    INSTANCE;

    private String name, url, mainColor, subColor, APIKEY;

    public void setStore(String store) {
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
        }
    }

    public String getUrl(String upc) {
        return url.replace("UPCX", upc);
    }

    public String getName() {
        return name;
    }

    public String getMainColor() {
        return mainColor;
    }

    public String getSubColor() {
        return subColor;
    }
}
