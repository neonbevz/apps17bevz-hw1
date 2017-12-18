package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private String name;
    private String surname;
    private int year;
    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname, int year, Tuple<String,
                   Integer>... exams) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.exams = exams;
    }

    private JsonObject exam(String course, int mark) {
        boolean passed = false;
        if (mark > 2) {
            passed = true;
        }
        JsonPair coursePair = new JsonPair("course", new JsonString(course));
        JsonPair markPair = new JsonPair("mark", new JsonNumber(mark));
        JsonPair passedPair = new JsonPair("passed", new JsonBoolean(passed));
        return new JsonObject(coursePair, markPair, passedPair);
    }

    public JsonObject toJsonObject() {
        JsonPair namePair = new JsonPair("name", new JsonString(name));
        JsonPair surnamePair = new JsonPair("surname",
                                            new JsonString(surname));
        JsonPair yearPair = new JsonPair("year", new JsonNumber(year));

        ArrayList<Json> jsonExamsArrayList = new ArrayList<>();
        for (Tuple<String, Integer> tuple : exams) {
            jsonExamsArrayList.add(exam(tuple.key, tuple.value));
        }

        JsonObject[] arrayExams =
                jsonExamsArrayList.toArray(new JsonObject[jsonExamsArrayList.size()]);

        JsonArray jsonExams = new JsonArray(arrayExams);

        return new JsonObject(namePair, surnamePair, yearPair,
                              new JsonPair("exams", jsonExams));
    }
}
