package com.integration.springintegrationdemo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Transformer {
    public String transform(String filePath) throws IOException
    {
        String res=new String(Files.readAllBytes(Paths.get(filePath)));
        System.out.println("Resultant Contents ---"+res);
        return "Resultant Contents ---"+res;
    }
}
