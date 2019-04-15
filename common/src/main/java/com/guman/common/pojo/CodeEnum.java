package com.guman.common.pojo;

import java.io.Serializable;

/**
 * 带有Code的枚举类型。
 *
 * @author duanhaoran
 * @since 2019/4/15 11:23 AM
 */
public interface CodeEnum<E extends Enum<E>> extends Comparable<E>, Serializable {

    int getCode();
}