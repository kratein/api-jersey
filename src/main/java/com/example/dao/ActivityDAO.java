package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.example.entities.Activity;

public class ActivityDAO extends AbstractDao<Activity> {

    @Override
    protected void getObjectFromResultSet(Map<Integer, Activity> map, ResultSet result) throws SQLException {

    }

    @Override
    protected PreparedStatement getUpdateStatement(Activity activity, String request) throws SQLException {
        return null;
    }

}