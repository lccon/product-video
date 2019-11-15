package cilicili.jz2.utils;

import cilicili.jz2.constant.VideoConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @Date:2019/11/6
 * @Author:lc
 */
public class AttachConvertUtil {

    /**
     * 包含原音乐的视频转换
     * @param seconds
     * @param storageFilename
     * @throws Exception
     */
    public static String existSoundVideoConvert(String storageFilename,
                                      double seconds, HttpServletRequest request) throws IOException {
        String convertFilename = "video"+storageFilename;
        String convertUtil = request.getSession().getServletContext().getRealPath(VideoConstants.CONVERT_UTIL_DIR);
        String realPath = request.getSession().getServletContext().getRealPath(VideoConstants.STORAGE_DIR);
        String audioRealPath = request.getSession().getServletContext().getRealPath(VideoConstants.AUDIO_DIR);
        List<String> command = new ArrayList<>();
        command.add(convertUtil+"\\ffmpeg.exe");

        command.add("-i");
        command.add(audioRealPath+"\\picture.mp3");

        command.add("-i");
        command.add(realPath+"\\"+storageFilename);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-filter_complex");
        command.add("amix=inputs=2");

        command.add("-y");
        command.add(realPath+"\\"+convertFilename);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

        return convertFilename;

    }

    public static String noSoundConvert(String videoUrl, HttpServletRequest request) throws Exception {
        String convertUtil = request.getSession().getServletContext().getRealPath(VideoConstants.CONVERT_UTIL_DIR);
        String realPath = request.getSession().getServletContext().getRealPath(VideoConstants.STORAGE_DIR);
        String newVideoUrl = "noSound"+videoUrl;
        List<String> command = new ArrayList<>();
        command.add(convertUtil+"\\ffmpeg.exe");
        command.add("-i");
        command.add(realPath+"\\"+videoUrl);

        command.add("-c:v");
        command.add("copy");
        command.add("-an");
        command.add(realPath+"\\"+newVideoUrl);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }
        return newVideoUrl;
    }

    /**
     * 不包含原原音乐的视频转换
     * @param seconds
     * @param storageFilename
     * @throws Exception
     */
    public static String noSoundVideoConvert(String storageFilename,
                                      double seconds, HttpServletRequest request) throws IOException {
        String convertFilename = "video"+storageFilename;
        String convertUtil = request.getSession().getServletContext().getRealPath(VideoConstants.CONVERT_UTIL_DIR);
        String realPath = request.getSession().getServletContext().getRealPath(VideoConstants.STORAGE_DIR);
        String audioRealPath = request.getSession().getServletContext().getRealPath(VideoConstants.AUDIO_DIR);
        List<String> command = new ArrayList<>();
        command.add(convertUtil+"\\ffmpeg.exe");

        command.add("-i");
        command.add(audioRealPath+"\\picture.mp3");

        command.add("-i");
        command.add(realPath+"\\"+storageFilename);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(realPath+"\\"+convertFilename);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

        return convertFilename;

    }
}
