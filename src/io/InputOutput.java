package io;

import java.util.List;

public interface InputOutput {

    String getInput(String title);

    double getDouble(String title);

    int getInt(String title);

    <T> int chooseOperation(List<T> operations, String title);

    <T> void printList(List<T> objects);

    void showText(String text);

}
