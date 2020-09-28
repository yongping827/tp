package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Amount;
import seedu.address.model.person.Date;
import seedu.address.model.person.Name;
import seedu.address.model.person.Transaction;
import seedu.address.model.tag.Category;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Transaction[] getSampleTransactions() {
        return new Transaction[] {
            new Transaction(new Name("Alex Yeoh"), new Amount("87438807"), new Date("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getCategoriesSet("friends")),
            new Transaction(new Name("Bernice Yu"), new Amount("99272758"), new Date("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getCategoriesSet("colleagues", "friends")),
            new Transaction(new Name("Charlotte Oliveiro"), new Amount("93210283"), new Date("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getCategoriesSet("neighbours")),
            new Transaction(new Name("David Li"), new Amount("91031282"), new Date("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getCategoriesSet("family")),
            new Transaction(new Name("Irfan Ibrahim"), new Amount("92492021"), new Date("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getCategoriesSet("classmates")),
            new Transaction(new Name("Roy Balakrishnan"), new Amount("92624417"), new Date("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getCategoriesSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
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
