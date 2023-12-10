package main.java.com.MusicPlayer.model;

public class MusicFile {
    private String filePath;
    private String title;
    private String artist;

    public MusicFile(String filePath, String title, String artist) {
        this.filePath = filePath;
        this.title = title;
        this.artist = artist;
    }

    // Getters
    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    // Setters
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}


