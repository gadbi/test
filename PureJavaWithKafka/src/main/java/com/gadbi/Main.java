package com.gadbi;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.*;

import com.gadbi.config.Application;
import org.apache.kafka.clients.admin.AdminClient;

public class Main {


    public static void main(String[] args) {
        Application app = new Application();
        System.out.println(checkIfTopicExists(app.getTopicName(),app.getlistener()));
    }

    public static boolean checkIfTopicExists(String topic,String listener) {
        Properties prop = new Properties();
       // prop.setProperty("bootstrap.servers", "localhost:9092");
        prop.setProperty("bootstrap.servers", listener);
        AdminClient admin = AdminClient.create(prop);

        try {
            boolean topicExists = admin.listTopics().names().get().stream().anyMatch(topicName -> topicName.equalsIgnoreCase(topic));
            return topicExists;
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        } catch (ExecutionException var4) {
            var4.printStackTrace();
        }

        return false;
    }
}