package com.example.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.song.model.Song;
import com.example.song.repository.*;

@Service
public class SongJpaService implements SongRepository {
	@Autowired
	private SongJpaRepository songJpaRepository;

	@Override
	public ArrayList<Song> getSongs() {
		List<Song> songList = songJpaRepository.findAll();
		ArrayList<Song> songs = new ArrayList<>(songList);
		return songs;
	}

	@Override
	public Song getSong(int songId) {
		try {
			Song songs = songJpaRepository.findById(songId).get();
			return songs;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public Song addSong(Song song) {
		songJpaRepository.save(song);
		return song;
	}

	@Override
	public Song updateSong(int songId, Song song) {
		try {
			Song exitingSong = songJpaRepository.findById(songId).get();
			if (song.getSongName() != null) {
				exitingSong.setSongName(song.getSongName());
			}
			if (song.getLyricist() != null) {
				exitingSong.setLyricist(song.getLyricist());
			}
			if (song.getSinger() != null) {
				exitingSong.setSinger(song.getSinger());
			}
			if (song.getMusicDirector() != null) {
				exitingSong.setMusicDirector(song.getMusicDirector());
			}
			songJpaRepository.save(exitingSong);
			return exitingSong;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deleteSong(int songId) {
		try {
			songJpaRepository.deleteById(songId);
		} 
		catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}
