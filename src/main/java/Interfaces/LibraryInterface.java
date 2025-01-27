package Interfaces;

import java.util.List;


public interface LibraryInterface<T>  {
    // Return all songs in alphabetical order by artist, then by title
    String listAllSongssorted();

    // Return all songs 
    String listAllSongs();

    // Retrieve information about a specific song
    String getSongInfo(String artist, String title);

    // Add a collection of songs to the library
    void importSongs(List<T> songs);

    // Remove given songs from the library and all playlists
    void removeSongsFromLibraryAndPlaylists(List<T> songs, CollectionPlaylistInterface<T> playlists);

    // Remove given songs from the library
    void removeSongsFromLibrary(List<T> songs);

    // Returns how many songs are in the Library
    int size();

    //Checks if a song is in the library
    boolean contains(T song);

}

