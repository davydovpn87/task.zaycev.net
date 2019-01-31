import java.util.*;

public class Main {
    // Коллекция для проверки на бесконечный цикл
    static HashMap<String, Integer> log = new HashMap<>();

    public static void main(String[] args) {
        // Создание списка входных параметров
        ArrayList<Integer> inputVariables = new ArrayList<Integer>();
        Collections.addAll(inputVariables, 0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11);
        // Переменные
        StringBuilder sb; String result; int count = 0;
        // Цикл с еднственной точкой выхода - бесконечный цикл
        while (true) {
            // Сохранение состояния массива в строку конкатенацией всех значений массива
            sb = new StringBuilder();
            for (Integer i:inputVariables) {sb.append(i);}
            result =  sb.toString();
            // Проверка на наличие в мапе существовавших состояний
            if (log.containsKey(result)) {
                //System.out.println(result + "    ");// для отладки
                System.out.println(count +" шагов до обнаружения бесконечного цикла");
                System.out.println("Повторившийся элемент "+ result );
                System.out.println("Длина цикла "+ (count - log.get(result)));
                break;
            }else{
                log.put(result, count);
                //System.out.println(result + "    " +  count);//для отладки
                count++;
            }
            // Распределение максимального элемента
            respectValuesOfArray(inputVariables);
        }
    }

    // Выполняет бизнес логику (распределение максимального значения) входного массива
    public static synchronized void respectValuesOfArray(ArrayList<Integer> list){
        int maxElementOfArray = Collections.max(list);
        int indexOfMaximumValue = list.indexOf(maxElementOfArray);
        int indexToUpdate = 0;
        // Обнулим ячейку для перераспределения
        list.set(indexOfMaximumValue, 0);
        // Пробегаем с ячейки следующей за ячейкой с максимальным значением и последовательно инкрементим значения
        for (int i = 1; i <= maxElementOfArray; i++) {
            // Проверка на выход за пределы массива
            indexToUpdate = indexOfMaximumValue + i >= list.size() ? (indexOfMaximumValue + i) % list.size() : indexOfMaximumValue + i;
            list.set(indexToUpdate, list.get(indexToUpdate) + 1); // сам инкремент
        }
    }
}
