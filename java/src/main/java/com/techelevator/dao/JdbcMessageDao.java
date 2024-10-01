package com.techelevator.dao;

import com.techelevator.model.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMessageDao implements MessageDao {

    private JdbcTemplate template;

    public JdbcMessageDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<Message> getInboxMessages(Principal principal) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages m " +
                "JOIN user_band ub ON ub.band_id = m.message_sender " +
                "WHERE ub.user_id = ? " +
                "ORDER BY message_time_sent DESC;";

        long principalId = getUserIdByUsername(principal.getName());

        try {
            SqlRowSet results = template.queryForRowSet(sql, principalId);
            while (results.next()) {
                messages.add(mapRowToMessage(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }
        return messages;
    }

    @Override
    public Message sendMessage(Message message) {
        return null;
    }

    // Private methods
    private long getUserIdByUsername(String username) {
        String sql = "SELECT user_id FROM users WHERE username = ?;";
        long userId = -1;

        try {
            userId = template.queryForObject(sql, new Object[]{username}, Long.class);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return userId;
    }
    private Message mapRowToMessage(SqlRowSet rowSet) {
        Message message = new Message();
        message.setMessageId(rowSet.getLong("message_id"));
        message.setMessageContent(rowSet.getString("message_content"));

        Timestamp timeSent = rowSet.getTimestamp("message_time_sent");
        if (timeSent != null) {
            message.setMessageTimeSent(timeSent.toLocalDateTime());
        }

        Timestamp timeExpiration = rowSet.getTimestamp("message_time_expiration");
        if (timeExpiration != null) {
            message.setMessageTimeExpiration(timeExpiration.toLocalDateTime());
        }

        message.setMessageSender(rowSet.getLong("message_sender"));
        return message;
    }
}
