import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
//Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        Stream stream1 = persons.stream()
                .filter(x -> x.getAge() < 18);
        System.out.println(stream1.count());

//Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> inTheArmyNow = persons.stream()
                .filter(x -> x.getAge() > 17 && x.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());

//Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
//(т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        Comparator<Person> personComparator = (o1, o2) -> {
            if (o1.getFamily().compareToIgnoreCase(o2.getFamily()) == 0) {
                if (o1.getAge() - o2.getAge() == 0) {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
                return o1.getAge() - o2.getAge();
            }
            return o1.getFamily().compareToIgnoreCase(o2.getFamily());
        };
        List<Person> toWork = persons.stream()
                .filter(x -> x.getAge() > 17 && x.getAge() < 65)
                .filter(x -> !(x.getAge() > 59 && x.getSex() == Sex.FEMALE))
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(personComparator)
                .collect(Collectors.toList());


    }
}