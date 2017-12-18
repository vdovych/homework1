package domain;

import json.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    List<Tuple<String,Integer>> exams;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList<>();
        this.exams.addAll(Arrays.asList(exams));
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = super.toJsonObject();
        List<JsonObject> jsonExams = new LinkedList<>();
        for (Tuple<String, Integer> exam:
             exams) {
            jsonExams.add(new JsonObject(new JsonPair("course",new JsonString(exam.key)),
                    new JsonPair("mark",new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(exam.value>2))));
        }
        JsonArray arr = new JsonArray();
        for (JsonObject exam:
             jsonExams) {
            arr.add(exam);
        }
        jsonObject.add(new JsonPair("exams", arr));
        return jsonObject;
    }
}
