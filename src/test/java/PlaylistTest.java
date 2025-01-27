import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import Classes.Song;
import Classes.SongDataIO;
import Classes.Playlist;

import Interfaces.PlaylistInterface;


public class PlaylistTest {

    @Test
    public void testAddSong() throws IOException{
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        Song song = new Song("Artist", "Song Title", 200, 0, 50, 0.8, 0.6, 0.7);

        playlist.add(song);

        assertEquals(1, playlist.size());
        assertTrue(playlist.getAllSongs().contains("Song Title"), "Playlist should contain the added song.");
    
        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_Tracks.json");
        PlaylistInterface<Song> madeplaylist = new Playlist("My Playlist 2");
        madeplaylist.add(allSongs.get(0));
        madeplaylist.add(allSongs.get(1));
        madeplaylist.add(allSongs.get(2));
        madeplaylist.add(allSongs.get(3));

        assertEquals(4, madeplaylist.size());

    }

    @Test
    public void testRemoveSong() throws IOException {
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        Song song1 = new Song("Artist1", "Song1", 200, 0, 50, 0.8, 0.6, 0.7);
        Song song2 = new Song("Artist2", "Song2", 180, 0, 50, 0.8, 0.6, 0.7);

        playlist.add(song1);
        playlist.add(song2);

        playlist.remove(song1);

        assertEquals(1, playlist.size());

        assertFalse(playlist.getAllSongs().contains("Song1"));
        
        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_Tracks.json");
        PlaylistInterface<Song> madeplaylist = new Playlist("My Playlist 2");
        madeplaylist.add(allSongs.get(0));
        madeplaylist.add(allSongs.get(1));
        madeplaylist.add(allSongs.get(2));
        madeplaylist.add(allSongs.get(3));

        Song song0 = allSongs.get(0);
        madeplaylist.remove(song0);

        assertEquals(3, madeplaylist.size());

    }

    @Test
    public void testGetDuration() throws IOException {
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        Song song1 = new Song("Artist1", "Song1", 200, 0, 50, 0.8, 0.6, 0.7);
        Song song2 = new Song("Artist2", "Song2", 180, 0, 50, 0.8, 0.6, 0.7);

        playlist.add(song1);
        playlist.add(song2);
        assertEquals(380, playlist.getDuration());

        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_Tracks.json");
        PlaylistInterface<Song> madeplaylist = new Playlist("My Playlist 2");
        madeplaylist.add(allSongs.get(0));
        madeplaylist.add(allSongs.get(1));

        assertEquals(247693+344173, madeplaylist.getDuration());
        

    }

    @Test
    public void testPlayNext() {
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        Song song1 = new Song("Artist1", "Song1", 200, 0, 50, 0.8, 0.6, 0.7);
        Song song2 = new Song("Artist2", "Song2", 180, 0, 50, 0.8, 0.6, 0.7);

        playlist.add(song1);
        playlist.add(song2);

        assertEquals("Title: Song1, Artist: Artist1, Duration: 200 seconds", playlist.playNext());
        assertEquals(1, playlist.size());


       
    }

    @Test
    public void testIsEmpty() {
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        Song song1 = new Song("Artist1", "Song1", 200, 0, 50, 0.8, 0.6, 0.7);

        playlist.add(song1);
        playlist.playNext();

        assertTrue(playlist.isEmpty());
    }

    @Test
    public void testGetName() {
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        assertEquals("My Playlist", playlist.getName());
    }
}
