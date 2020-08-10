package ru.krt.mcapple.script;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class MyUtils {
    /**
     * Convert object to byte array
     * @param object
     * @return
     */
    public static byte[] fromJavaToByteArray(Serializable object) {
        return SerializationUtils.serialize(object);
    }

    /**
     * Convert byte array to object
     * @param bytes
     * @return
     */
    public static Object fromByteArrayToJava(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }

//    public static String fromJavaToJson(Serializable object){ObjectMapper jsonMapper = new ObjectMapper();
//        return jsonMapper.writeValueAsString(object);}
//    public static Object fromJsonToJava(String json, Class type) {ObjectMapper jsonMapper = new ObjectMapper();
//        return jsonMapper.readValue(json, type);}

    /**
     * Convert a java object to XML
     * @param object
     * @return
     */
    public static String fromJavaToXML(Serializable object) {
        XStream xs = new XStream();
        return xs.toXML(object);
    }

    /**
     * Convert from XML to object
     * @param xml
     * @return
     */
    public static Object fromXMLToJava(String xml){
        XStream xs = new XStream();
        return xs.fromXML(xml);
    }


}
