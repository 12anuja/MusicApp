package com.stackroute.MuzixApplication.controller;


import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exception.TrackNotFoundException;
import com.stackroute.MuzixApplication.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Controller class that handles requests and sends a response
@RestController
@RequestMapping("api/v1")
//Swagger2 annotation for documentation
@Api(tags = {"Track Controller"})
public class TrackController {

    private TrackService trackService;

    //Autowired to inject the trackService dependency
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //swagger2 annotation for documentation
    @ApiOperation(value = "Insert track", response = ResponseEntity.class)
    //mapping to post request to /track
    @PostMapping("music")
    //handler to save track
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        trackService.saveTrack(track);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update track", response = ResponseEntity.class)
    //mapping to put request to /track
    @PutMapping("music")
    //handler to update a track
    public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackNotFoundException {
        trackService.updateTrack(track);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @ApiOperation(value = "Get list of all available tracks", response = ResponseEntity.class)
    //mapping to get request to /track
    @GetMapping("music")
    //handler to get all tracks
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get track requested by id", response = ResponseEntity.class)
    //mapping to get request to /track/id
    @GetMapping("music/{id}")
    //handler to get a track by its id
    public ResponseEntity<?> getTrack(@PathVariable String id){
        try {
            return new ResponseEntity<>(trackService.getTrackById(Integer.parseInt(id)), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Delete track whose id given", response = ResponseEntity.class)
    //mapping to delete request to /track/id
    @DeleteMapping("music/{id}")
    //handler to delete a track by its id
    public ResponseEntity<?> deleteTrack(@PathVariable String id){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity = new ResponseEntity<>("Successfully deleted", HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Search track by name", response = ResponseEntity.class)
    //mapping to get request to /track/search/name
    @GetMapping("music/search/{name}")
    //handler to search for a track by name or comments
    public ResponseEntity<?> searchTrack(@PathVariable String name){
        return new ResponseEntity<>(trackService.getTrackByNameOrComments(name), HttpStatus.OK);

    }
}
