package com.gadbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.*;

import com.gadbi.config.Application;
import kafka.admin.TopicCommand;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;

public class Main {


    public static void main(String[] args) {
        Application app = new Application();
        if(!checkIfTopicExists(app.getTopicName(),app.getlistener())){
            createTopic(app);
        }
    }

    public static boolean checkIfTopicExists(String topic,String listener) {
        Properties prop = new Properties();
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

    public static boolean createTopic(Application application)
    {
        try {
            Properties prop = new Properties();
            prop.setProperty("bootstrap.servers", application.getlistener());
            AdminClient admin = AdminClient.create(prop);
            NewTopic topic = new NewTopic(application.getTopicName(), application.getPartitions(), application.getReplication());
            List<NewTopic> newTopics = new ArrayList<>();
            newTopics.add(topic);
            final CreateTopicsResult result = admin.createTopics(newTopics);
            result.all().get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

}