package ay2021s1_cs2103_w16_3.finesse.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyFinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.category.Category;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Date;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Title;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Transaction;

/**
 * Contains utility methods for populating {@code FinanceTracker} with sample data.
 */
public class SampleDataUtil {
    public static Transaction[] getSampleTransactions() {
        return new Transaction[] {
            new Transaction(new Title("Alex Yeoh"), new Amount("87438807"), new Date("01/01/2020"),
                getCategoriesSet("friends")),
            new Transaction(new Title("Bernice Yu"), new Amount("99272758"), new Date("02/01/2020"),
                getCategoriesSet("colleagues", "friends")),
            new Transaction(new Title("Charlotte Oliveiro"), new Amount("93210283"), new Date("03/01/2020"),
                getCategoriesSet("neighbours")),
            new Transaction(new Title("David Li"), new Amount("91031282"), new Date("04/01/2020"),
                getCategoriesSet("family")),
            new Transaction(new Title("Irfan Ibrahim"), new Amount("92492021"), new Date("05/01/2020"),
                getCategoriesSet("classmates")),
            new Transaction(new Title("Roy Balakrishnan"), new Amount("92624417"), new Date("06/01/2020"),
                getCategoriesSet("colleagues"))
        };
    }

    public static ReadOnlyFinanceTracker getSampleFinanceTracker() {
        FinanceTracker sampleAb = new FinanceTracker();
        for (Transaction sampleTransaction : getSampleTransactions()) {
            sampleAb.addTransaction(sampleTransaction);
        }
        return sampleAb;
    }

    /**
     * Returns a category set containing the list of strings given.
     */
    public static Set<Category> getCategoriesSet(String... strings) {
        return Arrays.stream(strings)
                .map(Category::new)
                .collect(Collectors.toSet());
    }

}
