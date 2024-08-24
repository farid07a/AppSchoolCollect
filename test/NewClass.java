
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author client
 */
public class NewClass {
    
    public static void main(String[] args) {
//        LocalDate today = LocalDate.now();
//        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
//        String dayName = today.format(dayFormatter);
//        System.out.println(""+dayName);

          LocalDate startDate = LocalDate.of(2024, 8, 15);
     if(startDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)){
        // Find the next Sunday after the given date
             System.out.println("THURSDAY" );

     }else{
        LocalDate nextSunday = startDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));       
        System.out.println("The next Sunday after " + startDate + " is " + nextSunday);
     }
    }
    
    public String getday(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");
        String dayName = today.format(dayFormatter);
        switch ( dayName) {
            case "dimanche":
                return "الأحد";
            case "lundi":
                return "الإثنين";
            case "mardi":
                return "الثلاثاء";
            case "mercredi":
                return "الأربعاء";
            case "jeudi":
                return "الخميس";
            case "vendredi":
                return "الجمعة";
            case "samedi":
                return "السبت";
            default:
                throw new AssertionError();
        }
    
    }
    
}
