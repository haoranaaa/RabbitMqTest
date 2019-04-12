package com.guman.common.factory;

/**
 * @author duanhaoran
 * @since 2019/4/1 5:10 PM
 */
public interface ServiceFactory {

    <T> T getService(Class<T> type , String name);
}
