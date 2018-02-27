package com.example.darshan.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void composeEmail(String addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameOfUser = (EditText) findViewById(R.id.name_of_user);
        String name = nameOfUser.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhppedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        display(quantity);
        String priceMessage = name + "\nPrice: ₹" +calculatePrice(quantity, hasWhppedCream, hasChocolate) + "\nChocolate Topping" + hasChocolate + "\nWhipped Cream " + hasWhppedCream + "\n for " + quantity + " cups of coffee. \nThank You!";

       // displayMessage(priceMessage);
        String Sendersemail = "darshanv998@gmail.com";
        String subject = "Coffee order for " + name;
        composeEmail(Sendersemail, subject, priceMessage);

    }
    //Increase the value of number of coffee cups

    public void increment(View view) {

        if(quantity==100)
        {
            Context context = getApplicationContext();
            CharSequence text = "You can order a maximum of 100 coffee cups!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        else
        quantity = quantity + 1;
        display(quantity);
    }

    //Decrease the value of number of coffee cups

    public void decrement(View view) {
        if (quantity == 1)
        {
            Context context = getApplicationContext();
            CharSequence text = "You have to order a minimum of one cup.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        quantity -= 1;
        display(quantity);
    }

    public int calculatePrice(int number, boolean hasWhippedCream, boolean hasChocolate) {

        int price = 0 , basePrice = 5;
        if(hasChocolate == true)
            price+=2;
        if(hasWhippedCream == true)
            price+=1;
        price+=basePrice;
        return number*price;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */

    /*
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
