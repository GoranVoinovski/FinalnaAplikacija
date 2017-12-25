package com.mkdingo.goran.finalnaaplikacija.manager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mkdingo.goran.finalnaaplikacija.models.RestoraniModel;

/**
 * Created by goran on 18.12.17.
 */

public class RestoranPreferences {

    private static SharedPreferences getPreferences(Context c){
        return c.getApplicationContext().getSharedPreferences("MyRestorants", Activity.MODE_PRIVATE);
    }

    public static void addRestoran(RestoraniModel model, Context c){

        Gson gson = new Gson();
        String mapString = gson.toJson(model);
        getPreferences(c).edit().putString("Restorani", mapString).apply();


    }

    public static RestoraniModel getRestoran(Context c){

    return new Gson().fromJson(getPreferences(c).getString("Restorani", restaurants), RestoraniModel.class);
    }




    static String restaurants = "{\"restaurants\": [\n" +
            "  {\n" +
            "    \"logo\": \"https://en.opensuse.org/images/4/49/Amarok-logo-small.png\",\n" +
            "    \"city\": \"Skopje\",\n" +
            "    \"web\": \"https://en.opensuse.org\",\n" +
            "    \"name\": \"Vijayawada\",\n" +
            "    \"rating\": \"2.5\",\n" +
            "    \"menu\": [\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"90\",\n" +
            "        \"foodname\": \"Cream Of Tomato Soup\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"90\",\n" +
            "        \"foodname\": \"Veg Clear Soup\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"90\",\n" +
            "        \"foodname\": \"Veg Hot &amp; Sour Soup\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"90\",\n" +
            "        \"foodname\": \"Veg Manchow Soup\",\n" +
            "        \"isveg\": true\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"logo\": \"http://skopskimerak.mk/mk/img/cali-logo.png\",\n" +
            "    \"city\": \"Skopje\",\n" +
            "    \"web\": \"http://skopskimerak.mk\",\n" +
            "    \"name\": \"Skopski Merak\",\n" +
            "    \"rating\": \"3.5\",\n" +
            "    \"menu\": [\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"190\",\n" +
            "        \"foodname\": \"Пастрмајлија Свинска\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"180\",\n" +
            "        \"foodname\": \"Пастрмајлија Пилешка\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"220\",\n" +
            "        \"foodname\": \"Пастрмајлија Вегетерјанска\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"250\",\n" +
            "        \"foodname\": \"Телешки џигер\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/14.jpg\",\n" +
            "        \"price\": \"330\",\n" +
            "        \"foodname\": \"Турли Тава\",\n" +
            "        \"isveg\": false\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "{\n" +
            "    \"logo\": \"http://bravos.mk/img/bc-logos.png\",\n" +
            "    \"city\": \"Skopje\",\n" +
            "    \"web\": \"http://bravos.mk\",\n" +
            "    \"name\": \"Бравос\",\n" +
            "    \"rating\": \"4.5\",\n" +
            "    \"menu\": [\n" +
            "      {\n" +
            "        \"link\": \"http://bravos.mk/img/wel-2.png\",\n" +
            "        \"price\": \"200\",\n" +
            "        \"foodname\": \"Капричиоза Пица\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://bravos.mk/img/wel-2.png\",\n" +
            "        \"price\": \"210\",\n" +
            "        \"foodname\": \"Вегетерјанска Пица\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://bravos.mk/img/wel-2.png\",\n" +
            "        \"price\": \"210\",\n" +
            "        \"foodname\": \"Кулен Пица\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/27.jpg\",\n" +
            "        \"price\": \"160\",\n" +
            "        \"foodname\": \"Цезар Салата\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://skopskimerak.mk/mk/img/29.jpg\",\n" +
            "        \"price\": \"190\",\n" +
            "        \"foodname\": \"Туна Салата\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://bravos.mk/img/bravos-flyer-min.jpg\",\n" +
            "        \"price\": \"150\",\n" +
            "        \"foodname\": \"Сендвич Со Бечка\",\n" +
            "        \"isveg\": false\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "{\n" +
            "    \"logo\": \"http://bravos.mk/img/bc-logos.png\",\n" +
            "    \"city\": \"Skopje\",\n" +
            "    \"web\": \"http://www.kineskirestoran.com.mk\",\n" +
            "    \"name\": \"Sвезден Океан\",\n" +
            "    \"rating\": \"4.1\",\n" +
            "    \"menu\": [\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/22_23.JPG\",\n" +
            "        \"price\": \"220\",\n" +
            "        \"foodname\": \"Кинеска Салата\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/141_2005.png\",\n" +
            "        \"price\": \"100\",\n" +
            "        \"foodname\": \"Лута Салата со Зелка\",\n" +
            "        \"isveg\": true\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/40_30.JPG\",\n" +
            "        \"price\": \"230\",\n" +
            "        \"foodname\": \"Пржени Пилешки Крилца\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/49_%D0%A1%D0%B2%D0%B8%D0%BD%D1%81%D0%BA%D0%BE%20%D0%B2%D0%BE%20%D0%B3%D1%80%D0%BD%D0%B5%20_svinsko-vo-grne.png\",\n" +
            "        \"price\": \"390\",\n" +
            "        \"foodname\": \"Свинско Во Грне\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/98_81.JPG\",\n" +
            "        \"price\": \"170\",\n" +
            "        \"foodname\": \"Шпагети со ракчиња\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/99_80.JPG\",\n" +
            "        \"price\": \"230\",\n" +
            "        \"foodname\": \"Нудли со Пилешко\",\n" +
            "        \"isveg\": false\n" +
            "      },\n" +
            "      {\n" +
            "        \"link\": \"http://www.kineskirestoran.com.mk/images/menu/69_112.JPG\",\n" +
            "        \"price\": \"170\",\n" +
            "        \"foodname\": \"Ориз со Ракчиња\",\n" +
            "        \"isveg\": false\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "]}";

}
