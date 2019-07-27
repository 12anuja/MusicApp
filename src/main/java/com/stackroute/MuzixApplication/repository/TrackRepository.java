package com.stackroute.MuzixApplication.repository;

import com.stackroute.MuzixApplication.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repository class used to perform database operations, extends JpaRepository
//JpaRepository<EntityClassName,WrapperTypeOfIdPropertyInEntityClass>
@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    //Query to search a track by its name or comments in the database
    //used lower() to make searching case insensitive
    @Query("SELECT t FROM Track t WHERE lower(t.trackName) LIKE lower(CONCAT('%',:string,'%')) OR" +
            " lower(t.trackComments) LIKE lower(CONCAT('%',:string,'%'))")
    List<Track> findTrackByNameOrComments(String string);
}

