package com.lge.common.data;

import java.util.Comparator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

public class Feature {
    public String type;
    public String className;
    public String actionName;
    public List<String> categoryList = Lists.newArrayList();

    // URI sample: <scheme>://<host>:<port>[<path>|<pathPrefix>|<pathPattern>]
    // scheme: if not specified for intent-filter, all the other URI attributes
    // are ignored
    // host: if not specified for intent-filter, the port attribute and all the
    // path attributes are ignored
    // path: specifies a complete path that is matched against the complete path
    // in an intent object
    // pathPrefix: specifies a partial path that is matched against only the
    // initial part of the path in the intent object
    // pathPattern: almost same with 'path', but it can contain the wild cards (*)
    public List<String> schemeList = Lists.newArrayList();
    public List<String> hostList = Lists.newArrayList();
    public List<String> portList = Lists.newArrayList();
    public List<String> pathList = Lists.newArrayList();
    public List<String> pathPatternList = Lists.newArrayList();
    public List<String> pathPrefixList = Lists.newArrayList();
    public List<String> mimeTypeList = Lists.newArrayList();

    // For content provider only
    public String authorities;
    public String readPermission;
    public String writePermission;

    /**
     * @param category name declared in "category android:name="
     */
    public void addCategory(String category) {
        categoryList.add(category);
    }

    /**
     * @return string of categories, which are separated with carriage return between categories
     */
    public String getCategories() {
        return getDataValues("category");
    }

    /**
     * @param scheme name declared in "data android:scheme="
     */
    public void addScheme(String scheme) {
        schemeList.add(scheme);
    }

    /**
     * @return string of schemes, which are separated with carriage return  between schemes
     */
    public String getSchemes() {
        return getDataValues("scheme");
    }

    /**
     * @param host name declared in "data android:port="
     */
    public void addHost(String host) {
        hostList.add(host);
    }

    /**
     * @return string of hosts, which are separated with carriage return between hosts
     */
    public String getHosts() {
        return getDataValues("host");
    }

    /**
     * @param port name declared in "data android:port="
     */
    public void addPort(String port) {
        portList.add(port);
    }

    /**
     * @return string of ports, which are separated with carriage return between ports
     */
    public String getPorts() {
        return getDataValues("port");
    }

    /**
     * @param path name declared in "data android:path="
     */
    public void addPath(String path) {
        pathList.add(path);
    }

    /**
     * @return string of paths, which are separated with carriage return between paths
     */
    public String getPaths() {
        return getDataValues("path");
    }

    /**
     * @param pathPattern name declared in "data android:pathPattern="
     */
    public void addPathPattern(String pathPattern) {
        pathPatternList.add(pathPattern);
    }

    /**
     * @return string of pathPatterns, which are separated with carriage return between pathPatterns
     */
    public String getPathPatterns() {
        return getDataValues("pathPattern");
    }

    /**
     * @param pathPrefix name declared in "data android:pathPrefix="
     */
    public void addPathPrefix(String pathPrefix) {
        pathPrefixList.add(pathPrefix);
    }

    /**
     * @return string of pathPrefixes, which are separated with carriage return between pathPrefixes
     */
    public String getPathPrefixes() {
        return getDataValues("pathPrefix");
    }

    /**
     * @param mimeType name declared in "data android:mimeType="
     */
    public void addMimeType(String mimeType) {
        mimeTypeList.add(mimeType);
    }

    /**
     * @return string of mimeTypes, which are separated with carriage return between mimeTypes
     */
    public String getMimeTypes() {
        return getDataValues("mimeType");
    }

    private String getDataValues(String type) {
        List<String> datas = null;
        if (type.equalsIgnoreCase("category")) {
            datas = categoryList;
        } else if (type.equalsIgnoreCase("scheme")) {
            datas = schemeList;
        } else if (type.equalsIgnoreCase("host")) {
            datas = hostList;
        } else if (type.equalsIgnoreCase("port")) {
            datas = portList;
        } else if (type.equalsIgnoreCase("path")) {
            datas = pathList;
        } else if (type.equalsIgnoreCase("pathPattern")) {
            datas = pathPatternList;
        } else if (type.equalsIgnoreCase("pathPrefix")) {
            datas = pathPrefixList;
        } else if (type.equalsIgnoreCase("mimeType")) {
            datas = mimeTypeList;
        } else {
            return "";
        }

        return Joiner.on('\n').skipNulls().join(datas);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Feature) {
            Feature other = (Feature) obj;

            return ComparisonChain.start()
                    .compare(type, other.type, STRING_COMPARATOR)
                    .compare(className, other.className, STRING_COMPARATOR)
                    .compare(actionName, other.actionName, STRING_COMPARATOR)
                    .compare(categoryList, other.categoryList, STRING_LIST_COMPARATOR)
                    .compare(schemeList, other.schemeList, STRING_LIST_COMPARATOR)
                    .compare(hostList, other.hostList, STRING_LIST_COMPARATOR)
                    .compare(portList, other.portList, STRING_LIST_COMPARATOR)
                    .compare(pathList, other.pathList, STRING_LIST_COMPARATOR)
                    .compare(pathPatternList, other.pathPatternList, STRING_LIST_COMPARATOR)
                    .compare(pathPrefixList, other.pathPrefixList, STRING_LIST_COMPARATOR)
                    .compare(mimeTypeList, other.mimeTypeList, STRING_LIST_COMPARATOR)
                    .result() == 0;
        } else {
            return false;
        }
    }

    private Comparator<List<String>> STRING_LIST_COMPARATOR = new Comparator<List<String>>() {
        @Override
        public int compare(List<String> myList, List<String> yourList) {
            if (myList.size() != yourList.size()) {
                return -1;
            }
            for (String yourString : yourList) {
                if (!myList.contains(yourString.trim())) {
                    return -1;
                }
            }
            return 0;
        }
    };

    private Comparator<String> STRING_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String myString, String yourString) {
            if (myString != null && yourString == null) {
                return -1;
            }
            if (myString == null && yourString != null) {
                return -1;
            }
            if (myString == null && yourString == null) {
                return 0;
            }
            if (myString.trim().equals(yourString.trim())) {
                return 0;
            }
            return -1;
        }
    };
}
