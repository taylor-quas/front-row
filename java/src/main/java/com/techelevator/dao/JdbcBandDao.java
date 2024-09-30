package com.techelevator.dao;

import com.techelevator.model.Band;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class JdbcBandDao implements BandDao {

    private JdbcTemplate template;

    public JdbcBandDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<Band> getSubscribedBands(Principal principal) {
        String currentUser = principal.getName();
        List<Band> bands = new ArrayList<>();

        String sql = "SELECT * FROM bands\n" +
                "\tJOIN user_band ON bands.band_id = user_band.band_id\n" +
                "\tWHERE user_band.user_id = ?\n" +
                "\tORDER BY band_name;";

        long userId = getUserIdByUsername(currentUser);

        try {
            SqlRowSet results = template.queryForRowSet(sql, userId);
            while (results.next()) {
                bands.add(mapRowToBand(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bands;
    }

    @Override
    public Band getBandByBandId(long bandId) {
        return null;
    }

    @Override
    public List<Band> getBandsBySearchTerm(String searchTerm) {
        return null;
    }

    @Override
    public void updateBand(Band updatedBand) {

    }

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

    private Band mapRowToBand(SqlRowSet rowSet) {
        Band band = new Band();

        band.setBandId(rowSet.getLong("band_id"));
        band.setBandName(rowSet.getString("band_name"));
        band.setBandDescription(rowSet.getString("band_description"));
        band.setBandHeroImage(rowSet.getString("band_hero_image"));
        band.setBandManagerId(rowSet.getLong("band_manager_id"));

        return band;

    }

}
