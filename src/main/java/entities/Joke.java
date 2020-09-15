package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Joakim Skaarup Stensnæs
 */
@Entity
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String story, type, author;

    public Joke() {
    }

    public Joke(String story, String type, String author) {
        this.story = story; //The joke itself in a string
        this.type = type; //What type of joke ('Knock knock', 'Mother in law' etc.)
        this.author = author; //The original author of the joke
    }

    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}