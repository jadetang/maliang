package github.jadetang.maliang.builder;

import github.jadetang.maliang.conf.MConfig;
import github.jadetang.maliang.exception.MaliangException;
import github.jadetang.maliang.util.FileUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Tang Sicheng
 */
public class SourceCodeGenerator {


    private static Logger log = LoggerFactory.getLogger(SourceCodeGenerator.class);


    private String templatePathStr;
    private String outPutPath;
    private MConfig config;
    private VelocityEngine ve;
    private Path templatePath;
    private boolean ready = false;

    public SourceCodeGenerator(MConfig mConfig) {
        this.config = mConfig;
        this.templatePathStr = config.getTemplateDir();
        this.templatePath = Paths.get(templatePathStr);
        this.outPutPath = config.getOutputDir();
    }

    public void init() throws IOException {
        initEngine();
        FileUtil.delDirectory(outPutPath);
        ready = true;
    }


    private void initEngine() {
        ve = new VelocityEngine();
        ve.setProperty(ve.FILE_RESOURCE_LOADER_PATH, templatePathStr);
    }

    public void generate(VelocityContext context) throws IOException {
        if (!ready) {
            throw new MaliangException("should call init before");
        }
        Path outputPath = Paths.get(outPutPath);
        driver(templatePath, outputPath, context);
        ve.setProperty(ve.FILE_RESOURCE_LOADER_PATH, templatePath);
    }

    private void driver(Path sourcePath, Path desPath, VelocityContext context) throws IOException {
        File sourceFile = sourcePath.toFile();
        if (!sourceFile.exists()) {
            return;
        } else if (sourceFile.isDirectory()) {
            desPath = newDirPath(desPath, context);
            Files.createDirectories( desPath, new FileAttribute[]{});
            try (DirectoryStream<Path> subPaths = Files.newDirectoryStream(sourcePath)) {
                for (Path subPath : subPaths) {
                    driver(subPath, Paths.get(desPath.toAbsolutePath().toString() + File.separator + subPath.getFileName()), context);
                }
            }
        } else if (sourceFile.isFile()) {
            generateCode(sourcePath, desPath.getParent(), context);
        }
    }

    private void generateCode(Path oriFileName, Path tmpDir, VelocityContext context) throws IOException {
        String newFileName = newPathName(oriFileName.getFileName().toString(), context);
        String absoluteFileName = tmpDir.toAbsolutePath().toString() + File.separator + newFileName;
        if (Paths.get(absoluteFileName).toFile().exists()) {
            //the file already exist
            return;
        } else {
            log.debug("FILE_RESOURCE_LOADER_PATH:" + oriFileName.getParent().toAbsolutePath().toString());
            log.debug("try find resource:" + templatePath.relativize(oriFileName).toString());
            Template template = ve.getTemplate(templatePath.relativize(oriFileName).toString(), "UTF-8");
            assert template != null;
            Writer writer = null;
            try {
                writer = new FileWriter(new File(absoluteFileName), false);
                template.merge(context, writer);
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
    }

    /**
     * if the path string has velocity reference like ${bean.name},then replace it with
     * the content in the Velocity Context, and replace "." with File.pathSeparator
     * @param
     * @return
     */
    private Path newDirPath(Path originPath, VelocityContext context) {
        String newPathNameString = newPathName(originPath.toAbsolutePath().toString(), context).replace(".", File.separator);
        log.debug("new Path :"+newPathNameString);
        return Paths.get(newPathNameString);
    }

    /**
     * if the pathName has velocity reference like ${bean.name},then replace it with
     * the content in the Velocity Context
     * @param fileName
     * @return
     */
    private String newPathName(String fileName, VelocityContext context) {
        //match ${xxxx}
        String patternString = "\\$\\{(.|\\w)+\\}";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()){
            String contextValue = evaluateVTL(matcher.group(), context);
            fileName = fileName.replaceFirst(patternString, contextValue);
        }
        return fileName;
    }

    private String evaluateVTL(String contextKey, VelocityContext context) {
        try (StringWriter writer = new StringWriter()) {
            ve.evaluate(context, writer, "Maliang", contextKey);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
