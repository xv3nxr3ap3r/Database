package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EditAnime extends AppCompatActivity {

    private Anime currentAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_anime);
        currentAnime = new Anime();
        initTextChangeEvents();
        initSaveButton();
        setForEditing(false);
        initToggleButton();
        initListButton();

    }
    public void onResume(){
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if(position != -1){
            AnimeDataSource ds = new AnimeDataSource(this);
            try{
                ds.open();
                currentAnime = ds.getAnime(position +1);
                ds.close();

                EditText AnimeEdit = findViewById(R.id.editAnime);
                AnimeEdit.setText(currentAnime.getAnime());
                EditText AuthorEdit = findViewById(R.id.editAuthor);
                AuthorEdit.setText(currentAnime.getAuthor());
                EditText genreEdit = findViewById(R.id.editGenre);
                genreEdit.setText(currentAnime.getAnime());
            }catch(Exception e){
                Toast.makeText(this, "Error accessing anime", Toast.LENGTH_LONG).show();
            }
        }}
    private void initTextChangeEvents() { //need
        //initialize nine listeners
        EditText animeEdit = findViewById(R.id.editAnime);
        animeEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentAnime.setAnime(animeEdit.getText().toString());
                currentAnime.setAnimeID(-1);
            }


        });
        EditText authorEdit = findViewById(R.id.editAuthor);
        authorEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentAnime.setAuthor(authorEdit.getText().toString());
            }
        });
        EditText genreEdit = findViewById(R.id.editGenre);
        genreEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                currentAnime.setGenre(genreEdit.getText().toString());
            }
        });
    }
    private void initToggleButton(){
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setForEditing(toggleButton.isChecked());
            }
        });
    }
    private void initSaveButton(){
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                AnimeDataSource ds = new AnimeDataSource(EditAnime.this);
                try{
                    ds.open();
                    if(currentAnime.getAnimeID() == -1){
                        wasSuccessful = ds.insertAnime(currentAnime);
                        if(wasSuccessful){
                            int newId = ds.getLastAnimeID();
                            currentAnime.setAnimeID(newId);
                        }
                    }else{
                        wasSuccessful = ds.updateAnime(currentAnime);
                    }
                    ds.close();
                }catch(Exception e){
                    wasSuccessful = false;
                }
                if(wasSuccessful){
                    ToggleButton editToggle = findViewById(R.id.toggleButton);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }
    private void initListButton(){
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditAnime.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


    private void setForEditing(boolean enabled){
        EditText editAnime = findViewById(R.id.editAnime);
        EditText editAuthor = findViewById(R.id.editAuthor);
        EditText editGenre = findViewById(R.id.editGenre);
        Button buttonSave = findViewById(R.id.buttonSave);


        editAnime.setEnabled(enabled);
        editAuthor.setEnabled(enabled);
        editGenre.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

    }
}