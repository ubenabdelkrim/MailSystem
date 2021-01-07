package part1.mailServiceElements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Message>{

    @Override
    public int compare(Message o1, Message o2) {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yy");
        try {
            Date date1=df.parse(o1.getDate());
            Date date2=df.parse(o2.getDate());
            return date1.compareTo(date2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}
