/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import java.time.LocalTime;
import java.time.Duration;

public class CurrentClass {

    public static void main(String[] args) {
        // تعريف أوقات الحصص الدراسية
        LocalTime[] classStartTimes = {
            LocalTime.of(9, 0),  // بداية الحصة الأولى
            LocalTime.of(19, 0)  // بداية الحصة الثانية
        };
        LocalTime[] classEndTimes = {
            LocalTime.of(10, 0), // نهاية الحصة الأولى
            LocalTime.of(14, 0)  // نهاية الحصة الثانية
        };

        // الحصول على الوقت الحالي
        LocalTime currentTime = LocalTime.now();
        
        // العثور على الحصة الحالية
        int currentClassIndex = getCurrentClassIndex(currentTime, classStartTimes, classEndTimes);
        
        if (currentClassIndex == -1) {
            System.out.println("الوقت الحالي لا يقع ضمن أي حصة دراسية.");
        } else {
            System.out.println("الحصة الحالية هي: الحصة رقم " + (currentClassIndex + 1));
        }
    }

    private static int getCurrentClassIndex(LocalTime currentTime, LocalTime[] startTimes, LocalTime[] endTimes) {
        for (int i = 0; i < startTimes.length; i++) {
            if (currentTime.isAfter(startTimes[i]) && currentTime.isBefore(endTimes[i])) {
                return i; // العودة إلى مؤشر الحصة الحالية
            }
        }
        return -1; // العودة -1 إذا لم يكن الوقت الحالي ضمن أي حصة
    }
}

