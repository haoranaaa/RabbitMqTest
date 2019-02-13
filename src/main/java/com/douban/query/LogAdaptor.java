package com.douban.query;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author  wangyao
 * @date  2018/8/28  下午12:02
 * @param <T>
 */
public abstract class LogAdaptor<T>{
    public abstract String diffLog(T o,T n);
    public abstract void nullToNewLog(T n);

    public abstract void oldToNewLog(T o,T n);

    public abstract void oldToNullLog(T o);

    public String commonLog(T o, T n) {
        StringBuilder str = new StringBuilder();
        try {
            Class clazz = o.getClass();
            Field[] fields = n.getClass().getDeclaredFields();
            int i = 1;
            for (Field field : fields) {
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                Method getMethod = pd.getReadMethod();
                Class<?> type= pd.getPropertyType();
                Object o1 = getMethod.invoke(o);
                Object o2 = getMethod.invoke(n);
                if (o1 == null && o2 == null) {
                    continue;
                }
                if (o1 == null){
                    o1="EMPTY";
                }
                if (o2 == null){
                    o2="EMPTY";
                }
                if(type.equals(Date.class)){
                    /*o1 = DateForm((Date) o1);
                    o2 = DateFormatUtils.format4y2M2d2h2m2s((Date) o2);*/
                }
                if (!o1.toString().equals(o2.toString())) {
                    if (i != 1) {
                        str.append(";");
                    }
                    str.append(i + "、" + field.getName() + ":" + o1 + "->" + o2);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }
}
