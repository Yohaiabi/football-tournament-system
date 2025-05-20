package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for logging text messages to an output file.
 */
public final class MyFileLogWriter {

	private static FileWriter writer;

	/**
	 * Initializes the file writer and creates the output file.
	 */
	public static void initializeMyFileWriter() {
		File outputLogFile = new File("output.txt");
		try {
			writer = new FileWriter(outputLogFile);
		} catch (IOException e) {
			System.err.println("Error initializing file writer: " + e.getMessage());

		}
	}

	/**
	 * Writes a message to the log file in a new line.
	 *
	 * @param message the message to write
	 */
	public static void writeToFileInSeparateLine(String message) {
		if (writer == null) {
			System.err.println("Writer not initialized. Call initializeMyFileWriter() first.");
			return;
		}
		try {
			writer.write(message + "\n");
		} catch (IOException e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	/**
	 * Saves the log file by closing the writer.
	 */
	public static void saveLogFile() {
		if (writer == null) {
			System.err.println("Writer not initialized. Nothing to save.");
			return;
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.err.println("Error closing file writer: " + e.getMessage());
		}
	}
}