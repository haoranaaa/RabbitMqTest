package com.dhr.asm;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.NotFoundException;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * @author duanhaoran
 * @since 2019/11/2 4:42 PM
 */
public class MainTest {

    public static void main(String[] args,Instrumentation inst) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new AopClassFileTransform(), true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(Base.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }

}
