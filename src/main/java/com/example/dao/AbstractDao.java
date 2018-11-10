package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.Database;

public abstract class AbstractDao<T> {

	private static final Log LOG = LogFactory.getLog(AbstractDao.class);
	private final String defaultQuery;

	protected AbstractDao(String query) {
		this.defaultQuery = query;
	}

	protected abstract void getObjectFromResultSet(Map<Integer, T> map, ResultSet result) throws SQLException;

	protected abstract PreparedStatement getUpdateStatement(T obj, String request) throws SQLException;

	protected List<T> getListFromRequest(String requestComplement, Object value) {
		return getListFromRequest(defaultQuery, requestComplement, value);
	}

	private void addAttributeToStatement(int position, PreparedStatement statement, Object value) throws SQLException {
		if (value instanceof String) {
			String str = (String) value;
			statement.setString(position, str);
		} else if (value instanceof Integer) {
			Integer nbr = (Integer) value;
			statement.setInt(position, nbr);
		} else if (value instanceof Double) {
			Double nbr = (Double) value;
			statement.setDouble(position, nbr);
		}
	}

	protected List<T> getListFromRequest(String query, String requestComplement, Object value) {
		Map<Integer, T> map = new LinkedHashMap<Integer, T>();
		if (requestComplement != null) {
			ResultSet result = null;
			PreparedStatement statement = null;
			try {
				statement = Database.getInstance().prepareStatement(query + requestComplement);
				if (value != null && requestComplement.contains("?")) {
					if (value instanceof List<?>) {
						List<?> list = (List<?>) value;
						if (requestComplement.chars().filter(ch -> ch == '?').count() == list.size()) {
							int i = 0;
							for (Object obj : list) {
								addAttributeToStatement(++i, statement, obj);
							}
						}
					} else {
						addAttributeToStatement(1, statement, value);
					}
				}
				result = statement.executeQuery();
				while (result.next()) {
					getObjectFromResultSet(map, result);
				}
			} catch (Exception e) {
				LOG.debug(e.getMessage());
			} finally {
				Database.closeSqlResources(statement, result);
			}
		}
		return new ArrayList<T>(map.values());
	}

}
