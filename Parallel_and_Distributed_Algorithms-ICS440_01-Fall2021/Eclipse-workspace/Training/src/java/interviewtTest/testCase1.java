package interviewtTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import interview.*;

class testCase1 {
    Main main;

    @BeforeEach
    protected void setUp() {
        main = new Main();
    }

    @Test
    @DisplayName("Test for interview")
    void test() {
        assertEquals("test", main.testString(), "Main test should return 'test' string");
    }

}
