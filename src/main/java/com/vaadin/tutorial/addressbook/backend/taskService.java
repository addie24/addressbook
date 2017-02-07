package com.vaadin.tutorial.addressbook.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide.
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class taskService {

    // Create dummy data by randomly combining first and last names
    static String[] fnames = { "Peter", "Alice", "John", "Mike", "Olivia",
            "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa",
            "Linda", "Timothy", "Daniel", "Brian", "George", "Scott",
            "Jennifer" };
    static String[] lnames = { "Smith", "Johnson", "Williams", "Jones",
            "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
            "Thompson", "Young", "King", "Robinson" };
    static String[] subj={"eating", "drinking", "dancing", "singing",
    		"eating", "drinking", "dancing", "singing",
    		"eating", "drinking", "dancing", "singing",
    		"eating", "drinking", "dancing", "singing",
    		"eating", "drinking", "dancing", "singing"};
    

    private static taskService instance;

    public static taskService createDemoService() {
        if (instance == null) {

            final taskService taskService = new taskService();

            Random r = new Random(0);
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 100; i++) {
                Task task = new Task();
                task.setFirstName(fnames[r.nextInt(fnames.length)]);
                task.setLastName(lnames[r.nextInt(fnames.length)]);
                task.setSubject( subj[r.nextInt(fnames.length)]);
               // contact.setPhone("+ 358 555 " + (100 + r.nextInt(900)));
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                task.setStart(cal.getTime());
                task.setEnd(cal.getTime());
                taskService.save(task);
            }
            instance = taskService;
        }

        return instance;
    }

    private HashMap<Long, Task> contacts = new HashMap<>();
    private long nextId = 0;

    public synchronized List<Task> findAll(String stringFilter) {
        ArrayList arrayList = new ArrayList();
        for (Task contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(taskService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Task>() {

            @Override
            public int compare(Task o1, Task o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return contacts.size();
    }

    public synchronized void delete(Task value) {
        contacts.remove(value.getId());
    }

    public synchronized void save(Task entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Task) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        contacts.put(entry.getId(), entry);
    }

}
