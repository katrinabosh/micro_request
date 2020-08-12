package ru.test.request.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.test.request.model.Request;

import java.util.Map;
import java.util.UUID;

@Repository
public class RequestDao {

    private static final String INSERT_QUERY =
            "insert into request (id, person_id, stock_code, stock_count, request_date)" +
            " values (:id, :personId, :stockCode, :stockCount, :requestDate)";

    private static final String DELETE_QUERY = "delete from request where id = :requestId";

    public RequestDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void insert(Request request) {
        jdbcTemplate.update(INSERT_QUERY, new BeanPropertySqlParameterSource(request));
    }

    public void delete(UUID requestId) {
        jdbcTemplate.update(DELETE_QUERY, Map.of("requestId", requestId));
    }

}
