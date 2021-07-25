package collection.question;

import java.util.*;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {

        if (previous == null || current == null) {
            return new Info(-1, -1, -1);
        }
        int add = 0, del = 0, change = 0;
        Info info = new Info(add, change, del);
        HashMap<Integer, User> previousMap = new HashMap<>();
        previous.forEach(user -> previousMap.put(user.getId(), user));
        for (User user : current) {
            if (!previousMap.containsKey(user.getId())) {
                add++;
                info.setAdded(add);
            }
            if (previousMap.containsKey(user.getId())
                    && !user.equals(previousMap.get(user.getId()))) {
                change++;
                info.setChanged(change);
            }
        }
        del =  add + previousMap.size() - current.size();
        info.setDeleted(del);
        return info;
    }
}

