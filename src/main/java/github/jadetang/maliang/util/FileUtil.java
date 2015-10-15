package github.jadetang.maliang.util;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Tang Sicheng
 */
public class FileUtil {


    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static void delDirectory(String path) throws IOException {
        Path p = Paths.get(path);
        delHelp(p);
    }

    private static void delHelp(Path p) throws IOException {
        if (!p.toFile().exists()) {
            return;
        } else if (p.toFile().isFile()) {
            log.debug("delete file:" + p.toAbsolutePath().toString());
            Files.delete(p);
        } else if (p.toFile().isDirectory()) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(p)) {
                for (Path subPath : ds) {
                    delHelp(subPath);
                }
            }
            log.debug("delete directory:" + p.toAbsolutePath().toString());
            Files.delete(p);
        }
    }

    public static String concatPath(String... paths) {
        List<String> pathList = Arrays.asList(paths);
        return Joiner.on(File.separator).join(pathList).toString();
    }

    public static void main(String[] args) throws IOException {
        FileUtil.delDirectory("E:\\test");

    }
}
