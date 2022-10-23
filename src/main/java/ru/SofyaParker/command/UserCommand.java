package ru.SofyaParker.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommand {
    private String command;
    private String currency;
    private String forecastPeriod;
    private boolean endFlag = false;
    private boolean successFlag = false;

}
