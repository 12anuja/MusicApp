package com.stackroute.MuzixApplication.repository;


import com.stackroute.MuzixApplication.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

        @Autowired
        TrackRepository trackRepository;

        Track track;

        @Before
        public void setUp()
        {
            track = new Track();
            track.setTrackId(45);
            track.setTrackName("How Long");
            track.setTrackComments("Charlie Puth");

        }

        @After
        public void tearDown(){

            trackRepository.deleteAll();
        }


        @Test
        public void testSavetrack(){
            trackRepository.save(track);
            Track fetchUser = trackRepository.findById(track.getTrackId()).get();
            Assert.assertEquals(101,fetchUser.getTrackId());

        }

        @Test
        public void testSaveUserFailure(){

            Track testmuzix = new Track(34,"Capital Letters","Hailee");
            trackRepository.save(track);
            Track fetchMuzix = trackRepository.findById(track.getTrackId()).get();
            Assert.assertNotSame(testmuzix,track);

        }

        @Test
        public void testGetAllUser(){


            Track m1 = new Track(67,"Treat You Better","Shawn Mendes");
            Track m2 = new Track(45,"Love Story","Taylor Swift");
            trackRepository.save(m1);
            trackRepository.save(m2);

            List<Track> list = trackRepository.findAll();
            Assert.assertEquals(67,list.get(0).getTrackName());

        }

    }

