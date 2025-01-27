package Classes;
import Interfaces.CollectionPlaylistInterface;
import Interfaces.PlaylistInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CollectionPlaylist implements CollectionPlaylistInterface<Song> {

    @SuppressWarnings("rawtypes")
    private List<PlaylistInterface> playlists; 

    public CollectionPlaylist() {
        playlists = new ArrayList<>();  
    }

    @SuppressWarnings("rawtypes")
    public String listAllPlaylists() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < playlists.size(); i++) {
            PlaylistInterface playlist = playlists.get(i);
            result.append(playlist.getName()).append(": ").append(playlist.getDuration()).append(" seconds\n");
        }
        return result.toString();
    }

    @SuppressWarnings("rawtypes")
    public String getPlaylistContents(String playlistName) {
        for (int i = 0; i < playlists.size(); i++) {
            PlaylistInterface playlist = playlists.get(i);
            if (playlist.getName().equals(playlistName)) {
                return playlist.getAllSongs();
            }
        }
        return "Playlist not found.";
    }

    @SuppressWarnings("rawtypes")
    public void removePlaylist(String playlistName) {
        for (int i = 0; i < playlists.size(); i++) {
            PlaylistInterface playlist = playlists.get(i);
            if (playlist.getName().equals(playlistName)) {
                playlists.remove(i);  
            }
        }
    }
    @SuppressWarnings("rawtypes")

    public void addEmptyPlaylist(String playlistName) {
        PlaylistInterface newPlaylist = new Playlist(playlistName);
        playlists.add(newPlaylist);
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })

    public void addRandomPlaylist(String playlistName, int maxDuration) {
        List<Song> allSongs = new ArrayList<>();
        for (int i = 0; i < playlists.size(); i++) {
            PlaylistInterface playlist = playlists.get(i);
            for (int j = 0; j < playlist.getSongs().size(); j++) {
                allSongs.add((Song) playlist.getSongs().get(j)); 
            }
        }

        PlaylistInterface newPlaylist = new Playlist(playlistName);
        Random rand = new Random();
        int totalDuration = 0;

        while (!allSongs.isEmpty()) {
            int randomIndex = rand.nextInt(allSongs.size());
            Song selectedSong = allSongs.remove(randomIndex);

            if (totalDuration + selectedSong.getDuration() > maxDuration - 30) {
            
            }

            newPlaylist.add(selectedSong);
            totalDuration += selectedSong.getDuration();
        }

        playlists.add(newPlaylist);
    }
    @SuppressWarnings("rawtypes")

    public List<Song> getListSongs() {
        List<Song> allSongs = new ArrayList<>();
        for (int i = 0; i < playlists.size(); i++) {
            PlaylistInterface playlist = playlists.get(i);
            for (int j = 0; j < playlist.getSongs().size(); j++) {
                allSongs.add((Song) playlist.getSongs().get(j));
            }
        }
        return allSongs;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })

    public void removeSongFromAll(Song song) {
        for (int i = 0; i < playlists.size(); i++) {
            PlaylistInterface playlist = playlists.get(i);
            for (int j = 0; j < playlist.getSongs().size(); j++) {
                Song currentSong = (Song) playlist.getSongs().get(j);
                if (currentSong.equals(song)) {
                    playlist.remove(song);
                }
            }
        }
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<PlaylistInterface> listplaylists(){
        return playlists;
    }

    public int size() {
        return playlists.size();
    }
    
    
}


