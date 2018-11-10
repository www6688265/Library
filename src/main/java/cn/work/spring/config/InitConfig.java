package cn.work.spring.config;

import cn.work.util.FIleUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

import static cn.work.spring.config.LibraryConfig.projectCachePath;
import static cn.work.spring.config.LibraryConfig.projectPath;
import static cn.work.spring.config.LibraryConfig.projectUploadPath;

@Component
public class InitConfig implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        File TempDir = new File(projectPath + projectCachePath);
        File UploadDir = new File(projectPath + projectUploadPath);
        if (!TempDir.exists())
            FileUtils.forceMkdir(TempDir);
        if (!UploadDir.exists())
            FileUtils.forceMkdir(UploadDir);
        System.out.println("file:" + projectPath + projectUploadPath);
    }
}
