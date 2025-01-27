package Classes;

import Interfaces.LibraryInterface;
import Interfaces.PlaylistInterface;
import Interfaces.CollectionPlaylistInterface;
import java.util.ArrayList;
import java.util.List;

public class MusicLibrary implements LibraryInterface<Song> {
    private List<Song> songs;

    public MusicLibrary() {
        this.songs = new ArrayList<>();
    }
    public MusicLibrary(List<Song> songs) {
        this.songs = songs;
    }

    public String listAllSongs() {
        String result = "";
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            result += "Artist: " + song.getArtist() +
                      ", Title: " + song.getTitle() +
                      ", Duration: " + song.getDuration() + " seconds\n";
        }
        return result;
    }
    
    @Override
    public String listAllSongssorted() {
        //Similar to bubble sort
        for (int i = 0; i < songs.size(); i++) {
            for (int j = 0; j < songs.size() - i - 1; j++) {
                Song current = songs.get(j);
                Song next = songs.get(j + 1);

                if (current.getArtist().compareTo(next.getArtist()) > 0 ||(current.getArtist().equals(next.getArtist()) &&
                     current.getTitle().compareTo(next.getTitle()) > 0)) {
                    // Swap songs here
                    songs.set(j, next);
                    songs.set(j + 1, current);
                }
            }
        }
        String result = "";
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            result += "Artist: " + song.getArtist() +
                      ", Title: " + song.getTitle() +
                      ", Duration: " + song.getDuration() + " seconds\n";
        }
        return result;
    }

    public String getSongInfo(String artist, String title) {
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            if (song.getArtist().equals(artist) && song.getTitle().equals(title)) {
                return song.allinfo(); 
            }
        }
        return null;
    }

    public void importSongs(List<Song> newSongs) {
        for (int i = 0; i < newSongs.size(); i++) {
            Song song = newSongs.get(i);
    
            if (!songs.contains(song)) {
                songs.add(song); 
            }
        }
    }

    

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
    
    
    
    public void removeSongsFromLibrary(List<Song> songsToRemove) {
        for (int i = 0; i < songsToRemove.size(); i++) {
            Song songToRemove = songsToRemove.get(i);
    
            for (int j = songs.size() - 1; j >= 0; j--) {
                if (songs.get(j).equals(songToRemove)) {
                    songs.remove(j); 
                }
            }
        }
    
      
    }
    
    
    
    public int size() {
        return songs.size();
    }

    public boolean contains(Song song) {
        for (int i = 0; i < this.songs.size(); i++) {
            if (this.songs.get(i).equals(song)) { 
                return true;
            }
        }
        return false;
    }
    
}
