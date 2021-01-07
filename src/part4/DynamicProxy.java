package part4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import part1.mailServicePack.MailService;
import part1.mailServicePack.MailServiceInterface;

/**
 * DynamicProxy implementing Proxy Pattern and Singleton Pattern
 * @author Usama Benabdelkrim Zakan
 */
public class DynamicProxy implements InvocationHandler {
    private Object target;
    InvocationsLog invocationsLog=new InvocationsLog();

    /**
     * Method to get newInstance
     * @param target Target Object
     * @return MailServiceInterface newInstance
     */
    public static MailServiceInterface newInstance(Object target) {
        Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        if(MailService.getAnnotation().log()){
            return (MailServiceInterface) Proxy.newProxyInstance(targetClass.getClassLoader(),
                    interfaces, new DynamicProxy(target));
        }
        return (MailService) target;
    }

    /**
     * Private Constructor (Singleton Pattern)
     * @param target Target Object
     */
    private DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * invoke method
     * @param proxy Proxy Object
     * @param method Method to inovke
     * @param args args
     * @return new Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invocationResult = null;
        try {
            invocationResult = method.invoke(this.target, args);
            invocationsLog.add(method.getName());
            System.out.println(method.getName());
        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
            e.printStackTrace();
        }
        finally{
            return invocationResult;
        }
    }
}

