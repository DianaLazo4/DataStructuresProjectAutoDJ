import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Classes.CollectionPlaylist;
import Classes.Playlist;
import Classes.Song;
import Classes.SongDataIO;
import Interfaces.CollectionPlaylistInterface;
import Interfaces.PlaylistInterface;

import java.io.IOException;
import java.util.List;

public class PlaylistCollectionTest{

    @Test
    public void testAddEmptyPlaylist() {
        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();
        collection.addEmptyPlaylist("Workout");
        assertEquals(1, collection.listplaylists().size());
        assertEquals("Workout", ((Playlist) collection.listplaylists().get(0)).getName());
    }

    @Test
    public void testRemovePlaylist() {
        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();
        collection.addEmptyPlaylist("Workout");
        collection.addEmptyPlaylist("Chill");
        collection.removePlaylist("Workout");
        assertEquals(1, collection.listplaylists().size());
        assertEquals("Chill", ((Playlist) collection.listplaylists().get(0)).getName());
    }

    @Test
    public void testListAllPlaylists() {
        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();
        collection.addEmptyPlaylist("Workout");
        collection.addEmptyPlaylist("Chill");
        String result = collection.listAllPlaylists();
        assertTrue(result.contains("Workout"));
        assertTrue(result.contains("Chill"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetPlaylistContents() {
        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();
        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist = new Playlist("Workout");
        playlist.add(new Song("Artist1", "Song1", 180, 100, 50, 0.8, 0.7, 0.9));
        collection.listplaylists().add(playlist);

        String contents = collection.getPlaylistContents("Workout");
        assertTrue(contents.contains("Song1"));
        assertTrue(contents.contains("Artist1"));
    }

    @Test
    public void testAddRandomPlaylist() throws IOException {
        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();

        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksShorter.json");
        
        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist = new Playlist("Original", allSongs);

        assertNotEquals(playlist.size(), 5);
        
        collection.listplaylists().add(playlist);


        assertEquals(collection.size(), 1);


        collection.addRandomPlaylist("Random", 10000);
        assertEquals(2, collection.listplaylists().size());
        assertEquals("Random", ((Playlist) collection.listplaylists().get(1)).getName());

        System.out.println(((Playlist) collection.listplaylists().get(1)).getAllSongs());

        int diff = (10000- ((Playlist) collection.listplaylists().get(1)).getDuration());
        assertTrue(diff < 30);

        //runa couple of times, see if random playlists occurs, Yes! it works
        System.out.println(((Playlist) collection.listplaylists().get(1)).getAllSongs());


    }


    @Test
    public void testRemoveSongFromAll() throws IOException {

        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();

        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter.json");
        
        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist = new Playlist("Original", allSongs);

        assertEquals(playlist.size(), 3);
        
        collection.listplaylists().add(playlist);


        assertEquals(collection.size(), 1);

        List<Song> allSongs2 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter.json");
        
        Song toremove = allSongs2.get(0);
        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist2 = new Playlist("Original2", allSongs2);        
        collection.listplaylists().add(playlist2);



        collection.removeSongFromAll(toremove);

        for (int i = 0; i < collection.listplaylists().size(); i++) {
            @SuppressWarnings("rawtypes")
            PlaylistInterface playlistt = (Playlist) collection.listplaylists().get(i);
            assertFalse(playlistt.getSongs().contains(toremove));
            System.out.println(playlistt.getAllSongs());
        }
        
    }
}
    



