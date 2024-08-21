package com.example.demoConsumer.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;

@Service
public class ProcessDataServiceImpl implements IProcessDataService {

	Logger logger = LoggerFactory.getLogger(ProcessDataServiceImpl.class);

	@Value(value = "${ruta}")
	private String ruta;

	@Value(value = "${extension}")
	private String extension;

	@Override
	public void process(String message) {

		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		File file = new File(ruta.concat(uuidAsString).concat(extension));

		try {
			FileWriter outputFile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputFile);

			writer.writeNext(message.split(","));

			writer.close();
			outputFile.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}

	}

}
