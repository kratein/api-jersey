package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.Database;
import com.example.entities.User;
import com.example.exception.CustomException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserDAO extends AbstractDao<User> {
    private static final Log LOG = LogFactory.getLog(UserDAO.class);
    private static final String SQL_SELECT_REQUEST = "SELECT * FROM user WHERE ";

    public UserDAO() {
        super(SQL_SELECT_REQUEST);
    }

    @Override
    protected void getObjectFromResultSet(Map<Integer, User> map, ResultSet result) throws SQLException {
        User user = new User(result.getInt("id"), result.getString("name"),
                result.getString("firstname"), result.getDate("birthday"), result.getString("email"),
                result.getString("password"), result.getString("street"), result.getInt("zip_code"),
                result.getString("city"), result.getInt("id_role"), result.getString("photo"));
        map.put(user.getId(), user);
    }

	@Override
	protected PreparedStatement getUpdateStatement(User user, String request) throws SQLException {
        PreparedStatement statement = null;
        statement = Database.getInstance().prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getFirstname());
        statement.setDate(3, user.getBirthday());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        statement.setString(6, user.getStreet());
        statement.setInt(7, user.getZip_code());
        statement.setString(8, user.getCity());
        statement.setInt(9, user.getId_role());
        statement.setString(10, user.getPhoto());
        return statement;
    }
    
    public List<User> findAllUsers() {
        return getListFromRequest("1", null);
    }

    public User findUserById(int id) {
        List<User> users = getListFromRequest("id=?", id);
        return users != null && users.size() > 0 ? users.get(0) : null;
    }

    public User createUser(User user) {
        PreparedStatement statement = null;
        ResultSet result = null;
        if (user == null) {
            throw new CustomException(CustomException.ERROR_NULL,1);
        } else {
            boolean exists;
            try {
                exists = (this.findUserById(user.getId()) != null);
            } catch (NullPointerException Ex) {
                exists = false;
            }
            if (exists) {
                throw new CustomException(CustomException.ERROR_DUPLICATE_ID, 2);
            } else {
                try {
                    statement = getUpdateStatement(user,
                    "INSERT INTO user (id, name, firstname, birthday, email, password, street, zip_code, city, id_role, photo) VALUES (null,?,?,?,?,?,?,?,?,?,?)");
                    statement.executeUpdate();
                    result = statement.getGeneratedKeys();
                    if (result.next()) {
                        user.setId(result.getInt(1));
                    }
                } catch (SQLException e) {
                    LOG.debug(e.getMessage());
                } finally {
                    Database.closeSqlResources(statement, result);
                }
            }
        }
        return user;
    }

    public User updateUser(int id, User user) {
        PreparedStatement statement = null;
        if (user == null) {
            throw new CustomException(CustomException.ERROR_NULL,1);
        } else {
            if (this.findUserById(id) != null) {
                try {
                    statement = getUpdateStatement(user, 
                    "UPDATE user SET name=?, firstname=?, birthday=?, email=?, password=?, street=?, zip_code=?, city=?, id_role=?, photo=? WHERE id=" + id);
                    statement.executeUpdate();
                    user.setId(id);
                } catch (SQLException e) {
                    LOG.debug(e.getMessage());
                } finally {
                    Database.closeSqlResources(statement, null);
                }
            } else {
                throw new CustomException(CustomException.ERROR_NOTFOUND, 3);
            }
        }
        return user;
    }

    public boolean deleteUser (int id) {
        PreparedStatement statement = null;
        int deleted = 0;
        try {
            statement = Database.getInstance().prepareStatement("DELETE FROM user WHERE id=?");
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