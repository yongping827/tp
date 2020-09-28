package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Transaction;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withTransaction("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Transaction} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withTransaction(Transaction transaction) {
        addressBook.addTransaction(transaction);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
