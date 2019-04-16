package com.guman.config.client.store.support;

import com.guman.config.client.store.ConfigStore.Snapshot;
import com.guman.config.client.store.ConfigStore.Version;

/**
 * {@link Snapshot} 默认实现。
 *
 * @author duanhaoran
 * @since 2019/4/16 11:34 AM
 */
class DefaultSnapshot implements Snapshot<String> {

    private final Version version;

    private final String content;

    public DefaultSnapshot(Version version, String content) {
        this.version = version;
        this.content = content;
    }

    @Override
    public Version getVersion() {
        return version;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultSnapshot)) return false;

        DefaultSnapshot that = (DefaultSnapshot) o;

        if (!version.equals(that.version)) return false;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        int result = version.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DefaultSnapshot{" +
                "version=" + version +
                ", content='" + content + '\'' +
                '}';
    }
}
