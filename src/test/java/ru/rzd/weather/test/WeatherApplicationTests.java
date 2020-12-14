package ru.rzd.weather.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@TestPropertySource("parser.yaml")
public class WeatherApplicationTests {
    @Value("${sheetID}")
    private int SHEET_ID;
    private int CONTROL_SHEET_ID=3;

    @Test
    public void parserSettingsLoads() {
        assertEquals(SHEET_ID,CONTROL_SHEET_ID);
    }
}
