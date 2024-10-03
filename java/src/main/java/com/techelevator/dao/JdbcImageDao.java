package com.techelevator.dao;

import com.techelevator.model.Image;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcImageDao implements ImageDao{

    private JdbcTemplate template;

    public JdbcImageDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public List<Image> getGallery(String bandName) {
        List<Image> images = new ArrayList<>();
        String sql = "SELECT * FROM images WHERE band_id = ?;";

        long bandId = getBandIdFromBandName(bandName);

        try {
            SqlRowSet results = template.queryForRowSet(sql, bandId);
            while (results.next()) {
                images.add(mapRowToImage(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return images;
    }

    @Override
    public Image getImage(long imageId) {
        return null;
    }

    // Private methods
    private long getBandIdFromBandName(String bandName) {
        String sql = "SELECT band_id FROM bands WHERE band_name = ?;";
        long bandId = -1;

        try {
            bandId = template.queryForObject(sql, new Object[]{bandName}, Long.class);
        } catch (CannotGetJdbcConnectionException e) {
            System.out.println("Problem connecting");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Data problems");
        }

        return bandId;
    }

    private Image mapRowToImage(SqlRowSet rowSet) {
        Image image = new Image();
        image.setImageId(rowSet.getLong("image_id"));
        image.setBandId(rowSet.getLong("band_id"));
        image.setImageLink(rowSet.getString("image_link"));
        return image;
    }
}
