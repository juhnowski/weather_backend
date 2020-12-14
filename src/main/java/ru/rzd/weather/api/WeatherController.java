package ru.rzd.weather.api;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rzd.weather.dto.GenericResponse;
import ru.rzd.weather.entity.Excel;
import ru.rzd.weather.stub.Stub;

import java.util.GregorianCalendar;

@RestController
@RequiredArgsConstructor
@Api(value = "Данные", tags = "Weather")
public class WeatherController {
 //   private final ExcelService excelService;

//    @RequestMapping(value="/api/weather/all", method= RequestMethod.GET)
//    public GenericResponse findAll(){
//        return GenericResponse.of(excelService.findAll());
//    }

    @RequestMapping(value="/api/weather", method= RequestMethod.GET)
    public GenericResponse findAll(@RequestParam int day, @RequestParam int month){

        Excel stub = Stub.stub();
        stub.setDate(new GregorianCalendar(2019, month, day).getTime());

        return GenericResponse.of(stub);
    }

}