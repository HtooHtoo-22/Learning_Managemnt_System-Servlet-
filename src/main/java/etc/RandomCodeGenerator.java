package etc;

import java.security.SecureRandom;

public class RandomCodeGenerator {

    // Define the characters that can be used in the code (uppercase, lowercase, digits)
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();  // Secure random generator

    public static String generateCode() {
        int length = 5;  // Always fixed to 5 characters

        StringBuilder code = new StringBuilder(length);

        // Generate a random code using the characters string
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());  // Get a random index
            code.append(CHARACTERS.charAt(index));  // Append the character at the random index
        }

        return code.toString();  // Return the generated code
    }

    public static void main(String[] args) {
        // Example usage - will always generate a 5-character code
        String generatedCode = generateCode();  // No parameters, always returns 5 characters
        System.out.println("Generated Code: " + generatedCode);

        // You can call it multiple times
        String anotherCode = generateCode();  // Another randomly generated code
        System.out.println("Another Generated Code: " + anotherCode);
    }
}
