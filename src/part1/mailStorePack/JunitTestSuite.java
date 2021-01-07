package part1.mailStorePack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        MsFilterByReceiverUsernameTest.class , MsFilterBySenderUsernameTest.class, MailStoreFilterBySenderName.class
})

/**
 * Class with SuiteClasses
 */
public class JunitTestSuite {
}