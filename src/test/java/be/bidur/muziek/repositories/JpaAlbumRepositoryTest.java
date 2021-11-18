package be.bidur.muziek.repositories;

import be.bidur.muziek.domain.Album;
import be.bidur.muziek.domain.Artiest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import java.time.LocalTime;

@DataJpaTest(showSql = false)
@Import(JpaAlbumRepository.class)
@Sql({"/insertArtiest.sql", "/insertAlbum.sql"})
class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ALBUMS = "albums";
    private final JpaAlbumRepository repository;
    private final EntityManager manager;

    JpaAlbumRepositoryTest(JpaAlbumRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }
    private long idVanTestAlbum(){
        return jdbcTemplate.queryForObject(
                "select id from albums where naam ='test'", Long.class);
    }

    @Test
    void findById(){
        assertThat(repository.findById(idVanTestAlbum()))
                .hasValueSatisfying(album -> {
                    assertThat(album.getNaam()).isEqualTo("test");
                    assertThat(album.getArtiest().getNaam()).isEqualTo("test");
                    assertThat(album.getTijd()).isEqualTo(LocalTime.of(0,10));

                });
    }
    @Test void findByOnbestaandeId(){
        assertThat(repository.findById(-1)).isNotPresent();
    }

    @Test
    void findAll(){
        var albums = repository.findAll();
        manager.clear();
        assertThat(albums)
                .hasSize(countRowsInTable(ALBUMS))
                .extracting(Album::getNaam)
                .isSortedAccordingTo(String::compareToIgnoreCase);
        assertThat(albums)
                .extracting(Album::getArtiest)
                .extracting(Artiest::getNaam);
    }

}