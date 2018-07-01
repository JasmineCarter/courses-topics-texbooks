package courses;

import org.springframework.data.repository.CrudRepository;

import course.Textbook;

public interface TextbookRepository extends CrudRepository<Textbook, Long> {

}
