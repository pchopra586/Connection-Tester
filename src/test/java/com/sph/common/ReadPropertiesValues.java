package com.sph.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertiesValues {

    public String filepath;


    public ReadPropertiesValues(String filePath)
    {
        this.filepath = filePath;
    }

    public String readProperty(String propKey){
        String propVal="";
        try {
            int check = 0;
            while (check == 0) {
                File readPropfile = new File(filepath);
                if (readPropfile.exists()) {
                    Properties properties = new Properties();
                    FileInputStream io = new FileInputStream(readPropfile);
                    properties.load(io);
                    propVal = properties.getProperty(propKey);
                    check = 1;
                } else {
                    check = 0;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        return propVal;
    }
}
