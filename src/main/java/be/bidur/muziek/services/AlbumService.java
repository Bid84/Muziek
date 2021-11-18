package be.bidur.muziek.services;

import be.bidur.muziek.domain.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll();
    Optional<Album> findById(long id);
    void wijzigScore(long id, int score);
}
