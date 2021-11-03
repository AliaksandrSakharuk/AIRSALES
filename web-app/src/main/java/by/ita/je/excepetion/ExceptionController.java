package by.ita.je.excepetion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController{

    @ExceptionHandler(NotCorrectData.class)
    public String handlerNotCorrectData(Model model, NotCorrectData exception){
        ExceptionInfo fieldException=new ExceptionInfo();
        fieldException.setInfo("Вы ввели не корректные данные. Проверьте вводимые даныне");
        fieldException.setErrorCode(403);
        fieldException.setTypeException("NotCorrectData");
        model.addAttribute("except", fieldException);
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handlerNotCorrectData(Model model, NullPointerException exception){
        ExceptionInfo fieldException=new ExceptionInfo();
        fieldException.setInfo("Так как Вы сменили логин, Вам необходимо пройти заново авторизацию");
        fieldException.setErrorCode(404);
        fieldException.setTypeException("NullPointerException");
        System.out.println(fieldException);
        model.addAttribute("except", fieldException);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handlerException(Model model, Exception exception){
        System.out.println("EXCEPTION : ");
        System.out.println(exception.getClass());
        ExceptionInfo fieldException=new ExceptionInfo();
        int numberStatus=Integer.valueOf(exception.getMessage().substring(0,3).strip());
        if(numberStatus==404){
            fieldException.setInfo("Сервер не может найти запрашиваемый ресурс!");
            fieldException.setTypeException("NotFoundData");
        }
        else if(numberStatus>=500){
            fieldException.setInfo("Ошибка на стороне сервера");
        }
        else {
            fieldException.setInfo("Некорректный запрос");
            fieldException.setTypeException("VALIDATION");
        }
        fieldException.setErrorCode(numberStatus);
        model.addAttribute("except", fieldException);
        return "error";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }
}