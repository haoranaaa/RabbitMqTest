package com.guman.common;

import java.util.Map;

/**
 * @author duanhaoran
 * @since 2019/4/15 1:21 PM
 */
public interface Parameters {

    String getParameter(String key);

    String getParameter(String key, String defaultValue);

    Map<String, String> getParameters();

}
