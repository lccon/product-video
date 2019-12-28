package cilicili.jz2.utils;

import cilicili.jz2.constant.VideoConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 视频转换，添加背景音乐
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
        command.add(convertUtil+ File.separator +"ffmpeg.exe");

        command.add("-i");
        command.add(realPath+ File.separator +storageFilename);

        command.add("-i");
        command.add(audioRealPath+ File.separator +"picture.mp3");

        command.add("-filter_complex");
        command.add("[1:a]aloop=loop=-1:size=2e+09[out];[out][0:a]amix");
        command.add("-ss");
        command.add("0");

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(realPath+ File.separator +convertFilename);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

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

    /*
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
        command.add(convertUtil+ File.separator +"ffmpeg.exe");

        command.add("-i");
        command.add(realPath+ File.separator +storageFilename);

        command.add("-i");
        command.add(audioRealPath+ File.separator +"picture.mp3");

        command.add("-filter_complex");
        command.add("[1:a]aloop=loop=-1:size=2e+09");
        command.add("-ss");
        command.add("0");
        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(realPath+ File.separator +convertFilename);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

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

    /**
     * 去除视频自带背景音乐
     * @param videoUrl
     * @param request
     * @return
     * @throws Exception
     */
    public static String noSoundConvert(String videoUrl, HttpServletRequest request) throws Exception {
        String convertUtil = request.getSession().getServletContext().getRealPath(VideoConstants.CONVERT_UTIL_DIR);
        String realPath = request.getSession().getServletContext().getRealPath(VideoConstants.STORAGE_DIR);
        String newVideoUrl = "noSound"+videoUrl;
        List<String> command = new ArrayList<>();
        command.add(convertUtil+ File.separator +"ffmpeg.exe");
        command.add("-i");
        command.add(realPath+ File.separator +videoUrl);

        command.add("-c:v");
        command.add("copy");
        command.add("-an");
        command.add(realPath+ File.separator +newVideoUrl);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

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
}
