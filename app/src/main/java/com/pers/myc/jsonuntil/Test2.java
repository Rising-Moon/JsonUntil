package com.pers.myc.jsonuntil;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Test2 {
    String username;
    String clientToken;
    String enableShadow;
    String theme;
    List<Java> java;
    String logintype;
    String downloadtype;
    Configurations configurations;
    Yggdrasil yggdrasil;

    class Java {
        String ver;
        String platform;
        String location;
    }

    class Configurations {
        Default Default;

        class Default{
            String name;
            String selectedMinecraftVersion;
            String minecraftArgs;
            String gameDir;
            String javaDir;
            String precalledCommand;
            String serverIp;
            String java;
            String fullscreen;
            String debug;
            String noJVMArgs;
            String canceledWrapper;
            String launcherVisibility;
            String gameDirType;
        }
    }

    class Yggdrasil {
    }
}
