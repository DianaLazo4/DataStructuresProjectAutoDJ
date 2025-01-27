package Classes;

import Interfaces.PlaylistInterface;
import java.util.ArrayList;
import java.util.List;

public class Playlist implements PlaylistInterface<Song> {
    private String name;  
    private List<Song> songs;  

    public Playlist(Object name) {
        this.name = (String) name;
        this.songs = new ArrayList<>(); 
    }
    public Playlist(String name, List<Song> Songs) {
        this.name = name;
        this.songs = Songs; 
    }

    public void add(Song song) {
        songs.add(song);
    }

    public void remove(Song song) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(song.getTitle()) && songs.get(i).getArtist().equals(song.getArtist())) {
                songs.remove(i);  
                break;  
            }
        }
    }
    
  
    public String getAllSongs() {
        String result = "";
        for (int i = 0; i < songs.size(); i++) {
            result += songs.get(i).toString() + "\n";
        }
        return result;
    }
    

 
    public int getDuration() {
        int totalDuration = 0;
        for (int i = 0; i < songs.size(); i++) {
            totalDuration += songs.get(i).getDuration();
        }
        return totalDuration;
    }
    

 
    public String playNext() {
        if (isEmpty()) {
            return "No songs available to play.";  
        }
        Song nextSong = songs.get(0);  
        songs.remove(0); 
        return "Title: " + nextSong.getTitle() + ", Artist: " + nextSong.getArtist() + ", Duration: " + nextSong.getDuration() + " seconds";
    }

    public boolean isEmpty() {
        return songs.isEmpty();
    }


    public int size() {
        return songs.size();
    }

    public List<Song> getSongs() {
        return songs;
    }
    public String getName() {
        return name;
    }

    public boolean contains(Song song) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getTitle().equals(song.getTitle()) && songs.get(i).getArtist().equals(song.getArtist())) {
                return true; 
            }
        }
        return false; 
    }
    
   
    
}
