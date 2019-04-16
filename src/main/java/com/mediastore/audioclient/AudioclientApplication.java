package com.mediastore.audioclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.client.RestTemplate;

import com.mediastore.audioclient.service.AudioclientService;

/**
 * 
 * @author mbgangurde
 *
 */

@SpringBootApplication
public class AudioclientApplication {

	@Autowired
	private AudioclientService audioclientService;

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
			ByteArrayResource fileData = restTemplate.getForObject(
					applicationUrl + ":" + applicationPort + "/audio/" + uniqueId, ByteArrayResource.class);

			try {

				audioclientService.playClip(fileData.getInputStream());

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			log.info(fileData.toString());
		};
	}
}
