package ru.rzd.weather.clients;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.rzd.weather.entity.Excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.IntStream;

@Component
@PropertySource("/parser.yaml")
public class Parser {
    private final Logger logger = LoggerFactory.getLogger(Parser.class);
    private static final ArrayList<String> MONTHES =new ArrayList<String>(Arrays.asList("янв","фев","мар","апр","май","июн","июл","авг","сен","окт","ноя","дек"));
    private static HashMap<String,Integer> cells;
    static {
        cells = new HashMap<>();
        cells.put("A", 0);
        cells.put("B", 1);
        cells.put("C", 2);
        cells.put("D", 3);
        cells.put("E", 4);
        cells.put("F", 5);
        cells.put("G", 6);
        cells.put("H", 7);
        cells.put("I", 8);
        cells.put("J", 9);
        cells.put("K", 10);
        cells.put("L", 11);
        cells.put("M", 12);
        cells.put("N", 13);
        cells.put("O", 14);
        cells.put("P", 15);
        cells.put("Q", 16);
        cells.put("R", 17);
        cells.put("S", 18);
        cells.put("T", 19);
        cells.put("U", 20);
        cells.put("V", 21);
        cells.put("W", 22);
        cells.put("X", 23);
        cells.put("Y", 24);
        cells.put("Z", 25);

        cells.put("AA", 26);
        cells.put("AB", 27);
        cells.put("AC", 28);
        cells.put("AD", 29);
        cells.put("AE", 30);
        cells.put("AF", 31);
        cells.put("AG", 32);
        cells.put("AH", 33);
        cells.put("AI", 34);
        cells.put("AJ", 35);
        cells.put("AK", 36);
        cells.put("AL", 37);
        cells.put("AM", 38);
        cells.put("AN", 39);
        cells.put("AO", 40);
        cells.put("AP", 41);
        cells.put("AQ", 42);
        cells.put("AR", 43);
        cells.put("AS", 44);
        cells.put("AT", 45);
        cells.put("AU", 46);
        cells.put("AV", 47);
        cells.put("AW", 48);
        cells.put("AX", 49);
        cells.put("AY", 50);
        cells.put("AZ", 51);

        cells.put("BA", 52);
        cells.put("BB", 53);
        cells.put("BC", 54);
        cells.put("BD", 55);
        cells.put("BE", 56);
        cells.put("BF", 57);
        cells.put("BG", 58);
        cells.put("BH", 59);
        cells.put("BI", 60);
        cells.put("BJ", 61);
        cells.put("BK", 62);
        cells.put("BL", 63);
        cells.put("BM", 64);
        cells.put("BN", 65);
        cells.put("BO", 66);
        cells.put("BP", 67);
        cells.put("BQ", 68);
        cells.put("BR", 69);
        cells.put("BS", 70);
        cells.put("BT", 71);
        cells.put("BU", 72);
        cells.put("BV", 73);
        cells.put("BW", 74);
        cells.put("BX", 75);
        cells.put("BY", 76);
        cells.put("BZ", 77);
    }
    @Value("${sheetID}")
    private String parserSheetID;
    @Value("${dataReportCol}")
    private String dataReport_col;
    @Value("${dataReportRow}")
    private String dataReport_row;
    @Value("${vidOsadkovN}")
    private String[] vidOsadkovN;
    @Value("${vidOsadkovD}")
    private String[] vidOsadkovD;
    @Value("${vidOsadkovStart}")
    private String vidOsadkovStart;
    @Value("${vidOsadkovEnd}")
    private String vidOsadkovEnd;

    public void parse(String name) throws ParserException{
        try (InputStream inp = new FileInputStream(name))
        {
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(Integer.parseInt(parserSheetID));
            Excel excel = Excel.builder().date(dataReport(sheet)).build();
            vidOsadkov(sheet,excel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ParserException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParserException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParserException(e.getMessage());
        }
    }

    private Date dataReport(Sheet sheet) throws ParserException{

        String errorMessage;

        int date=0;
        int month=0;

        int iRow = Integer.parseInt(dataReport_row);
        int iCell = Integer.parseInt(dataReport_col);

        Row row = sheet.getRow(iRow);
        Cell cell = row.getCell(iCell);

        String result = cell.getStringCellValue();
        if (result!=null){

            try{
               date = Integer.parseInt(result.substring(2,4));
            } catch (Exception e){
                errorMessage = String.format("Ошибка парсинга даты в ячейке row={} cell={} value={}",iRow, iCell, result);
                logger.error(errorMessage);
                throw new ParserException(errorMessage);
            }

            if ((date<1)||(date>31)){
                errorMessage = String.format("Ошибка парсинга даты в ячейке row={} cell={} value={}",iRow, iCell, result);
                logger.error(errorMessage);
                throw new ParserException(errorMessage);
            }

            try{
                month = MONTHES.indexOf(result.substring(5).toLowerCase())+1;
            } catch (Exception e){
                errorMessage = String.format("Ошибка парсинга месяца в ячейке row={} cell={} value={}",iRow, iCell, result);
                logger.error(errorMessage);
                throw new ParserException(errorMessage);
            }

            if((month<1)||(month>12)){
                errorMessage = String.format("Ошибка парсинга месяца в ячейке row={} cell={} value={}",iRow, iCell, result);
                logger.error(errorMessage);
                throw new ParserException(errorMessage);
            }
        } else {
            errorMessage = String.format("Ошибка парсинга пустой ячейки row={} cell={}",iRow, iCell);
            logger.error(errorMessage);
            throw new ParserException(errorMessage);
        }
        logger.debug("dataReport row={} cell={} value={}",Integer.parseInt(dataReport_row),Integer.parseInt(dataReport_col),result);

        Date dt = new Date();
        dt.setDate(date);
        dt.setMonth(month);
        return dt;
    }

    private void vidOsadkov(Sheet sheet, Excel excel){
        int iRowStart = Integer.parseInt(vidOsadkovStart);
        int iRowEnd = Integer.parseInt(vidOsadkovEnd);

        IntStream.range(iRowStart,iRowEnd).forEach(iRow->{
            Row row = sheet.getRow(iRow);

            Arrays.stream(vidOsadkovN).forEach(sCell->{
                Cell cell = row.getCell(cells.get(sCell));
                String result = cell.getStringCellValue();
            });



        });

    }

}
