package com.lge.lai.common.db.dao;

import static com.lge.lai.common.db.dao.DAOUtil.toSqlTimestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lge.lai.common.db.dao.SQL.ASB.Where;
import com.lge.lai.common.db.dto.ASB;

public class ASBDAO extends BaseDAO implements DAOCallback {
    public ASBDAO(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object find(long id) {
        return find(this, SQL.ASB.FIND_BY_ID, id).get(0);
    }

    @Override
    public List<Object> list() {
        throw new DAOException("This API is not availble in ASBDAO");
    }

    public List<Object> list(Where where, Object... values) {
        String query = SQL.ASB.QUERY_TABLE.get(where);
        if (query == null) {
            return null;
        }
        return find(this, query, values);
    }

    @Override
    public long create(Object obj) {
        if (obj instanceof ASB) {
            ASB asb = (ASB)obj;
            Object[] values = { asb.versionName, asb.type, asb.desc, asb.packageName, asb.className, asb.actionName, asb.updatedBy, toSqlTimestamp() };
            return create(SQL.ASB.INSERT, values);
        } else {
            throw new DAOException("instance is invalid: " + obj.getClass().getName());
        }
    }

    public int update(long id, String manifestPath) {
        Object[] values = { manifestPath, id };
        return update(SQL.ASB.UPDATE_MANIFEST_PATH, values);
    }

    @Override
    public void delete(long id) {
        delete(SQL.ASB.DELETE_BY_ID, id);
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
        String updatedBy = rs.getString("_updated_by");
        return new ASB(version, type, desc, packageName, className, actionName, updatedBy, id);
    }

}
