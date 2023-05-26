package com.emmutua.content_calender.repository;

import com.emmutua.content_calender.model.Content;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Repository
class ContentDataAccess implements ContentDao {

    //get all the data from the db/ establish db connection
    private final JdbcTemplate jdbcTemplate;


    public ContentDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Content> selectContent() {
        var sql = """
                SELECT id, title, `desc`, status, contentType, dateCreated, dateUpdated, url
                 FROM content 
                 LIMIT 100;
                """;
        return jdbcTemplate.query(sql, new ContentRowMapper());
    }

    @Override
    public int insertContent(Content content) {
        var sql = """
                INSERT INTO Content (title,`desc`,`status`,contentType,dateCreated,dateUpdated,url) values (?, ?, ?, ?, ?, ?, ?);
                """;
        return jdbcTemplate.update(sql, content.title(), content.desc(),content.status(), content.contentType(), content.dateCreated(),content.dateUpdated(),content.url());
    }

    @Override
    public int deleteContent(int id) {
        var sql = """
                DELETE FROM Content where id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Content> selectContentById(int id) {
        var sql = """
                SELECT title,desc,status,contentType,dateCreated,dateUpdated,url FROM Content where id = ?;
                """;
        return jdbcTemplate.query(sql, new ContentRowMapper(), id).stream().findFirst();
    }
    public void updateContent(int id, String title, String desc, String status, String contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }
}