package ay2021s1_cs2103_w16_3.finesse.model.transaction;

import static ay2021s1_cs2103_w16_3.finesse.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import ay2021s1_cs2103_w16_3.finesse.model.transaction.exceptions.TransactionNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of incomes that does not allow nulls.
 * The removal of an income uses Income#equals(Object) so as to ensure that the income with exactly the
 * same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class IncomeList implements Iterable<Income> {

    private final ObservableList<Income> internalList = FXCollections.observableArrayList();
    private final ObservableList<Income> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a income to the list.
     */
    public void add(Income toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the income {@code target} in the list with {@code editedIncome}.
     * {@code target} must exist in the list.
     * The income identity of {@code editedIncome} must not be the same as another existing income in
     * the list.
     */
    public void setIncome(Income target, Income editedIncome) {
        requireAllNonNull(target, editedIncome);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TransactionNotFoundException();
        }

        internalList.set(index, editedIncome);
    }

    /**
     * Removes the equivalent income from the list.
     * The income must exist in the list.
     */
    public void remove(Income toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TransactionNotFoundException();
        }
    }

    public void setIncomes(IncomeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code incomes}.
     */
    public void setIncomes(List<Income> incomes) {
        requireAllNonNull(incomes);

        internalList.setAll(incomes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Income> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Income> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IncomeList // instanceof handles nulls
                && internalList.equals(((IncomeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
