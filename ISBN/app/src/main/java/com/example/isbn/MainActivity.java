package com.example.isbn;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import ISBN_SOAP.FKMISBNServiceSoapBinding;

public class MainActivity extends AppCompatActivity {

    private EditText editTextISBN;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextISBN = findViewById(R.id.ISBN);
        buttonSubmit = findViewById(R.id.btnSubmit);

        // Set input filter to allow only numbers and dashes
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (!Character.isDigit(c) && c != '-') {
                        return "";
                    }
                }
                return null;
            }
        };
        editTextISBN.setFilters(new InputFilter[]{filter});

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isbn = editTextISBN.getText().toString().trim();
                if (!isbn.isEmpty()) {
                    new FetchISBNInfoTask().execute(isbn);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter an ISBN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class FetchISBNInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String isbn = params[0];
            try {
                FKMISBNServiceSoapBinding service = new FKMISBNServiceSoapBinding();
                service.setLoggingEnabled(true); // Enable logging

                // First, check if it's a valid ISBN10
                Boolean isValidISBN10 = service.IsValidISBN10(isbn);
                if (isValidISBN10 != null) {
                    if (isValidISBN10) {
                        return "Valid ISBN10";
                    } else {
                        // If not a valid ISBN10, check for ISBN13
                        Boolean isValidISBN13 = service.IsValidISBN13(isbn);
                        if (isValidISBN13 != null) {
                            return isValidISBN13 ? "Valid ISBN13" : "Invalid ISBN";
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
            return "Error";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            editTextISBN.setText("");  // Clear the input field after submission
        }
    }
}
