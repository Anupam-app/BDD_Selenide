package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import org.junit.Assert;

public class TimezoneUtils {

    private static int timezoneSeconds;

    public static void setTimezoneDiffInSecondsFromProperties() throws IOException {
        InputStream input = new FileInputStream("config/offset-timezone.properties");
        Properties prop = new Properties();
        prop.load(input);
        var timezonesecondsFromProperties = prop.getProperty("offset.timezone.total.seconds");
        timezoneSeconds = Integer.parseInt(timezonesecondsFromProperties);
    }

    public static void compareDateFromLocalToDistantServer(String message, LocalDateTime dateTimeLocal, LocalDateTime dateTimeDistantServer) {
        var offsetDateTimeLocal = dateTimeLocal.atOffset(OffsetDateTime.now().getOffset());
        var offsetDateDistantServer = dateTimeDistantServer.atOffset(ZoneOffset.ofTotalSeconds(timezoneSeconds));
        Assert.assertEquals(message, offsetDateTimeLocal.toInstant(), offsetDateDistantServer.toInstant());
    }

    public static void compareDateFromLocalToDistantServer(String message, String dateTimeLocalString, String dateTimeDistantServerString, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat).localizedBy(Locale.ENGLISH);
        LocalDateTime dateTimeLocal = LocalDateTime.parse(dateTimeLocalString, formatter);
        LocalDateTime dateTimeDistantServer = LocalDateTime.parse(dateTimeDistantServerString, formatter);
        compareDateFromLocalToDistantServer(message, dateTimeLocal, dateTimeDistantServer);
    }
}
