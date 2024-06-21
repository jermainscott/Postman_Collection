package models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Board {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

//    @JsonProperty("initials")
//    private String initials;


    @JsonProperty("url")
    private String url;

    // Constructors
    public Board() {}

    public Board(String name) {
        this.name = name;
    }

    public Board( String id, String name, String url) {
        this.id = id;
        this.name = name;
//        this.initials =initials;
        this.url = url;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
//                ", initials='" + initials + '\'' +
                ", url='" + url + '\'' +
                '}';
    }




}
