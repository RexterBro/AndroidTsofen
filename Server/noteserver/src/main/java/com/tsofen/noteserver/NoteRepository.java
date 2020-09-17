package com.tsofen.noteserver;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.CrudRepository;


public interface NoteRepository extends CrudRepository<Note, Integer> {
}
