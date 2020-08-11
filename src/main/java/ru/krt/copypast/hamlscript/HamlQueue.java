package ru.krt.copypast.hamlscript;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.krt.copypast.hamlscript.pojo.FileList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class HamlQueue {
    String jsonSources;
    HashMap<String, Object> sourcesQueue //= new HashMap<>()
        ;
//    Map<String,Object> sourcesQueue;

    public HamlQueue(String hamlsSources){
        try {
            jsonSources = new String(Files.readAllBytes(Paths.get(hamlsSources)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FileList files = objectMapper.readValue(jsonSources, FileList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("");
    }
}
