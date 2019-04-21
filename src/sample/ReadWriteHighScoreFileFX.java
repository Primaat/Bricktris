package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class ReadWriteHighScoreFileFX implements StandardFX{
    /**
     * Class reads from and writes to high score files and loads them into maps
     */
    // separator added while writing and used for splitting while reading into maps
    private static final String DATA_SEPARATOR = ",";

    protected Map<Integer, String> scoreMap = new TreeMap<Integer, String>(new Comparator<Integer>() {
        public int compare(Integer score1, Integer score2) {
            return (score2.compareTo(score1));
        }
    });
    protected Map<String, String> scoreModeScoreMap = new TreeMap<String, String>();

    Path file;


    public ReadWriteHighScoreFileFX() {
    }

    public void readFile(){
        file = selectReadWriteFile();
        Charset charset = Charset.forName("US-ASCII");
        String line;
        scoreModeScoreMap.clear();
        scoreMap.clear();
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DATA_SEPARATOR, 2);
                if(parts.length >= 2){
                    if(mode.modeType.equals("Score")){
                        String key = parts[0];
                        String value = parts[1];
                        scoreModeScoreMap.put(key, value);
                    }
                    else{
                        Integer key = Integer.parseInt(parts[0]);
                        String value = parts[1];
                        scoreMap.put(key, value);
                    }
                }
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    public Map<Integer, String> getScoreMap(){
        return scoreMap;
    }

    public Map<String, String> getScoreModeScoreMap(){
        return scoreModeScoreMap;
    }

    public void writeFile(Map<?, String> map){
        /**
         * Writes the called file int @Map<?, String> map
         */
        file = selectReadWriteFile();
        try {
            Files.write(file, () -> map.entrySet().stream().<CharSequence>map(e -> e.getKey() + DATA_SEPARATOR + e.getValue()).iterator());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public Path selectReadWriteFile(){
        /**
         * The selects the relevant file to be read
         */
        if(mode.getModeType().equals("Normal")){
            return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\StandardHighScores");
        }
        else {
            switch (mode.getModeTypeSelection()) {
                case 0:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\StandardHighScores");
                case 2:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\timeAttack2Mins");
                case 5:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\timeAttack5Mins");
                case 10:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\timeAttack10Mins");
                case 10_000:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\scoreAttack10_000");
                case 50_000:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\scoreAttack50_000");
                case 100_000:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\scoreAttack100_000");
                default:
                    return file = Paths.get("F:\\JavaFXTesting\\src\\sample\\StandardHighScores");
            }
        }
    }
}
