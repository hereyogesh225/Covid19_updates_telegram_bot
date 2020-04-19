package com.telegram.yogesh.Telegram_Bot;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Update_Message {

    private  String WRONG_MESSAGE = "<b><i>Oops !! You have Entered Wrong Code !! Please Enter Correct Code !!</i></b>";
    private  String WRONG_COMMAND = "<b><i>Looks Like You have Entered Wrong Command !! Please Check your Command !!</i></b>";
    private  String appendMessage = "<b>Please Enter Correct <i>State Code</i> to check  State Data : </b>\n";
    private  String lines = "=============================\n";
    private  String caption = lines + "<strong>State         -       State Code</strong>\n";
   

    private  String district_message = 
             "<b><code>District   - Confirmed Cases </code></b>\n";


    public SendMessage sendMessage(Update update) {
        
        Telegram_API api = new Telegram_API();
        SendMessage message = new SendMessage();
        message.setParseMode(ParseMode.HTML);
        message.setChatId(update.getMessage().getChatId());

        String command = update.getMessage().getText();

        if (command.equals("/start")) {
            String firstname = update.getMessage().getFrom().getFirstName();

            message.setText("\nHi " + firstname + " , " + Telegram_Message.welcomeMessage);

        }
        
        else if (command.equals("/state")) {
            String state_inputData=api.getInputData().toString();
             message.setText(
                     appendMessage
                             + caption + lines + state_inputData
                                     .substring(1, state_inputData.length() - 1).replace("=", " - ")
                             + "\n\n");
  
         }

         else if (command.length() == 2) {
            String example = command.substring(0, 2).toUpperCase();
            String state = null;
            try {
                Set<Entry<String, String>> set= api.geState_codes().entrySet();
                
                for (Map.Entry<String, String> map1 : set) {
                    if (map1.getValue().equals(example)) {
                        state = map1.getKey();
                        break;
                    }
                }
                String state_name = "<b>" + state + "</b>";
                
                String state_data=api.geStateData(example);
                    if (state_data == null) {
                        message.setText(WRONG_MESSAGE);
                    } else {
                        String state_result=state_name +"\n"+ state_data + "\n\n";
                        String district_data=api.geDistrictData(state).toString();
                        message.setText(state_result+district_message + "<pre>" + district_data
                                       .substring(1, district_data.length() - 1).replace("=", " - ")
                                       + "</pre>" + "\n");
                            
                    }             
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
         else if (command.equals("/dice")) {
             try {
                 api.getDiceValue(message.getChatId());
                 
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (ParseException e) {
                 e.printStackTrace();
             }

         }
        
        else if (command.equals("/location")) {
            try {
                System.out.println("location get called");
                api.getLocation(update.getMessage(),message.getChatId());
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

      
        else {
            message.setText(WRONG_COMMAND);
        }

        return message;

    }

}
