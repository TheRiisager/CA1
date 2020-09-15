/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Joke;

/**
 *
 * @author Joakim Skaarup Stensnæs
 */
public class JokeDTO {

    private long id;
    private String story, type;

    public JokeDTO() {
    }

    public JokeDTO(Joke joke) {
        this.id = joke.getId();
        this.story = joke.getStory();
        this.type = joke.getType();
    }

    //GETTERS AND SETTERS
    public long getId() {
        return id;
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
