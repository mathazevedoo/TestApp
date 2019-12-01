package com.location.inoarb.builder.impl;

import android.os.Build;

import com.location.inoarb.builder.Builder;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class InoarbImageDataClobBuilder implements Builder<String, File> {

    private final Base64 base64 = new Base64();

    @Override
    public String build(File file) {
        try {
            if (file != null && file.exists() && Build.VERSION.SDK_INT >= 26) {
                return base64.encodeAsString(Files.readAllBytes(file.toPath()));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
