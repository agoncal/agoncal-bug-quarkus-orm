package org.agoncal.bug.quarkus.orm;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository repository;

    @Test
    @TestTransaction
    public void shouldCreateAndFindAnArtist() {
        Artist artist = new Artist();
        artist.setName("Dummy Name");
        artist.setBio("Dummy Bio");
        artist.setCreatedDate(Instant.now());

        repository.persist(artist);

        artist = repository.findById(artist.getId());

        assertNotNull(artist.getId());
        assertNotNull(artist.getName());
        assertNotNull(artist.getCreatedDate());
    }

    @Test
    @TestTransaction
    public void shouldNotCreateAndFindAnArtistWithNullValue() {
        Artist artist = new Artist();
        artist.setName(null);
        artist.setBio("Dummy Bio");
        artist.setCreatedDate(null);

        repository.persist(artist);

        artist = repository.findById(artist.getId());

        assertNotNull(artist.getId());
        assertNull(artist.getName());
        assertNull(artist.getCreatedDate());
    }
}
