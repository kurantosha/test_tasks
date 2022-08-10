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
public class FindPeoples {

    private int count;
    private String next;
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
        private String name;
        private String height;
        private String mass;
        private String hair_color;
        private String skin_color;
        private String eye_color;
        private String birth_year;
        private String gender;
        private String homeworld;
        private ArrayList<String> films;
        private ArrayList<String> species;
        private ArrayList<String> vehicles;
        private ArrayList<String> starships;
        private Date created;
        private Date edited;
        private String url;
    }

    public static List<String> getPeoples(List<FindPeoples.Result> peoples) {
        List<String> peoplesList = new ArrayList<>();
        for (FindPeoples.Result people : peoples) {
            peoplesList.add(people.getName());
        }
        return peoplesList;
    }

    public static List<String> getNameFromCharacters(List<FindPeoples.Result> peoples, final String expected) {
        List<String> names = new ArrayList<>();
        for (FindPeoples.Result people : peoples) {
            if (people.getName().equals(expected)) {
                names.add(people.name);
            }
        }
        return names;
    }
}
