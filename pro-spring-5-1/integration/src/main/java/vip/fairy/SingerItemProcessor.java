package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component("itemProcessor")
public class SingerItemProcessor implements ItemProcessor<Singer, Singer> {

  private static final Logger logger = LoggerFactory.getLogger(SingerItemProcessor.class);

  @Override
  public Singer process(Singer singer) {
    String firstName = singer.getFirstName().toUpperCase();
    String lastName = singer.getLastName().toUpperCase();
    String song = singer.getSong().toUpperCase();

    Singer transformedSinger = new Singer();
    transformedSinger.setFirstName(firstName);
    transformedSinger.setLastName(lastName);
    transformedSinger.setSong(song);

    logger.info("Transformed singer: " + singer + " Into: " + transformedSinger);

    return transformedSinger;
  }
}
