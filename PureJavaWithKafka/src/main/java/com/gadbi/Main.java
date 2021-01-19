package com.gadbi;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.*;
import org.apache.kafka.clients.admin.AdminClient;

public class Main {


    public static void main(String[] args) {

        System.out.println(checkIfTopicExists());
    }

    public static boolean checkIfTopicExists() {
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers", "localhost:9092");
        AdminClient admin = AdminClient.create(prop);

        try {
            boolean topicExists = admin.listTopics().names().get().stream().anyMatch(topicName -> topicName.equalsIgnoreCase("gadTopic"));
            return topicExists;
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        } catch (ExecutionException var4) {
            var4.printStackTrace();
        }

        return false;
    }
}