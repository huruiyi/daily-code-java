package com.example.config;


import com.example.entities.Album;
import com.example.entities.Singer;
import com.example.repos.SingerRepository;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class DBInitializer {

  private final Logger logger = LoggerFactory.getLogger(DBInitializer.class);

  final SingerRepository singerRepository;

  public DBInitializer(SingerRepository singerRepository) {
    this.singerRepository = singerRepository;
  }

  @PostConstruct
  public void initDB() {
    logger.info("Starting database initialization...");

    Singer singer = new Singer();
    singer.setFirstName("John");
    singer.setLastName("Mayer");
    singer.setBirthDate(new Date((new GregorianCalendar(1977, 9, 16)).getTime().getTime()));

    Album album1 = new Album();
    album1.setTitle("The Search For Everything");
    album1.setReleaseDate(new java.sql.Date((new GregorianCalendar(2017, 0, 20)).getTime().getTime()));
    singer.addAlbum(album1);

    Album album2 = new Album();
    album2.setTitle("Battle Studies");
    album2.setReleaseDate(new java.sql.Date((new GregorianCalendar(2009, 10, 17)).getTime().getTime()));
    singer.addAlbum(album2);

    singerRepository.save(singer);

    singer = new Singer();
    singer.setFirstName("Eric");
    singer.setLastName("Clapton");
    singer.setBirthDate(new Date((new GregorianCalendar(1945, 2, 30)).getTime().getTime()));

    Album album = new Album();
    album.setTitle("From The Cradle");
    album.setReleaseDate(new java.sql.Date((new GregorianCalendar(1994, 8, 13)).getTime().getTime()));
    singer.addAlbum(album);

    singerRepository.save(singer);

    singer = new Singer();
    singer.setFirstName("John");
    singer.setLastName("Butler");
    singer.setBirthDate(new Date((new GregorianCalendar(1975, 3, 1)).getTime().getTime()));

    singerRepository.save(singer);
    logger.info("Database initialization finished.");
  }

}
