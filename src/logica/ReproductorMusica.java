package logica;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ReproductorMusica {
    private static Clip clip;

    public static void reproducirLoop(String rutaArchivo) {
        try {
        	// Loop de audio de manera infinita
            URL url = ReproductorMusica.class.getResource(rutaArchivo);
            if (url == null) {
                System.out.println("No se encontró el archivo de audio en la ruta: " + rutaArchivo);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            // Manipulamos el volumen con "FloatControl"
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f);
            
            // Reproduce en bucle infinito
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    // Funcion efectos de sonido (No loop)
    public static void reproducirEfecto(String rutaArchivo) {
        try {
            URL url = ReproductorMusica.class.getResource(rutaArchivo);
            if (url == null) {
                System.out.println("No se encontró el efecto de audio: " + rutaArchivo);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            Clip efectoClip = AudioSystem.getClip();
            efectoClip.open(audioStream);
            
            FloatControl gainControl = (FloatControl) efectoClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); 
            
            efectoClip.start();
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}