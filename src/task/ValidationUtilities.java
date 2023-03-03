package task;



import java.time.DateTimeException;
import java.time.LocalDate;


public class ValidationUtilities {
    public static String checkTitleValidation(String input) {
        if (input == null || input.isBlank()) {
            return "Новая задача";
        } else {
            return input;
        }
    }
    public static String checkDescriptionValidation(String input){
        if (input == null || input.isBlank()) {
            return "Описание отсутствует";
        } else {
            return input;
        }
    }
    public static boolean isDateValid(int year, int month, int day) {
        boolean dateIsValid = true;
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }


}
