package cucumber.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import org.junit.Test;
import org.locationtech.jts.util.Assert;


public class UnitTestPdf {

    @Test
    public void playWithDates2() throws IOException {

        //2022-10-17T16:52:26+02:00> but was:<2022-10-17T09:52:26-05:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss").localizedBy(Locale.ENGLISH);
        LocalDateTime dateTimeLocal = LocalDateTime.parse("18/Nov/2022 15:09:56", formatter);
        var offsetDateTimeLocal = dateTimeLocal.atOffset(OffsetDateTime.now().getOffset());
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss a").localizedBy(Locale.ENGLISH);
        LocalDateTime dateTimeLocal2 = LocalDateTime.parse("Nov 18, 2022 3:09:56 PM", formatter2);
        var offsetDateDistantServer = dateTimeLocal2.atOffset(ZoneOffset.ofTotalSeconds(3600));
        //Assert.isTrue(offsetDateTimeLocal.equals(offsetDateDistantServer));
        org.junit.Assert.assertEquals( offsetDateTimeLocal.toInstant(), offsetDateDistantServer.toInstant());

        /*
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                // case insensitive to parse JAN and FEB
                //.parseCaseInsensitive()
                // add pattern
                .appendPattern("dd/MMM/yyyy HH:mm:ss")
                // create formatter (use English Locale to parse month names)
                .toFormatter(Locale.ENGLISH);*/
        /*
        LocalDateTime dateTimeLocal = LocalDateTime.parse("17/Oct/2022 16:33:16", formatter);
        var offsetDateTimeLocal = dateTimeLocal.atOffset(OffsetDateTime.now().getOffset());
        LocalDateTime dateTimeDistantServer = LocalDateTime.parse("17/Oct/2022 09:33:16", formatter);
        var offsetDateDistantServer = dateTimeDistantServer.atOffset(ZoneOffset.ofTotalSeconds(-18000));
        Assert.isTrue(offsetDateTimeLocal.toInstant().equals(offsetDateDistantServer.toInstant()));
        org.junit.Assert.assertEquals("message", offsetDateTimeLocal.toInstant(), offsetDateDistantServer.toInstant());
*/

    }

    @Test
    public void playWithDates() throws IOException {
        //var cipSystemTimeZone="America/Chicago";
        //var tz=TimeZone.getTimeZone(ZoneId.of(cipSystemTimeZone));

        //([datetime]::Now-[datetime]::Now.ToUniversalTime()).hours

        //([datetime]::Now-[datetime]::Now.ToUniversalTime()).TotalSeconds

        //var date=  new Date();

        LocalDateTime dateTime1 = LocalDateTime.parse("2018-05-05T11:50:55");
        //var offsetDateTime1=dateTime1.atOffset(ZoneOffset.ofHours(2));
        var offsetDateTime1 = dateTime1.atOffset(OffsetDateTime.now().getOffset());

        LocalDateTime dateTime2 = LocalDateTime.parse("2018-05-05T04:50:55");
        //var offsetDateTime2=dateTime2.atOffset(ZoneOffset.ofHours(-5));
        var offsetDateTime2 = dateTime2.atOffset(ZoneOffset.ofTotalSeconds(-18000));

        Assert.isTrue(offsetDateTime2.isEqual(offsetDateTime1));


    }


    @Test
    public void getTablesFromTableTitle() throws IOException {
        File file = new File("C:\\Users\\m305415\\Downloads\\Consolidated_different_188.pdf");
        //File file = new File("C:\\Users\\m305415\\Downloads\\Consolidated_same_189.pdf");
        InputStream targetStream = new FileInputStream(file);
        //List<Table> runSummaryTables = PdfTableExtractUtils.getTablesFromTableTitle(targetStream, "Report Summary");

        //var consolidatedReportSummary = runSummaryTables.get(0);
        //var runsFromReport = PdfTableExtractUtils.getTableFieldValue(consolidatedReportSummary, "Runs");


    }


}
