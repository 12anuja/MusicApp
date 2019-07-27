package com.stackroute.MuzixApplication.service;

import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class TrackServiceTest {

        Track track;

        //Create a mock for UserRepository
        @Mock
    TrackRepository trackRepository;

        //Inject the mocks as dependencies into UserServiceImpl
        @InjectMocks
        TrackServiceImpl trackService;
        List<Track> list= null;


        @Before
        public void setUp(){
            //Initialising the mock object
            MockitoAnnotations.initMocks(this);
            track = new Track();
            track.setTrackId(45);
            track.setTrackName("Closer");
            track.setTrackComments("Chainsmokers");
            list = new ArrayList<>();
            list.add(track);


        }

        @Test
        public void saveUserTestSuccess() throws TrackAlreadyExistsException {

            when(trackRepository.save((Track) any())).thenReturn(track);
            Track savedUser = trackService.saveTrack(track);
            Assert.assertEquals(track,savedUser);

            //verify here verifies that userRepository save method is only called once
            verify(trackRepository,times(1)).save(track);

        }
//
//    @Test(expected = TrackAlreadyExistsException.class)
//    public void saveUserTestFailure() throws TrackAlreadyExistsException {
//        when(trackRepository.save((Track) any()).thenReturn(null));
//        Track savedUser = trackService.saveTrack(track);
//        System.out.println("savedUser" + savedUser);
//        //Assert.assertEquals(user,savedUser);
//
//       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
//       userService.saveUser(user);*/
//
//    }

        @Test
        public void getAllUser(){

            trackRepository.save(track);
            //stubbing the mock to return specific data
            when(trackRepository.findAll()).thenReturn(list);
            List<Track> userlist = trackService.getAllTracks();
            Assert.assertEquals(list,userlist);
        }

    }
