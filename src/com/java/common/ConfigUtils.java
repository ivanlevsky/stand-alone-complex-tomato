package com.java.common;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;

import java.io.*;
import java.util.*;

public class ConfigUtils {
    public static void main(String[] args) {
//        addConfOptionsBySection(result,"tt", "test_f=12","sdb=12");
//        for (String s: result.keySet()){
//            System.out.println(s+result.get(s));
//        }
        readConfFile(GlobalParams.confPath2);
    }

    static Object readConfFile(String confFile, String ...sectionOption) {
        LinkedHashMap<String, Properties> result = new LinkedHashMap<>();
        try {
            new Properties() {
                private Properties section;

                @Override
                public Object put(Object key, Object value) {
                    String header = (key + " " + value).trim();
                    if (header.startsWith("[") && header.endsWith("]"))
                        return result.put(header.substring(1, header.length() - 1),
                                section = new Properties());
                    else
                        return section.put(key, value);
                }

            }.load(new FileInputStream(confFile));

            if(sectionOption.length == 0){
                return result;
            }

            if(!result.containsKey(sectionOption[0])){
                System.out.println( "config section \""+ sectionOption[0] + "\" not found");
                return "no section";
            }

            if(sectionOption.length == 1){
                return result.keySet();
            }

            if(!result.get(sectionOption[0]).containsKey(sectionOption[1])){
                System.out.println("config section \""+ sectionOption[0] +"\" option \""+ sectionOption[1] + "\" not found");
                return (result.get(sectionOption[0]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.get(sectionOption[0]).get(sectionOption[1]);
    }

    public static void writeConfFile(String confFile, LinkedHashMap<String, Properties> config){
        StringBuilder confText = new StringBuilder();
        for (String ck : config.keySet()){
            confText.append("[").append(ck).append("]").append(System.lineSeparator());
            for (Object ckk : config.get(ck).keySet()){
                confText.append(ckk).append("=").append(config.get(ck).get(ckk).toString().
                        replace("\\","\\\\")).append(System.lineSeparator());
            }
            confText.append(System.lineSeparator());
        }
        try {
            File file = new File(confFile);
            ByteSink sink = Files.asByteSink(file);
            sink.write(confText.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void removeConfSection(LinkedHashMap<String, Properties> config, String section){
        config.remove(section);
    }

    public static void removeConfOption(LinkedHashMap<String, Properties> config, String section, String option){
        config.get(section).remove(option);
    }

    //warning: new insert key and value not in right order now!!
    public static void addConfOptionsBySection(LinkedHashMap<String, Properties> config, String section,
                                               String  ...keyAndValues){
        Properties sectionNew = new Properties();
        if(keyAndValues.length == 0){
            config.put(section,sectionNew);
        }

        if(keyAndValues.length > 0){
            if(!config.containsKey(section)) {
                config.put(section, sectionNew);
            }
            for (String kv : keyAndValues){
                String[] kvs = kv.split("=");
                config.get(section).put(kvs[0],kvs[1]);
            }
        }


    }
}
