package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Transaction;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {

    public static final Transaction ALICE = new TransactionBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withDate("alice@example.com")
            .withAmount("94351253").withCategories("friends").build();
    public static final Transaction BENSON = new TransactionBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25").withDate("johnd@example.com").withAmount("98765432")
            .withCategories("owesMoney", "friends").build();
    public static final Transaction CARL = new TransactionBuilder().withName("Carl Kurz").withAmount("95352563")
            .withDate("heinz@example.com").withAddress("wall street").build();
    public static final Transaction DANIEL = new TransactionBuilder().withName("Daniel Meier").withAmount("87652533")
            .withDate("cornelia@example.com").withAddress("10th street").withCategories("friends").build();
    public static final Transaction ELLE = new TransactionBuilder().withName("Elle Meyer").withAmount("9482224")
            .withDate("werner@example.com").withAddress("michegan ave").build();
    public static final Transaction FIONA = new TransactionBuilder().withName("Fiona Kunz").withAmount("9482427")
            .withDate("lydia@example.com").withAddress("little tokyo").build();
    public static final Transaction GEORGE = new TransactionBuilder().withName("George Best").withAmount("9482442")
            .withDate("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Transaction HOON = new TransactionBuilder().withName("Hoon Meier").withAmount("8482424")
            .withDate("stefan@example.com").withAddress("little india").build();
    public static final Transaction IDA = new TransactionBuilder().withName("Ida Mueller").withAmount("8482131")
            .withDate("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Transaction's details found in {@code CommandTestUtil}
    public static final Transaction AMY = new TransactionBuilder().withName(VALID_NAME_AMY).withAmount(VALID_AMOUNT_AMY)
            .withDate(VALID_DATE_AMY).withAddress(VALID_ADDRESS_AMY).withCategories(VALID_CATEGORY_FRIEND).build();
    public static final Transaction BOB = new TransactionBuilder().withName(VALID_NAME_BOB).withAmount(VALID_AMOUNT_BOB)
            .withDate(VALID_DATE_BOB).withAddress(VALID_ADDRESS_BOB).withCategories(VALID_CATEGORY_HUSBAND,
                    VALID_CATEGORY_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTransactions() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical transactions.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Transaction transaction : getTypicalTransactions()) {
            ab.addTransaction(transaction);
        }
        return ab;
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
