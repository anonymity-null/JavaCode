package xyz.chengzi.halma.util;

import java.io.*;

public class FileUtil {

    private final static String path="huhu/Halam/store.txt";

        public static void saveFile(String str) throws IOException {
            File file=new File(path);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter=new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        }
        public static String getFile() throws IOException {
            StringBuilder builder=new StringBuilder();
            FileReader reader=new FileReader(path);
            int i=0;
            while ((i=reader.read())!=-1){
                builder.append((char)i);
            }
            return builder.toString();
        }
}
