package by.ita.je.excepetion;

import lombok.Data;

@Data
public class ExceptionInfo {
    private int errorCode;
    private String typeException;
    private String info;

}
