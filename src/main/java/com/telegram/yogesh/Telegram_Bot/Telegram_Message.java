package com.telegram.yogesh.Telegram_Bot;

import com.vdurmont.emoji.EmojiParser;

public class Telegram_Message {

    public static String welcomeMessage = "\n\nGet the latest COVID-19 progress at your fingertips"
            + EmojiParser.parseToUnicode(":blush:") + "\n\n" + EmojiParser.parseToUnicode(":one:")
            + " To get latest state wise statistics of Covid 19, send : /state \n\n"
            + EmojiParser.parseToUnicode(":two:")+
            " To get latest statistics of India , send : /country \n\n"
            + EmojiParser.parseToUnicode(":three:") 
            +" To roll dice as you got bored this quarantine send: /dice\n\n"
            +EmojiParser.parseToUnicode(":four:")
            + " Data source: https://www.covid19india.org/\n\n"
            +EmojiParser.parseToUnicode(":five:")+" <b> Stay </b>"+EmojiParser.parseToUnicode(":house:")+"<b> Stay Safe </b>\n"
            + "\nCreated by: Yogesh Here ( https://t.me/Yogesh_here )\n\n";
}
