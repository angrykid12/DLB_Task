package testings;
import buses.Bus;
import org.junit.*;

import static org.junit.Assert.*;


import java.time.LocalTime;

public class BusTest {
    private Bus bus;
    private String excepted;


    @Before
    public void setUp() {

         bus = new Bus();
    }

    @After
    public void tearDown() {
         bus = null;
    }

    @Test()
    public void testRideToString_Succeed() {
        Bus bus = new Bus("Posh", LocalTime.of(13, 20), LocalTime.of(12, 41));
        excepted = "Posh 13:20 12:41";
        assertEquals(excepted, bus.toString());

    }

    @Test(expected = Exception.class)
    public void testRideToString_Fail() {
        Bus bus = new Bus("Posh", LocalTime.of(121, 20), LocalTime.of(12, 40));
        excepted = "Posh 12:20 12:40";
        assertEquals(excepted, bus.toString());

    }


    @Test(expected = AssertionError.class)
    public void testStringToRide() {
        Bus bus = new Bus("Grotty",LocalTime.of(11, 20), LocalTime.of(12, 40));
        excepted = "Grotty 11:20 12:40";
        assertEquals(bus, Bus.stringToRide(excepted));
    }

    @Test
    public void testGetDifferenceInMinutes_Succeed() {
        Bus bus = new Bus("Grotty",LocalTime.of(11, 20), LocalTime.of(12, 40));
        int minutes = 80;
        assertEquals(minutes, bus.getDifferenceInMinutes());
    }


    @Test(expected = AssertionError.class)
    public void testGetDifferenceInMinutes_Fail() {
        Bus bus = new Bus("Grotty",LocalTime.of(14, 20), LocalTime.of(12, 40));
        int minutes = 830;
        assertEquals(minutes, bus.getDifferenceInMinutes());
    }

    @Test
    public void testLessEffective_Succeed_True() {
        Bus bus = new Bus("Posh", LocalTime.of(11, 10), LocalTime.of(12, 40));
        Bus bus1 = new Bus("Posh", LocalTime.of(11, 20), LocalTime.of(12, 40));
        assertTrue(bus.inefficient(bus1));

    }

    @Test
    public void testLessEffective_Succeed_Fail() {
        Bus bus = new Bus("Posh", LocalTime.of(11, 10), LocalTime.of(12, 40));
        Bus bus1 = new Bus("Posh", LocalTime.of(14, 20), LocalTime.of(12, 40));
        assertFalse(bus.inefficient(bus1));
    }


    @Test(expected = AssertionError.class)
    public void testLessEffective_Fail() {
        Bus bus = new Bus("Posh", LocalTime.of(11, 10), LocalTime.of(12, 40));
        Bus bus1 = new Bus("Posh", LocalTime.of(14, 20), LocalTime.of(12, 40));
        assertTrue(bus.inefficient(bus1));

    }

    @Test
    public void getCompanyName_Succeed() {
        Bus bus = new Bus("Posh", LocalTime.of(11, 20), LocalTime.of(12, 40));
        excepted = "Posh";
        assertEquals(excepted, bus.getCompanyName());
    }

    @Test(expected = ComparisonFailure.class)
    public void getCompanyName_Fail() {
        Bus bus = new Bus("Posh", LocalTime.of(11, 20), LocalTime.of(12, 40));
        excepted = "Grotty";
        assertEquals(excepted, bus.getCompanyName());
    }


    @Ignore("Message for ignored test")
    @Test
    public void ignoredTest() {
        System.out.println("Will not print it");
    }

}
