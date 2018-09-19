/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.mohammedomran.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    String type = "";
    // price of one cope
    int pricePerCup = 0;
    int priceCupOfTea = 7;
    int priceCupOfCappuccion = 18;
    int priceCupOfCoffeeBlend = 13;
    int priceCupOfMochaCoffee = 20;
    int priceCupOfFrenchCoffee = 15;
    int priceCupOfIcedCappuccion = 22;
    int priceCupOfIcedChocolata = 24;
    int priceCupOfIcedMocha = 28;
    int priceCupOfIcedVanilla = 30;
    int priceCupOfIcedTea = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText personNameEditText = (EditText) findViewById(R.id.name_editText_view);
        String personName = personNameEditText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_CheckBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.Chocolate_CheckBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        RadioButton teaRadioButton = (RadioButton) findViewById(R.id.tea_RadioButton);
        RadioButton cappuccionRadioButton = (RadioButton) findViewById(R.id.cappuccion_RadioButton);
        RadioButton coffeeBlendRadioButton = (RadioButton) findViewById(R.id.coffeeBlend_RadioButton);
        RadioButton mochaCoffeeRadioButton = (RadioButton) findViewById(R.id.mochaCoffee_RadioButton);
        RadioButton frenchCoffeeRadioButton = (RadioButton) findViewById(R.id.frenchCoffee_RadioButton);
        RadioButton icedCappuccionRadioButton = (RadioButton) findViewById(R.id.icedCappuccion_RadioButton);
        RadioButton icedChocolataRadioButton = (RadioButton) findViewById(R.id.icedChocolata_RadioButton);
        RadioButton icedMochaRadioButton = (RadioButton) findViewById(R.id.icedMocha_RadioButton);
        RadioButton icedVanillaRadioButton = (RadioButton) findViewById(R.id.icedVanilla_RadioButton);
        RadioButton icedTeaRadioButton = (RadioButton) findViewById(R.id.icedTea_RadioButton);

        if (teaRadioButton.isChecked()) {
            pricePerCup = priceCupOfTea;
            type = "Tea";
        } else if (cappuccionRadioButton.isChecked()) {
            pricePerCup = priceCupOfCappuccion;
            type = "Cappuccion";
        } else if (coffeeBlendRadioButton.isChecked()) {
            pricePerCup = priceCupOfCoffeeBlend;
            type = "Coffee Blend";
        } else if (mochaCoffeeRadioButton.isChecked()) {
            pricePerCup = priceCupOfMochaCoffee;
            type = "Mocha Coffee";
        } else if (frenchCoffeeRadioButton.isChecked()) {
            pricePerCup = priceCupOfFrenchCoffee;
            type = "French Coffee";
        } else if (icedCappuccionRadioButton.isChecked()) {
            pricePerCup = priceCupOfIcedCappuccion;
            type = "Iced Cappuccion";
        } else if (icedChocolataRadioButton.isChecked()) {
            pricePerCup = priceCupOfIcedChocolata;
            type = "Iced Chocolata";
        } else if (icedMochaRadioButton.isChecked()) {
            pricePerCup = priceCupOfIcedMocha;
            type = "Iced Mocha";
        } else if (icedVanillaRadioButton.isChecked()) {
            pricePerCup = priceCupOfIcedVanilla;
            type = "Iced Vanilla";
        } else if (icedTeaRadioButton.isChecked()) {
            pricePerCup = priceCupOfIcedTea;
            type = "Iced Tea";
        }

        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(personName, totalPrice, hasWhippedCream, hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + personName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream Whipped Cream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @return total price .
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {


        // add 1$ if user wants whipped cream
        if (addWhippedCream) {
            pricePerCup += 1;
        }

        // add 2$ if user wants chocolata
        if (addChocolate) {
            pricePerCup += 2;
        }

        int totalPrice = quantity * pricePerCup;
        return totalPrice;
    }

    /**
     * Create summary of the order.
     *
     * @param personName      is the name entered by person in editText
     * @param addWhippedCream Whipped Cream is whether or not the user wants whipped cream topping
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @param totalPrice      of the order
     * @return text summary
     */
    private String createOrderSummary(String personName, int totalPrice, boolean addWhippedCream, boolean addChocolate) {

        String orderMessage = "Name: " + personName;
        orderMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        orderMessage += "\nAdd Chocolate? " + addChocolate;
        orderMessage += "\nQuantity: " + quantity + " Of " + type;
        orderMessage += "\nTotal: $" + totalPrice;
        orderMessage += "\nThink you !";
        return orderMessage;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }


    /**
     * This method increment quantity when the + button is clicked
     */
    public void increment(View view) {
        if (quantity == 100) {
            //show an error message as a tost
            Toast.makeText(this, "You cannot have more than 100 coffee", Toast.LENGTH_SHORT).show();
            //Exit this method early becouse there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrement quantity when the - button is clicked
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //show an error message as a tost
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            //Exit this method early becouse there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


}