package cn.work.util;
import java.util.UUID;

public class FIleUtil {
    public static String getUniqueFileName(String fileAllName){
        String fileName=fileAllName.substring(0,fileAllName.lastIndexOf("."));
        String fileType=fileAllName.substring(fileAllName.lastIndexOf("."));
        fileName= UUID.randomUUID()+fileName;
        fileAllName=fileName+fileType;
        return fileAllName;
    }
}
