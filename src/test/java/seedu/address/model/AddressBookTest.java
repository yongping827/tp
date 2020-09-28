package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.ALICE;
import static seedu.address.testutil.TypicalTransactions.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Transaction;
import seedu.address.model.person.exceptions.DuplicateTransactionException;
import seedu.address.testutil.TransactionBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getTransactionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateTransactions_throwsDuplicateTransactionException() {
        // Two transactions with the same identity fields
        Transaction editedAlice = new TransactionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        List<Transaction> newTransactions = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newTransactions);

        assertThrows(DuplicateTransactionException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasTransaction_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTransaction(null));
    }

    @Test
    public void hasTransacion_transactionNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTransaction(ALICE));
    }

    @Test
    public void hasTransaction_transactionInAddressBook_returnsTrue() {
        addressBook.addTransaction(ALICE);
        assertTrue(addressBook.hasTransaction(ALICE));
    }

    @Test
    public void hasTransaction_transactionWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addTransaction(ALICE);
        Transaction editedAlice = new TransactionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(addressBook.hasTransaction(editedAlice));
    }

    @Test
    public void getTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTransactionList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose transactions list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        AddressBookStub(Collection<Transaction> transactions) {
            this.transactions.setAll(transactions);
        }

        @Override
        public ObservableList<Transaction> getTransactionList() {
            return transactions;
        }
    }

}
