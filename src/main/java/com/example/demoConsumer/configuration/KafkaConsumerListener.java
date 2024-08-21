package com.example.demoConsumer.configuration;

import org.springframework.beans.factory.annotation.Autowired;

//Java Program to Illustrate Kafka Consumer 

//Importing required classes 
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demoConsumer.service.IProcessDataService;

@Component
public class KafkaConsumerListener {

	@Autowired
	private IProcessDataService processDataService;

	@KafkaListener(topics = "transaction", groupId = "group1")

	public void consume(String message) {
		System.out.println("message = " + message);

		processDataService.process(message);

	}
}
