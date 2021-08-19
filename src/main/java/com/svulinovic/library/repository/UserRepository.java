package com.svulinovic.library.repository;

import com.svulinovic.library.exception.NotFoundException;
import com.svulinovic.library.model.UserResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class UserRepository {

    private static final String CREATE_USER_CALL = "User_Create";
    private static final String UPDATE_USER_CALL = "User_Update";

    private final JdbcTemplate jdbcTemplate;

    private final SimpleJdbcCall createUserCall;
    private final SimpleJdbcCall updateUserCall;

    public UserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        createUserCall = new SimpleJdbcCall(dataSource).withProcedureName(CREATE_USER_CALL);
        updateUserCall = new SimpleJdbcCall(dataSource).withProcedureName(UPDATE_USER_CALL);
    }

    public UserResponse getUser(Integer userId) {
        try {
            String sql = "SELECT id, firstName, lastName, dateOfBirth FROM dbo.[User] WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, new UserResponseRowMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User not found");
        }
    }

    public void createUser(Map<String, Object> inParam) {
        createUserCall.execute(inParam);
    }

    public void updateUser(Map<String, Object> inParam) {
        updateUserCall.execute(inParam);
    }

    public UserResponse getUserByLongestOverdueDate() {
        try {
            String sql = "SELECT TOP 1 u.id, u.firstName, u.lastName, u.dateOfBirth FROM dbo.[User] u " +
                    "INNER JOIN dbo.History h ON h.userId = u.id ORDER BY h.returnTime - h.expectedReturnTime DESC";
            return jdbcTemplate.queryForObject(sql, new UserResponseRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("User not found");
        }
    }

    private static class UserResponseRowMapper implements RowMapper<UserResponse> {
        @Override
        public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserResponse user = new UserResponse();

            user.setId(rs.getInt("id"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setDateOfBirth(rs.getString("dateOfBirth"));

            return user;
        }
    }

}
