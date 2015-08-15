package com.lge.lai.common.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lge.lai.common.db.dto.ASBCategory;

public class ASBCategoryDAO extends BaseDAO implements DAOCallback {
    private long asbId;

    public ASBCategoryDAO(long asbId, DAOFactory daoFactory) {
        super(daoFactory);
        this.asbId = asbId;
    }

    @Override
    public Object find(long id) {
        return find(this, SQL.ASBCategory.FIND_BY_ID, id).get(0);
    }

    @Override
    public List<Object> list() {
        return find(this, SQL.ASBCategory.FIND_BY_FK, asbId);
    }

    public List<Object> list(String category) {
        return find(this, SQL.ASBCategory.FIND_BY_FK_AND_CATEGORY, asbId, category);
    }

    @Override
    public long create(Object obj) {
        if (obj instanceof ASBCategory) {
            ASBCategory asbCategory = (ASBCategory)obj;
            Object[] values = { asbId, asbCategory.category };
            return create(SQL.ASBCategory.INSERT, values);
        } else {
            throw new DAOException("instance is not valid: " + obj.getClass().getName());
        }
    }

    @Override
    public void delete(long id) {
        delete(SQL.ASBCategory.DELETE_BY_ID, id);
    }

    @Override
    public Object convertToDTO(ResultSet rs) throws SQLException {
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
