package com.lge.lai.common.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lge.lai.common.db.dto.ASBMime;

public class ASBMimeDAO extends BaseDAO implements DAOCallback {
    private long asbId;

    public ASBMimeDAO(long asbId, DAOFactory daoFactory) {
        super(daoFactory);
        this.asbId = asbId;
    }

    @Override
    public Object find(long id) {
        return find(this, SQL.ASBMime.FIND_BY_ID, id).get(0);
    }

    @Override
    public List<Object> list() {
        return find(this, SQL.ASBMime.FIND_BY_FK, asbId);
    }

    public List<Object> list(String mimeType) {
        return find(this, SQL.ASBMime.FIND_BY_FK_AND_MIME, asbId, mimeType);
    }

    @Override
    public long create(Object obj) throws DAOException {
        if (obj instanceof ASBMime) {
            ASBMime asbMime = (ASBMime)obj;
            Object[] values = { asbId, asbMime.mimeType };

            return create(SQL.ASBMime.INSERT, values);
        } else {
            throw new DAOException("instance is not valid: " + obj.getClass().getName());
        }
    }

    @Override
    public void delete(long id) {
        delete(SQL.ASBMime.DELETE_BY_ID, id);
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
        String mimeType = rs.getString("_mime_type");
        String updatedBy = rs.getString("_updated_by");
        return new ASBMime(version, type, desc, packageName, className, actionName, mimeType, updatedBy, id);
    }
}
