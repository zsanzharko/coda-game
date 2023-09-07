package kz.zsanzharrko.enums;

import kz.zsanzharrko.argument.InvalidArgumentExceptions;
import lombok.Getter;

public enum ScreenType {
    IMAGE("image"),GIF("gif"),VIDEO("video");

    @Getter private final String type;

    ScreenType(String type) {
        this.type = type;
    }

    public static ScreenType resolverType(String value) throws InvalidArgumentExceptions {
        for(ScreenType argument : values()){
            if(value.contains(argument.getType())){
                return argument;
            }
        }
        throw new InvalidArgumentExceptions(String.format("%s is invalid argument", value));
    }
}
