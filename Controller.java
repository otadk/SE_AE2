package com.letg;

import com.letg.User;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Controller {
    private User user;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<List<Object>> loadData() {

        List<List<Object>> data = new ArrayList<List<Object>>();

        for (int i = 0; i < 4; ++i) {
            data.add(new ArrayList<Object>());
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data.csv"))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] stringData = line.split(",");
                if (stringData[0].equals("TeachingRequirement")) {
                    data.get(0).add(new TeachingRequirement(stringData[1]));
                } else if (stringData[0].equals("Staff")) {
                    data.get(1).add(new Staff(stringData[1], Arrays.stream(stringData, 2, stringData.length).collect(Collectors.toList())));
                } else if (stringData[0].equals("Training")) {
                    data.get(2).add(new Training(stringData[1], Arrays.stream(stringData, 2, stringData.length).map(arr -> new Staff(arr)).collect(Collectors.toList())));
                } else if (stringData[0].equals("User")) {
                    data.get(3).add(Arrays.stream(stringData, 1, stringData.length).collect(Collectors.toList()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    public void saveData(List<List<Object>> data) {

        List<String[]> stringData = new ArrayList<String[]>();

        for (TeachingRequirement t : dataToTeachingRequirements(data)) {
            stringData.add(
                    Stream.concat(Stream.of("TeachingRequirement"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Staff t : dataToStaffs(data)) {
            stringData.add(
                    Stream.concat(Stream.of("Staff"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Training t : dataToTrainings(data)) {
            stringData.add(
                    Stream.concat(Stream.of("Training"), Arrays.stream(t.toString().split(",")))
                            .toArray(String[]::new)
            );
        }

        for (Object t : dataToUsers(data)) {
            List<String> list = (ArrayList<String>) t;

            stringData.add(
                    Stream.concat(Stream.of("User"), Arrays.stream(list.toArray()))
                            .toArray(String[]::new)
            );
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("data.csv"))) {
            stringData.stream().map(row -> String.join(",", row)).forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TeachingRequirement> dataToTeachingRequirements(List<List<Object>> data) {
        return data.get(0).stream().map(obj -> (TeachingRequirement) obj).collect(Collectors.toList());
    }

    public List<Staff> dataToStaffs(List<List<Object>> data) {
        return data.get(1).stream().map(obj -> (Staff) obj).collect(Collectors.toList());
    }

    public List<Training> dataToTrainings(List<List<Object>> data) {
        return data.get(2).stream().map(obj -> (Training) obj).collect(Collectors.toList());
    }

    public List<Object> dataToUsers(List<List<Object>> data) {
        return  data.get(3);
    }

}
