package task;



import exceptions.TaskNotFoundException;
import task.task_repeatability.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;


public class TaskService {
    private static Map<Integer, Task> taskMap = new HashMap<>();  //поменять на приват. ?создать геттеры дял мапы?
    private static List<Task> removedTasks = new LinkedList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void taskManagementApp() {
        System.out.println("Меню сервиса задач");
        System.out.println("Список доступных действий: " +
                "\n1 - Добавить задачу" +
                "\n2 - Удалить задачу" +
                "\n3 - Изменить оглавление" +
                "\n4 - Изменить описание" +
                "\n5 - Посмотреть задачи на день" +
                "\n6 - Посмотреть все задачи по дням" +
                "\n7 - Посмотреть удаленные задачи" +
                "\n0 - Выход");
        checkInt();
        int method = scanner.nextInt();
        scanner.nextLine();
        if(method<0||method>7){
            System.out.println("Введите корректное число ");
            taskManagementApp();
        }
        switch (method){
            case 0:
                System.exit(0);
            case 1:
                try{
                    createNewTask();
                }catch (DateTimeParseException e){
                    System.err.println("Проверьте правильность введения даты в формате dd.MM.yyyy HH:mm и повторите еще раз");
                }
                taskManagementApp();
                break;
            case 2:
                try{
                    removeTask();
                } catch (TaskNotFoundException e) {
                    System.err.println(e.getMessage());
                }
                taskManagementApp();
                break;
            case 3:
                updateTitle();
                taskManagementApp();
                break;
            case 4:
                updateDescription();
                taskManagementApp();
                break;
            case 5:
                try{
                    System.out.println(getAllByDate());
                }catch (DateTimeParseException e){
                    System.err.println("Проверьте правильность введения даты в формате dd.MM.yyyy и повторите еще раз");
                }
                taskManagementApp();
                break;
            case 6:
                System.out.println(getAllGroupByDate());
                taskManagementApp();
                break;
            case 7:
                getRemovedTasks();
                taskManagementApp();
                break;

        }
    }
    private static void createNewTask() {
        System.out.println("Введите заголовок для задачи ");

        String title = scanner.nextLine();

        System.out.println("Введите описание для задачи ");

        String description = scanner.nextLine();

        System.out.println("Введите тип задачи: \n1 - рабочая, 2 - личная");
        checkInt();
        int type = scanner.nextInt();

        TaskType currentType;
        if(type>2){
            System.out.println("Вы ввели неверный тип задачи, по умолчанию был присвоен тип - личная");
            currentType = TaskType.PERSONAL;
        }
        if(type==1) {
            currentType = TaskType.WORK;
        }else{
            currentType = TaskType.PERSONAL;
        }

        System.out.println("Введите повторяемость задачи: \n1 - однократная, 2 - ежедневная, 3 - еженедельная , 4 - ежемесячная , 5 - ежегодная ");
        checkInt();

        int repeatability = scanner.nextInt();
        scanner.nextLine();
        if(repeatability<1||repeatability>5){
            System.out.println("Вы ввели неверный номер повторяемости, по умолчанию была присовена однократная повторяемость");
            repeatability = 1;
        }
        System.out.println("Введите дату появления задачи в формате: dd.MM.yyyy HH:mm");
        LocalDateTime taskDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));


        switch (repeatability){
            case 1:
                System.out.println("Введите дату для задачи");
                Task oneTimeTask = new OneTimeTask(title,
                        description,
                        currentType,
                        taskDate);
                taskMap.put(oneTimeTask.getId(), oneTimeTask);
                System.out.println("Вы успешно добавили задачу: ");
                System.out.println(oneTimeTask);
                break;
            case 2:
                Task dailyTask = new DailyTask(title,
                        description,
                        currentType,
                        taskDate);
                taskMap.put(dailyTask.getId(), dailyTask);
                System.out.println("Вы успешно добавили задачу: ");
                System.out.println(dailyTask);
                break;
            case 3:
                Task weeklyTask = new WeeklyTask(title,
                        description,
                        currentType,
                        taskDate);
                taskMap.put(weeklyTask.getId(), weeklyTask);
                System.out.println("Вы успешно добавили задачу: ");
                System.out.println(weeklyTask);
                break;
            case 4:
                Task monthlyTask = new MonthlyTask(title,
                        description,
                        currentType,
                        taskDate);
                taskMap.put(monthlyTask.getId(), monthlyTask);
                System.out.println("Вы успешно добавили задачу: ");
                System.out.println(monthlyTask);
                break;
            case 5:
                Task yearlyTask = new YearlyTask(title,
                        description,
                        currentType,
                        taskDate);
                taskMap.put(yearlyTask.getId(), yearlyTask);
                System.out.println("Вы успешно добавили задачу: ");
                System.out.println(yearlyTask);
                break;
        }
    }
    private static void updateDescription(){
        System.out.println("Введите id задачи, в которой необходимо изменить оглавление");
        checkInt();

        int a = scanner.nextInt();
        if(!taskMap.containsKey(a)){
            System.err.println("Задачи с таким id не обнаружено");
        }else {
            System.out.println("Введите новое описание для задачи "+taskMap.get(a).getTitle());
            taskMap.get(a).setDescription(scanner.next());
            System.out.println("Вы успешно изменили описание для задачи ");
            System.out.println(taskMap.get(a));
        }
    }
    private static void updateTitle(){
        System.out.println("Введите id задачи, в которой необходимо изменить оглавление");
        checkInt();

        int a = scanner.nextInt();
        if(!taskMap.containsKey(a)){
            System.err.println("Задачи с таким id не обнаружено");
        }else {
            System.out.println("Введите новое оглавление для задачи "+taskMap.get(a).getTitle());
            taskMap.get(a).setTitle(scanner.next());
            System.out.println("Вы успешно изменили оглавление для задачи ");
            System.out.println(taskMap.get(a));
        }

    }
    private static void removeTask () throws TaskNotFoundException{
        System.out.println("Введите id задачи, которую необходимо удалить");
        checkInt();

        int a = scanner.nextInt();

        if(!taskMap.containsKey(a)){
            throw new TaskNotFoundException("Такой задачи не обнаружено");
        }else {
            removedTasks.add(taskMap.get(a));
            taskMap.remove(a);
        }
    }
    private static void getRemovedTasks(){
        removedTasks.forEach(System.out::println);
    }
    private static List<Task> getAllByDate() {
        System.out.println("Введите дату, для которой вы хотите посмотреть имеющиеся задачи, в формате dd.MM.yyyy");
        LocalDate dateForChecking = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        List<Task> taskListAllByDate = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getValue().appearsLn(dateForChecking.atStartOfDay())) {
                taskListAllByDate.add(entry.getValue());
            }
        }
        return taskListAllByDate;
    }
    private static Map<LocalDate, List<Task>> getAllGroupByDate(){
        List<Task> listOfAll = new ArrayList<>(taskMap.values());
        Map<LocalDate, List<Task>> groupedMap =
                listOfAll.stream().collect(Collectors.groupingBy(Task::getLocalDate));
                return groupedMap;
    }
    private static void checkInt(){
        while(!scanner.hasNextInt()){
            System.out.println("Введите число");
            scanner.nextLine();
        }
    }

}
