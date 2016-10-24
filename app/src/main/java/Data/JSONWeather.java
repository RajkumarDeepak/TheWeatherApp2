package Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Model.Place;
import Model.Weather;
import Util.Utils;

/**
 * Created by Raushan on 10/22/2016.
 */

public class JSONWeather {
    public static Weather getWeather(String data){
        Weather weather = new Weather();
        //create jsonObject from data
        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            JSONObject coordObj= Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat",coordObj));
            place.setLon(Utils.getFloat("lon",coordObj));

            //Get the sys object
            JSONObject sysObj= Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country",sysObj));
            place.setLastUpdate(Utils.getInt("dt",jsonObject));
            place.setSunrise(Utils.getInt("sunrise",sysObj));
            place.setSunset(Utils.getInt("sunset",sysObj));
            place.setCity(Utils.getString("city", jsonObject));
            weather.place=place;

            //Get weather report
            JSONArray jsonArray= jsonObject.getJSONArray(("weather"));
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id",jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description",jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            JSONObject windObj = Utils.getObject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));
            weather.wind.setDeg(Utils.getFloat("deg",windObj));

            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all",cloudObj));

            return weather;







        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

}
