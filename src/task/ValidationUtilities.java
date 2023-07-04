package task;

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




}
