package com.douban.query.model;

/**
 * TODO completion javadoc.
 *
 * @author haoran.duan
 * @since 26 九月 2018
 */
public class IndexInfo {

        private static final int ONE_MINUTE_MILLS = 60 * 1000;

        /**
         * 指标code
         */
        private String indexCode;
        /**
         * 指标名称
         */
        private String indexName;



        private String actorType;

        /**
         * 数据所在表名
         */
        private String sourceTableName;
        /**
         * 数据的指标key
         */
        private String sourceTableKey;


        private String indexDataType;

        private IndexRateData rateData;

        /**
         * 是否需要同步数仓数据
         */
        private boolean needSyncData;

        /**
         * 同步数据持续时长（分钟）
         */
        private long syncTime = 15;

}
class IndexRateData {

    /**
     * 分子
     */
    private String numeratorKey;
    /**
     * 分母
     */
    private String denominatorKey;
}