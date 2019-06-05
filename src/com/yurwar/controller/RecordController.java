package com.yurwar.controller;

import com.yurwar.model.Notebook;
import com.yurwar.model.Record;
import com.yurwar.view.View;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordController {
    private Notebook notebook;
    private View view;
    private ConsoleReader reader;
    private ResourceManager regexpManager;

    public RecordController(Notebook notebook, View view) {
        this.notebook = notebook;
        this.view = view;
        reader = new ConsoleReader();
        regexpManager = ResourceManager.REGEXP;
    }

    public void processUser() {
        String condition;
        view.printMessage(view.getUIManager().getString(View.MENU_GREETING));
        view.printMessage(view.getUIManager().getString(View.MENU_LANGUAGE));
        try {
            changeLanguage(reader.readInt());
        } catch (NumberFormatException e) {
            view.printMessage(view.getUIManager().getString(View.MENU_LANGUAGE_DEFAULT));
        }
        do {
            view.printRequestInput(view.getUIManager().getString(View.LAST_NAME));
            String lastName = getFormElementFromUser(regexpManager.getString(View.NAME_REGEXP));

            view.printRequestInput(view.getUIManager().getString(View.FIRST_NAME));
            String firstName = getFormElementFromUser(regexpManager.getString(View.NAME_REGEXP));

            view.printRequestInput(view.getUIManager().getString(View.PATRONYMIC));
            String patronymic = getFormElementFromUser(regexpManager.getString(View.NAME_REGEXP));

            view.printRequestInput(view.getUIManager().getString(View.NICKNAME));
            String nickname = getFormElementFromUser(regexpManager.getString(View.NICKNAME_REGEXP));

            view.printRequestInput(view.getUIManager().getString(View.MOBILE_PHONE));
            String mobilePhone = getFormElementFromUser(regexpManager.getString(View.MOBILE_PHONE_REGEXP));

            view.printRequestInput(view.getUIManager().getString(View.EMAIL));
            String email = getFormElementFromUser(regexpManager.getString(View.EMAIL_REGEXP));

            notebook.getRecords().add(
                    new Record(
                            lastName,
                            firstName,
                            patronymic,
                            nickname,
                            mobilePhone,
                            email
                    )
            );
            view.printMessage(view.getUIManager().getString(View.CONTINUE_REQUEST));
            condition = reader.readLine();
        } while (condition.equalsIgnoreCase(view.getUIManager().getString(View.CONTINUE_CONDITION)));
        view.printRecords(notebook.getRecords());
    }

    private void changeLanguage(int languageMark) {
        Locale switchLocale;
        switch (languageMark) {
            case 1: {
                switchLocale = Locale.UK;
                regexpManager.changeResource(switchLocale);
                view.getUIManager().changeResource(switchLocale);
                break;
            }
            case 2: {
                switchLocale = new Locale.Builder()
                        .setLanguage("ua")
                        .build();

                regexpManager.changeResource(switchLocale);
                view.getUIManager().changeResource(switchLocale);
                break;
            }
            case 3: {
                switchLocale = new Locale.Builder()
                        .setLanguage("ru")
                        .setRegion("RU")
                        .build();

                regexpManager.changeResource(switchLocale);
                view.getUIManager().changeResource(switchLocale);
                break;
            }
            default:
        }
    }

    private String getFormElementFromUser(String regexpString) {
        Pattern pattern = Pattern.compile(regexpString);
        Matcher matcher;
        while (true) {
            String userInput = reader.readLine();
            matcher = pattern.matcher(userInput);
            if(matcher.find()) {
                return matcher.group();
            } else {
                view.printMessage(view.getUIManager().getString(View.INPUT_INCORRECT));
            }
        }
    }
}
