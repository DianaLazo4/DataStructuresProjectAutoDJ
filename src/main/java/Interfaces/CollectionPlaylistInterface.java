package Interfaces;

import java.util.List;

public interface CollectionPlaylistInterface<T>{

    //Returns a string representing all playlist names and their durations
    String listAllPlaylists();

    
    //Returns a string representing the contents of a particular playlist given the name
    String getPlaylistContents(String playlistName);

    //Removes the playlist with the given name
    void removePlaylist(String playlistName);


    //Adds a new empty playlist with the given name
    void addEmptyPlaylist(String playlistName);

    
    //Creates a new playlist with the given name, populated with random non-repeating songs
    void addRandomPlaylist(String playlistName, int maxDuration);

    //Removes the song from all playlists
    void removeSongFromAll(T song);

    //Gets a list of all songs in all playlists
    List<T> getListSongs();

    //Returns the list of playlists
    <Playlist> List<Playlist> listplaylists();

    //return how many playlists are in the collection of playlists
    int size();

}