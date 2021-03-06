package com.lge.lai.common.data;

import java.util.Comparator;
import java.util.List;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

public class FeatureProvider {
    public String packageName;
    public String versionName;
    public List<Feature> featureList = Lists.newArrayList();

    public void addFeature(Feature feature) {
        featureList.add(feature);
    }

    public void addAllFeatures(List<Feature> featureList) {
        this.featureList.addAll(featureList);
    }

    @Override public boolean equals(Object obj) {
        if (obj instanceof FeatureProvider) {
            FeatureProvider other = (FeatureProvider)obj;
            int result = ComparisonChain.start()
                    .compare(packageName, other.packageName, STRING_COMPARATOR)
                    .compare(versionName, other.versionName, STRING_COMPARATOR).result();
            if (result != 0) {
                return false;
            }

            List<Feature> myFeatureList = this.featureList;
            List<Feature> yourFeatureList = other.featureList;
            if (myFeatureList.size() == yourFeatureList.size()) {
                for (Feature yourFeature : yourFeatureList) {
                    if (!hasSameFeature(yourFeature)) {
                        return false;
                    }
                }
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean hasSameFeature(Feature feature) {
        for (Feature myFeature : this.featureList) {
            if (myFeature.equals(feature)) {
                return true;
            }
        }
        return false;
    }

    private Comparator<String> STRING_COMPARATOR = new Comparator<String>() {
        @Override public int compare(String myString, String yourString) {
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
