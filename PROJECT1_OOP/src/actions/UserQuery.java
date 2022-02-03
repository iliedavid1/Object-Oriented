package actions;

import database.Repository;
import entities.User;
import fileio.ActionInputData;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class UserQuery extends Query {

    public UserQuery() { }

    public UserQuery(final ActionInputData action) {
        super(action);
    }

    /**
     * Shows a list of users
     * sorted by number of ratings
     */
    public String userQuery() {
        StringBuilder answer = new StringBuilder("Query result: [");
        Map<String, Integer> users = new HashMap<>();
        for (User user : Repository.getInstance().getUserData()) {
            users.put(user.getUsername(), user.getNumberRating());
        }

        users = this.sortByKeyInteger(users);
        LinkedHashMap<String, Integer> sortMap = this.sortMap(users);


        Set<String> setKeys = sortMap.keySet();
        int k = 0;
        for (String key : setKeys) {
            if (k == this.getNumber()) {
                break;
            }
            if (sortMap.get(key) == 0) {
                continue;
            }
            answer.append(key);
            answer.append(", ");
            k++;
        }
        answer = new StringBuilder(this.removeLast2Char(answer.toString()));
        return answer + "]";
    }
}
