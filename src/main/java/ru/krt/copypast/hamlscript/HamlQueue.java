package ru.krt.copypast.hamlscript;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HamlQueue {
    String jsonSources;
    HashMap<String, String> sourcesQueue //= new HashMap<>()
        ;
//    Map<String,Object> sourcesQueue;

    public HamlQueue(String hamlsSources) {
        try {
            jsonSources = new String(Files.readAllBytes(Paths.get(hamlsSources)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sourcesQueue = new ObjectMapper().readValue(jsonSources, HashMap.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }
}
