package kz.zsanzharrko.argument;

import kz.zsanzharrko.enums.ScreenType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArgumentResolver {
    public static ArgumentConfig resolveConfig(String... args) throws InvalidArgumentExceptions {
        ArgumentConfig config = new ArgumentConfig();
        for (String arg : args) {
            ArgumentType type = ArgumentType.resolverType(arg);
            String value = arg.substring(arg.indexOf('=') + 1);
            switch (type) {
                case ENV_FILE -> config.setConfigFilePath(value);
                case SCREEN_TYPE -> config.setScreenType(ScreenType.resolverType(value));
            }
        }
        return config;
    }

    private enum ArgumentType {
        ENV_FILE("env-file"),
        SCREEN_TYPE("screen-type");

        @Getter private final String argument;

        ArgumentType(String argument) {
            this.argument = argument;
        }

        public static ArgumentType resolverType(String arg) throws InvalidArgumentExceptions {
            for(ArgumentType argument : values()){
                if(arg.contains(argument.getArgument())){
                    return argument;
                }
            }
            throw new InvalidArgumentExceptions(String.format("%s is invalid argument", arg));
        }
    }
}
