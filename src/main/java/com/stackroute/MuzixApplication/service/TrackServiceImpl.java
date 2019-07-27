package com.stackroute.MuzixApplication.service;


import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exception.TrackNotFoundException;
import com.stackroute.MuzixApplication.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//service class contains the business logic
@Service
public class TrackServiceImpl implements TrackService {

    //TrackRepository object to perform database
    private TrackRepository trackRepository;

    //Autowired constructor to inject dependency
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    //method to save track
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        //check if track already exists
        if (trackRepository.existsById(track.getTrackId())) {
            //throw custom exception
            throw new TrackAlreadyExistsException("Track Already Exists!");
        }
        //otherwise try to save track
        Track savedTrack = trackRepository.save(track);
        //if new track was not created throw custom exception
        if (savedTrack == null) {
            throw new TrackAlreadyExistsException("Track Already Exists!");
        }
        //return the track that was inserted
        return savedTrack;
    }

    //method to update an existing track
    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {
        //check if track does not exist
        if (!trackRepository.existsById(track.getTrackId())) {
            //throw custom exception
            throw new TrackNotFoundException("Track Not Found!");
        }
        //otherwise update the track
        return trackRepository.save(track);
    }

    @Override
    //method to delete track
    public void deleteTrack(int id) {
        trackRepository.deleteById(id);
    }

    @Override
    //method to get all tracks
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    //method to get a track by id
    public Track getTrackById(int id) {
        return trackRepository.findById(id).orElse(null);
    }

    @Override
    //method to search a track by its name or comments
    public List<Track> getTrackByNameOrComments(String name) {
        return trackRepository.findTrackByNameOrComments(name);
    }
}
