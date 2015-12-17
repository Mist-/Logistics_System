package utils;

import java.util.Calendar;

/**
 *
 * Created by mist on 2015/11/16 0016.
 */
public class Timestamper {

    public static String getTimeByDate() {
        Calendar now = Calendar.getInstance();
        String result = "";
        result += now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH)+1) + "/" + now.get(Calendar.DATE);
        return result;
    }
    
    public static String getDir() {
    	Calendar now = Calendar.getInstance();
        String result = "";
        result += now.get(Calendar.YEAR) + "_" + now.get(Calendar.MONTH) + "_" + now.get(Calendar.DATE);
        return result;
    }

    public static String getTimeBySecond() {
        Calendar now = Calendar.getInstance();
        String result = "";
        result += now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH)+1) + "/" + now.get(Calendar.DATE);
        result += now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + ":" +
                "" + now.get(Calendar.SECOND);
        return result;
    }

    public Calendar parseTime(String time) {
        Calendar result = Calendar.getInstance();
        String t1[] = time.split("[ ]");
        String timeString[] = t1[0].split("[/]");
        result.set(Calendar.YEAR, Integer.parseInt(timeString[0]));
        result.set(Calendar.MONTH, Integer.parseInt(timeString[1])-1);
        result.set(Calendar.DATE, Integer.parseInt(timeString[2]));
        if (t1.length == 2) {
            timeString = t1[1].split("[:]");
            result.set(Calendar.HOUR, Integer.parseInt(timeString[0]));
            result.set(Calendar.MINUTE, Integer.parseInt(timeString[1]));
            result.set(Calendar.SECOND, Integer.parseInt(timeString[2]));
        }
        return result;
    }

    public static String getTimeByDate(Calendar calendar) {
        String result = "";
        result += calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE);
        return result;
    }

    public static String getTimeBySecond(Calendar calendar) {
        String result = "";
        result += calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.DATE);
        result += calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" +
                "" + calendar.get(Calendar.SECOND);
        return result;
    }
}
