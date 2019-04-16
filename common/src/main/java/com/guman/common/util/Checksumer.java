package com.guman.common.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.util.StringUtils;

/**
 * @author duanhaoran
 * @since 2019/4/16 11:21 AM
 */
public class Checksumer {

    private static final String NO_FILE_CHECKSUM = Hashing.md5().hashString("Checksumer.NO_FILE_CHECKSUM", Charsets.UTF_8).toString();

    public static String checksum(String data) {
        if (StringUtils.hasText(data)) {
            return NO_FILE_CHECKSUM;
        }
        return Hashing.md5().hashString(data, Charsets.UTF_8).toString();
    }
}
