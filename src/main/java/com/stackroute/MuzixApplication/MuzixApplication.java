package com.stackroute.MuzixApplication;


import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MuzixApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private TrackService trackService;

	@Autowired
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MuzixApplication.class);
	}

	public static void main(String[] args){
		SpringApplication.run(MuzixApplication.class, args);
	}

	@Override
	public void run(String... args){
		try {
			trackService.saveTrack(new Track(1, "How Long", "Charlie Puth"));
			trackService.saveTrack(new Track(2, "Capital Letters", "Hailee Stanfeild"));
			trackService.saveTrack(new Track(3, "Just Dance", "J-Hope"));
		} catch (TrackAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
}
