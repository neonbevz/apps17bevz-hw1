package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    protected String sName;
    protected String sSurname;
    protected Integer sYear;
    protected Tuple<String, Integer>[] sExams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        sName = name;
        sSurname = surname;
        sYear = year;
        sExams = exams;
    }

    private JsonObject exam(String course, Integer mark) {
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
        JsonPair namePair = new JsonPair("name", new JsonString(sName));
        JsonPair surnamePair = new JsonPair("surname", new JsonString(sSurname));
        JsonPair nameYear = new JsonPair("year", new JsonNumber(sYear));

        ArrayList<Json> jsonExamsArrayList = new ArrayList<>();
        for (Tuple<String, Integer> tuple : sExams) {
            jsonExamsArrayList.add(exam(tuple.key, tuple.value));
        }
        ;
        JsonArray jsonExams = new JsonArray();

        JsonObject jsonObject = new JsonObject();
    }
}
