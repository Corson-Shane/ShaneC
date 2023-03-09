public class ParagraphFrequency implements Comparable<ParagraphFrequency>{
    String word;
    Integer wordCount;

    public ParagraphFrequency (String word, Integer wordCount)
    {
        setWord(word);
        setWordCount(wordCount);
    }

    public String getWord()
    {
        return this.word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public Integer getWordCount()
    {
        return this.wordCount;
    }

    public void setWordCount(Integer wordCount)
    {
        this.wordCount = wordCount;
    }

    @Override
    public int compareTo(ParagraphFrequency otherWordFrequency) {
        int wordValue = 2;
        if(this.wordCount > otherWordFrequency.wordCount)
            wordValue = 1; 
        else if(this.wordCount < otherWordFrequency.wordCount)
            wordValue = -1;
        else 
            wordValue = 0;
        return wordValue;
    }

    @Override
    public String toString()
    {
        return "Word: " + word + ", Word Count: " + wordCount;
    }
}
