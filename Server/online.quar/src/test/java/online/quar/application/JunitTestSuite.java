package online.quar.application;

import online.quar.application.controller.TestController;
import online.quar.application.model.TestCar;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestCar.class,
        TestController.class
//        TestJunit2.class
})

public class JunitTestSuite {
}