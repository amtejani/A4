package assignment4;

/*
    Sonal Jain and Ali Tejani
    sj23277 and amt3639
    Assignment 4 - Word Ladder
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface {
	private HashSet<Word> dict;
	public final static int WORD_LENGTH = 5;

	public WordLadderSolver(String filename) {
		dict = new HashSet<>();
		BufferedReader reader;
		try {
			FileReader freader = new FileReader(filename);
			reader = new BufferedReader(freader);
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				if (s.charAt(0) != '*')
					dict.add(new Word(s.substring(0,5).toLowerCase()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
		unmarkAll();
		Word w1 = new Word(startWord);
		Word w2 = new Word(endWord);
		boolean contained1 = false;
		boolean contained2 = false;
		for(Word w: dict) {
			if (w.equals(w1)) {
				contained1 = true;
			}
			if (w.equals(w2)) {
				contained2 = true;
			}
		}
		if (!contained1 || !contained2) {
			return null;
		}
		List<Word> ladder = getLadder(w1, w2);
		List<String> ladderStrings = new ArrayList<>();


		if (ladder != null) {
			for (Word w: ladder) {
				ladderStrings.add(w.getWord());
			}
			return ladderStrings;
		}
		throw new NoSuchLadderException("No ladder between " + startWord + " and " + endWord + "!");
	}

	// bfs getladder
	private List<Word> getLadder(Word startWord, Word endWord) {
		Map<Word,ArrayList<Word>> ladder = new HashMap<>(); // map of word ladders for each word
		LinkedList<Word> queue = new LinkedList<>(); // queue used for bfs
		queue.addLast(startWord);
		ArrayList<Word> startLadder = new ArrayList<>(); // ladder for start word
		startLadder.add(startWord);
		ladder.put(startWord, startLadder);
		Word temp = startWord;
		
		while (!temp.equals(endWord) && queue.peek() != null) { // iterate if queue isnt empty and end condition isnt reached yet 
			temp = queue.removeFirst(); //get next word
			for (Word w : dict) { 
				if (!w.getMarker() && w.differentByOne(temp)) { // for each word that is differnt from current word and that hasnt been marked yet 
					queue.addLast(w);
					ArrayList<Word> tempLadder = new ArrayList<>(ladder.get(temp)); // ladder for next word
					tempLadder.add(w);
					ladder.put(w, tempLadder);
					w.setMarker(true); // mark word
				}
			}
		}
		if(temp.equals(endWord))
			return ladder.get(temp);
		return null;
	}

	// returns true if wordLadder is a valid ladder for the two input words
	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
		if(wordLadder == null || wordLadder.size() == 0)
			return false;
		if (!startWord.equals(wordLadder.get(0)) || !endWord.equals(wordLadder.get(wordLadder.size() -1))){
			return false;
		}
		for(int i = 0; i < wordLadder.size() - 1; i += 1) {
			if (!diffByOne(wordLadder.get(i), wordLadder.get(i + 1)))
				return false;
		}
		return true;
//		throw new UnsupportedOperationException("Not implemented yet!");
	}

	// returns true if word1 and word2 are different by at most one letter
	private boolean diffByOne(String word1, String word2) {
		if (word1.substring(0, 4).equals(word2.substring(0, 4)) || word1.substring(1, 5).equals(word2.substring(1, 5)))
			return true;
		for (int i = 1; i < WORD_LENGTH - 1; i += 1) {
			if ((word1.substring(0, i).equals(word2.substring(0, i)) && word1.substring(i + 1, WORD_LENGTH).equals(word2.substring(i+1, WORD_LENGTH)))) {
				return true;
			}
		}
		return false;
	}

	// clears all markers in the dictionary
	private void unmarkAll() {
		for (Word w : dict) {
			w.setMarker(false);
		}
	}
}
