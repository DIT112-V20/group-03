package online.quar.application;

import online.quar.application.controller.TestRestfulController;
import online.quar.application.model.TestCar;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestCar.class,
        TestRestfulController.class
//        TestJunit2.class
})

public class JunitTestSuite {
}