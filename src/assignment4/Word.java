package assignment4;

/*
    Sonal Jain and Ali Tejani
    sj23277 and amt3639
    Assignment 4 - Word Ladder
*/

public class Word {
   private String word;
   private boolean marker;
   public final static int WORD_LENGTH = 5;

   // creates a new word
   public Word(String w) {
      word = w;
      marker = false;
   }

   // returns the string contained by this word
   public String getWord() {
      return word;
   }

   // gets the value of the marker
   public boolean getMarker() {
      return marker;
   }

   // sets the marker
   public void setMarker(boolean c) {
      marker = c;
   }

   // return true if word2 and this word contain the same string
   public boolean equals(Word word2) {
      return this.word.equals(word2.getWord());
   }

   // return true if word2 is different from this word by at most one letter
   public boolean differentByOne(Word word2) {
      if (word.substring(1,5).equals(word2.getWord().substring(1,5)))
         return true;
      if (word.substring(0,4).equals(word2.getWord().substring(0,4)))
         return true;
      for (int i = 1; i < WORD_LENGTH - 1; i += 1) {
         if (word.substring(0, i).equals(word2.getWord().substring(0, i)) && word.substring(i + 1, WORD_LENGTH).equals(word2.getWord().substring(i+1, WORD_LENGTH))) {
            return true;
         }
      }
      return false;
   }
}

