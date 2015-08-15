package com.lge.lai.common.db.dao;

import java.util.HashMap;
import java.util.Map;

public class SQL {
    static final String DB = "LGAppIF";

    public static class ASB {
        static final String TABLE = DB + ".asb";

        static final String INSERT = String.format("INSERT INTO %s (_version, _type, _desc, _pkg_name, _cls_name, _action_name, _updated_by, _ctime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", TABLE);
        static final String FIND_BY_ID = String.format("SELECT * FROM %s WHERE _id = ?", TABLE);
        static final String FIND_BY_PACKAGE = String.format("SELECT * FROM %s WHERE _pkg_name = ?", TABLE);
        static final String FIND_BY_PACKAGE_AND_VERSION = String.format("SELECT * FROM %s WHERE _pkg_name = ? AND _version = ?", TABLE);
        static final String FIND_BY_PACKAGE_AND_ACTION = String.format("SELECT * FROM %s WHERE _pkg_name = ? AND _action_name = ?", TABLE);
        static final String UPDATE_MANIFEST_PATH = String.format("UPDATE %s SET _manifest_path = ? WHERE _id = ?", TABLE);
        static final String DELETE_BY_ID = String.format("DELETE FROM %s WHERE _id = ?", TABLE);

        public enum Where {
            PACKAGE_ONLY, PACKAGE_AND_VERSION, PACKAGE_AND_ACTION
        }

        static final Map<Where, String> QUERY_TABLE = new HashMap<>();
        static {
            QUERY_TABLE.put(Where.PACKAGE_ONLY, FIND_BY_PACKAGE);
            QUERY_TABLE.put(Where.PACKAGE_AND_VERSION, FIND_BY_PACKAGE_AND_VERSION);
            QUERY_TABLE.put(Where.PACKAGE_AND_ACTION, FIND_BY_PACKAGE_AND_ACTION);
        }
    }

    public static class ASBCategory {
        static final String TABLE = DB + ".asb_category";

        static final String INSERT = String.format("INSERT INTO %s (_asb_id, _category) VALUES (?, ?)", TABLE);
        static final String FIND_BY_ID = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _id = ?) AS ASB_CATEGORY_T ON ASB_T._id = ASB_CATEGORY_T._asb_id", ASB.TABLE, TABLE);
        static final String FIND_BY_FK = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _asb_id = ?) AS ASB_CATEGORY_T ON ASB_T._id = ASB_CATEGORY_T._asb_id", ASB.TABLE, TABLE);
        static final String FIND_BY_FK_AND_CATEGORY = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _asb_id = ? AND _category = ?) AS ASB_CATEGORY_T ON ASB_T._id = ASB_CATEGORY_T._asb_id", ASB.TABLE, TABLE);
        static final String DELETE_BY_ID = String.format("DELETE FROM %s WHERE _id = ?", TABLE);
    }

    public static class ASBMime {
        static final String TABLE = DB + ".asb_mime";

        static final String INSERT = String.format("INSERT INTO %s (_asb_id, _mime_type) VALUES (?, ?)", TABLE);
        static final String FIND_BY_ID = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _id = ?) AS ASB_MIME_T ON ASB_T._id = ASB_MIME_T._asb_id", ASB.TABLE, TABLE);
        static final String FIND_BY_FK = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _asb_id = ?) AS ASB_MIME_T ON ASB_T._id = ASB_MIME_T._asb_id", ASB.TABLE, TABLE);
        static final String FIND_BY_FK_AND_MIME = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _asb_id = ? AND _mime_type = ?) AS ASB_MIME_T ON ASB_T._id = ASB_MIME_T._asb_id", ASB.TABLE, TABLE);
        static final String DELETE_BY_ID = String.format("DELETE FROM %s WHERE _id = ?", TABLE);
    }

    public static class ASBUri {
        static final String TABLE = DB + ".asb_uri";

        static final String INSERT = String.format("INSERT INTO %s (_asb_id, _uri, _uri_desc) VALUES (?, ?, ?)", TABLE);
        static final String FIND_BY_ID = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _id = ?) AS ASB_URI_T ON ASB_T._id = ASB_URI_T._asb_id", ASB.TABLE, TABLE);
        static final String FIND_BY_FK = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _asb_id = ?) AS ASB_URI_T ON ASB_T._id = ASB_URI_T._asb_id", ASB.TABLE, TABLE);
        static final String FIND_BY_FK_AND_URI = String.format("SELECT * FROM (SELECT * FROM %s) AS ASB_T INNER JOIN (SELECT * FROM %s WHERE _asb_id = ? AND _uri = ?) AS ASB_URI_T ON ASB_T._id = ASB_URI_T._asb_id", ASB.TABLE, TABLE);
        static final String DELETE_BY_ID = String.format("DELETE FROM %s WHERE _id = ?", TABLE);
    }

    public static class Provider {
        static final String TABLE = DB + ".provider";

        static final String INSERT = String.format("INSERT INTO %s (_version, _type, _desc, _pkg_name, _cls_name, _table_name, _read_permission, _write_permission, _authorities, _primary, _updated_by, _ctime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", TABLE);
        static final String FIND_BY_ID = String.format("SELECT * FROM %s WHERE _id = ?", TABLE);
        static final String FIND_BY_PACKAGE = String.format("SELECT * FROM %s WHERE _pkg_name = ?", TABLE);
        static final String FIND_BY_PACKAGE_AND_VERSION = String.format("SELECT * FROM %s WHERE _pkg_name = ? AND _version = ?", TABLE);
        static final String UPDATE_MANIFEST_PATH = String.format("UPDATE %s SET _manifest_path = ? WHERE _id = ?", TABLE);
        static final String DELETE = "DELETE FROM " + TABLE + " WHERE _id = ?";

        public enum Where {
            PACKAGE_ONLY, PACKAGE_AND_VERSION
        };

        static final Map<Where, String> QUERY_TABLE = new HashMap<>();
        static {
            QUERY_TABLE.put(Where.PACKAGE_ONLY, FIND_BY_PACKAGE);
            QUERY_TABLE.put(Where.PACKAGE_AND_VERSION, FIND_BY_PACKAGE_AND_VERSION);
        }
    }
}
