package common;

import io.github.cozyloon.EzConfig;

public class Constants {
    public final static String BASE_URL = EzConfig.getProperty("base.url");
    public final static String DATA = System.getProperty("user.dir") + "\\src\\main\\resources\\data\\";
}
