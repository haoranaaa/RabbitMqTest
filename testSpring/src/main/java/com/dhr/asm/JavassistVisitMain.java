package com.dhr.asm;

import com.dhr.annotation.Aop;
import org.apache.ibatis.javassist.*;
import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author duanhaoran
 * @since 2019/11/2 4:46 PM
 */
public class JavassistVisitMain{



    public <T> T develop(Class<T> clazz) throws NotFoundException, CannotCompileException, IOException, IllegalAccessException, InstantiationException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get(clazz.getName());
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(Aop.class)) {
                continue;
            }
            CtMethod m = cc.getDeclaredMethod(method.getName());
            m.insertBefore("{ System.out.println(\"start\"); }");
            m.insertAfter("{ System.out.println(\"end\"); }");
        }
        Class c = cc.toClass();
        T h = (T)c.newInstance();
        return h;
    }

}
