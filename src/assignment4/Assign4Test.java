package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Assign4Test {

	@Test
	public void computeLadderTest() throws NoSuchLadderException {
		Assignment4Interface wordLadderSolver = new WordLadderSolver("A4words.dat");
		List<String> result;
		
		// NoSuchLadder tests
		try{
			result = wordLadderSolver.computeLadder("devil", "angel");
			fail("should through NoSuchLadderException");
		} catch (NoSuchLadderException e) {}
		try{
			result = wordLadderSolver.computeLadder("mumbo", "ghost");
			fail("should through NoSuchLadderException");
		} catch (NoSuchLadderException e) {}
		
		try {
			// invalid input tests
			result = wordLadderSolver.computeLadder("a", "angel");
			assertEquals(null,result);
			result = wordLadderSolver.computeLadder("angel", "b");
			assertEquals(null,result);
			result = wordLadderSolver.computeLadder("a", "b");
			assertEquals(null,result);
			
			// short ladder tests
			result = wordLadderSolver.computeLadder("dears", "fears");
			assertEquals(2, result.size());
			result = wordLadderSolver.computeLadder("hello", "hello");
			assertEquals(1, result.size());
		} catch(NoSuchLadderException e) {
			fail("should either return ladder or null, should not throw NoSuchLadderException");
		}
	}
	
	@Test
	public void validateLadderTest() {
		Assignment4Interface wordLadderSolver = new WordLadderSolver("A4words.dat");
		List<String> result = new ArrayList<>();
		
		// short ladder tests
		result.add("dears");
		result.add("fears");
		assertTrue(wordLadderSolver.validateResult("dears", "fears", result));
		
		result.clear();
		result.add("dears");
		assertTrue(wordLadderSolver.validateResult("dears", "dears", result));
	}

	@Test
	public void wordTest() {
		Word w1 = new Word("hello");
		Word w2 = new Word("jello");
		Word w3 = new Word("heplo");
		Word w4 = new Word("helno");
		Word w5 = new Word("hells");
		Word w6 = new Word("hello");
		Word w7 = new Word("jello");
		
		// getWord() test
		assertEquals("hello", w1.getWord());
		assertEquals("jello", w2.getWord());
		assertEquals("heplo", w3.getWord());
		
		// equals() test
		assertTrue(w1.equals(w6));
		assertTrue(w2.equals(w7));
		assertFalse(w1.equals(w2));
		assertFalse(w3.equals(w4));
		assertFalse(w1.equals(w5));

		// differentByOne() test
		assertTrue(w1.differentByOne(w2));
		assertTrue(w1.differentByOne(w3));
		assertTrue(w1.differentByOne(w4));
		assertTrue(w1.differentByOne(w5));
		assertFalse(w2.differentByOne(w3));
		assertFalse(w2.differentByOne(w4));
	}
}
