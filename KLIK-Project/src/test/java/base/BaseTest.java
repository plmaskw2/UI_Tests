package base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

//TestInstance to avoid setup be static.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest extends WebBaseTestJUnit {
    @BeforeAll
    public void setup() {
        initializeStepdefs();
    }
}
