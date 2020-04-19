package com.telegram.yogesh.Telegram_Bot;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.objects.Message;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Telegram_API {

    private  OkHttpClient client = new OkHttpClient();

    public  void getLocation(Message  message, String chatId) throws IOException, ParseException {

        getLocation1(message, chatId);
        Float latitude=message.getLocation().getLatitude();
        Float longitutude=message.getLocation().getLongitude();
        
        
        System.out.println("latiitude"+latitude);

        System.out.println("Longitude"+longitutude);
        Request request = new Request.Builder()
                .url("https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/sendLocation?chat_id="
                        + chatId + "&latitude="+latitude+"&longitude="+longitutude)
                .get().build();
        
        System.out.println(request.toString());

        try (Response response = client.newCall(request).execute()) {

        }
    }
    
    public  void getLocation1(Message  message, String chatId) throws IOException, ParseException {

        System.out.println("inside getLocation1");
        String latitude=String.valueOf(message.getLocation().getLatitude());
       System.out.println("Lattitude"+latitude);
       
        
        String longitutude=String.valueOf(message.getLocation().getLongitude());
       
        System.out.println("Longitude"+longitutude);
        
        Request request = new Request.Builder()
                .url("https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/sendLocation?chat_id="
                        + chatId + "&latitude="+latitude+"&longitude="+longitutude)
                .get().build();
        
        System.out.println(request.toString());

        try (Response response = client.newCall(request).execute()) {

        }
    }


    public  void sendMessage(String chatId, String text) throws IOException, ParseException {

        Request request = new Request.Builder()
                .url("https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/sendMessage?"
                        + "chat_id=" + chatId + "&text=" + text)
                .get().build();

        try (Response response = client.newCall(request).execute()) {
        }
    }

    public void getDiceValue(String chatId) throws IOException, ParseException {

        Request request = new Request.Builder()
                .url("https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/sendDice?" + "chat_id="
                        + chatId)
                .get().build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);
            JSONObject result = (JSONObject) object.get("result");
            JSONObject dice = (JSONObject) result.get("dice");
            String dicevalue = dice.get("value").toString();
        }
    }

    public  TreeMap<String, Long> geDistrictData(String stateCode) throws IOException, ParseException {
        TreeMap<String, Long> map = new TreeMap<>();

        Request request = new Request.Builder().url("https://api.covid19india.org/state_district_wise.json").get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);

            JSONObject state = (JSONObject) object.get(stateCode);
            if(state!=null)
            {
                JSONObject districtData = (JSONObject) state.get("districtData");
                districtData.keySet().forEach(key -> {
                    JSONObject obj = ((JSONObject) districtData.get(key));
                    map.put("\n" + (String) key, (Long) obj.get("confirmed"));
                });
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return map;
    }

    public  String geStateData(String stateCode , boolean isCountry) throws IOException, ParseException {
        String data1 = null;
        

        Request request = new Request.Builder().url("https://api.covid19india.org/data.json").get().build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();

            // System.out.println(data);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);

            JSONArray array = (JSONArray) object.get("statewise");
            Iterator iterator = array.iterator();

            if(isCountry)
            {
                JSONObject object12 = (JSONObject) iterator.next();
                
                if (object12.get("statecode").equals(stateCode)) {
                    data1 = "\n<b>Confirmed : </b>  "+ object12.get("confirmed") + " ,\n<b>Active : </b> "  + object12.get("active") +" ,\n<b>Recovered : </b>"+ object12.get("recovered")
                            + ",\n<b>Deaths : </b> " + object12.get("deaths");
                    
                }
             
            }
            else
            {
            
            while (iterator.hasNext()) {
                JSONObject object12 = (JSONObject) iterator.next();
                
                if (object12.get("statecode").equals(stateCode)) {
                    data1 = "\n<b>Confirmed : </b>  "+ object12.get("confirmed") + " ,\n<b>Active : </b> "  + object12.get("active") +" ,\n<b>Recovered : </b>"+ object12.get("recovered")
                            + ",\n<b>Deaths : </b> " + object12.get("deaths");
                    break;
                }

            }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return data1;
    }

    public  TreeMap<String, String> getInputData() {

        Set<Entry<String, String>> set = null;
        try {
            set = geState_codes().entrySet();
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        TreeMap<String, String> map = new TreeMap<>();

        for (Map.Entry<String, String> map1 : set) {
            map.put("\n" + (String) map1.getKey(), map1.getValue());

        }

        return map;
    } 
    
    public  TreeMap<String, String> geState_codes() throws IOException, ParseException {
        TreeMap<String, String> state_codes=new TreeMap<>();
        Request request = new Request.Builder().url("https://api.covid19india.org/data.json").get().build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);

            JSONArray array = (JSONArray) object.get("statewise");
            Iterator<?> iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject object12 = (JSONObject) iterator.next();
                
                state_codes.put((String)object12.get("state"), (String)object12.get("statecode"));
                
            }
            state_codes.remove("Total");
        
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return state_codes;
}
}