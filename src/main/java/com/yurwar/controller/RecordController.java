package com.yurwar.controller;

import com.yurwar.exception.LoginNotUniqueException;
import com.yurwar.model.Notebook;
import com.yurwar.model.Record;
import com.yurwar.view.View;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controls records in notebook by adding them by user.
 * Checks all users input by regexp.
 * @author Yurii Matora
 */
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

    /**
     * Controller entry point
     */
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
            Record record = new Record();

            view.printRequestInput(view.getUIManager().getString(View.LAST_NAME));
            String lastName = getFormElementFromUser(regexpManager.getString(View.NAME_REGEXP));
            record.setLastName(lastName);

            view.printRequestInput(view.getUIManager().getString(View.FIRST_NAME));
            String firstName = getFormElementFromUser(regexpManager.getString(View.NAME_REGEXP));
            record.setFirstName(firstName);

            view.printRequestInput(view.getUIManager().getString(View.PATRONYMIC));
            String patronymic = getFormElementFromUser(regexpManager.getString(View.NAME_REGEXP));
            record.setPatronymic(patronymic);

            view.printRequestInput(view.getUIManager().getString(View.LOGIN));
            String login = getFormElementFromUser(regexpManager.getString(View.LOGIN_REGEXP));
            record.setLogin(login);

            view.printRequestInput(view.getUIManager().getString(View.MOBILE_PHONE));
            String mobilePhone = getFormElementFromUser(regexpManager.getString(View.MOBILE_PHONE_REGEXP));
            record.setMobilePhone(mobilePhone);

            view.printRequestInput(view.getUIManager().getString(View.EMAIL));
            String email = getFormElementFromUser(regexpManager.getString(View.EMAIL_REGEXP));
            record.setEmail(email);

            while (true) {
                try {
                    notebook.addRecord(record);
                    break;
                } catch (LoginNotUniqueException e) {
                    view.printMessage(e.getLogin() + ": " + view.getUIManager().getString(View.LOGIN_NOT_UNIQUE));
                    view.printRequestInput(view.getUIManager().getString(View.LOGIN));
                    login = getFormElementFromUser(regexpManager.getString(View.LOGIN_REGEXP));
                    record.setLogin(login);
                }
            }
            view.printMessage(view.getUIManager().getString(View.CONTINUE_REQUEST));
            condition = reader.readLine();
        } while (condition.equalsIgnoreCase(view.getUIManager().getString(View.CONTINUE_CONDITION)));
        view.printRecords(notebook.getRecords());
    }

    /**
     * Change program language to the selected one
     * @param languageMark Indicate what language to set
     */
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
                view.printMessage(view.getUIManager().getString(View.MENU_LANGUAGE_DEFAULT));
        }
    }

    /**
     * Asks user to write string that matched with
     * regular expression until correct answer
     * @param regexpString Regular expression string, with
     *                     which user input is compared
     * @return Correct string from user
     */
    private String getFormElementFromUser(String regexpString) {
        Pattern pattern = Pattern.compile(regexpString);
        Matcher matcher;
        while (true) {
            String userInput = reader.readLine();
            matcher = pattern.matcher(userInput);
            if(matcher.matches()) {
                return matcher.group();
            } else {
                view.printMessage(view.getUIManager().getString(View.INPUT_INCORRECT));
            }
        }
    }
}
