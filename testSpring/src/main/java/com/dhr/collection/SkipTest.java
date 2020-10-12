package com.dhr.collection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.guman.common.json.JSON;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author duanhaoran
 * @since 2019/10/17 11:27 AM
 */
public class SkipTest {

    public static void main(String[] args) {
        IntStream.range(0,100).boxed().map(i->ABSAC.builder().b(i).c(i%3).state(i%5).build())
                .sorted(Comparator.comparing(ABSAC::getState).thenComparing((a,b)->{
                    if (Objects.equals(a.getState(), b.getState()) && Objects.equals(a.getState(), 1)) {
                        return a.getC() - b.getC();
                    }
                    return a.getB() - b.getB();
                })).forEach(System.out::println);

    }

    @Data
    @Builder
    public static class ABSAC{
        private int state;

        private int b;

        private int c;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("ABSAC{");
            sb.append("state=").append(state);
            sb.append(", b=").append(b);
            sb.append(", c=").append(c);
            sb.append('}');
            return sb.toString();
        }
    }

}
