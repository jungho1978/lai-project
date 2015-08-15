package com.lge.lai.common.db.dao;

import static com.lge.lai.common.db.dao.DAOUtil.toSqlTimestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lge.lai.common.db.dao.SQL.Provider.Where;
import com.lge.lai.common.db.dto.Provider;

public class ProviderDAO extends BaseDAO implements DAOCallback {
    public ProviderDAO(DAOFactory daoFactory) {
        super(daoFactory);
    }

    @Override
    public Object find(long id) throws DAOException {
        return find(this, SQL.Provider.FIND_BY_ID, id).get(0);
    }

    @Override
    public List<Object> list() throws DAOException {
        throw new DAOException("This API is not available in ProviderDAO");
    }

    public List<Object> list(Where where, Object... values) throws DAOException {
        String sql = SQL.Provider.QUERY_TABLE.get(where);
        if (sql == null) {
            return null;
        }
        return find(this, sql, values);
    }

    @Override
    public long create(Object obj) throws DAOException {
        if (obj instanceof Provider) {
            Provider provider = (Provider)obj;
            Object[] values = { provider.versionName, provider.type, provider.desc, provider.packageName, provider.className, provider.tableName, provider.readPermission, provider.writePermission, provider.authorities, provider.primaryKey, provider.updatedBy, toSqlTimestamp() };
            return create(SQL.Provider.INSERT, values);
        } else {
            throw new DAOException("instance is invalid: " + obj.getClass().getName());
        }
    }

    public int update(long id, String manifestPath) throws DAOException {
        Object[] values = { manifestPath, id };
        return update(SQL.Provider.UPDATE_MANIFEST_PATH, values);
    }

    @Override
    public void delete(long id) throws DAOException {
        delete(SQL.Provider.DELETE, id);
    }

    @Override
    public Object convertToDTO(ResultSet rs) throws SQLException {
        long id = rs.getLong("_id");
        String version = rs.getString("_version");
        String type = rs.getString("_type");
        String desc = rs.getString("_desc");
        String packageName = rs.getString("_pkg_name");
        String className = rs.getString("_cls_name");
        String tableName = rs.getString("_table_name");
        String readPermission = rs.getString("_read_permission");
        String writePermission = rs.getString("_write_permission");
        String authorities = rs.getString("_authorities");
        String primaryKey = rs.getString("_primary");
        String updatedBy = rs.getString("_updated_by");
        return new Provider(version, type, desc, packageName, className, tableName, readPermission, writePermission, authorities, primaryKey, updatedBy, id);
    }
}
