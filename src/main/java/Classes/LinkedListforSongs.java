package Classes;
import java.util.ArrayList;

public class LinkedListforSongs {
    private LinkedNode<Song> head;
    private int size;  
    public LinkedListforSongs() {
        this.head = null;
        this.size = 0; 
    }

    public void add(Song song) {
        if (contains(song)) {
            return; 
        }
    
        LinkedNode<Song> newNode = new LinkedNode<>(song);
        newNode.setNext(head);
        head = newNode;
        size++;
    }
    

    public Song get(int index) {
        if (index < 0 || index >= size) {
            return null;  
        }
        LinkedNode<Song> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getItem();
    }

    public int size() {
        return size;
    }

   public boolean contains(Song song) {
        LinkedNode<Song> current = head;
        while (current != null) {
            Song currentSong = current.getItem();
            if (currentSong.getArtist().equals(song.getArtist()) && currentSong.getTitle().equals(song.getTitle())) {
                return true;
            }
            current = current.getNext();
        }
        return false; 
    }


    

    // Method to remove a song from the list
    public void remove(Song song) {
        if (head == null) {
            return;  // If the list is empty, do nothing
        }
    
        // If the song is at the head
        if (head.getItem().getArtist().equals(song.getArtist()) && head.getItem().getTitle().equals(song.getTitle())) {
            head = head.getNext();
            size--;
            return;
        }
    
        // Search for the song in the list
        LinkedNode<Song> current = head;
        while (current.getNext() != null) {
            // Check if the next node's song has the same artist and title
            if (current.getNext().getItem().getArtist().equals(song.getArtist()) && 
                current.getNext().getItem().getTitle().equals(song.getTitle())) {
                current.setNext(current.getNext().getNext());
                size--;
                return;
            }
            current = current.getNext();
        }
    }
    

    // Method to list all songs in the linked list (unsorted)
    public String listAllSongs() {
        String result = "";
        LinkedNode<Song> current = head;

        // Traverse the linked list and build the result string
        while (current != null) {
            Song song = current.getItem();
            result += "Artist: " + song.getArtist() +
                      ", Title: " + song.getTitle() +
                      ", Duration: " + song.getDuration() + " seconds\n";
            current = current.getNext();
        }
        return result;
    }

    // Method to list all songs in sorted order (only by title)
    public String listAllSongsSorted() {
        // Convert the linked list to an ArrayList for easier sorting
        ArrayList<Song> songList = new ArrayList<>();
        LinkedNode<Song> current = head;

        while (current != null) {
            songList.add(current.getItem());
            current = current.getNext();
        }

        // Sort the list by title
        for (int i = 0; i < songList.size(); i++) {
            for (int j = 0; j < songList.size() - i - 1; j++) {
                Song currentSong = songList.get(j);
                Song nextSong = songList.get(j + 1);

                if (currentSong.getTitle().compareTo(nextSong.getTitle()) > 0) {
                    // Swap songs if out of order by title
                    songList.set(j, nextSong);
                    songList.set(j + 1, currentSong);
                }
            }
        }

        // Build the result string from the sorted song list
        String result = "";
        for (Song song : songList) {
            result += "Artist: " + song.getArtist() +
                      ", Title: " + song.getTitle() +
                      ", Duration: " + song.getDuration() + " seconds\n";
        }

        return result;
    }
}
