package Classes;

import Interfaces.LibraryInterface;
import Interfaces.PlaylistInterface;
import Interfaces.CollectionPlaylistInterface;
import java.util.*;

public class MusicLibraryHashMap implements LibraryInterface<Song> {
    private Map<Integer, LinkedListforSongs> table;
    private int size;

    public MusicLibraryHashMap() {
        this.table = new HashMap<>();
        this.size = 0;
    }

    @Override
    public void importSongs(List<Song> newSongs) {
        for (Song song : newSongs) {
            int hash = song.getArtist().hashCode();
            
            LinkedListforSongs list = table.get(hash);
            if (list == null) {
                list = new LinkedListforSongs();
                table.put(hash, list);
            }
    
            list.add(song);
            size++; 
        }
    }

    public String listAllSongssorted() {
        String result = "";
    
        for (int i = 0; i < table.size(); i++) {
            LinkedListforSongs list = table.get(i); 
    
            if (list != null) {
                String sortedSongs = list.listAllSongsSorted(); 
                result += sortedSongs; 
            }
        }
    
        return result;
    }
  
    public String getSongInfo(String artist, String title) {
        int hash = artist.hashCode();

        LinkedListforSongs list = table.get(hash);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Song song = list.get(i);
                if (song.getArtist().equals(artist) && song.getTitle().equals(title)) {
                    return song.allinfo(); 
                }
            }
        }

        return null;
    }


    public void removeSongsFromLibrary(List<Song> songsToRemove) {
        for (Song songToRemove : songsToRemove) {
            int hash = songToRemove.getArtist().hashCode();
            LinkedListforSongs list = table.get(hash);
    
            if (list != null) {
                list.remove(songToRemove); 
                    size--;
                    if (list.listAllSongs().isEmpty()) {
                    table.remove(hash);
                }
            }
        }
    }
    

    @Override
    public void removeSongsFromLibraryAndPlaylists(List<Song> songsToRemove, CollectionPlaylistInterface<Song> collectionPlaylists) {
        removeSongsFromLibrary(songsToRemove);

        List<PlaylistInterface<Song>> playlists = collectionPlaylists.listplaylists();
        for (Song songToRemove : songsToRemove) {
            for (PlaylistInterface<Song> playlist : playlists) {
                if (playlist.contains(songToRemove)) {
                    playlist.remove(songToRemove);
                }
            }
        }
    }


    @Override
    public int size() {
        return size;
    }

    public boolean contains(Song song) {
        int hash = song.getArtist().hashCode();
        LinkedListforSongs list = table.get(hash);
        return list.contains(song);
    }

    public String listAllSongs() {
        String result = "";

        for (int i = 0; i < table.size(); i++) {
            LinkedListforSongs list = table.get(i); 

            if (list != null) {
                String Songs = list.listAllSongs();
                result += Songs; 
            }
        }

        return result;
    }

        
}
