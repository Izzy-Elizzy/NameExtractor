package edu.odu.cs.cs350;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class provides utility methods to access various word lists.
 */
public final class WordLists {
    /**
     * Package path contains word lists.
     */
    private static String packagePath = "/";

    private WordLists() {
    }

    /**
     * Retrieves an iterable for the English dictionary word list.
     * 
     * @return An iterable for the English dictionary word list.
     */
    public static Iterable<String> englishDictionary() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.english.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the stoplist word list.
     * 
     * @return An iterable for the stoplist word list.
     */
    public static Iterable<String> stopList() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Stoplist.english.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of cities and states in the US.
     * 
     * @return An iterable for the list of cities and states in the US.
     */
    public static Iterable<String> citiesAndStatesUS() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.citiesStates.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of countries and territories.
     * 
     * @return An iterable for the list of countries and territories.
     */
    public static Iterable<String> countriesAndTerritories() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.territories.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of places.
     * 
     * @return An iterable for the list of places.
     */
    public static Iterable<String> places() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.placenames.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of first names.
     * 
     * @return An iterable for the list of first names.
     */
    public static Iterable<String> firstNames() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.firstNames.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of last names.
     * 
     * @return An iterable for the list of last names.
     */
    public static Iterable<String> lastNames() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.lastNames.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of common first names.
     * 
     * @return An iterable for the list of common first names.
     */
    public static Iterable<String> commonFirstNames() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "commonFirstNames.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of common last names.
     * 
     * @return An iterable for the list of common last names.
     */
    public static Iterable<String> commonLastNames() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.commonLastNames.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of honorifics.
     * 
     * @return An iterable for the list of honorifics.
     */
    public static Iterable<String> honorifics() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.honorifics.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of last name prefixes.
     * 
     * @return An iterable for the list of last name prefixes.
     */
    public static Iterable<String> lastNamePrefixes() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.prefixes.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of last name suffixes.
     * 
     * @return An iterable for the list of last name suffixes.
     */
    public static Iterable<String> lastNameSuffixes() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.suffixes.txt");
        return new StreamInput(list);
    }

    /**
     * Retrieves an iterable for the list of non-personal identifier cues.
     * 
     * @return An iterable for the list of non-personal identifier cues.
     */
    public static Iterable<String> nonPersonalIdentifierCues() {
        InputStream list = WordLists.class.getResourceAsStream(packagePath + "Dictionary.nonPersonalProperNames.txt");
        return new StreamInput(list);
    }

}

/**
 * This class provides an iterable for an input stream of strings.
 */
class StreamInput implements Iterable<String> {
    /**
     * InputStream variable.
     */
    private InputStream inputStream;

    /**
     * Constructs a StreamIterable object with the specified input stream.
     * 
     * @param inputStream The input stream containing strings.
     */
    public StreamInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Returns an iterator over the elements in the input stream.
     * 
     * @return An iterator.
     */
    public Iterator<String> iterator() {
        return new InputStreamIterator(this.inputStream);
    }

}

/**
 * This class provides an iterator over an input stream of strings.
 */
class InputStreamIterator implements Iterator<String> {
    /**
     * BufferReader variable.
     */
    private BufferedReader in;
    /**
     * String variable.
     */
    private String buffer;

    /**
     * Constructs an InputStreamIterator object with the specified input stream.
     * 
     * @param input The input stream to iterate over.
     */
    public InputStreamIterator(InputStream input) {
        this.in = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        try {
            this.buffer = this.in.readLine();
        } catch (IOException variableThree) {
            this.buffer = null;
        }

    }

    /**
     * Checks if there are more elements in the input stream.
     * 
     * @return true if there are more elements, otherwise false.
     */

    public boolean hasNext() {
        return this.buffer != null;
    }

    /**
     * Retrieves the next element from the input stream.
     * 
     * @return The next element.
     * @throws NoSuchElementException if there are no more elements.
     */

    public String next() {
        if (this.buffer == null) {
            throw new NoSuchElementException();
        } else {
            String current = this.buffer;

            try {
                this.buffer = this.in.readLine();
            } catch (IOException variableThree) {
                this.buffer = null;
            }

            return current;
        }
    }

    /**
     * Removes the current element from the input stream (unsupported operation).
     * 
     * @throws UnsupportedOperationException always, as remove() is not supported.
     */
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported on InputStreamIterators");
    }
}