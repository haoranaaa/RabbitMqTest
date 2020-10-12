package com.dhr.asm;

import com.dhr.annotation.Aop;
import org.apache.ibatis.javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

/**
 * @author duanhaoran
 * @since 2019/11/2 5:14 PM
 */
public class AopClassFileTransform implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            Class<?> clazz = Class.forName(className);
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get(clazz.getName());
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(Aop.class)) {
                    continue;
                }
                CtMethod m = null;
                m = cc.getDeclaredMethod(method.getName());
                m.insertBefore("{ System.out.println(\"start\"); }");
                m.insertAfter("{ System.out.println(\"end\"); }");
            }
            return cc.toBytecode();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}
