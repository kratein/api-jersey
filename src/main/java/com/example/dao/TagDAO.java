package com.example.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.example.Database;
import com.example.entities.Tag;
import com.example.exception.CustomException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TagDAO extends AbstractDao<Tag> {
    private static final Log LOG = LogFactory.getLog(TagDAO.class);
    private static final String SQL_SELECT_REQUEST = "SELECT * FROM tag WHERE ";

    public TagDAO() {
        super(SQL_SELECT_REQUEST);
    }

    @Override
    protected void getObjectFromResultSet(Map<Integer, Tag> map, ResultSet result) throws SQLException {
        Tag tag = new Tag(result.getInt("id"), result.getString("label"));
        map.put(tag.getId(), tag);
    }

	@Override
	protected PreparedStatement getUpdateStatement(Tag tag, String request) throws SQLException {
        PreparedStatement statement = null;
        statement = Database.getInstance().prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, tag.getLabel());
        return statement;
    }
    
    public List<Tag> findAllTags() {
        return getListFromRequest("1", null);
    }

    public Tag findTagById(int id) {
        List<Tag> tags = getListFromRequest("id=?", id);
        return tags != null && tags.size() > 0 ? tags.get(0) : null;
    }

    public Tag createTag(Tag tag) {
        PreparedStatement statement = null;
        ResultSet result = null;
        if (tag == null) {
            throw new CustomException(CustomException.ERROR_NULL,1);
        } else {
            boolean exists;
            try {
                exists = (this.findTagById(tag.getId()) != null);
            } catch (NullPointerException Ex) {
                exists = false;
            }
            if (exists) {
                throw new CustomException(CustomException.ERROR_DUPLICATE_ID, 2);
            } else {
                try {
                    statement = getUpdateStatement(tag,
                    "INSERT INTO tag (id, label) VALUES (null,?)");
                    statement.executeUpdate();
                    result = statement.getGeneratedKeys();
                    if (result.next()) {
                        tag.setId(result.getInt(1));
                    }
                } catch (SQLException e) {
                    LOG.debug(e.getMessage());
                } finally {
                    Database.closeSqlResources(statement, result);
                }
            }
        }
        return tag;
    }

    public Tag updateTag(int id, Tag tag) {
        PreparedStatement statement = null;
        if (tag == null) {
            throw new CustomException(CustomException.ERROR_NULL,1);
        } else {
            if (this.findTagById(id) != null) {
                try {
                    statement = getUpdateStatement(tag, 
                    "UPDATE tag SET label=? WHERE id=" + id);
                    statement.executeUpdate();
                    tag.setId(id);
                } catch (SQLException e) {
                    LOG.debug(e.getMessage());
                } finally {
                    Database.closeSqlResources(statement, null);
                }
            } else {
                throw new CustomException(CustomException.ERROR_NOTFOUND, 3);
            }
        }
        return tag;
    }

    public boolean deleteTag (int id) {
        PreparedStatement statement = null;
        int deleted = 0;
        try {
            statement = Database.getInstance().prepareStatement("DELETE FROM tag WHERE id=?");
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