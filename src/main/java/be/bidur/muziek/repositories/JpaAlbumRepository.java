package be.bidur.muziek.repositories;

import be.bidur.muziek.domain.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
@Repository
public class JpaAlbumRepository implements AlbumRepository
{
    private final EntityManager manager;

    public JpaAlbumRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Album> findAll() {
        return manager.createNamedQuery("Album.findAll", Album.class)
                .setHint("javax.persistence.loadgraph",
                        manager.createEntityGraph(Album.MET_ARTIEST)).getResultList();
    }

    @Override
    public Optional<Album> findById(long id) {
        return Optional.ofNullable(manager.find(Album.class, id));
    }
}
