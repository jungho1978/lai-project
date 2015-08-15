package com.lge.lai.common.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lge.lai.common.db.dto.ASBUri;

public class ASBUriDAO extends BaseDAO implements DAOCallback {
    private long asbId;

    public ASBUriDAO(long asbId, DAOFactory daoFactory) {
        super(daoFactory);
        this.asbId = asbId;
    }

    @Override
    public Object find(long id) throws DAOException {
        return find(this, SQL.ASBUri.FIND_BY_ID, id).get(0);
    }

    @Override
    public List<Object> list() throws DAOException {
        return find(this, SQL.ASBUri.FIND_BY_FK, asbId);
    }

    public List<Object> list(String uri) throws DAOException {
        return find(this, SQL.ASBUri.FIND_BY_FK_AND_URI, asbId, uri);
    }

    @Override
    public long create(Object obj) throws DAOException {
        if (obj instanceof ASBUri) {
            ASBUri asbUri = (ASBUri)obj;
            Object[] values = { asbId, asbUri.uri, asbUri.uriDesc };

            return create(SQL.ASBUri.INSERT, values);
        } else {
            throw new DAOException("instance is not valid: " + obj.getClass().getName());
        }
    }

    @Override
    public void delete(long id) throws DAOException {
        delete(SQL.ASBUri.DELETE_BY_ID, id);
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
        String uri = rs.getString("_uri");
        String uriDesc = rs.getString("_uri_desc");
        String updatedBy = rs.getString("_updated_by");
        return new ASBUri(version, type, desc, packageName, className, actionName, uri, uriDesc, updatedBy, id);
    }
}
