package org.example;

import org.example.config.AppConfig;
import org.example.dao.SingerDao;
import org.example.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {

	private static final Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean(SingerDao.class);
		Singer singer = singerDao.findById(2L);
		logger.info(singer.toString());

		singerDao.delete(singer);

		listSingersWithAlbum(singerDao.findAllWithAlbum());
		ctx.close();
	}

	private static void listSingersWithAlbum(List<Singer> singers) {
		logger.info(" ---- Listing singers with instruments:");
		singers.forEach(s -> {
			logger.info(s.toString());
			if (s.getAlbums() != null) {
				s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
			}
			if (s.getInstruments() != null) {
				s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
			}
		});
	}
}