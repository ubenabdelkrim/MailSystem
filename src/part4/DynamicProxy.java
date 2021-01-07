package part4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import part1.mailServicePack.MailService;
import part1.mailServicePack.MailServiceInterface;

public class DynamicProxy implements InvocationHandler {
    private Object target;
    InvocationsLog invocationsLog=new InvocationsLog();

    public static MailServiceInterface newInstance(Object target) {
        Class targetClass = target.getClass();
        Class interfaces[] = targetClass.getInterfaces();
        if(MailService.getAnnotation().log()){
            return (MailServiceInterface) Proxy.newProxyInstance(targetClass.getClassLoader(),
                    interfaces, new DynamicProxy(target));
        }
        return (MailService) target;
    }

    private DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invocationResult = null;
        try {
            invocationResult = method.invoke(this.target, args);
            invocationsLog.add(method.getName());
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

