package com.small.business.utils;;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.result.ResultInfo;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil extends FileCopyUtils {
    public FileUtil() {
    }



    public static void toFile(MultipartFile multipartFile, final File file) {
        try {
            toFile(multipartFile.getInputStream(), file);
        } catch (IOException var3) {
            throw new RuntimeException();

        }
    }

    public static void toFile(InputStream in, final File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            Throwable var3 = null;

            try {
                copy(in, out);
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (out != null) {
                    if (var3 != null) {
                        try {
                            out.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        out.close();
                    }
                }

            }

        } catch (IOException var15) {
            throw new RuntimeException();
        }
    }

    public static void moveFile(final File srcFile, final File destFile) throws IOException {
        Assert.notNull(srcFile, "Source must not be null");
        Assert.notNull(destFile, "Destination must not be null");
        if (!srcFile.exists()) {
            throw new FileNotFoundException("Source '" + srcFile + "' does not exist");
        } else if (srcFile.isDirectory()) {
            throw new IOException("Source '" + srcFile + "' is a directory");
        } else if (destFile.exists()) {
            throw new IOException("Destination '" + destFile + "' already exists");
        } else if (destFile.isDirectory()) {
            throw new IOException("Destination '" + destFile + "' is a directory");
        } else {
            boolean rename = srcFile.renameTo(destFile);
            if (!rename) {
                copy(srcFile, destFile);
                if (!srcFile.delete()) {
                    deleteQuietly(destFile);
                    throw new IOException("Failed to delete original file '" + srcFile + "' after copy to '" + destFile + "'");
                }
            }

        }
    }

    public static boolean deleteQuietly(@Nullable final File file) {
        if (file == null) {
            return false;
        } else {
            try {
                if (file.isDirectory()) {
                    FileSystemUtils.deleteRecursively(file);
                }
            } catch (Exception var3) {
                ;
            }

            try {
                return file.delete();
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static class TrueFilter implements FileFilter, Serializable {
        private static final long serialVersionUID = -6420452043795072619L;
        public static final FileUtil.TrueFilter TRUE = new FileUtil.TrueFilter();

        public TrueFilter() {
        }

        public boolean accept(File pathname) {
            return true;
        }
    }
}
