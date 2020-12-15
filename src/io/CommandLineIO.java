package io;

import java.util.List;
import java.util.Scanner;

public class CommandLineIO implements InputOutput {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getInput(String title) {
        showText(title);
        return scanner.nextLine();
    }

    @Override
    public double getDouble(String title) {
        double input = -1;
        showText(title);
        while (input == -1) {
            try {
                input = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException ignored) { }
        }
        return input;
    }

    @Override
    public int getInt(String title) {
        int input = -1;
        showText(title);
        while (input == -1) {
            try {
                input = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignored) { }
        }
        return input;
    }

    @Override
    public <T> int chooseOperation(List<T> operations, String title) {
        showText(title);
        int choice = -1;

        for (int i = 0; i < operations.size(); i++) {
            showText(i + " - " + operations.get(i).toString());
        }

        while (choice == -1) {
            try {
                choice = Integer.parseInt(getInput("\nPlease choose operation : "));
            } catch (NumberFormatException ignored) { }
        }

        return choice;
    }

    @Override
    public <T> void printList(List<T> objects) {
        for (int i = 0; i < objects.size(); i++) {
            showText(i + " - " + objects.get(i).toString());
        }
    }

    @Override
    public void showText(String text) {
        System.out.println(text);
    }
}
