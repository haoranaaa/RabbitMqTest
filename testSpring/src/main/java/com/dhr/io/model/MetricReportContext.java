package com.dhr.io.model;

/**
 * @author duanhaoran
 * @since 2020/7/4 1:54 PM
 */
public class MetricReportContext {

    private String appName;

    private String ip;

    private Integer port;

    private long timestamp;

    private byte version;

    private String metricData;

    public String getAppName() {
        return appName;
    }

    public MetricReportContext setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public MetricReportContext setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public MetricReportContext setPort(Integer port) {
        this.port = port;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public MetricReportContext setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public byte getVersion() {
        return version;
    }

    public MetricReportContext setVersion(byte version) {
        this.version = version;
        return this;
    }

    public String getMetricData() {
        return metricData;
    }

    public MetricReportContext setMetricData(String metricData) {
        this.metricData = metricData;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MetricReportContext{");
        sb.append("appName='").append(appName).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", port=").append(port);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", version=").append(version);
        sb.append(", metricData='").append(metricData).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

