package com.telegram.yogesh.Telegram_Bot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.methods.stickers.GetStickerSet;
import org.telegram.telegrambots.meta.api.methods.updates.GetWebhookInfo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSonReader {

    private String states = "Maharashtra - MH \n\n " + "Delhi - DL \n\n " + "Tamil Nadu - TN \n\n "
            + "Rajasthan - RJ \n\n " + "Madhya Pradesh - MP \n\n " + "Telangana - TG \n\n " + "Gujarat - GJ \n\n "
            + "Uttar Pradesh - UP \n\n " + "Andhra Pradesh - AP \n\n " + "Kerala - KL \n\n "
            + "Jammu and Kashmir - JK \n\n " + "Karnataka - KA " + "Haryana - HR\n\n" + "West Bengal - WB\n\n"
            + "Punjab - PB\n\n" + "Bihar - BR\n\n" + "Odisha - OR\n\n" + "Uttarakhand - UT\n\n"
            + "Himachal Pradesh - HP\n\n" + "Chhattisgarh - CT\n\n" + "Assam - AS\n\n" + "Jharkhand - JH\n\n"
            + "Chandigarh - CH\n\n" + "Ladakh - LA\n\n" + "Andaman and Nicobar Islands - AN\n\n" + "Goa - GA\n\n"
            + "Puducherry - PY\n\n" + "Manipur - MN\n\n" + "Tripura - TR\n\n" + "Mizoram - MZ\n\n"
            + "Arunachal Pradesh - AR\n\n" + "Dadra and Nagar Haveli - DN\n\n" + "Nagaland - NL\n\n"
            + "Meghalaya - ML\n\n" + "Daman and Diu - DD\n\n" + "Lakshadweep - LD\n\n" + "Sikkim - SK";

    public static void main(String[] args) throws IOException, ParseException {
        // TODO Auto-generated method stub
        //System.out.println(geStateData());

             //String str=getInputData().toString().substring(1, getInputData().toString().length()-1).replace("=", " - ");
             System.out.println(geState_codes());
    }
    
    public static String geState_codes() throws IOException, ParseException {
        OkHttpClient client = new OkHttpClient();
        TreeMap<String, String> state_codes=new TreeMap<>();
        
        Request request = new Request.Builder().url("https://api.covid19india.org/data.json").get().build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();

            // System.out.println(data);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);

            JSONArray array = (JSONArray) object.get("statewise");
            Iterator iterator = array.iterator();
            while (iterator.hasNext()) {
                JSONObject object12 = (JSONObject) iterator.next();
                
                state_codes.put((String)object12.get("state"), (String)object12.get("statecode"));
                
                
                
            }
        
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return state_codes.toString();
    }

    
    private void main_raw_data()
    {
        HashMap<String, String> state_data = new HashMap<>();

        state_data.put("Goa", "GA");
        state_data.put("Ladakh", "LA");
        state_data.put("Andaman and Nicobar Islands", "AN");
        state_data.put("Ladakh", "LA");
        state_data.put("Uttarakhand", "UT");
        state_data.put("Himachal Pradesh", "HP");
        state_data.put("Chhattisgarh", "CT");
        state_data.put("Assam", "AS");
        state_data.put("Jharkhand", "JH");
        state_data.put("Telangana", "TG");
        state_data.put("Gujarat", "GJ");
        state_data.put("Uttar Pradesh", "UP");
        state_data.put("Andhra Pradesh", "AP");
        state_data.put("Bihar", "BR");
        state_data.put("Odisha", "OR");
        state_data.put("Dadra and Nagar Haveli", "DN");
        state_data.put("Tamil Nadu", "TN");
        state_data.put("Arunachal Pradesh", "AR");
        state_data.put("Mizoram", "MZ");
        state_data.put("Tripura", "TR");
        state_data.put("Puducherry", "PY");
        state_data.put("Manipur", "MN");
        state_data.put("Maharashtra", "MH");
        state_data.put("Delhi", "DL");
        state_data.put("Rajasthan", "RJ");
        state_data.put("Madhya Pradesh", "MP");
        state_data.put("Kerala", "KL");
        state_data.put("Jammu and Kashmir", "JK");
        state_data.put("Karnataka", "KA");
        state_data.put("Haryana", "HR");
        state_data.put("West Bengal", "WB");
        state_data.put("Punjab", "PB");
        state_data.put("Meghalaya", "ML");
        state_data.put("Daman and Diu", "DD");
        state_data.put("Lakshadweep", "LD");
        state_data.put("Sikkim", "SK");
        state_data.put("Nagaland", "NL");
        
        Set<Entry<String, String>> set = state_data.entrySet();
        
        HashMap<String , String> map=new HashMap<>();
        int i=1;
      
        for (Map.Entry<String, String> map1 : set) 
        {
            map.put("\n"+map1.getKey(), map1.getValue());
            i++;
            
        }
        
        
        System.out.println(map.toString().replace("=", " - ").substring(1, map.toString().length()-1));
        
        //System.out.println(map.toString().substring(1, map.toString().length()-1).replace("=", " - "));

    }
    
    private  static HashMap<String, String> getInputData()
    {
        HashMap<String, String> state_data = new HashMap<>();

        state_data.put("Goa", "GA");
        state_data.put("Ladakh", "LA");
        state_data.put("Andaman and Nicobar Islands", "AN");
        state_data.put("Ladakh", "LA");
        state_data.put("Uttarakhand", "UT");
        state_data.put("Himachal Pradesh", "HP");
        state_data.put("Chhattisgarh", "CT");
        state_data.put("Assam", "AS");
        state_data.put("Jharkhand", "JH");
        state_data.put("Telangana", "TG");
        state_data.put("Gujarat", "GJ");
        state_data.put("Uttar Pradesh", "UP");
        state_data.put("Andhra Pradesh", "AP");
        state_data.put("Bihar", "BR");
        state_data.put("Odisha", "OR");
        state_data.put("Dadra and Nagar Haveli", "DN");
        state_data.put("Tamil Nadu", "TN");
        state_data.put("Arunachal Pradesh", "AR");
        state_data.put("Mizoram", "MZ");
        state_data.put("Tripura", "TR");
        state_data.put("Puducherry", "PY");
        state_data.put("Manipur", "MN");
        state_data.put("Maharashtra", "MH");
        state_data.put("Delhi", "DL");
        state_data.put("Rajasthan", "RJ");
        state_data.put("Madhya Pradesh", "MP");
        state_data.put("Kerala", "KL");
        state_data.put("Jammu and Kashmir", "JK");
        state_data.put("Karnataka", "KA");
        state_data.put("Haryana", "HR");
        state_data.put("West Bengal", "WB");
        state_data.put("Punjab", "PB");
        state_data.put("Meghalaya", "ML");
        state_data.put("Daman and Diu", "DD");
        state_data.put("Lakshadweep", "LD");
        state_data.put("Sikkim", "SK");
        state_data.put("Nagaland", "NL");
        
        Set<Entry<String, String>> set = state_data.entrySet();
        
        HashMap<String , String> map=new HashMap<>();
       
        for (Map.Entry<String, String> map1 : set) 
        {
            map.put("\n\n"+(String)map1.getKey(), map1.getValue()+" ");
          
            
        }
        
        
         return map;
        
        //System.out.println(map.toString().substring(1, map.toString().length()-1).replace("=", " - "));

    }
    
    public static void call() throws IOException, ParseException
    {
        String states = "Maharashtra - MH" + "%0ADelhi - DL" + "%0ATamil Nadu - TN" + "%0ARajasthan - RJ"
                + "%0AMadhya Pradesh - MP" + "%0ATelangana - TG" + "\nGujarat - GJ" + "\nUttar Pradesh - UP"
                + "\nAndhra Pradesh - AP" + "%0AKerala - KL" + "\nJammu and Kashmir - JK" + "\nKarnataka - KA"
                + "\nHaryana - HR" + "%0AWest Bengal - WB" + "\nPunjab - PB" + "\nBihar - BR" + "\nOdisha - OR"
                + "\nUttarakhand - UT" + "\nHimachal Pradesh - HP" + "\nChhattisgarh - CT" + "\nAssam - AS"
                + "\nJharkhand - JH" + "\nChandigarh - CH" + "\nLadakh - LA" + "\nAndaman and Nicobar Islands - AN"
                + "\nGoa - GA" + "\nPuducherry - PY" + "\nManipur - MN" + "\nTripura - TR" + "\nMizoram - MZ"
                + "\nArunachal Pradesh - AR" + "\nDadra and Nagar Haveli - DN" + "\nNagaland - NL" + "\nMeghalaya - ML"
                + "\nDaman and Diu - DD" + "\nLakshadweep - LD" + "\nSikkim - SK";
        //System.out.println(states);
        String str=geDistrictData("Kerala").toString();
        String substr=str.substring(1, str.length()-1).replace("=", " - ");
        System.out.println(substr);

    }
    
    public static String geStateData() throws IOException, ParseException 
    {
        String data1 = null;
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
  .url("https://api.covid19india.org/data.json")
            .get()
            .build();

       
        
        try (Response response = client.newCall(request).execute()) {
            String data=response.body().string();
            
            //System.out.println(data);
            JSONParser parser=new JSONParser();
            JSONObject object=(JSONObject)parser.parse(data);
            
            JSONArray array=(JSONArray)object.get("statewise");
            Iterator iterator=array.iterator();
            
            while(iterator.hasNext())
            {
                JSONObject object12=(JSONObject)iterator.next();
                if(object12.get("statecode").equals("MH"))
                {
                    data1="Active :"+ object12.get("active")+" Confirmed :"+object12.get("confirmed")
                   +" Deaths :"+object12.get("deaths")+" Recovered :"+object12.get("recovered");
                   break;
                    
                }
            }
               
        }catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Data1 is "+data1);
        return data1;
    }
    
    private static HashMap<String, Long> geDistrictData(String stateCode) throws IOException, ParseException {
        HashMap<String, Long> map = new HashMap<>();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.covid19india.org/state_district_wise.json").get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String data = response.body().string();

            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(data);

            JSONObject state = (JSONObject) object.get(stateCode);
            JSONObject districtData = (JSONObject) state.get("districtData");
            districtData.keySet().forEach(key -> {
                // System.out.println(" Key : "+key);
                JSONObject obj = ((JSONObject) districtData.get(key));
                map.put("\n" + (String) key, (Long) obj.get("confirmed"));
            });

           // System.out.println(map);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
      return map;
    }
    

    // URL to check on browser
    // https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/getme

    // to check updates
    // https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/getupdates
    // will store messages sent by bot

    // https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/sendLocation?chat_id=222792018&latitude=18.5204&longitude=73.8567

    /*
     * System.out.println("inside location");
     * System.out.println("Lattitude :"+update.getMessage().getLocation().
     * getLatitude()+"\n Longitude:"+update.getMessage().getLocation().getLongitude(
     * ));
     * message.setText("Lattitude :"+update.getMessage().getLocation().getLatitude()
     * +"\n Longitude:"+update.getMessage().getLocation().getLongitude());
     */

    // My chat id is : 222792018

    // https://api.telegram.org/bot1240568404:AAEFb-Vy2dt-Wt1anRELKCm5R_H1rerhMaI/sendMessage?chat_id=222792018&parse_mode=HTML&text=


}
