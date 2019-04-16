package com.guman.common.constant;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author duanhaoran
 * @since 2019/4/15 8:23 PM
 */
public class CommonConstants {

    public static final String DEFAULT_PARAMETER = "default";

    public static final String STRING_EMPTY = "";

    public static final String COMMA = ",";

    public static final String PIPE = "|";

    public static final String EQUAL = "=";

    public static final String COLON = ":";

    public static final String POINT = ".";

    public static final String UNDERLINE = "_";

    public static final String LINE = System.getProperty("line.separator");

    public static final String DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMATTER_PATTERN);

    public static final String DATE_TIME_FORMATTER_NO_MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter DATE_TIME_FORMATTER_NO_MILLIS = DateTimeFormat.forPattern(DATE_TIME_FORMATTER_NO_MILLIS_PATTERN);

    public static final Splitter SPLITTER_COMMA = Splitter.on(COMMA).omitEmptyStrings().trimResults();

    public static final Splitter SPLITTER_PIPE = Splitter.on(PIPE).omitEmptyStrings().trimResults();

    public static final Splitter SPLITTER_EQUAL = Splitter.on(EQUAL).omitEmptyStrings().trimResults();

    public static final Splitter SPLITTER_COLON = Splitter.on(COLON).omitEmptyStrings().trimResults();

    public static final Splitter SPLITTER_POINT = Splitter.on(POINT).omitEmptyStrings().trimResults();

    public static final Splitter SPLITTER_UNDERLINE = Splitter.on(UNDERLINE).omitEmptyStrings().trimResults();

    public static final Splitter SPLITTER_LINE = Splitter.on(LINE).omitEmptyStrings().trimResults();

    public static final Joiner JOINER_COMMA = Joiner.on(COMMA);

    public static final Joiner JOINER_PIPE = Joiner.on(PIPE);

    public static final Joiner JOINER_EQUAL = Joiner.on(EQUAL);

    public static final Joiner JOINER_COLON = Joiner.on(COLON);

    public static final Joiner JOINER_POINT = Joiner.on(POINT);

    public static final Joiner JOINER_UNDERLINE = Joiner.on(UNDERLINE);

    public static final Joiner JOINER_LINE = Joiner.on(LINE);

    public static final CharMatcher MATCHER_COMMA = CharMatcher.is(COMMA.charAt(0));

    public static final CharMatcher MATCHER_PIPE = CharMatcher.is(PIPE.charAt(0));

    public static final CharMatcher MATCHER_EQUAL = CharMatcher.is(EQUAL.charAt(0));

    public static final CharMatcher MATCHER_COLON = CharMatcher.is(COLON.charAt(0));

    public static final CharMatcher MATCHER_POINT = CharMatcher.is(POINT.charAt(0));

    public static final CharMatcher MATCHER_UNDERLINE = CharMatcher.is(UNDERLINE.charAt(0));

    public static final CharMatcher MATCHER_LINE = CharMatcher.is(LINE.charAt(0));

    public static final String X_REAL_WORMPEX_HOSTNAME = "X-Real-Wormpex-HostName";

    public static final String X_REAL_WORMPEX_IP = "X-Real-Wormpex-IP";

    public static final String ACCEPT_JSON = "application/json";

    public static final String CONTENT_TYPE_HTML = "text/html; charset=" + Charsets.UTF_8;

    public static final String CONTENT_TYPE_JSON = "application/json; charset=" + Charsets.UTF_8;

    public static final String CONTENT_TYPE_JAVASCRIPT = "application/javascript; charset=" + Charsets.UTF_8;

    public static final String CONTENT_TYPE_PLAIN = "text/plain; charset=" + Charsets.UTF_8;
}

