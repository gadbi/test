package com.gadbi.config;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Application {
    public String APPLICATION_FILE_PROPERTIES = "application.properties";
    public String SOCKET_SERVER_LISTENERS = "socket.server.listeners";
    public String ZOOKEEPER_HOST="zookeeper.host";
    public String KAFKA_PARTITIONS="kafka.partitions";
    public String KAFKA_REPLICATION="kafka.replication";

    public String TOPIC_NAME="topic.name";


    Properties prop = null;
    InputStream inputStream = null;
    public Application()
    {
        if(prop == null)
        {
            prop = new Properties();
        }
        if(inputStream == null){

            try {
                inputStream = getConfigFilePath();
                prop.load(inputStream);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }

    private InputStream getConfigFilePath(){
        //System.out.println(new File("").getAbsolutePath());
        return this.getClass().getClassLoader().getResourceAsStream(APPLICATION_FILE_PROPERTIES);

    }


    public String getlistener(){
        return prop.getProperty(SOCKET_SERVER_LISTENERS);
    }
    public String getTopicName()
    {
        return prop.getProperty(TOPIC_NAME);
    }

    public int getPartitions()
    {
         return Integer.parseInt(prop.getProperty(KAFKA_PARTITIONS));
    }
     public short getReplication()
     {

         return Short.parseShort(prop.getProperty(KAFKA_REPLICATION));
     }


}
