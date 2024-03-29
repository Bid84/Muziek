package be.bidur.muziek.services;

import be.bidur.muziek.domain.Album;
import be.bidur.muziek.exceptions.AlbumNietGevondenException;
import be.bidur.muziek.repositories.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DefaultMuziekServiceTest {
    private DefaultAlbumService service;
    @Mock private AlbumRepository repository;
    private Album album;
    @BeforeEach
    void beforeEach(){
        album =new Album();
        service =new DefaultAlbumService(repository);
    }
    @Test
    void wijzigScore(){
        when(repository.findById(1)).thenReturn(Optional.of(album));
        service.wijzigScore(1, 10);
        assertThat(album.getScore()).isEqualTo(10);
        verify(repository).findById(1);
    }
    @Test
    void wijzigScoreVoorOnbestaandAlbum(){
        assertThatExceptionOfType(AlbumNietGevondenException.class).isThrownBy(
                ()->service.wijzigScore(-1,10));
                verify(repository).findById(-1);

    }

}