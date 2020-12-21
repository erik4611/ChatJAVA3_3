package client;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Story {
    private static PrintWriter out;

    private static String getStoryFileNameByLogin(String login) {
        return "C:\\Users\\erik4\\IdeaProjects\\ChatJava3_3\\ChatJAVA3_3\\Story/Story_" + login + ".txt";
    }

    public static void start(String login) {
        try {
            out = new PrintWriter(new FileOutputStream(getStoryFileNameByLogin(login), true), true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (out != null) {
            out.close();
        }
    }

    public static void writeLine(String msg) {
        out.println(msg);
    }

    public static String getLast100LinesOfStory(String login) {
        if (!Files.exists(Paths.get(getStoryFileNameByLogin(login)))) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            List<String> historyLines = Files.readAllLines(Paths.get(getStoryFileNameByLogin(login)));
            int startPosition = 0;
            if (historyLines.size() > 100) {
                startPosition = historyLines.size() - 100;
            }
            for (int i = startPosition; i < historyLines.size(); i++) {
                sb.append(historyLines.get(i)).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
