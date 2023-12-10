package main.java.com.MusicPlayer.player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import main.java.com.MusicPlayer.model.MusicFile;
import java.io.File;

public class PlaylistManager extends JPanel {
    private JList<MusicFile> playlist;
    private DefaultListModel<MusicFile> playlistModel;
    private AudioPlayer audioPlayer;
    private JButton addButton;
    private JButton removeButton;

    public PlaylistManager(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
        setLayout(new BorderLayout());

        playlistModel = new DefaultListModel<>();
        playlist = new JList<>(playlistModel);
        playlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addButton = new JButton("Add");
        removeButton = new JButton("Remove");

        addButton.addActionListener(e -> addSongFromFileChooser());
        removeButton.addActionListener(e -> removeSong());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(new JScrollPane(playlist), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addSong(MusicFile song) {
        playlistModel.addElement(song);
    }

    public void removeSong() {
        int selectedIndex = playlist.getSelectedIndex();
        if (selectedIndex != -1) {
            playlistModel.remove(selectedIndex);
        }
    }

    public MusicFile getSelectedSong() {
        return playlist.getSelectedValue();
    }

    public List<MusicFile> getPlaylist() {
        List<MusicFile> songs = new ArrayList<>();
        for (int i = 0; i < playlistModel.size(); i++) {
            songs.add(playlistModel.getElementAt(i));
        }
        return songs;
    }

    private void addSongFromFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            MusicFile newSong = new MusicFile(selectedFile.getAbsolutePath(), selectedFile.getName(), "Unknown Artist");
            addSong(newSong);
            audioPlayer.loadMusic(selectedFile.getAbsolutePath()); // Carga la canción en AudioPlayer
        }
    }
}
