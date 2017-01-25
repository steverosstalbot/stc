package com.fsm.example.fakedns;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

//
// Do we need to establish two topics, one for the call and one for the return?
//

public class KafkaDNSEntry extends AbstractDNSEntry {
	
	private static Scanner in;
    private String topicName;
    private String groupId;
    private KafkaConsumer<String,String> kafkaConsumer;
    org.apache.kafka.clients.producer.Producer kafkaProducer;
	
	public KafkaDNSEntry()
	{
		
	}
	
	public KafkaDNSEntry(String s, String i, String p)
	{
		setServerRoleName(s);
		//
		// Need to externalise the config like the config.txt file
		//
		configureServerSide();
		configureConsumerSide();
	}
	
	public void configureServerSide()
	{
	       //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        kafkaProducer = new KafkaProducer(configProperties);
	}
	
	public void configureConsumerSide()
	{
        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");

        //Figure out where to start processing messages from
        kafkaConsumer = new KafkaConsumer<String, String>(configProperties);
        kafkaConsumer.subscribe(Arrays.asList(topicName));
	}
	
	public void send(String message)
	{
		ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName,message);
        kafkaProducer.send(rec);
	}
	
	public String receive()
	{
		String message = "";

		return message;
	}
	
	public String toString()
	{
		String s = getClientRoleName() + "/" + getServerRoleName();
		return s;
	}

}
