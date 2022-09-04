package knubisoft.base.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class DateTasksImpl implements DateTasks {

    @Override
    public String add1Day(String date) {
        LocalDate res = LocalDate.parse(date);
        res = res.plusDays(1);
        return String.valueOf(res);
    }

    @Override
    public int getMonthFromDate(String date) throws ParseException {
        Date formatter = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(formatter);
        return cal.get(Calendar.MONTH) + 1;
    }

    @Override
    public String findBiggestDate(String date1, String date2, String date3) {
        LocalDateTime first = LocalDateTime.parse(date1.replace(" ", "T"));
        LocalDateTime second = LocalDateTime.parse(date2.replace(" ", "T"));
        LocalDateTime third = LocalDateTime.parse(date3.replace(" ", "T"));
        List<LocalDateTime> res = new ArrayList<>();
        res.add(first);
        res.add(second);
        res.add(third);
        Collections.sort(res);
        LocalDateTime date = res.get(res.size() - 1);
        String s = String.valueOf(date);
        s = s.replace("T", " ");
        return s;
    }

    @Override
    public String getLastDayOfTheMonth(String date) {
        LocalDate d = LocalDate.parse(date);
        Temporal res = TemporalAdjusters.lastDayOfMonth().adjustInto(d);
        return String.valueOf(res);
    }

    @Override
    public String sumTimes(String time1, String time2) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime1 = LocalTime.parse(time1, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime localTime2 = LocalTime.parse(time2, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return localTime1.plusHours(localTime2.getHour())
                .plusMinutes(localTime2.getMinute())
                .plusSeconds(localTime2.getSecond())
                .format(dateTimeFormatter);
    }

    @Override
    public String getDateAfter2Weeks(String date) {
        LocalDate d = LocalDate.parse(date);
        d = d.plusWeeks(2);
        return String.valueOf(d);
    }

    @Override
    public long getNumberOfDaysBetweenTwoDates(String date1, String date2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = simpleDateFormat.parse(date1);
        Date d2 = simpleDateFormat.parse(date2);
        return ((d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000));
    }

    @Override
    public String[] getTheNextAndPreviousFriday(String date) {
        String[] res = new String[2];
        LocalDate ld = LocalDate.parse(date);
        res[0] = ld.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)).toString();
        res[1] = ld.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).toString();
        return res;
    }

    @Override
    public int getNumberOfMonthsRemainingInTheYear(String date) {
        LocalDate ld = LocalDate.parse(date);
        return 12 - ld.getMonthValue();
    }
}
