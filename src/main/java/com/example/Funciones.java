package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;
import org.json.XML;

public class Funciones {
    public static void txtToXml(String txtFilePath, String xmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFilePath));
        writer.write("<root>\n");
        String line;
        while ((line = reader.readLine()) != null) {
            writer.write("    <line>" + line + "</line>\n");
        }
        writer.write("</root>");
        reader.close();
        writer.close();
    }

    public static void xmlToTxt(String xmlFilePath, String txtFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(xmlFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().startsWith("<line>") && line.trim().endsWith("</line>")) {
                writer.write(line.trim().substring(6, line.trim().length() - 7) + "\n");
            }
        }
        reader.close();
        writer.close();
    }

    public static void xmlToJson(String xmlFilePath, String jsonFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(xmlFilePath));
        StringBuilder xmlContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            xmlContent.append(line);
        }
        reader.close();
        JSONObject json = XML.toJSONObject(xmlContent.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFilePath));
        writer.write(json.toString(4));
        writer.close();
    }

    public static void jsonToXml(String jsonFilePath, String xmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
        StringBuilder jsonContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }
        reader.close();
        JSONObject json = new JSONObject(jsonContent.toString());
        String xml = XML.toString(json);
        BufferedWriter writer = new BufferedWriter(new FileWriter(xmlFilePath));
        writer.write(xml);
        writer.close();
    }

    public static void txtToJson(String txtFilePath, String jsonFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
        JSONObject json = new JSONObject();
        int lineNumber = 1;
        String line;
        while ((line = reader.readLine()) != null) {
            json.put("line" + lineNumber++, line);
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFilePath));
        writer.write(json.toString(4));
        writer.close();
    }

    public static void jsonToTxt(String jsonFilePath, String txtFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(jsonFilePath));
        StringBuilder jsonContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }
        reader.close();
        JSONObject json = new JSONObject(jsonContent.toString());
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFilePath));
        for (String key : json.keySet()) {
            writer.write(json.getString(key) + "\n");
        }
        writer.close();
    }
}
