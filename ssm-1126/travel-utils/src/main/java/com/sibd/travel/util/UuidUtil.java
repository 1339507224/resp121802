package com.sibd.travel.util;

import java.util.UUID;

public class UuidUtil {
    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }
}
