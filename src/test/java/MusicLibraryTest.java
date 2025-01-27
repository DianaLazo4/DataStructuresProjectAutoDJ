import org.junit.jupiter.api.Test;

import Classes.CollectionPlaylist;
import Classes.MusicLibrary;
import Classes.MusicLibraryHashMap;
import Classes.Playlist;
import Classes.Song;
import Classes.SongDataIO;

import Interfaces.CollectionPlaylistInterface;
import Interfaces.LibraryInterface;
import Interfaces.PlaylistInterface;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicLibraryTest {

    @Test
    public void testGetSongInfo() throws IOException {
        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksShorter.json");
        LibraryInterface<Song> MusicLibrary = new MusicLibrary();
        
        MusicLibrary.importSongs(allSongs);
        

        System.out.println(MusicLibrary.getSongInfo("Death Valley Girls","Death Valley Boogie"));
        assertNotEquals(MusicLibrary.size(), 0);
        
        //Second Implemenation Test
        List<Song> allSongs2 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksShorter.json");
        LibraryInterface<Song> MusicLibrary2 = new MusicLibraryHashMap();
        
        MusicLibrary2.importSongs(allSongs2);
        

        System.out.println(MusicLibrary2.getSongInfo("Death Valley Girls","Death Valley Boogie"));
        assertNotEquals(MusicLibrary2.size(), 0);
    }

    @Test
    public void testImportSongs() {
        PlaylistInterface<Song> playlist = new Playlist("My Playlist");
        Song song = new Song("Artist", "Song Title", 200, 0, 50, 0.8, 0.6, 0.7);

        playlist.add(song);

        MusicLibrary musicLibrary = new MusicLibrary();

        musicLibrary.importSongs(playlist.getSongs());

        assertEquals(musicLibrary.size(), 1);

        assertTrue(musicLibrary.contains(song));

        //Second Implemenation Test
        PlaylistInterface<Song> playlist2 = new Playlist("My Playlist2");

        playlist2.add(song);

        MusicLibrary musicLibrary2 = new MusicLibrary();

        musicLibrary2.importSongs(playlist2.getSongs());

        assertEquals(musicLibrary2.size(), 1);

        assertTrue(musicLibrary2.contains(song));


    }

    @Test
    public void testGetSortedvsGetUnsorted() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksShorter.json");
        LibraryInterface<Song> MusicLibrary = new MusicLibrary();
        
        MusicLibrary.importSongs(allSongs);
        
        System.out.println("Unsorted:  " + MusicLibrary.listAllSongs());
        
        System.out.println("Unsorted:  " + MusicLibrary.listAllSongssorted());

        //Second Implemenation Test

        List<Song> allSongs2 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksShorter.json");
        LibraryInterface<Song> MusicLibrary2 = new MusicLibraryHashMap();
        
        MusicLibrary2.importSongs(allSongs2);
        
        System.out.println("Unsorted:  " + MusicLibrary2.listAllSongs());
        
        System.out.println("Unsorted:  " + MusicLibrary2.listAllSongssorted());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void testRemoveSongsFromLibraryAndPlaylists() throws IOException {

        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter.json");
        

        LibraryInterface<Song> musicLibrary = new MusicLibrary();
        musicLibrary.importSongs(allSongs);

        assertEquals(musicLibrary.size(),3);
        System.out.println(musicLibrary.listAllSongs());

        List<Song> songs = new ArrayList<>();
        Song song1 = allSongs.get(1);
        Song song2 = allSongs.get(2);
        songs.add(song1);
        songs.add(song2);
                
        CollectionPlaylistInterface<Song> collection = new CollectionPlaylist();

        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist = new Playlist("Original");
        playlist.add(allSongs.get(0));
        playlist.add(allSongs.get(1));
        playlist.add(allSongs.get(2));


        assertEquals(playlist.size(), 3);
        
        collection.listplaylists().add(playlist);

        assertEquals(collection.size(), 1);

        List<Song> allSongs2 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter copy.json");
        
        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist2 = new Playlist("Original2", allSongs2);        
        collection.listplaylists().add(playlist2);


        musicLibrary.removeSongsFromLibraryAndPlaylists(songs, collection);
        
        assertEquals(collection.size(), 2);

        assertFalse(((Playlist) collection.listplaylists().get(0)).contains(song1));
        assertFalse(((Playlist) collection.listplaylists().get(0)).contains(song2));
        System.out.println("First Playlist:");
        System.out.println(((Playlist) collection.listplaylists().get(0)).getAllSongs());
        System.out.println("Should have one song above:");


      
        assertFalse(((Playlist) collection.listplaylists().get(1)).contains(song1));
        assertFalse(((Playlist) collection.listplaylists().get(1)).contains(song2));
        System.out.println("Second Playlist:");
        System.out.println(((Playlist) collection.listplaylists().get(1)).getAllSongs());
        System.out.println(((Playlist) collection.listplaylists().get(1)).size());
        System.out.println("Should have one song above:");

        assertFalse(musicLibrary.contains(song2));
        assertFalse(musicLibrary.contains(song1));
        System.out.println(musicLibrary.listAllSongs());

        //Second Implementation Tests

        List<Song> allSongs1 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter.json");
        

        LibraryInterface<Song> musicLibrary1 = new MusicLibrary();
        musicLibrary1.importSongs(allSongs1);

        assertEquals(musicLibrary1.size(),3);
        System.out.println(musicLibrary1.listAllSongs());

        List<Song> songs1 = new ArrayList<>();
        Song song11 = allSongs1.get(1);
        Song song22 = allSongs1.get(2);
        songs1.add(song11);
        songs1.add(song22);
                
        CollectionPlaylistInterface<Song> collection1 = new CollectionPlaylist();

        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist1 = new Playlist("Original");
        playlist1.add(allSongs1.get(0));
        playlist1.add(allSongs1.get(1));
        playlist1.add(allSongs1.get(2));


        assertEquals(playlist1.size(), 3);
        
        collection1.listplaylists().add(playlist);

        assertEquals(collection1.size(), 1);

        List<Song> allSongs22 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter copy.json");
        
        @SuppressWarnings("rawtypes")
        PlaylistInterface playlist22 = new Playlist("Original2", allSongs22);        
        collection1.listplaylists().add(playlist22);


        musicLibrary1.removeSongsFromLibraryAndPlaylists(songs1, collection1);
        
        assertEquals(collection1.size(), 2);

        assertFalse(((Playlist) collection1.listplaylists().get(0)).contains(song1));
        assertFalse(((Playlist) collection1.listplaylists().get(0)).contains(song2));
        System.out.println("First Playlist:");
        System.out.println(((Playlist) collection1.listplaylists().get(0)).getAllSongs());
        System.out.println("Should have one song above:");


      
        assertFalse(((Playlist) collection1.listplaylists().get(1)).contains(song1));
        assertFalse(((Playlist) collection1.listplaylists().get(1)).contains(song2));
        System.out.println("Second Playlist:");
        System.out.println(((Playlist) collection1.listplaylists().get(1)).getAllSongs());
        System.out.println(((Playlist) collection1.listplaylists().get(1)).size());
        System.out.println("Should have one song above:");

        assertFalse(musicLibrary1.contains(song22));
        assertFalse(musicLibrary1.contains(song11));
        System.out.println(musicLibrary1.listAllSongs());

    }

    @Test
    public void testRemoveSongsFromLibrary() throws IOException{
        List<Song> allSongs = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter.json");

        LibraryInterface<Song> musicLibrary = new MusicLibrary();
        musicLibrary.importSongs(allSongs);

        assertEquals(musicLibrary.size(),3);
        System.out.println(musicLibrary.listAllSongs());

        List<Song> songs = new ArrayList<>();
        Song song1 = allSongs.get(1);
        Song song2 = allSongs.get(2);
        songs.add(song1);
        songs.add(song2);

        musicLibrary.removeSongsFromLibrary(songs);

        assertFalse(musicLibrary.contains(song2));
        assertFalse(musicLibrary.contains(song1));
        System.out.println(musicLibrary.listAllSongs());


        //Second implemenation Tests
        List<Song> allSongs2 = SongDataIO.buildSongListFromJsonFile("src/main/java/Localify_97k_TracksEvenShorter.json");

        LibraryInterface<Song> musicLibrary2 = new MusicLibraryHashMap();
        musicLibrary2.importSongs(allSongs2);

        assertEquals(musicLibrary2.size(),3);

        System.out.println(musicLibrary2.listAllSongs());

        List<Song> songs2 = new ArrayList<>();
        Song song11 = allSongs2.get(1);
        Song song22 = allSongs2.get(2);
        songs2.add(song11);
        songs2.add(song22);

        musicLibrary2.removeSongsFromLibrary(songs2);

        System.out.println(musicLibrary2.listAllSongs());


        assertFalse(musicLibrary2.contains(song22));
        assertFalse(musicLibrary2.contains(song11));
        System.out.println(musicLibrary2.listAllSongs());

    }

    
    
}
