package com.techelevator.dao;

import com.techelevator.model.Event;
import com.techelevator.model.EventBandDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventDao implements EventDao {

    private JdbcTemplate template;

    public JdbcEventDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<EventBandDto> getAllEvents() {
        List<EventBandDto> events = new ArrayList<>();

        String sql = "SELECT event_id, event_time, event_venue, event_address, " +
                "event_host, event_name, b.band_name FROM events e " +
                "JOIN bands b ON e.event_host = b.band_id;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                events.add(mapRowToEventBandDto(results));
            }
            for (EventBandDto event : events) {
                long eventHost = event.getEvent().getEventHost();
                event.setBandName(getBandNameByEventHost(eventHost));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems 2");
        }

        return events;
    }

    @Override
    public List<EventBandDto> getEventsByEventHost(long eventHost) {
        return null;
    }



// Private methods
    private String getBandNameByEventHost(long eventHost) {
        String bandName = null;

        String sql = "SELECT band_name FROM bands WHERE band_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, eventHost);
            if (results.next()) {
                bandName = results.getString("event_host");
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bandName;

    }

    private Event mapRowToEvent(SqlRowSet rowSet) {
        Event event = new Event();

        event.setEventId(rowSet.getLong("event_id"));
        event.setEventTime((rowSet.getTimestamp("event_time")).toLocalDateTime());
        event.setEventVenue(rowSet.getString("event_venue"));
        event.setEventHost(rowSet.getLong("event_host"));
        event.setEventName(rowSet.getString("event_name"));

        return event;
    }

    private EventBandDto mapRowToEventBandDto(SqlRowSet rowSet) {
        EventBandDto eventBandDto = new EventBandDto();
        eventBandDto.setEvent(mapRowToEvent(rowSet));
        return eventBandDto;
    }

}
