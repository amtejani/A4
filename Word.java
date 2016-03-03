package assignment4;

/**
 * Created by Ali on 2/27/2016.
 */
public class Word {
   private String word;
   private boolean marker;
   public final static int WORD_LENGTH = 5;

   public Word(String w) {
      word = w;
      marker = false;
   }

   public String getWord() {
      return word;
   }

   public boolean getMarker() {
      return marker;
   }

   public void setMarker(boolean c) {
      marker = c;
   }

   public boolean equals(Word word2) {
      return this.word.equals(word2.getWord());
   }

   public int differentByOne(Word word2) {
      if (word.substring(1,5).equals(word2.getWord().substring(1,5)))
         return 0;
      if (word.substring(0,4).equals(word2.getWord().substring(0,4)))
         return WORD_LENGTH - 1;
      for (int i = 1; i < WORD_LENGTH - 1; i += 1) {
         if (word.substring(0, i).equals(word2.getWord().substring(0, i)) && word.substring(i + 1, WORD_LENGTH).equals(word2.getWord().substring(i+1, WORD_LENGTH))) {
            return i;
         }
      }
      return WORD_LENGTH;
   }
}

