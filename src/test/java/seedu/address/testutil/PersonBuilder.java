package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.*;
import seedu.address.model.person.Amount;
import seedu.address.model.tag.Category;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Transaction objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Amount amount;
    private Date date;
    private Address address;
    private Set<Category> categories;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        amount = new Amount(DEFAULT_PHONE);
        date = new Date(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        categories = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code transactionToCopy}.
     */
    public PersonBuilder(Transaction transactionToCopy) {
        name = transactionToCopy.getName();
        amount = transactionToCopy.getPhone();
        date = transactionToCopy.getEmail();
        address = transactionToCopy.getAddress();
        categories = new HashSet<>(transactionToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Transaction} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and set it to the {@code Transaction} that we are building.
     */
    public PersonBuilder withTags(String ... categories) {
        this.categories = SampleDataUtil.getTagSet(categories);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Transaction} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Transaction} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.amount = new Amount(phone);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Transaction} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.date = new Date(email);
        return this;
    }

    public Transaction build() {
        return new Transaction(name, amount, date, address, categories);
    }

}
