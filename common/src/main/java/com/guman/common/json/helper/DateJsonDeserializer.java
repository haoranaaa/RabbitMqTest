package com.guman.common.json.helper;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author duanhaoran
 * @since 2019/4/16 10:48 AM
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {
    public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public DateJsonDeserializer() {
    }

    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        if (date != null && !date.isEmpty()) {
            try {
                return DateUtils.parseDate(date, new String[]{"yyyy-MM-dd HH:mm:ss"});
            } catch (ParseException var5) {
                throw new JsonParseException(jsonParser, "cannot parse date string: " + date, jsonParser.getCurrentLocation(), var5);
            }
        } else {
            return null;
        }
    }
}