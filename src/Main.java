import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    // счетчики для никнеймов разной длины
    public static AtomicLong counterThree = new AtomicLong();
    public static AtomicLong counterFour = new AtomicLong();
    public static AtomicLong counterFive = new AtomicLong();
    public static int three = 3;
    public static int four = 4;
    public static int five = 5;


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];


        for (int i = 0; i < texts.length; i++) {
            texts[i] = GenerateNickname.generateText("abc", 3 + random.nextInt(3));
        }
        // создаем потоки для проверки "красоты" никнеймов
        Thread threadThree = new Thread(() -> countBeautifulNicknames(texts, three, counterThree));
        Thread threadFour = new Thread(() -> countBeautifulNicknames(texts, four, counterFour));
        Thread threadFive = new Thread(() -> countBeautifulNicknames(texts, five, counterFive));

        // запускаем потоки
        threadThree.start();
        threadFour.start();
        threadFive.start();

        // закрываем потоки
        threadThree.join();
        threadFour.join();
        threadFive.join();

        //выводим результат
        System.out.printf("Количество никнеймов из 3 символов: %d\n" +
                        "Количество никнеймов из 4 символов: %d\n" +
                        "Количество никнеймов из 5 символов: %d\n",
                counterThree.get(), counterFour.get(), counterFive.get());

    }

    // метод для проверки условий
    public static boolean complexСheck(ConditionCheck check, String nick) {
        if (check.sameSymbols(nick) || check.isCharactersAscending(nick) || check.isPalindrome(nick)) {
            return true;
        } else {
            return false;
        }
    }

    // метод для подсчета "красивых" никнеймов
    public static void countBeautifulNicknames(String[] texts, int lenght, AtomicLong counter) {
        for (String nick : texts) {
            if (nick.length() == lenght && complexСheck(new ConditionCheck(), nick)) {
                counter.incrementAndGet();
            }
        }
    }
}
