package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.ALICE;
import static seedu.address.testutil.TypicalTransactions.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TransactionBuilder;

public class TransactionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Transaction transaction = new TransactionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> transaction.getCategories().remove(0));
    }

    @Test
    public void isSameTransaction() {
        // same object -> returns true
        assertTrue(ALICE.isSameTransaction(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameTransaction(null));

        // different amount and date -> returns false
        Transaction editedAlice = new TransactionBuilder(ALICE).withAmount(VALID_AMOUNT_BOB)
                .withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.isSameTransaction(editedAlice));

        // different name -> returns false
        editedAlice = new TransactionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameTransaction(editedAlice));

        // same name, same amount, different attributes -> returns true
        editedAlice = new TransactionBuilder(ALICE).withDate(VALID_DATE_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameTransaction(editedAlice));

        // same name, same date, different attributes -> returns true
        editedAlice = new TransactionBuilder(ALICE).withAmount(VALID_AMOUNT_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameTransaction(editedAlice));

        // same name, same amount, same date, different attributes -> returns true
        editedAlice = new TransactionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameTransaction(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Transaction aliceCopy = new TransactionBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different transaction -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Transaction editedAlice = new TransactionBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different amounts -> returns false
        editedAlice = new TransactionBuilder(ALICE).withAmount(VALID_AMOUNT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different dates -> returns false
        editedAlice = new TransactionBuilder(ALICE).withDate(VALID_DATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new TransactionBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different categories -> returns false
        editedAlice = new TransactionBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
