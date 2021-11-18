package be.bidur.muziek.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name = Album.MET_ARTIEST,
 attributeNodes = @NamedAttributeNode("artiest"))
public class Album {
    public static final String MET_ARTIEST ="Album.metArtiest";
    @Id
    private long id;
    private String naam;
    @ManyToOne(optional =false, fetch = FetchType.LAZY)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    @Range(min = 0, max = 10)
    private int score;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks =new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public int getScore() {
        return score;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalTime getTijd(){
        LocalTime som = LocalTime.MIN;
        for(var track :tracks){
            var tijd =track.getTijd();
            som = som.plusHours(tijd.getHour()).plusMinutes(tijd.getMinute()).plusSeconds(tijd.getSecond());
        }
        return som;
    }
}

