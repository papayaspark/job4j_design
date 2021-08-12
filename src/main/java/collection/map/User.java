package collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    public static void main(String[] args) {
        String name = "Mike";
        int children = 2;
        Calendar calendar = new GregorianCalendar(1992, 10, 5);
        User user1 = new User(name, children, calendar);
        User user2 = new User(name, children, calendar);
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));

        HashMap<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Integer.hashCode(children);
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return name.equals(other.name)
                && children == other.children
                && birthday.equals(other.birthday);
    }
}
