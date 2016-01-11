import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Amysue on 2016/1/10.
 */
public class TestDate {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss z");
        Date d = new Date();
        System.out.println(d.getTime());
        System.out.println(sdf.format(d));
        long times = d.getTime();
        long year = 1000 * 60 * 60 * 24 * 365;
        long years = (((times / 1000) / 3600)/24)/365;
        System.out.println("years = " + years);
        String timeStr = "1970-01-01 00:00:00 PST";
        String tStr1 = "1978-12-22 00:00:00 CST";
        String tStr2 = "1998-12-30 23:59:59 CST";
        Date start;
        Date d1;
        Date d2;
        try {
            start = sdf.parse(timeStr);
            d1 = sdf.parse(tStr1);
            d2 = sdf.parse(tStr2);
            System.out.println(start.getTime());
            long d1Time = d1.getTime();
            long d2Time = d2.getTime();
            long gap = d2Time - d1Time;
            System.out.println("d1.getTime() = " + d1Time);
            System.out.println("d2.getTime() = " + d2Time);
            System.out.println(gap);
            Random r = new Random();
            Set<Long> dSets = new HashSet<>(30);
            while (dSets.size() < 30){
                double tmp = r.nextDouble();
                double a = Math.pow(10, 12);
                long aTime = Math.round(tmp * a);
                if (aTime <= gap) {
                    dSets.add(aTime + d1Time);
                }
            }
            for (long at : dSets) {
                System.out.println(sdf.format(at));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
