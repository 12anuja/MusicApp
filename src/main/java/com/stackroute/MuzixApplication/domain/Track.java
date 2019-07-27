package com.stackroute.MuzixApplication.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity used to mark it as a database entity
@Entity
//Lombok annotations
//Lombok plugin automatically generates getters, setters and constructors for any class marked as @Data
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class Track {

    //properties
    @Id     //annotated with @Id to make it primary key in database
    @ApiModelProperty(notes = "Track id")
    //Swagger2's annotation to describe properties
    // in the documentation
    private int trackId;

    @ApiModelProperty(notes = "Name of the Track")
    private String trackName;

    @ApiModelProperty(notes = "Comments about the Track")
    private String trackComments;

    public Track(int trackId, String trackName, String trackComments) {
        this.trackId = trackId;
        this.trackName = trackName;
        this.trackComments = trackComments;
    }

    public Track() {
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackComments() {
        return trackComments;
    }

    public void setTrackComments(String trackComments) {
        this.trackComments = trackComments;
    }



    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", trackName='" + trackName + '\'' +
                ", trackComments='" + trackComments + '\'' +
                '}';
    }
}
