package org.zerock.util;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

/**
 * @author LeeSoohoon
 */
public class UploadFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
        final UUID uid = UUID.randomUUID();
        final String savedName = uid.toString() + "_" + originalName;
        final String savedPath = calcPath(uploadPath);
        final File target = new File(uploadPath + savedPath, savedName);

        FileCopyUtils.copy(fileData, target);

        final String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
        String uploadedFileName;

        if (MediaUtil.getMediaType(formatName) != null) {
            uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
        } else {
            uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
        }

        return uploadedFileName;
    }

    private static String makeIcon(final String uploadPath, final String path, final String fileName) {

        final String iconName = uploadPath + path + File.separator + fileName;

        return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    private static String calcPath(String uploadPath) {
        final Calendar cal = Calendar.getInstance();

        final String yearPath = File.separator + cal.get(cal.YEAR);
        final String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(cal.MONTH) + 1);
        final String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(cal.DATE));

        makeDir(uploadPath, yearPath, monthPath, datePath);

        logger.info(datePath);

        return datePath;
    }

    private static void makeDir(final String uploadPath, final String... paths) {
        if (new File(paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            final File dirPath = new File(uploadPath + path);

            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
        final BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
        final BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        final String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

        final File newFile = new File(thumbnailName);
        final String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

        ImageIO.write(destImg, formatName.toUpperCase(), newFile);

        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }
}
