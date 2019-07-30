package com.stackroute.MuzixApplication.service;


import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exception.TrackNotFoundException;

import java.util.List;

//interface for the service class
public interface TrackService {

    //method to save a track in database
    Track saveTrack(Track track) throws TrackAlreadyExistsException;

    //method to update a track in database
    Track updateTrack(Track track) throws TrackNotFoundException;

    //method to delete a track
    void deleteTrack(int id);

    //method to get all tracks saved in database
    List<Track> getAllTracks();

    //method to get a track by id
    Track getTrackById(int id);

    //method to search track by name or comments
    List<Track> getTrackByNameOrComments(String name);
    
    
    //method to get tracks from last.fm api and save in database
    void saveTracksFromApi();
}
