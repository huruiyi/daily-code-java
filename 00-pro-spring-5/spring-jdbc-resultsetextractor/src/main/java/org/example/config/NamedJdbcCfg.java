package org.example.config;

import javax.sql.DataSource;

import org.example.CleanUp;
import org.example.dao.JdbcSingerDao;
import org.example.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class NamedJdbcCfg {
    private static Logger logger = LoggerFactory.getLogger(NamedJdbcCfg.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:db/schema.sql", "classpath:db/test-data.sql").build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public SingerDao singerDao() {
        JdbcSingerDao dao = new JdbcSingerDao();
        dao.setNamedParameterJdbcTemplate(jdbcTemplate());
        return dao;
    }

    @Bean(destroyMethod = "destroy")
    public CleanUp cleanUp() {
        return new CleanUp(new JdbcTemplate(dataSource()));
    }
}
