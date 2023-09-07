package kz.zsanzharrko.argument;

import kz.zsanzharrko.enums.ScreenType;
import lombok.Data;

@Data
public class ArgumentConfig {
    private String configFilePath;
    private ScreenType screenType = ScreenType.IMAGE;
}
