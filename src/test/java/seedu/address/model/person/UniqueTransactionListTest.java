package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.ALICE;
import static seedu.address.testutil.TypicalTransactions.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateTransactionException;
import seedu.address.model.person.exceptions.TransactionNotFoundException;
import seedu.address.testutil.TransactionBuilder;

public class UniqueTransactionListTest {

    private final UniqueTransactionList uniqueTransactionList = new UniqueTransactionList();

    @Test
    public void contains_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.contains(null));
    }

    @Test
    public void contains_transactionNotInList_returnsFalse() {
        assertFalse(uniqueTransactionList.contains(ALICE));
    }

    @Test
    public void contains_transactionInList_returnsTrue() {
        uniqueTransactionList.add(ALICE);
        assertTrue(uniqueTransactionList.contains(ALICE));
    }

    @Test
    public void contains_transactionWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTransactionList.add(ALICE);
        Transaction editedAlice = new TransactionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(uniqueTransactionList.contains(editedAlice));
    }

    @Test
    public void add_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.add(null));
    }

    @Test
    public void add_duplicateTransaction_throwsDuplicateTransactionException() {
        uniqueTransactionList.add(ALICE);
        assertThrows(DuplicateTransactionException.class, () -> uniqueTransactionList.add(ALICE));
    }

    @Test
    public void setTransaction_nullTargetTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.setTransaction(null, ALICE));
    }

    @Test
    public void setTransaction_nullEditedTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.setTransaction(ALICE, null));
    }

    @Test
    public void setTransaction_targetTransactionNotInList_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> uniqueTransactionList.setTransaction(ALICE, ALICE));
    }

    @Test
    public void setTransaction_editedTransactionIsSameTransaction_success() {
        uniqueTransactionList.add(ALICE);
        uniqueTransactionList.setTransaction(ALICE, ALICE);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        expectedUniqueTransactionList.add(ALICE);
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransaction_editedTransactionHasSameIdentity_success() {
        uniqueTransactionList.add(ALICE);
        Transaction editedAlice = new TransactionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        uniqueTransactionList.setTransaction(ALICE, editedAlice);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        expectedUniqueTransactionList.add(editedAlice);
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransaction_editedTransactionHasDifferentIdentity_success() {
        uniqueTransactionList.add(ALICE);
        uniqueTransactionList.setTransaction(ALICE, BOB);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        expectedUniqueTransactionList.add(BOB);
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransaction_editedTransactionHasNonUniqueIdentity_throwsDuplicateTransactionException() {
        uniqueTransactionList.add(ALICE);
        uniqueTransactionList.add(BOB);
        assertThrows(DuplicateTransactionException.class, () -> uniqueTransactionList.setTransaction(ALICE, BOB));
    }

    @Test
    public void remove_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.remove(null));
    }

    @Test
    public void remove_transactionDoesNotExist_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> uniqueTransactionList.remove(ALICE));
    }

    @Test
    public void remove_existingTransaction_removesTransaction() {
        uniqueTransactionList.add(ALICE);
        uniqueTransactionList.remove(ALICE);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransactions_nullUniqueTransactionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> uniqueTransactionList.setTransactions((UniqueTransactionList) null));
    }

    @Test
    public void setTransactions_uniqueTransactionList_replacesOwnListWithProvidedUniqueTransactionList() {
        uniqueTransactionList.add(ALICE);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        expectedUniqueTransactionList.add(BOB);
        uniqueTransactionList.setTransactions(expectedUniqueTransactionList);
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransactions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTransactionList.setTransactions((List<Transaction>) null));
    }

    @Test
    public void setTransactions_list_replacesOwnListWithProvidedList() {
        uniqueTransactionList.add(ALICE);
        List<Transaction> transactionList = Collections.singletonList(BOB);
        uniqueTransactionList.setTransactions(transactionList);
        UniqueTransactionList expectedUniqueTransactionList = new UniqueTransactionList();
        expectedUniqueTransactionList.add(BOB);
        assertEquals(expectedUniqueTransactionList, uniqueTransactionList);
    }

    @Test
    public void setTransacions_listWithDuplicateTransactions_throwsDuplicateTransactionException() {
        List<Transaction> listWithDuplicateTransactions = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTransactionException.class, ()
            -> uniqueTransactionList.setTransactions(listWithDuplicateTransactions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTransactionList.asUnmodifiableObservableList().remove(0));
    }
}
