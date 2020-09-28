package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Category;

/**
 * Represents a Transaction in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {

    // Identity fields
    private final Name name;
    private final Amount amount;
    private final Date date;

    // Data fields
    private final Address address;
    private final Set<Category> categories = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Transaction(Name name, Amount amount, Date date, Address address, Set<Category> categories) {
        requireAllNonNull(name, amount, date, address, categories);
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.address = address;
        this.categories.addAll(categories);
    }

    public Name getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable category set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    /**
     * Returns true if both transactions of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two transactions.
     */
    public boolean isSameTransaction(Transaction otherTransaction) {
        if (otherTransaction == this) {
            return true;
        }

        return otherTransaction != null
                && otherTransaction.getName().equals(getName())
                && (otherTransaction.getAmount().equals(getAmount()) || otherTransaction.getDate().equals(getDate()));
    }

    /**
     * Returns true if both transactions have the same identity and data fields.
     * This defines a stronger notion of equality between two transactions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return otherTransaction.getName().equals(getName())
                && otherTransaction.getAmount().equals(getAmount())
                && otherTransaction.getDate().equals(getDate())
                && otherTransaction.getAddress().equals(getAddress())
                && otherTransaction.getCategories().equals(getCategories());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, amount, date, address, categories);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate())
                .append(" Address: ")
                .append(getAddress())
                .append(" Categories: ");
        getCategories().forEach(builder::append);
        return builder.toString();
    }

}
