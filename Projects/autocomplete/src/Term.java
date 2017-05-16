
/*************************************************************************
 * @author Kevin Wayne
 *
 * Description: A term and its weight.
 * 
 *************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private final String myWord;
	private final double myWeight;

	/**
	 * The constructor for the Term class. Should set the values of word and
	 * weight to the inputs, and throw the exceptions listed below
	 * 
	 * @param word
	 *            The word this term consists of
	 * @param weight
	 *            The weight of this word in the Autocomplete algorithm
	 * @throws NullPointerException
	 *             if word is null
	 * @throws IllegalArgumentException
	 *             if weight is negative
	 */
	public Term(String word, double weight) {
		// TODO: Complete Term constructor
		if(word == null){
			throw new NullPointerException("Word is empty!");
		}
		if(weight<0){
			throw new IllegalArgumentException("Weight is negative!");
		}
		myWord = word;
		myWeight = weight;
	}

	/**
	 * A Comparator for comparing Terms using a set number of the letters they
	 * start with. This Comparator may be useful in writing your implementations
	 * of Autocompletors.
	 *
	 */
	public static class PrefixOrder implements Comparator<Term> {
		private final int r;

		public PrefixOrder(int r) {
			this.r = r;
		}

		/**
		 * Compares v and w lexicographically using only their first r letters.
		 * If the first r letters are the same, then v and w should be
		 * considered equal. This method should take O(r) to run, and be
		 * independent of the length of v and w's length. You can access the
		 * Strings to compare using v.word and w.word.
		 * 
		 * @param v/w
		 *            - Two Terms whose words are being compared
		 */
		public int compare(Term v, Term w) {
			int length = Math.min(Math.min(v.myWord.length(), w.myWord.length()), r);
			for(int i = 0; i<length; i++){
				if(Character.toString(v.myWord.charAt(i)).compareTo(Character.toString(w.myWord.charAt(i)))  != 0 )
					return Character.toString(v.myWord.charAt(i)).compareTo(Character.toString(w.myWord.charAt(i))); 
			}
			if(r-length>0)
				return v.myWord.length()-w.myWord.length();
			return 0;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in descending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class ReverseWeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			if(v.myWeight-w.myWeight > 0){
				return -1;
			} else if(v.myWeight-w.myWeight < 0){
				return 1;
			}
			return 0;
		}
	}

	/**
	 * A Comparator for comparing Terms using only their weights, in ascending
	 * order. This Comparator may be useful in writing your implementations of
	 * Autocompletor
	 *
	 */
	public static class WeightOrder implements Comparator<Term> {
		public int compare(Term v, Term w) {
			if(v.myWeight-w.myWeight > 0){
				return 1;
			} else if(v.myWeight-w.myWeight < 0){
				return -1;
			}
			return 0;
		}
	}

	/**
	 * The default sorting of Terms is lexicographical ordering.
	 */
	public int compareTo(Term that) {
		return myWord.compareTo(that.myWord);
	}

	/**
	 * Getter methods, use these in other classes which use Term
	 */
	public String getWord() {
		return myWord;
	}

	public double getWeight() {
		return myWeight;
	}

	public String toString() {
		return String.format("%14.1f\t%s", myWeight, myWord);
	}
	
	public static void main(String[] args){
		PrefixOrder test = new Term.PrefixOrder(4);
		System.out.println(test.compare(new Term("bug", 0), new Term("bees", 0)));
	}
}