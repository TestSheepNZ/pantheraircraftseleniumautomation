package testsheepnz.panther.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateStringFromTime {

    private String prevUniqueName;
    private int increment;

    public GenerateStringFromTime() {
        prevUniqueName = "";
        increment = 0;
    }

    public String getUniqueName ()
    {
        String newString = new String();
        CharSequence css = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int max = css.length();
        long unixTime = System.currentTimeMillis();
        int index;

        do {
            index = (int)((long)unixTime % (long)max);
            newString = css.charAt(index) + newString;
            unixTime = unixTime / (long)max;
        } while (unixTime > max);

        if (newString.equals(prevUniqueName)) {
            increment++;
            newString = newString + increment;
        } else {
            increment = 0;
        }

        return newString;
    }

    public String getCurrentDate () {
        String dateString = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        return dateString;
    }

    public String getJustTime() {
        String dateString = new SimpleDateFormat("HHmm:ss").format(new Date());
        return dateString;
    }

    public String getJustDate() {
        String dateString = new SimpleDateFormat("dd/MM/YYYY").format(new Date());
        return dateString;
    }
}
