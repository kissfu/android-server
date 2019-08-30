package com.testerkit.common.utils;



import com.testerkit.common.exceptions.FatalHostError;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Pattern;


public class FileUtil {
//    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private static final Pattern schemePrefixPattern = Pattern.compile("(file:*[a-z]:)|(\\w+://.+?/)|((jar|zip):.+!/)|(\\w+:)", Pattern.CASE_INSENSITIVE);
    /**
     * The minimum allowed disk space in megabytes. File creation methods will throw
     * {@link LowDiskSpaceException} if the usable disk space in desired partition is less than
     * this amount.
     */
    private static final long MIN_DISK_SPACE_MB = 100;
    /** The min disk space in bytes */
    private static final long MIN_DISK_SPACE = MIN_DISK_SPACE_MB * 1024 * 1024;



    /**
     * 删除某个文件夹下的所有文件夹和文件
     *
     * @param file 待删除的文件夹
     */
    public static void delete(File file) throws Exception {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    delete(files[i]);
                }
            }
            file.delete();
        }
    }


    /**
     * Helper wrapper function around {@link File#createTempFile(String, String)} that audits for
     * potential out of disk space scenario.
     *
     * @see {@link File#createTempFile(String, String)}
     * @throws LowDiskSpaceException if disk space on temporary partition is lower than minimum
     *             allowed
     */
    public static File createTempFile(String prefix, String suffix) throws IOException {
        File returnFile = File.createTempFile(prefix, suffix);
        verifyDiskSpace(returnFile);
        return returnFile;
    }

    private static void verifyDiskSpace(File file) {
        // Based on empirical testing File.getUsableSpace is a low cost operation (~ 100 us for
        // local disk, ~ 100 ms for network disk). Therefore call it every time tmp file is
        // created
        if (file.getUsableSpace() < MIN_DISK_SPACE) {
            throw new LowDiskSpaceException(String.format("Available space on %s is less than %s MB", file.getAbsolutePath(), MIN_DISK_SPACE_MB));
        }
    }


    /**
     * A helper method that copies a file's contents to a local file
     *
     * @param origFile the original file to be copied
     * @param destFile the destination file
     * @throws IOException if failed to copy file
     */
    public static void copyFile(File origFile, File destFile) throws IOException {
        writeToFile(new FileInputStream(origFile), destFile);
    }

    /**
     * A helper method for writing stream data to file
     *
     * @param input the unbuffered input stream
     * @param destFile the dest file to write to
     */
    public static void writeToFile(InputStream input, File destFile) throws IOException {
        InputStream origStream = null;
        OutputStream destStream = null;
        try {
            origStream = new BufferedInputStream(input);
            destStream = new BufferedOutputStream(new FileOutputStream(destFile));
            StreamUtil.copyStreams(origStream, destStream);
        } finally {
            StreamUtil.close(origStream);
            StreamUtil.close(destStream);
        }
    }

    /**
     * Try to delete a file and silently ignore IOExceptions.  Intended for use when cleaning up
     * in {@code finally} stanzas. {@param file} may be null.
     */
    public static void deleteFile(File file) {
        if (file != null) {
            file.delete();
        }
    }

    /**
     * Thrown if usable disk space is below minimum threshold.
     */
    public static class LowDiskSpaceException extends FatalHostError {

        LowDiskSpaceException(String msg, Throwable cause) {
            super(msg, cause);
        }

        LowDiskSpaceException(String msg) {
            super(msg);
        }

    }

    /**
     * Gets the extension for given file name.
     *
     * @param fileName
     * @return the extension or empty String if file has no extension
     */
    public static String getExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return fileName.substring(index);
        }
    }

    public static String getFileName(String pathFile){
        String fileName = "";
        File tempFile =new File( pathFile.trim());
        fileName = tempFile.getName();
        return fileName;
    }

    /**
     * Gets the base name, without extension, of given file name.
     * <p/>
     * e.g. getBaseName("file.txt") will return "file"
     *
     * @param fileName
     * @return the base name
     */
    public static String getBaseName(String fileName) {
        fileName = getFileName(fileName);
        int index = fileName.lastIndexOf('.');
        if (index == -1) {
            return fileName;
        } else {
            return fileName.substring(0, index);
        }
    }

    private static File createDir(File tmpDir) throws IOException {
        if (!tmpDir.mkdirs()) {
            throw new IOException("unable to create directory");
        }
        return tmpDir;
    }

    public static String getDirParent(String pathFile){
        File file = new File(pathFile);
        File dirParent = file.getParentFile();

        if(!dirParent.exists()){
           boolean success = dirParent.mkdir();
           //log.info("dir is not exits {} ,make dir is  {}" ,pathFile,success);
        }
        return dirParent.getAbsolutePath();
    }

    /**
     * Helper method to build a system-dependent relative path
     *
     * @param pathSegments the relative path segments to use
     * @return the {@link String} representing given path, with each <var>pathSegment</var>
     *         separated by {@link File#separatorChar}
     */
    public static String getPath(String... pathSegments) {
        StringBuilder pathBuilder = new StringBuilder();
        boolean isFirst = true;
        for (String path : pathSegments) {
            if (!isFirst) {
                pathBuilder.append(File.separatorChar);
            } else {
                isFirst = false;
            }
            pathBuilder.append(path);
        }
        return pathBuilder.toString();
    }

    public static boolean exists(String path){
        return new File(path).exists();
    }


    public static String readContent(String pathFile) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        InputStream inputStream = new FileInputStream(pathFile);
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        line = reader.readLine();
        while (line != null) {
            stringBuffer.append(line);
            stringBuffer.append("\n");
            line = reader.readLine();
        }
        reader.close();
        inputStream.close();
        return stringBuffer.toString();
    }


    public static void main(String[] args) throws Exception {
        String pathFile = "";

        getBaseName("/Users/able/Downloads/temp/ddd.mp4");
    }
}