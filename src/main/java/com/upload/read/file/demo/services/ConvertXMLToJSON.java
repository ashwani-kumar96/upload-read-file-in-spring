package com.upload.read.file.demo.services;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

@Service
public class ConvertXMLToJSON {

    public ConvertXMLToJSON() {

    }

    public static String xml= "<?xml version=\"1.0\" ?><root><test attribute=\"text1\">javatpoint</test><test attribute=\"text2\">JTP</test></root>";

    public String showJSON() {

        String jsonString = null;
        try {

            JSONObject json = XML.toJSONObject(xml);
            jsonString = json.toString();
            System.out.println(jsonString);

        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return jsonString;
    }
}
