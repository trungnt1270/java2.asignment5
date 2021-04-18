package FileProcessingApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileManagement {
    public FileManagement() {

    }

    public List<Person> getPerson(String path, double money) {
        List<Person> unsortedPersonList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                var getPersonAttributes = line.split(";");
                double salary = Double.parseDouble(getPersonAttributes[2]);
                if (salary < 0 || Double.isNaN(money) || getPersonAttributes[2].equals("")) {
                    salary = 0;
                }
                unsortedPersonList.add(new Person(getPersonAttributes[0], getPersonAttributes[1], salary));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        List<Person> sortingPersonList = new ArrayList<>();
        for (var person : unsortedPersonList) {
            if (person.getMoney() >= money) {
                sortingPersonList.add(person);
            }
        }
        Collections.sort(sortingPersonList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return (int) (p1.getMoney() - p2.getMoney());
            }
        });
        return sortingPersonList;
    }

    public boolean copyWordOneTimes(String source, String destination) {
        try (BufferedReader bf = new BufferedReader(new FileReader(source));
             FileWriter fileWriter = new FileWriter(destination)) {
            String line;
            List<String> getAllWords = new ArrayList<>();
            while ((line = bf.readLine()) != null) {
                var getLineWords = line.split("\s+");
                for (var word : getLineWords) {
                    getAllWords.add(word);
                }
            }
            for (int i = 0; i < getAllWords.size(); i++) {
                for (int j = 0; j < getAllWords.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    if (getAllWords.get(i).equals(getAllWords.get(j))) {
                        getAllWords.remove(getAllWords.get(j));
                    }
                }
            }
            String removedDuplicateText = getAllWords.get(0);
            for (int i = 1; i < getAllWords.size(); i++) {
                removedDuplicateText = removedDuplicateText.concat(" ").concat(getAllWords.get(i));
            }
            fileWriter.write(removedDuplicateText);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
