
package com.telegram.yogesh.Telegram_Bot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.impl.conn.Wire;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Data;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class CovidDataWriter {

    private static final String APPLICATION_NAME = "Google Sheets Example";
    private static Sheets sheetService;
    private static String SPREADSHEET_ID="1BrR-JRHZ8D9pQR99OQxALtkOZwmJcLMBtzuLFJhhhL4";


    
    public  Credential authorize() throws IOException, GeneralSecurityException {

        // build GoogleClientSecrets from JSON file
        InputStream in = GoogleSheetAPI.class.getResourceAsStream("/credentials.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),
                new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets,
                scopes).setDataStoreFactory(new FileDataStoreFactory(new File("tokens"))).setAccessType("offline")
                        .build();

        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

        // build Credential object

        return credential;
    }

    public Sheets getSheetsService() throws IOException, GeneralSecurityException {

        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                credential).setApplicationName(APPLICATION_NAME).build();
    }

    
    public  void writeData(String name,int id) throws IOException, GeneralSecurityException
    {
        sheetService=getSheetsService();
        
          ValueRange appendBody=new ValueRange()
          .setValues(Arrays.asList(Arrays.asList(name,id)));
          
          AppendValuesResponse appendResult=sheetService.spreadsheets().values()
          .append(SPREADSHEET_ID, "users", appendBody)
          .setValueInputOption("RAW").execute();
                
        
        
    }
    
    public  void readData() throws IOException, GeneralSecurityException
    {
        sheetService=getSheetsService();

        String range = "users!A3:B3";
        ValueRange response = sheetService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();

        List<List<Object>> values = response.getValues();
        List<Data> list = new ArrayList<>();

        System.out.printf("State Confirmed Recovered Deaths\n");
        if (values == null || values.isEmpty()) {
            System.out.println("No data found");
        } else {
            for (List row : values) {
                System.out.printf("%s %s %s %s %s\n", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4));
            }
        }

    }
}