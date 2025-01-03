package AllureUtils;

import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;

public class VideoRecorder {
    public static final String USER_DIR = "user.dir";
    public static final String DOWNLOADED_FILES_FOLDER = "downloadFiles";

    private static org.monte.screenrecorder.ScreenRecorder screenRecorder;

    public static File startRecording() throws Exception {
        File file = new File("./target/videos/");

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        screenRecorder = new ScreenRecorder(gc,
                gc.getBounds(),
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null,
                file);
        screenRecorder.start();
        return screenRecorder.getCreatedMovieFiles().get(0);
    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
    }

    public static void renameVideoRecord(File oldFile, File newFile) {
        try {
            boolean isRenamed = oldFile.renameTo(newFile);

            if (isRenamed) {
                System.out.println("Recorded file renamed to: " + newFile.getName());
            } else {
                System.out.println("Failed to rename recorded file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteRecordedVideo(File video) {
        if (video.exists()) {
            boolean isDeleted = video.delete();
            if (isDeleted) {
                System.out.println("Recorded file deleted successfully: " + video.getName());
            } else {
                System.out.println("Failed to delete recorded file: " + video.getName());
            }
        }
    }
}
