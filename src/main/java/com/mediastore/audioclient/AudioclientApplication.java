package com.mediastore.audioclient;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AudioclientApplication {

	@Value("${application.url}")
	private String applicationUrl;

	@Value("${application.port}")
	private String applicationPort;

	@Value("${download.file.name}")
	private String downloadFileName;
	
	@Value("${download.file.uniqueid}")
	private String uniqueId;

	private static final Logger log = LoggerFactory.getLogger(AudioclientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AudioclientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			ByteArrayResource quote = restTemplate.getForObject(applicationUrl + ":" + applicationPort + "/audio/" + uniqueId,
					ByteArrayResource.class);

			File file = new File(downloadFileName);
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(quote.getByteArray());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			log.info(quote.toString());
		};
	}

}
