package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Band;
import com.techelevator.model.Genre;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
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

        Band band = new Band();
        String sql = "SELECT * FROM bands WHERE band_id = ?;";


        try {
            SqlRowSet results = template.queryForRowSet(sql, bandId);
            if (results.next()) {
                band = mapRowToBand(results);
            } else {
                throw new DaoException("Band not found by given ID.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return band;
    }

    @Override
    public List<Band> getBandsBySearchTerm(String searchTerm) {

        List<Band> bands = new ArrayList<>();
        searchTerm = "%" + searchTerm + "%";
        String sql = "SELECT * FROM bands WHERE band_name ILIKE ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, searchTerm);
            while (results.next()) {
                bands.add(mapRowToBand(results));
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return bands;
    }

    //TODO: Check functionality of getBandsByGenres
    @Override
    public List<Band> getBandsByGenres(List<Genre> genres) {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands" +
                "JOIN band_genre ON bands.band_id = band_genre.band_id" +
                "WHERE ";

        if (genres.size() == 1) {
            sql += "band_genre.genre_id = ?;";
        } else {
            for (int i = 0; i < genres.size() - 1; i++) {
                sql += "band_genre.genre_id = ? OR";
            }

            sql += " band_genre.genre_id = ?;";

        }

        try {
            SqlRowSet results = template.queryForRowSet(sql, genres);
            while (results.next()) {
                bands.add(mapRowToBand(results));

            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return bands;

    }

    @Override
    public void updateBand(Band updatedBand) {

        String sql = "UPDATE bands" +
                "SET band_name = ?, band_description = ?, band_hero_image = ?\n" +
                "\tWHERE band_id = ?;";

        try {
            int rowsAffected = template.update(sql, updatedBand.getBandName(), updatedBand.getBandDescription(), updatedBand.getBandHeroImage(), updatedBand.getBandId());
            if (rowsAffected == 0) {
                throw new DaoException("No bands affected. Expected at least one.");

            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

    }

    //TODO: Check functionality of createBand
    @Override
    public Band createBand(Band newBand) {

        String sql = "INSERT INTO bands (band_name,band_description,band_manager_id,band_hero_image) VALUES (?,?,?,?) RETURNING band_id;";

        long newBandId = -1;

        try {
            newBandId = template.queryForObject(sql, Long.class,
                    newBand.getBandName(),
                    newBand.getBandDescription(),
                    newBand.getBandManagerId(),
                    newBand.getBandHeroImage(), 1);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return getBandByBandId(newBandId);
    }



// Private methods below
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
