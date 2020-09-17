package com.tsofen.noteserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdk.jfr.BooleanFlag;

@RestController
@RequestMapping("notes")
public class NotesController {

	@Autowired
	private NoteRepository noteRepo;

	@GetMapping("all")
	public ResponseEntity<?> getAllNotes() {
		List<Note> notes = new ArrayList<>();
		noteRepo.findAll().forEach(notes::add);
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}
	
	@GetMapping("delete/{id}")
	public String removeNote(@PathVariable Integer id) {
		noteRepo.deleteById(id);
		return id.toString();
	}
	
	@PostMapping("add")
	public ResponseEntity<?> addNote(@RequestBody Note noteToAdd) {
		Note addedNote = noteRepo.save(noteToAdd);
		return new ResponseEntity<>(addedNote, HttpStatus.OK);
	}
	
	@GetMapping("read/{id}")
	public ResponseEntity<?> markAsRead(@PathVariable Integer id) {
		Optional<Note> noteOptional = noteRepo.findById(id);
		if(noteOptional.isPresent()) {
			Note note = noteOptional.get();
			note.setHasBeenRead(true);
			noteRepo.save(note);
			return new ResponseEntity<>(note, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
	}
}
