package com.guman.config.client;

import lombok.Data;

/**
 * @author duanhaoran
 * @since 2019/4/12 8:55 PM
 */
@Data
public class Feature {
    private boolean autoReload = true;

    private long minimumVersion = 0;

    private boolean failOnNotExists = true;

    private boolean trimValue = true;

    public static class Builder{
        private final static Feature feature = new Feature();

        public Builder autoReload(boolean autoReload) {
            feature.setAutoReload(autoReload);
            return this;
        }

        public Builder minimumVersion(long minimumVersion) {
            feature.setMinimumVersion(minimumVersion);
            return this;
        }

        public Builder failOnNotExists(boolean failOnNotExists) {
            feature.setFailOnNotExists(failOnNotExists);
            return this;
        }

        public Builder trimValue(boolean trimValue) {
            feature.setTrimValue(trimValue);
            return this;
        }

        public Feature build(){
            return feature;
        }
    }
}
