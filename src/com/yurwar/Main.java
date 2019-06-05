package com.yurwar;

import com.yurwar.controller.RecordController;
import com.yurwar.model.Notebook;
import com.yurwar.view.View;

/**
 * Program entry point class
 * @author Yurii Matora
 */
public class Main {

    public static void main(String[] args) {
        RecordController controller = new RecordController(new Notebook(), new View());

        controller.processUser();
    }
}
