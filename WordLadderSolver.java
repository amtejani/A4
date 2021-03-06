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
	//    Dictionary dictionary;
	private HashSet<Word> dict;
	public final static int WORD_LENGTH = 5;

	public WordLadderSolver(String filename) {
//        dictionary = new Dictionary(filename);


		dict = new HashSet<>();
		BufferedReader reader;
		try {
			FileReader freader = new FileReader(filename);
			reader = new BufferedReader(freader);
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				if (s.charAt(0) != '*')
					dict.add(new Word(s.substring(0,5).toLowerCase()));
			}
//			for(Word s: dict){
//				System.out.println(s.getWord());
//			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

	// do not change signature of the method implemented from the interface
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
		unmarkAll();
		Word w1 = new Word(startWord);
		Word w2 = new Word(endWord);
		boolean contained = false;
		for(Word w: dict) {
			if (w.equals(w1) || w.equals(w2)) {
				contained = true;
			}
		}
		if (!contained) {
			return null;
		}
//		List<Word> ladder = getLadder(w1, w2, 5);
		List<Word> ladder = getLadder(w1, w2);
		List<String> ladderStrings = new ArrayList<>();


		if (ladder != null) {
			for (Word w: ladder) {
				ladderStrings.add(w.getWord());
			}
			return ladderStrings;
		}
		throw new NoSuchLadderException("No ladder between " + startWord + " and " + endWord + "!");
		// implement this method
//		throw new UnsupportedOperationException("Not implemented yet!");
	}

	private List<Word> getLadder(Word startWord, Word endWord, int differentLetter) {
		List<Word> ladder = null;
		if (startWord.differentByOne(endWord) < 5) {
			ladder = new ArrayList<>();
			ladder.add(startWord);
			return ladder;
		}
		for (Word w : dict) {
			int letter = startWord.differentByOne(w);
			if (!w.getMarker() && letter < WORD_LENGTH && letter != differentLetter) {
				w.setMarker(true);
				List<Word> tempLadder = getLadder(w, endWord, letter);
//				w.setMarker(false);
				if ((tempLadder != null) && ((ladder == null) || (tempLadder.size() < ladder.size()))) {
					ladder = tempLadder;
					ladder.add(0,startWord);
//					return ladder;
				}
			}
		}
		return ladder;
	}

	private List<Word> getLadder(Word startWord, Word endWord) {
		Map<Word,ArrayList<Word>> ladder = new HashMap<>();
		LinkedList<Word> queue = new LinkedList<>();
		queue.addLast(startWord);
		ArrayList<Word> startLadder = new ArrayList<>();
		startLadder.add(startWord);
		ladder.put(startWord, startLadder);
		Word temp = startWord;
		
		while (!temp.equals(endWord) && queue.peek() != null) {
			temp = queue.removeFirst();
			for (Word w : dict) {
				if (!w.getMarker() && w.differentByOne(temp) < WORD_LENGTH) {
					queue.addLast(w);
					ArrayList<Word> tempLadder = new ArrayList<>(ladder.get(temp));
					tempLadder.add(w);
					ladder.put(w, tempLadder);
					w.setMarker(true);
				}
			}
		}
		if(temp.equals(endWord))
			return ladder.get(temp);
		return null;
	}

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

	// add additional methods here
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

	public void unmarkAll() {
		for (Word w : dict) {
			w.setMarker(false);
		}
	}
}
