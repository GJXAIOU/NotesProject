package com.gjxaiou.jdk8.streamSourceCode;

import javafx.scene.control.Button;


import java.util.List;
import java.util.function.BiFunction;

public class Main {

    public static void main(String[] args) {
        BiFunction<List<String>, List<String>, List<String>> resultList =
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                };


        Main main = new Main();
        main.setButtonText(new Button(), "hello:world", true);
    }

    public void setButtonText(Button view, String string, boolean flag) {
        String[] temp = string.split(":");
        if (temp.length <= 1) {
            return;
        }
        new Thread(() -> view.setText(temp[1])).start();
    }
}


