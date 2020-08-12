package ru.krt.copypast.hamlscript;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class HamlQueue {
    String jsonSources;
    Map queueMap = new HashMap<>();

    public Map getQueueMap() {
        return queueMap;
    }

//    ObjectMapper mapper = new ObjectMapper();
    public HamlQueue(String hamlsSources){
        try {
            jsonSources = new String(Files.readAllBytes(Paths.get(hamlsSources)));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        queueMap = mapper.readValue(jsonSources, new TypeReference<HashMap<String, String>>(){});

//        System.out.println("");
    }

}
