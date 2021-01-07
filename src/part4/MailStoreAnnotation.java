package part4;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/**
 * MailStoreAnnotation interface
 */
public @interface MailStoreAnnotation{
    /**
     * Store Name
     * @return name
     */
    String store();

    /**
     * Log boolean
     * @return boolean
     */
    boolean log();
}