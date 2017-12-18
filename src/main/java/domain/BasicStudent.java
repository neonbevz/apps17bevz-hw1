package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/5/2017.
 */
public class BasicStudent implements Jsonable {

    private String name;
    private String surname;
    private int year;

    public BasicStudent() {
    }

    public BasicStudent(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair jsonName = new JsonPair("name", new JsonString(name));
        JsonPair jsonSurname = new JsonPair("surname", new JsonString(surname));
        JsonPair jsonYear = new JsonPair("year", new JsonNumber(year));

        return new JsonObject(jsonName, jsonSurname, jsonYear);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }
}
