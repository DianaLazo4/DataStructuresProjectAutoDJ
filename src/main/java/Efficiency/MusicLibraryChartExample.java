package Efficiency;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;


import Classes.*;
import Interfaces.*;

public class MusicLibraryChartExample {

    @SuppressWarnings({ "unchecked", "hiding" })
    public static <Song> void main(String[] args) throws IOException {
        
        List<Song> songs = (List<Song>) SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEfficiency.json");

        // Test MusicLibrary
        collectLibraryImportData(new MusicLibrary(), "MusicLibrary Import", (List<Classes.Song>) songs);
        collectLibraryLookupData(new MusicLibrary(), "MusicLibrary Lookup", (List<Classes.Song>) songs);

        // Test MusicLibraryHashMap
        collectLibraryImportData(new MusicLibraryHashMap(), "MusicLibraryHashMap Import", (List<Classes.Song>) songs);
        collectLibraryLookupData(new MusicLibraryHashMap(), "MusicLibraryHashMap Lookup", (List<Classes.Song>) songs);

        
    }

    public static void collectLibraryImportData(LibraryInterface<Song> library, String chartTitle, List<Song> songs) {
        final int numItemsToImportEachTime = 2500; // Number of songs to import each time
        final int numDataPointsToPrint = 25; // How many times to print data points
        final int numToAverageOver = 20; // Number of times to average the import
    
        System.out.println("Testing Import for " + chartTitle);
        System.out.println("Number of Songs \t Time to Import (ms)");
    
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(chartTitle);
    
        for (int i = 0; i < numDataPointsToPrint; i++) {
            // Calculate the number of songs to import in this iteration
            int songsToImport = (i + 1) * numItemsToImportEachTime;
            // Ensure we don't exceed the number of songs available
            if (songsToImport > songs.size()) {
                songsToImport = songs.size();
            }
    
            // Import the songs (you can copy a sublist or process them directly)
            // This sublist stores the songs in a music library that grows after each import
            List<Song> subList = new ArrayList<>(songs.subList(0, songsToImport));
            double avgImportTime = averageTimeForImport(library, subList, numToAverageOver);
    
            System.out.println(songsToImport + "\t\t" + avgImportTime);
            series.add(songsToImport, avgImportTime);
    
            if (songsToImport == songs.size()) {
                break;
            }
        }
    
        dataset.addSeries(series);
    
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,                      // Chart Title
                "Number of Songs",               // X-axis label
                "Time to Import (ms)",           // Y-axis label
                dataset,                         // Data set
                PlotOrientation.VERTICAL,        // Plot orientation
                true,                            // Show legend
                true,                            // Show tooltips
                false                            // No URLs
        );
    
        // Create a JFrame to display the chart
        JFrame frame = new JFrame(chartTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    private static double averageTimeForImport(LibraryInterface<Song> library, List<Song> songs, int numToAverageOver) {
        long totalImportTime = 0;
        for (int i = 0; i < numToAverageOver; i++) {
            long startTime = System.nanoTime();
            library.importSongs(songs);
            long endTime = System.nanoTime();
            totalImportTime += (endTime - startTime);
        }
        return (totalImportTime / numToAverageOver) / 1_000_000.0; 
    }
    
    
    public static void collectLibraryLookupData(LibraryInterface<Song> library, String chartTitle, List<Song> songs) {
        final int numLookupsToPerform = 2500; 
        final int numDataPointsToPrint = 25;
        final int numToAverageOver = 20; 
    
        System.out.println("Testing Lookup for " + chartTitle);
        System.out.println("Number of Songs \t Time to Perform Lookups (ms)");
    
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries(chartTitle);
    
    
        int totalSongs = songs.size();
    
        // Start with an empty library and then import songs bit by bit
        for (int i = 1; i <= numDataPointsToPrint; i++) {
            int songsToImport = i * numLookupsToPerform; 
    
            if (songsToImport > totalSongs) {
                songsToImport = totalSongs;
            }
            
            
            List<Song> subList = songs.subList(0, songsToImport); // Gets a subset of songs to import
            library.importSongs(subList); // Import the current subset of songs into the library
    
            // Perform lookups for this subset of songs and average the time
            double avgLookupTime = averageTimeForLookup(library, subList, numLookupsToPerform, numToAverageOver);
    
            System.out.println(songsToImport + "\t\t" + avgLookupTime);
            series.add(songsToImport, avgLookupTime);
    
            if (songsToImport == totalSongs) {
                break;
            }
        }
    
        dataset.addSeries(series);
    
        // Create the chart using the dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,                      // Chart Title
                "Number of Songs",               // X-axis label
                "Time to Perform Lookups (ms)",  // Y-axis label
                dataset,                         // Data set
                PlotOrientation.VERTICAL,        // Plot orientation
                true,                            // Show legend
                true,                            // Show tooltips
                false                            // No URLs
        );
    
        // Create a JFrame to display the chart
        JFrame frame = new JFrame(chartTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
    
    private static double averageTimeForLookup(LibraryInterface<Song> library, List<Song> songs, int numLookups, int numToAverageOver) {
        long totalLookupTime = 0;
        Random random = new Random();
    
        // Average over the specified number of times
        for (int i = 0; i < numToAverageOver; i++) {
            long startTime = System.nanoTime();
            for (int j = 0; j < numLookups; j++) {
                Song randomSong = songs.get(random.nextInt(songs.size())); // Pick a random song
                library.contains(randomSong); // Perform lookup
            }
            long endTime = System.nanoTime();
            totalLookupTime += (endTime - startTime);
        }
    
        return (totalLookupTime / numToAverageOver) / 1_000_000.0; // Convert to milliseconds
    }
    
   
}