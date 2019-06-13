package com.yurwar.view;

import com.yurwar.controller.ResourceManager;
import com.yurwar.model.Record;

import java.util.Set;

/**
 * Contains methods to interact with user by messages
 * @author Yurii Matora
 */
public class View {
    private ResourceManager UIManager;

    public static final String LAST_NAME = "lastName";
    public static final String FIRST_NAME = "firstName";
    public static final String PATRONYMIC = "patronymic";
    public static final String EMAIL = "email";
    public static final String LOGIN = "login";
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String CONTINUE_REQUEST = "continue.request";
    public static final String CONTINUE_CONDITION = "continue.condition";
    public static final String INPUT_REQUEST = "input.request";
    public static final String INPUT_INCORRECT = "input.incorrect";
    public static final String MENU_LANGUAGE = "menu.language";
    public static final String MENU_LANGUAGE_DEFAULT = "menu.language.default";
    public static final String MENU_GREETING = "menu.greeting";
    public static final String LOGIN_NOT_UNIQUE = "unique.login";

    public static final String EMAIL_REGEXP = "regexp.email";
    public static final String NAME_REGEXP = "regexp.name";
    public static final String LOGIN_REGEXP = "regexp.login";
    public static final String MOBILE_PHONE_REGEXP = "regexp.mobilePhone";


    public View() {
        UIManager = ResourceManager.USER_INTERFACE;
    }


    /**
     * Print message with break line
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * @param requestField Requested field from user
     */
    public void printRequestInput(String requestField) {
        System.out.println(UIManager.getString(View.INPUT_REQUEST) + " " + requestField);
    }

    /**
     * Print all records in table view
     */
    public void printRecords(Set<Record> records) {
        StringBuffer recordsString = new StringBuffer();
        recordsString.append(String.format("%-30s%-30s%-30s%-16s%-20s%-50s%n",
                UIManager.getString(View.LAST_NAME).toUpperCase(),
                UIManager.getString(View.FIRST_NAME).toUpperCase(),
                UIManager.getString(View.PATRONYMIC).toUpperCase(),
                UIManager.getString(View.LOGIN).toUpperCase(),
                UIManager.getString(View.MOBILE_PHONE).toUpperCase(),
                UIManager.getString(View.EMAIL).toUpperCase()
                )
        );
        for (Record record : records) {
            recordsString.append(record.toString()).append('\n');
        }
        System.out.println(recordsString);
    }

    public ResourceManager getUIManager() {
        return UIManager;
    }
}
