package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Band;
import com.techelevator.model.BandGenreDto;
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
    public Band getBandByBandName(String bandName) {
        Band band = new Band();

        String sql = "SELECT * FROM bands WHERE band_name = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, bandName);
            if (results.next()) {
                band = mapRowToBand(results);
            } else {
                throw new DaoException("Band not found by given name.");
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return band;
    }

    @Override
    public List<Band> getAllBands() {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM bands;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
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
    public List<Band> getBandsBySearchTerm(String searchTerm, List<Long> genreIds) {

        List<Band> bands = new ArrayList<>();
        searchTerm = "%" + searchTerm + "%";

        String sql = "SELECT * FROM bands" +
                "JOIN band_genre ON bands.band_id = band_genre.band_id" +
                "WHERE band_name ILIKE ?";

        if (genreIds.size() > 0) {
            sql += " AND";
            if (genreIds.size() == 1) {
                sql += " band_genre.genre_id = ?;";
            } else {
                for (int i = 0; i < genreIds.size() - 1; i++) {
                    sql += " band_genre.genre_id = ? OR";
                }
                sql += " band_genre.genre_id = ?;";
            }
        } else {
            sql += ";";
        }
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

    @Override
    public void createBand(Band newBand) {

        String sql = "INSERT INTO bands (band_name,band_description,band_manager_id,band_hero_image) VALUES (?,?,?,?);";

        try {
            template.update(sql,
                    newBand.getBandName(),
                    newBand.getBandDescription(),
                    newBand.getBandManagerId(),
                    newBand.getBandHeroImage());
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

    }

    @Override
    public List<BandGenreDto> searchBandGenre(String searchTerm) {

        List<BandGenreDto> bandGenreDtos = new ArrayList<>();

        String sql = "SELECT band_name, band_hero_image FROM bands " +
                "WHERE band_name ILIKE ?;";

        searchTerm = "%" + searchTerm + "%";

        try {
            SqlRowSet results = template.queryForRowSet(sql, searchTerm);
            while (results.next()) {
                bandGenreDtos.add(mapRowToBandGenreDto(results));
            }
            for (BandGenreDto band : bandGenreDtos) {
                String bandName = band.getBandName();
                band.setGenreNames(getGenresByBandName(bandName));
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems" + e.getMessage());
        }

        return bandGenreDtos;
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

    private BandGenreDto mapRowToBandGenreDto(SqlRowSet rowSet) {
        BandGenreDto bandGenreDto = new BandGenreDto();

        bandGenreDto.setBandName(rowSet.getString("band_name"));
        bandGenreDto.setBandHeroImage(rowSet.getString("band_hero_image"));

        return bandGenreDto;

    }

    private String mapGenreNameToString(SqlRowSet rowSet) {

        return rowSet.getString("genre_name");

    }

    private List<String> getGenresByBandName(String bandName) {
        List<String> genres = new ArrayList<>();

        String sql = "SELECT genre_name FROM genres\n" +
                "\tJOIN band_genre ON genres.genre_id = band_genre.genre_id\n" +
                "\tJOIN bands ON band_genre.band_id = bands.band_id\n" +
                "\tWHERE band_name = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, bandName);
            while(results.next()) {
                genres.add(mapGenreNameToString(results));
            }

        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return genres;

    }

}
