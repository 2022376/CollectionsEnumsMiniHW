/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author georgedias
 */
public class CollectionsEnumsMiniHW {

    public static void main(String[] args) {
        List<Person> people = readDataFromFile("MOCK_DATA");
        List<Team> teams = createTeams(people, 20, 5);
        printTeams(teams);
    }

    public static List<Person> readDataFromFile(String fileName) {
        List<Person> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String firstName = parts[1];
                String lastName = parts[2];
                String email = parts[3];
                Person person = new Person(id, firstName, lastName, email);
                people.add(person);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return people;
    }

    public static List<Team> createTeams(List<Person> people, int teamSize, int maxTeams) {
        List<Team> teams = new ArrayList<>();
        Collections.shuffle(people);

        int numPeople = Math.min(people.size(), teamSize * maxTeams);
        for (int i = 0; i < numPeople; i++) {
            int teamIndex = i % maxTeams;
            if (teams.size() <= teamIndex) {
                teams.add(new Team("Team " + (teamIndex + 1)));
            }
            teams.get(teamIndex).addMember(people.get(i));
        }

        return teams;
    }

    public static void printTeams(List<Team> teams) {
        for (Team team : teams) {
            System.out.println(team.name + ":");
            for (Person person : team.members) {
                System.out.println("  " + person.firstName + " " + person.lastName);
            }
        }
    }
}

class Person {
    public final int id;
    public final String firstName;
    public final String lastName;
    public final String email;

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}

class Team {
    public final String name;
    public final List<Person> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Person person) {
        members.add(person);
    }
}