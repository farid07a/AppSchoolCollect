/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PreviousDayOfWeek {
    public static void main(String[] args) {
        // التاريخ المدخل
        String dateStr = "02-09-2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate inputDate = LocalDate.parse(dateStr, formatter);

        // اليوم المطلوب، على سبيل المثال: الأحد
        DayOfWeek desiredDay = DayOfWeek.SUNDAY;

        // العثور على التاريخ الذي يسبق التاريخ المدخل
        LocalDate previousDate = getPreviousDayOfWeek(inputDate, desiredDay);

        // طباعة النتيجة
        System.out.println(previousDate.format(formatter));
    }

    private static LocalDate getPreviousDayOfWeek(LocalDate date, DayOfWeek desiredDay) {
        // العثور على الفرق بالأيام بين اليوم الحالي واليوم المطلوب
        int daysToSubtract = (date.getDayOfWeek().getValue() - desiredDay.getValue() + 7) % 7;
        if (daysToSubtract == 0) {
            daysToSubtract = 7;
        }
        // حساب التاريخ السابق
        return date.minusDays(daysToSubtract);
    }
}
