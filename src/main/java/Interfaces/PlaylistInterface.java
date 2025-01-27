package Interfaces;

import java.util.List;

public interface PlaylistInterface<T> {
    //Add a song to the playlist
    void add(T song);

    //Remove a song from the playlist
    void remove(T song);

    //Return a string representing all songs in the playlist
    String getAllSongs();

    //Calculate the total duration of the playlist
    int getDuration();

    //Play the next song, remove it from the playlist and return its info
    String playNext();

    //Check if the playlist is empty
    boolean isEmpty();

    //Return the number of songs in the playlist
    int size();
    
    //returns the list of Songs
    List<T> getSongs();
    

    //Return the name of the playlist
    String getName();

    //Checks if the Playlist contains a song
    boolean contains(T Song);
}
