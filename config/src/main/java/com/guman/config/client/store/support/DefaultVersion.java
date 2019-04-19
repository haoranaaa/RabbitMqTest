package com.guman.config.client.store.support;

import com.guman.config.client.store.ConfigStore.Version;

import java.util.Objects;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:17 PM
 */
class DefaultVersion implements Version {

    private final long version;

    private final String profile;

    public DefaultVersion(long version, String profile) {
        this.version = version;
        this.profile = profile;
    }

    static boolean needUpdate(Version version1, Version version2) {
        if (version2 == null) {
            return false;
        }

        if (version2 == DefaultConfigStoreContainer.ABSENT) {
            return false;
        }

        if (version1 == DefaultConfigStoreContainer.ABSENT) {
            return true;
        }

        if (version1.getProfile().equals(version2.getProfile()) && version1.getVersion() >= version2.getVersion()) {
            return false;
        }

        return true;
    }

    @Override
    public long getVersion() {
        return version;
    }

    @Override
    public String getProfile() {
        return profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultVersion)) return false;
        DefaultVersion that = (DefaultVersion) o;
        return version == that.version &&
                Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {

        return Objects.hash(version, profile);
    }

    @Override
    public String toString() {
        return "DefaultVersion{" +
                "version=" + version +
                ", profile='" + profile + '\'' +
                '}';
    }
}
