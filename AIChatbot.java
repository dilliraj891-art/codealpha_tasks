import java.util.Scanner;

public class AIChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("        AI CHATBOT");
        System.out.println("================================");
        System.out.println("Hello! I am your AI chatbot.");
        System.out.println("Type 'help' to see what I can answer.");
        System.out.println("Type 'bye' to exit.");

        while (true) {

            System.out.print("\nYou: ");
            String input = sc.nextLine().toLowerCase().trim();

            String response = getResponse(input);

            System.out.println("Bot: " + response);

            if (input.equals("bye") || input.equals("exit")) {
                break;
            }
        }

        sc.close();
    }

    public static String getResponse(String input) {

        if (input.contains("hello") ||
            input.contains("hi") ||
            input.contains("hey")) {

            return "Hello! How can I help you?";
        }

        if (input.contains("how are you")) {
            return "I'm doing great! Thanks for asking.";
        }

        if (input.contains("your name") ||
            input.contains("who are you")) {

            return "I am a Java-based AI chatbot created for CodeAlpha Task 3.";
        }

        if (input.contains("java")) {
            return "Java is a popular object-oriented programming language.";
        }

        if (input.contains("what is ai") ||
            input.contains("artificial intelligence")) {

            return "Artificial Intelligence is technology that enables computers to perform tasks that normally require human intelligence.";
        }

        if (input.contains("what is machine learning") ||
            input.contains("machine learning")) {

            return "Machine Learning is a branch of AI that allows computers to learn patterns from data.";
        }

        if (input.contains("help")) {

            return "I can answer questions about Java, AI, Machine Learning, and general greetings.";
        }

        if (input.contains("thank")) {
            return "You're welcome!";
        }

        if (input.equals("bye") ||
            input.equals("exit")) {

            return "Goodbye! Have a great day!";
        }

        return "Sorry, I don't understand that yet. Type 'help' to see what I can answer.";
    }
}