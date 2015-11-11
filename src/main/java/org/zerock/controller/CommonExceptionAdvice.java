package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author LeeSoohoon
 */
@ControllerAdvice
public class CommonExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @ExceptionHandler(value = Exception.class)
    public String common(Exception e) {
        logger.info(e.toString());

        return "error_common";
    }

    @ExceptionHandler(value = Exception.class)
    private ModelAndView errorModelAndView(Exception ex) {
        final ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/error_common");
        modelAndView.addObject("exception", ex);

        return modelAndView;
    }
}
