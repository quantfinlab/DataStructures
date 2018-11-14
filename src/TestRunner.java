import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/*
* We run the Junit test class for 10 times and check the result.
* Size of the tree(random) and inserted elements(random) are displayed.
* The true is result is generated if all the tests are passed for both the normal and the worst case.
* Worst case it the insertion of elements in increasing order. (Right-right-right-.... tree)
* */
public class TestRunner {
    public static void main(String[] args) {

        for (int i = 0; i <= 10; i++) {
            System.out.println("Test: "+i);
            Result result = JUnitCore.runClasses(TestJunit.class);

            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }

            if(result.wasSuccessful())
                System.out.println("All test passed for both normal and worst case");
            else
                System.out.println("Failure");
        }
    }
}