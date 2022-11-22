package com.liveweather.api;

import org.apache.commons.codec.binary.Base64;

public class GHKey {
    public String get() {
        return new String(Base64.decodeBase64("Z2hwX3NrNWZkbXlXMDdpa1FqU3kwY0tLanB1WVg3dkp0aDFkZ3dONg=="));
    }
}

