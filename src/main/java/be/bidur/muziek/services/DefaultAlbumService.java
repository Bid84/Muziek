package be.bidur.muziek.services;

import be.bidur.muziek.domain.Album;
import be.bidur.muziek.exceptions.AlbumNietGevondenException;
import be.bidur.muziek.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
@Service @Transactional
public class DefaultAlbumService implements AlbumService{
    private final AlbumRepository albumRepository;

    public DefaultAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override @Transactional(readOnly = true)
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override @Transactional(readOnly = true)
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void wijzigScore(long id, int score) {
        albumRepository.findById(id)
                .orElseThrow(() ->new AlbumNietGevondenException())
                .setScore(score);
    }
}
