package com.dhr.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
/**
 *
 * @author haoran.duan
 * @since 27 十二月 2018
 */
public class DynamicInvokeTest {

    public static void main(String[] args) throws Throwable
    {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        Point point = new Point();

        // Set the x and y fields.

        MethodHandle mh = lookup.findSetter(Point.class, "x",
                int.class);
        mh.invoke(point, 15);

        mh = lookup.findSetter(Point.class, "y", int.class);
        mh.invoke(point, 30);

        mh = lookup.findGetter(Point.class, "x", int.class);
        int x = (int) mh.invoke(point);
        System.out.printf("x = %d%n", x);

        mh = lookup.findGetter(Point.class, "y", int.class);
        int y = (int) mh.invoke(point);
        System.out.printf("y = %d%n", y);
    }

}
class Point
{
    int x;
    int y;
}
