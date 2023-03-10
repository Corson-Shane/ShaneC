import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<String> words = read("res/words.txt"); 
        HashMap<String, Integer> wordCounter = buildHashMap(words); 
        createHTMLFile(wordCounter,"Words" ,"res/word.html");
        ArrayList<WordFrequency> wordFrequencyList = populateArrayList(wordCounter);
        Collections.sort(wordFrequencyList);
        createHTMLFile(wordFrequencyList, "res/sortedWords.html");

        ArrayList<String> paragraphs = read("res/paragraph.txt"); 
        HashMap<String, Integer> paragraphCounter = buildHashMap(paragraphs);  
        createHTMLFile(paragraphCounter, "Paragraph Words", "res/paragraph.html"); 
        ArrayList<ParagraphFrequency> paragraphFrequencyList = populateParagraphArrayList(paragraphCounter);
        Collections.sort(paragraphFrequencyList);
        createParagraphHTMLFile(paragraphFrequencyList, "res/sortedParagraphWords.html");
    }

    /**
     * @param fileName
     * reads input from file and loads words into ArrayList wordList
     * @return
     */
    private static ArrayList<String> read(String fileName){
        File file = new File(fileName);
        ArrayList<String> wordList = new ArrayList<>();

        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            
            while (line != null) 
            {
                String[] words = line.split("[ .,]+");
                
                for(String word: words)
                {
                    if(word.trim().length() > 0)
                    {
                        wordList.add(word.toLowerCase());
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }

        catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return wordList;
    }


    /**
     * @param words
     * creates HashMap counter from ArrayList
     * @return
     */
    private static HashMap<String, Integer> buildHashMap(ArrayList<String> words)
    {
        HashMap<String, Integer> counter = new HashMap<>();
        for(String word: words)
        {
            Integer count = counter.get(word);
            if(count == null)
            {
                counter.put(word, 1);
            }
            else
            {
                counter.put(word, count + 1);
            }
        }
        return counter;
    }



    
    /**
     * @param counter
     * creates HTML file containing table using values from HashMap, unsorted
     * @param pathname
     */
    private static void createHTMLFile(HashMap<String, Integer> counter, String header, String pathname)
    {
        File file = new File(pathname);
        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            builder.append("<h1>" + header + ", unsorted</h1>");
            builder.append("<table>");
            for(String key: counter.keySet())
            {
                builder.append("<tr>");
                builder.append("<td>" + key + "</td>");
                builder.append("<td>" + counter.get(key) + "</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
            fileWriter.append(builder.toString());
            fileWriter.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        for(String keyWord: counter.keySet())
        {
            System.out.println(keyWord + ": " + counter.get(keyWord));
        }
    }

    /**
     * @param wordCounter
     * populates ArrayList of type WordFrequency using values from HashMap
     * @return
     */
    private static ArrayList<WordFrequency> populateArrayList(HashMap<String, Integer> wordCounter)
    {
        ArrayList<WordFrequency> wordFrequencyList = new ArrayList<>();
        for(String key: wordCounter.keySet())
        {
            WordFrequency wordFrequency = new WordFrequency(null, null);
            wordFrequency.wordCount = wordCounter.get(key); 
            wordFrequency.word = key;
            wordFrequencyList.add(wordFrequency);
        }
        return wordFrequencyList;
    }

    /**
     * @param wordCounter
     * populates ArrayList of type ParagraphFrequency using values from HashMap
     * @return
     */
    private static ArrayList<ParagraphFrequency> populateParagraphArrayList(HashMap<String, Integer> wordCounter)
    {
        ArrayList<ParagraphFrequency> paragraphFrequencyList = new ArrayList<>();
        for(String key: wordCounter.keySet())
        {
            ParagraphFrequency paragraphFrequency = new ParagraphFrequency(null, null);
            paragraphFrequency.wordCount = wordCounter.get(key); 
            paragraphFrequency.word = key;
            paragraphFrequencyList.add(paragraphFrequency);
        }
        return paragraphFrequencyList;
    }

    /**
     * @param wordFrequencyList
     * creates HTML file containing values from ArrayList of type WordFrequency
     * @param pathname
     */
    private static void createHTMLFile(ArrayList<WordFrequency> wordFrequencyList, String pathname)
    {
        File file = new File(pathname);
        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Words, sorted<h1>");
            builder.append("<table>");
            for(WordFrequency wordFrequency: wordFrequencyList)
            {
                builder.append("<tr>");
                builder.append("<td>" + wordFrequency.word + "</td>");
                builder.append("<td>" + wordFrequency.wordCount + "</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
            fileWriter.append(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * @param paragraphFrequencyList
     * creates HTML file containing values from ArrayList of type ParagraphFrequency
     * @param pathname
     */
    private static void createParagraphHTMLFile(ArrayList<ParagraphFrequency> paragraphFrequencyList, String pathname)
    {
        File file = new File(pathname);
        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder builder = new StringBuilder();
            builder.append("<h1>Paragraph words, sorted<h1>");
            builder.append("<table>");
            for(ParagraphFrequency paragraphFrequency: paragraphFrequencyList)
            {
                builder.append("<tr>");
                builder.append("<td>" + paragraphFrequency.word + "</td>");
                builder.append("<td>" + paragraphFrequency.wordCount + "</td>");
                builder.append("</tr>");
            }
            builder.append("</table>");
            fileWriter.append(builder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
