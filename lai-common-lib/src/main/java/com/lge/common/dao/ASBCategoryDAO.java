package com.lge.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lge.common.dto.ASBCategory;

public class ASBCategoryDAO extends BaseDAO implements DAOCallback {
    private static final String DB = "LGAppIF";
    private static final String ASB_TABLE = DB + ".asb";
    private static final String TABLE = DB + ".asb_category";

    private static final String SQL_INSERT = String.format("INSERT INTO %s (_asb_id, _category) VALUES (?, ?)", TABLE);

    private static final String SQL_FIND_BY_ID = "SELECT * FROM (SELECT * FROM " + ASB_TABLE
            + ") AS ASB_T INNER JOIN (SELECT * FROM " + TABLE
            + " WHERE _id = ?) AS ASB_CATEGORY_T ON ASB_T._id = ASB_CATEGORY_T._asb_id";

    private static final String SQL_LIST_BY_FK = "SELECT * FROM (SELECT * FROM " + ASB_TABLE
            + ") AS ASB_T INNER JOIN (SELECT * FROM " + TABLE
            + " WHERE _asb_id = ?) AS ASB_CATEGORY_T ON ASB_T._id = ASB_CATEGORY_T._asb_id";

    private static final String SQL_LIST_BY_FK_AND_CATEGORY = "SELECT * FROM (SELECT * FROM " + ASB_TABLE
            + ") AS ASB_T INNER JOIN (SELECT * FROM " + TABLE
            + " WHERE _asb_id = ? AND _category = ?) AS ASB_CATEGORY_T ON ASB_T._id = ASB_CATEGORY_T._asb_id";

    private static final String SQL_DELETE_BY_ID = "DELETE FROM " + TABLE + " WHERE _id = ?";

    private long asbId;

    public ASBCategoryDAO(long asbId, DAOFactory daoFactory) {
        super(daoFactory);
        this.asbId = asbId;
    }

    @Override public Object find(long id) {
        return find(this, SQL_FIND_BY_ID, id).get(0);
    }

    @Override public List<Object> list() {
        return find(this, SQL_LIST_BY_FK, asbId);
    }

    public List<Object> list(String category) {
        return find(this, SQL_LIST_BY_FK_AND_CATEGORY, asbId, category);
    }

    @Override public long create(Object obj) {
        if (obj instanceof ASBCategory) {
            ASBCategory asbCategory = (ASBCategory)obj;
            Object[] values = { asbId, asbCategory.category };
            return create(SQL_INSERT, values);
        } else {
            throw new DAOException("instance is not valid: " + obj.getClass().getName());
        }
    }

    @Override public void delete(long id) {
        delete(SQL_DELETE_BY_ID, id);
    }

    @Override public Object convertToDTO(ResultSet rs) throws SQLException {
        long id = rs.getLong("_id");
        String version = rs.getString("_version");
        String type = rs.getString("_type");
        String desc = rs.getString("_desc");
        String packageName = rs.getString("_pkg_name");
        String className = rs.getString("_cls_name");
        String actionName = rs.getString("_action_name");
        String category = rs.getString("_category");
        String updatedBy = rs.getString("_updated_by");
        return new ASBCategory(version, type, desc, packageName, className, actionName, category, updatedBy, id);
    }

}
