package api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
@ToString
@Data
public class FindFilms {
    private int count;
    private Object next;
    private Object previous;
    private List<Result> results;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(NON_NULL)
    @ToString
    @Data
    public static class Result {
        private String title;
        private int episode_id;
        private String opening_crawl;
        private String director;
        private String producer;
        private String release_date;
        private ArrayList<String> characters;
        private ArrayList<String> planets;
        private ArrayList<String> starships;
        private ArrayList<String> vehicles;
        private ArrayList<String> species;
        private Date created;
        private Date edited;
        private String url;
    }

    public static List<String> getTitleFilms(List<FindFilms.Result> films) {
        List<String> titleFilms = new ArrayList<>();
        for (FindFilms.Result film : films) {
            titleFilms.add(film.getTitle());
        }
        return titleFilms;
    }

    public static List<String> getCharactersFromFilm(List<FindFilms.Result> films, final String expectedFilm) {
        List<String> characters = new ArrayList<>();
        for (FindFilms.Result film : films) {
            if (film.getTitle().equals(expectedFilm))
                characters.addAll(film.characters);
        }
        return characters;
    }
}
