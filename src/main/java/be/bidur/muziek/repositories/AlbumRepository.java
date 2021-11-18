package be.bidur.muziek.repositories;

import be.bidur.muziek.domain.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {
    List<Album> findAll();
    Optional<Album> findById(long id);
}
