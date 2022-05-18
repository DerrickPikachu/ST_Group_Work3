import static org.junit.jupiter.api.Assertions.*;


class ThermostatTest {
    private Thermostat thermo;
    private ProgrammedSettings settings;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        thermo = new Thermostat();
        settings = new ProgrammedSettings();
        thermo.setPeriod(Period.MORNING);
        thermo.setDay(DayType.WEEKDAY);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        thermo = null;
        settings = null;
    }

    @org.junit.jupiter.api.Test
    void PCTestTrueTurnHeaterOn() {
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp(63);
        thermo.setThresholdDiff(5);
        thermo.setOverride(true);
        thermo.setOverTemp(70);
        thermo.setMinLag(10);
        thermo.setTimeSinceLastRun(12);
        assertTrue(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void PCTestOverrideFalseTurnHeaterOn() {
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp(63);
        thermo.setThresholdDiff(5);
        thermo.setOverride(false);
        thermo.setOverTemp(70);
        thermo.setMinLag(10);
        thermo.setTimeSinceLastRun(12);
        assertTrue(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void PCTestFalseTurnHeaterOn() {
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 60);
        thermo.setCurrentTemp(69);
        thermo.setThresholdDiff(5);
        thermo.setMinLag(10);
        thermo.setTimeSinceLastRun(5);
        assertFalse(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CCTestTurnHeaterOn() {
        // The CC test of TTTT
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp(63);
        thermo.setThresholdDiff(5);
        thermo.setOverride(true);
        thermo.setOverTemp(70);
        thermo.setMinLag(10);
        thermo.setTimeSinceLastRun(12);
        assertTrue(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CCTestTurnHeaterOn2() {
        // The CC test of FFFF
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 60);
        thermo.setCurrentTemp(65);
        thermo.setThresholdDiff(5);
        thermo.setOverride(false);
        thermo.setOverTemp(66);
        thermo.setMinLag(10);
        thermo.setTimeSinceLastRun(5);
        assertFalse(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CACCTestTurnHeaterOn() {
        // The CACC test of TTFT (1)
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        thermo.setOverride (true);
        thermo.setOverTemp (67);
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        assertTrue(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CACCTestTurnHeaterOn() {
        // The CACC test of FTFT (2)
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp (66); // a is false
        thermo.setThresholdDiff (5);
        thermo.setOverride (true);
        thermo.setOverTemp (67); // c is false
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        assertFalse(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CACCTestTurnHeaterOn() {
        // The CACC test of FTTT (3)
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp (66); 
        thermo.setThresholdDiff (5);
        thermo.setOverride (true);
        thermo.setOverTemp (72);
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        assertTrue(thermo.turnHeaterOn(settings));

    }

    @org.junit.jupiter.api.Test
    void CACCTestTurnHeaterOn() {
        // The CACC test of FFTT (4)
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp (66); 
        thermo.setThresholdDiff (5);
        thermo.setOverride (false);
        thermo.setOverTemp (72);
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        assertFalse(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CACCTestTurnHeaterOn() {
        // The CACC test of TTTT (5)
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        thermo.setOverride (true);
        thermo.setOverTemp (72);
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (12);
        assertTrue(thermo.turnHeaterOn(settings));
    }

    @org.junit.jupiter.api.Test
    void CACCTestTurnHeaterOn() {
        // The CACC test of TTTF (6)
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, 69);
        thermo.setCurrentTemp (63);
        thermo.setThresholdDiff (5);
        thermo.setOverride (true);
        thermo.setOverTemp (72);
        thermo.setMinLag (10);
        thermo.setTimeSinceLastRun (8);
        assertTrue(thermo.turnHeaterOn(settings));
    }
}