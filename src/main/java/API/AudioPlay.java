package API;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AudioPlay {

    public static void TTS(String text, String lang) throws IOException, InterruptedException, UnsupportedAudioFileException, LineUnavailableException {
        String encodedText = URLEncoder.encode(text, "UTF-8");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://voicerss-text-to-speech.p.rapidapi.com/?key=a1ebb15a22fa42ca8ad9b87523d3648e&src="+encodedText+"&hl=" + lang +"&r=0&c=WAV&f=22khz_8bit_mono"))
                .header("X-RapidAPI-Key", "76bc97a9d3mshee7d7c0825c7778p17f65ejsn6638c8856d73")
                .header("X-RapidAPI-Host", "voicerss-text-to-speech.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<byte[]> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofByteArray());
        playAudio(response.body());
    }

    private static void playAudio(byte[] audioData) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));

        // Sử dụng SourceDataLine thay vì Clip
        SourceDataLine line = AudioSystem.getSourceDataLine(audioInputStream.getFormat());
        line.open(audioInputStream.getFormat());
        line.start();

        int bytesRead = 0;
        byte[] buffer = new byte[4096];
        while ((bytesRead = audioInputStream.read(buffer)) != -1) {
            line.write(buffer, 0, bytesRead);
        }

        line.drain();
        line.close();
    }
}