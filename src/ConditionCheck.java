public class ConditionCheck {

    // проверка на одинаковые символы
    public static boolean sameSymbols(String str) {
        for (int j = 0; j < str.length() - 1; j++) {
            if (str.charAt(j) == str.charAt(j + 1)) {
                return true;
            }
        }
        return false;
    }

    // метод для проверки, идут ли символы в порядке возрастания
    public static boolean isCharactersAscending(String str) {
        for (int i = 1; i < str.length(); i++) { // начинаем с 1, чтобы избежать выхода за пределы
            if (str.charAt(i - 1) < str.charAt(i)) {
                return true; // символы идут в порядке возрастания
            }
        }
        return false; // символы не идут в порядке возрастания
    }

    // проверка на палиндром
    public static boolean isPalindrome(String str) {
        String reversedv = new StringBuilder(str).reverse().toString();
        if (reversedv.equals(str)) {
            return true;
        }
        return false;
    }
}