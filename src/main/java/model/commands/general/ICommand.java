package model.commands.general;

import controller.wrappers.RequestWrapper;
import controller.wrappers.ResponseWrapper;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by lexy on 08.12.16.
 */
public interface ICommand {
    void execute(String command, RequestWrapper request, ResponseWrapper response)
            throws ServletException, IOException;
}
