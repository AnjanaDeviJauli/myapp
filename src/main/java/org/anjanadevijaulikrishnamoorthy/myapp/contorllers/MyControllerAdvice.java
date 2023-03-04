package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class MyControllerAdvice {
    @ExceptionHandler(Exception.class)
    public RedirectView exceptionHandle(Exception ex){

        log.debug("something wrong");
        ex.printStackTrace();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/students/list");
        return redirectView;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public RedirectView exceptionHandle(NoSuchElementException ex){

        log.debug("No such element is found");
        ex.printStackTrace();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8080/students/list");
        return redirectView;
    }
}
