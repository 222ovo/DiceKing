package Client;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AudioManager {

    /* ================= 单例 ================= */
    public static final AudioManager Instance = new AudioManager();

    /* ================= 音频字典 ================= */
    private static final Map<String, Clip> audioMap = new HashMap<>();
    private static final List<String> audioList = new ArrayList<>();

    /* ================= 私有构造 ================= */
    private AudioManager() {}

    /* ================= 对外接口 ================= */

    /** 注册音乐（提前加载） */
    public static void RegisterAudio(String name, String filePath,boolean loop) {
        try {
            AudioInputStream ais =
                    AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();

            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else
                clip.loop(0);

            Instance.audioMap.put(name, clip);
            Instance.audioList.add(name);
        } catch (Exception e) {
            System.out.println("加载音乐失败：" + filePath);
            e.printStackTrace();
        }
    }

    public static void initAudio()
    {
        RegisterAudio("die1", "Sound/dieSound1.wav",false);
        RegisterAudio("die2", "Sound/dieSound2.wav",false);
        RegisterAudio("die3", "Sound/dieSound3.wav",false);
        RegisterAudio("die4", "Sound/dieSound4.wav",false);
        RegisterAudio("die5", "Sound/dieSound5.wav",false);
        RegisterAudio("die6", "Sound/dieSound6.wav",false);
        RegisterAudio("die7", "Sound/dieSound7.wav",false);
    }
    /** 播放音乐 */
    public static void PlayAudio(String name) {
        Clip clip = Instance.audioMap.get(name);
        if (clip == null) {
            System.out.println("未找到音乐文件：" + name);
            return;
        }

        if (!clip.isRunning()) {
            clip.setFramePosition(0); // 从头播放
            clip.start();
        }
    }

    /** 停止指定音乐 */
    public static void StopAudio(String name) {
        Clip clip = Instance.audioMap.get(name);
        if (clip == null) {
            System.out.println("未找到音乐文件：" + name);
            return;
        }

        if (clip.isRunning()) {
            clip.stop();
        }
    }

    /** 停止所有音乐 */
    public static void StopAllAudio() {
        for (Clip clip : Instance.audioMap.values()) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
    }

    public static Map<String,Clip> getClipMap(){
        return audioMap;
    }

    public static List<String> getAudioList()
    {
        return audioList;
    }
}
