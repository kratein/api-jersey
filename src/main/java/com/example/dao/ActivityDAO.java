package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.Database;
import com.example.entities.Activity;
import com.example.exception.CustomException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ActivityDAO extends AbstractDao<Activity> {
    private static final Log LOG = LogFactory.getLog(ActivityDAO.class);
    private static final String SQL_SELECT_REQUEST = "SELECT * FROM hobbyactivity WHERE ";

    public ActivityDAO() {
        super(SQL_SELECT_REQUEST);
    }
    
    @Override
    protected void getObjectFromResultSet(Map<Integer, Activity> map, ResultSet result) throws SQLException {
        Activity activity = new Activity(result.getInt("id"), result.getString("label"), result.getString("description"), result.getString("web_site"), result.getInt("minimum_older"), result.getString("street"), result.getString("zip_code"), result.getString("city"), result.getString("cover"), result.getString("slogan"), result.getDouble("price"));
        map.put(activity.getId(), activity);
    }

    @Override
    protected PreparedStatement getUpdateStatement(Activity activity, String request) throws SQLException {
        PreparedStatement statement = null;
        statement = Database.getInstance().prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, activity.getLabel());
        statement.setString(2, activity.getDescription());
        statement.setString(3, activity.getWeb_site());
        statement.setInt(4, activity.getMin_older());
        statement.setString(5, activity.getStreet());
        statement.setString(6, activity.getZip_code());
        statement.setString(7, activity.getCity());
        statement.setString(8, activity.getCover());
        statement.setString(9, activity.getSlogan());
        statement.setDouble(10, activity.getPrice());
        return statement;
    }

    public List<Activity> findAllActivities() {
        return getListFromRequest("1", null);
    }

    public Activity findActivityById(int id) {
        List<Activity> activities = getListFromRequest("id=?", id);
        return activities != null && activities.size() > 0 ? activities.get(0) : null;
    }

    public Activity createActivity(Activity activity) {
        PreparedStatement statement = null;
        ResultSet result = null;
        if (activity == null) {
            throw new CustomException(CustomException.ERROR_NULL,1);
        } else {
            boolean exists;
            try {
                exists = (this.findActivityById(activity.getId()) != null);
            } catch (NullPointerException Ex) {
                exists = false;
            }
            if (exists) {
                throw new CustomException(CustomException.ERROR_DUPLICATE_ID, 2);
            } else {
                try {
                    statement = getUpdateStatement(activity,
                    "INSERT INTO hobbyactivity (id, label, description, web_site, minimum_older, street, zip_code, city, cover, slogan, price) VALUES (null,?,?,?,?,?,?,?,?,?,?)");
                    statement.executeUpdate();
                    result = statement.getGeneratedKeys();
                    if (result.next()) {
                        activity.setId(result.getInt(1));
                    }
                } catch (SQLException e) {
                    LOG.debug(e.getMessage());
                } finally {
                    Database.closeSqlResources(statement, result);
                }
            }
        }
        return activity;
    }

    public Activity updateActivity(int id, Activity activity) {
        PreparedStatement statement = null;
        if (activity == null) {
            throw new CustomException(CustomException.ERROR_NULL,1);
        } else {
            if (this.findActivityById(id) != null) {
                try {
                    statement = getUpdateStatement(activity, 
                    "UPDATE hobbyactivity SET label=?, description=?, web_site=?, minimum_older=?, street=?, zip_code=?, city=?, cover=?, slogan=?, price=? WHERE id=" + id);
                    statement.executeUpdate();
                    activity.setId(id);
                } catch (SQLException e) {
                    LOG.debug(e.getMessage());
                } finally {
                    Database.closeSqlResources(statement, null);
                }
            } else {
                throw new CustomException(CustomException.ERROR_NOTFOUND, 3);
            }
        }
        return activity;
    }

    public boolean deleteActivity (int id) {
        PreparedStatement statement = null;
        int deleted = 0;
        try {
            statement = Database.getInstance().prepareStatement("DELETE FROM hobbyactivity WHERE id=?");
            statement.setInt(1, id);
            deleted = statement.executeUpdate();
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        } finally {
            Database.closeSqlResources(statement, null);
        }
        return deleted > 0;
    }

}